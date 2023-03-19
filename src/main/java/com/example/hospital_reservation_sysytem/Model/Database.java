package com.example.hospital_reservation_sysytem.Model;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    protected Connection conn;
    protected Statement statement;


    public Database() throws SQLException {
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalsystem","root","");
        statement=conn.createStatement();

    }

    public ArrayList<Doctor> getAllDoctors(String query) throws SQLException {
        ArrayList<Doctor>doctors=new ArrayList<>();
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Doctor doctor=new Doctor(rs.getString("Name"),rs.getString("Password"),rs.getString("email"),rs.getString("department"),rs.getFloat("salary"),rs.getInt("Id"));
            doctors.add(doctor);
            doctor.PrintDoctor();
        }
    return doctors;
    }

    public ArrayList<Patient> getAllPatients(String query) throws SQLException {
        ArrayList<Patient>patients=new ArrayList<>();
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Patient patient=new Patient(rs.getString("Name"),rs.getString("Password"),rs.getString("email"),rs.getInt("Age"),rs.getBoolean("gender"),rs.getString("Phonenumber"),rs.getString("Address"),rs.getInt("ID"));
            patients.add(patient);
            patient.PrintPatient();
        }
        return patients;
    }

    public ArrayList<Appointment> getAllAppointments(String query) throws SQLException {
        ArrayList<Appointment>appointments=new ArrayList<>();
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Appointment appointment=new Appointment(rs.getInt("PatientId"),rs.getInt("DoctorId"),rs.getDate("Date"),rs.getTime("Time"));
            appointments.add(appointment);
            appointment.printAppointment();
        }
        return appointments;
    }

    public ArrayList<Date> getAllAppointmentDates() throws SQLException {
        ArrayList<Date> appointmentDates = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT Date FROM appointment");
        while(rs.next()){
            Date date = rs.getDate("Date");
            appointmentDates.add(date);

        }
        return appointmentDates;
    }
    public ArrayList<Doctor> getDoctorsByDepartment(String department) throws SQLException {
        ArrayList<Doctor> DepartmentDoctors = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM doctor WHERE department ='"+department+"'");
        while(rs.next()){
            Doctor doctor=new Doctor(rs.getString("Name"),rs.getString("Password"),rs.getString("email"),rs.getString("department"),rs.getFloat("salary"),rs.getInt("Id"));
            DepartmentDoctors.add(doctor);
            doctor.PrintDoctor();
        }
        return DepartmentDoctors;
    }

    public void deleteAppointment(String query) throws SQLException {
        statement.executeUpdate(query);

    }

    public void insertDoctor(Doctor doctor) throws SQLException {
        String SQL = "INSERT INTO doctor(Name,Password,Id,email,department,salary) "
                + "VALUES(?,?,?,?,?,?)";
        //long id = 0;
        PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, doctor.getName());
        pstmt.setString(2, doctor.getPassword());
        pstmt.setInt(3, doctor.getId());
        pstmt.setString(4, doctor.getEmail());
        pstmt.setString(5, doctor.getDepartment());
        pstmt.setFloat(6, doctor.getSalary());

        int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    System.out.println("Insertion in Doctor successful");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }


    }

    public void insertPatient(Patient patient) throws SQLException {
        String SQL = "INSERT INTO patient(Name,Password,ID,email,Age,gender,Phonenumber,Address) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        //long id = 0;
        PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, patient.getName());
        pstmt.setString(2, patient.getPassword());
        pstmt.setInt(3, patient.getId());
        pstmt.setString(4, patient.getEmail());
        pstmt.setInt(5, patient.getAge());
        pstmt.setBoolean(6, patient.isMale());
        pstmt.setString(7, patient.getPhoneNumber());
        pstmt.setString(8, patient.getAddress());

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                System.out.println("Insertion in patient successful");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }

    public void insertAppointment(Appointment appointment) throws SQLException {
        String SQL = "INSERT INTO appointment(PatientId,DoctorId,Date,Time) "
                + "VALUES(?,?,?,?)";
        //long id = 0;
        PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, appointment.getPatientID());
        pstmt.setInt(2, appointment.getDoctorId());
        pstmt.setDate(3, (Date) appointment.getDate());
        pstmt.setTime(4, appointment.getTime());


        int affectedRows = pstmt.executeUpdate();
        // check the affected rows
        if (affectedRows > 0) {
            // get the ID back
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                System.out.println("Insertion in appointment successful");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }

    public ArrayList<DoctorAppointments> getAllDoctorAppointments(int doctorID) throws SQLException {
        ArrayList<DoctorAppointments>doctorAppointments=new ArrayList<>();
        String query="SELECT\n" +
                "  appointment.Date,\n" +
                "  appointment.Time,\n" +
                "  patient.Name,\n" +
                "  patient.gender,\n" +
                "  patient.Phonenumber,\n" +
                "  patient.Address\n" +
                "FROM appointment\n" +
                "JOIN patient\n" +
                "  ON appointment.PatientId = patient.ID\n" +
                "JOIN doctor\n" +
                "  ON appointment.DoctorId = doctor.Id\n" +
                "  WHERE doctor.Id="+doctorID+ " AND appointment.DoctorId=doctor.Id;";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            DoctorAppointments doctorAppointment=new DoctorAppointments(rs.getDate("Date"),rs.getTime("Time"),rs.getString("Name"),rs.getBoolean("gender"),rs.getString("Phonenumber"),rs.getString("Address"));
            doctorAppointments.add(doctorAppointment);
            System.out.println(doctorAppointment);
        }
        return doctorAppointments;
    }
    public ArrayList<PatientAppointments> getAllPatientAppointments(int patientID) throws SQLException {
        ArrayList<PatientAppointments>patientAppointments=new ArrayList<>();
        String query="SELECT\n" +
                "  appointment.Date,\n" +
                "  appointment.Time,\n" +
                "  doctor.department,\n" +
                "  doctor.Name\n" +
                "FROM appointment\n" +
                "JOIN patient\n" +
                "  ON appointment.PatientId = patient.ID\n" +
                "JOIN doctor\n" +
                "  ON appointment.DoctorId = doctor.Id\n" +
                "  WHERE patient.ID="+patientID+ " AND appointment.PatientId=patient.ID;";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            PatientAppointments patientAppointment=new PatientAppointments(rs.getDate("Date"),rs.getTime("Time"),rs.getString("department"),rs.getString("Name"));
            patientAppointments.add(patientAppointment);
            System.out.println(patientAppointment);
        }
        return patientAppointments;
    }

    public ArrayList<DoctorEarnings> getAllDoctorEarnings(int doctorId) throws SQLException {
        ArrayList<DoctorEarnings>doctorEarnings=new ArrayList<>();
        String query="SELECT COUNT(appointment.DoctorId) , appointment.Date\n" +
                "FROM appointment\n" +
                "WHERE appointment.DoctorId="+doctorId+" \n" +
                "GROUP BY appointment.Date;";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            float salary=rs.getInt("COUNT(appointment.DoctorId)");
            salary*=Doctor.CURR_DOCTOR_SALARY;
            DoctorEarnings doctoreaning=new DoctorEarnings(rs.getDate("Date"),salary);
            doctorEarnings.add(doctoreaning);
            System.out.println(doctoreaning);
        }
        return doctorEarnings;

    }


}


/*
* CREATE TABLE `hospitalsystem`.`doctor` (`Id` INT(20) NOT NULL , `Name` VARCHAR(256) NOT NULL , `Password` VARCHAR(256) NOT NULL , `email` VARCHAR(256) NOT NULL , `department` VARCHAR(256) NOT NULL , `salary` VARCHAR(256) NOT NULL ) ENGINE = InnoDB;
CREATE TABLE `hospitalsystem`.`patient` (`Name` VARCHAR(256) NOT NULL , `Password` VARCHAR(256) NOT NULL , `ID` INT(20) NOT NULL , `email` VARCHAR(256) NOT NULL , `Age` INT(20) NOT NULL , `gender` BOOLEAN NOT NULL , `Phonenumber` VARCHAR(256) NOT NULL , `Address` VARCHAR(256) NOT NULL ) ENGINE = InnoDB;
 *CREATE TABLE `hospitalsystem`.`appointment` (`PatientId` INT(20) NOT NULL , `DoctorId` INT(20) NOT NULL , `Date` DATE NOT NULL , `Time` TIME(6) NOT NULL ) ENGINE = InnoDB;
 * INSERT INTO `patient` (`Name`, `Password`, `ID`, `email`, `Age`, `gender`, `Phonenumber`, `Address`) VALUES ('Mark', '123456', '1000', 'Mark@gmail.com', '25', '1', '+204454541', 'address');
 * INSERT INTO `doctor` (`Id`, `Name`, `Password`, `email`, `department`, `salary`) VALUES ('1050', 'Doctor House', '123456', 'House@gmail.com', 'surgery', '100');
 * INSERT INTO `appointment` (`PatientId`, `DoctorId`, `Date`, `Time`) VALUES ('1000', '1050', '2022-05-18', '22:38:26.000000');
* */