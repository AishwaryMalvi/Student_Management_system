package WithJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO() {
        connection = DBConnection.getConnection();
    }

    // 1. Add Student
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (roll_number, name, age, class, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, student.getRollNumber());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getStudentClass());
            ps.setString(5, student.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. View All Students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setStudentClass(rs.getString("class"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 3. Search Student by Roll Number
    public Student getStudentByRollNumber(String rollNumber) {
        String query = "SELECT * FROM students WHERE roll_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, rollNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setStudentClass(rs.getString("class"));
                student.setAddress(rs.getString("address"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Update Student
    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, age = ?, class = ?, address = ? WHERE roll_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getStudentClass());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getRollNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Delete Student by Roll Number
    public boolean deleteStudent(String rollNumber) {
        String query = "DELETE FROM students WHERE roll_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, rollNumber);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 6. Count Total Students
    public int countStudents() {
        String query = "SELECT COUNT(*) AS total FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Additional features can be added here.
}

