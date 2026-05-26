package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add Student
    public void addStudent(Student student) {

        String query =
                "INSERT INTO students(id,name,age,course,marks) VALUES(?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());
            ps.setDouble(5, student.getMarks());

            ps.executeUpdate();

            System.out.println("Student Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View All Students
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while(rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getDouble("marks"));

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Search Student
    public Student searchStudent(int id) {

        String query = "SELECT * FROM students WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Student
    public void updateStudent(Student student) {

        String query =
                "UPDATE students SET name=?, age=?, course=?, marks=? WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());
            ps.setInt(5, student.getId());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Updated Successfully");
            } else {
                System.out.println("Student Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Student
    public void deleteStudent(int id) {

        String query = "DELETE FROM students WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Student Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
