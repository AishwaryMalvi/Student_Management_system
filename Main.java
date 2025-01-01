package WithJDBC;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add a New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student Details");
            System.out.println("6. Delete a Student by Roll Number");
            System.out.println("7. Count Total Students");
            System.out.println("8. List Students by Class");
            System.out.println("9. Sort Students by Name");
            System.out.println("10. Exit");
            System.out.print("Choose an option (1-10): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a New Student
                    Student newStudent = new Student();
                    System.out.print("Enter Roll Number: ");
                    newStudent.setRollNumber(scanner.nextLine());
                    System.out.print("Enter Name: ");
                    newStudent.setName(scanner.nextLine());
                    System.out.print("Enter Age: ");
                    newStudent.setAge(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Class: ");
                    newStudent.setStudentClass(scanner.nextLine());
                    System.out.print("Enter Address: ");
                    newStudent.setAddress(scanner.nextLine());

                    if (dao.addStudent(newStudent)) {
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Failed to add student.");
                    }
                    break;

                case 2:
                    // View All Students
                    List<Student> students = dao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("\nList of Students:");
                        for (Student student : students) {
                            System.out.println(student.getRollNumber() + " - " + student.getName() + " - " +
                                    student.getAge() + " - " + student.getStudentClass() + " - " + student.getAddress());
                        }
                    }
                    break;

                case 3:
                    // Search Student by Roll Number
                    System.out.print("Enter Roll Number: ");
                    String rollNumber = scanner.nextLine();
                    Student foundStudent = dao.getStudentByRollNumber(rollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student Found: " + foundStudent.getName() + " - " +
                                foundStudent.getAge() + " - " + foundStudent.getStudentClass() + " - " + foundStudent.getAddress());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    // Search Student by Name
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    List<Student> matchingStudents = dao.getAllStudents();
                    boolean found = false;
                    for (Student student : matchingStudents) {
                        if (student.getName().equalsIgnoreCase(name)) {
                            System.out.println(student.getRollNumber() + " - " + student.getName() + " - " +
                                    student.getAge() + " - " + student.getStudentClass() + " - " + student.getAddress());
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No student found with name: " + name);
                    }
                    break;

                case 5:
                    // Update Student Details
                    System.out.print("Enter Roll Number of Student to Update: ");
                    String rollToUpdate = scanner.nextLine();
                    Student studentToUpdate = dao.getStudentByRollNumber(rollToUpdate);
                    if (studentToUpdate != null) {
                        System.out.print("Enter New Name: ");
                        studentToUpdate.setName(scanner.nextLine());
                        System.out.print("Enter New Age: ");
                        studentToUpdate.setAge(scanner.nextInt());
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Class: ");
                        studentToUpdate.setStudentClass(scanner.nextLine());
                        System.out.print("Enter New Address: ");
                        studentToUpdate.setAddress(scanner.nextLine());

                        if (dao.updateStudent(studentToUpdate)) {
                            System.out.println("Student details updated successfully!");
                        } else {
                            System.out.println("Failed to update student details.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    // Delete a Student by Roll Number
                    System.out.print("Enter Roll Number of Student to Delete: ");
                    String rollToDelete = scanner.nextLine();
                    if (dao.deleteStudent(rollToDelete)) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Failed to delete student.");
                    }
                    break;

                case 7:
                    // Count Total Students
                    int count = dao.countStudents();
                    System.out.println("Total Students: " + count);
                    break;

                case 8:
                    // List Students by Class
                    System.out.print("Enter Class: ");
                    String className = scanner.nextLine();
                    List<Student> classStudents = dao.getAllStudents();
                    boolean classFound = false;
                    for (Student student : classStudents) {
                        if (student.getStudentClass().equalsIgnoreCase(className)) {
                            System.out.println(student.getRollNumber() + " - " + student.getName() + " - " +
                                    student.getAge() + " - " + student.getStudentClass() + " - " + student.getAddress());
                            classFound = true;
                        }
                    }
                    if (!classFound) {
                        System.out.println("No students found in class: " + className);
                    }
                    break;

                case 9:
                    // Sort Students by Name
                    List<Student> sortedStudents = dao.getAllStudents();
                    sortedStudents.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
                    System.out.println("\nStudents Sorted by Name:");
                    for (Student student : sortedStudents) {
                        System.out.println(student.getRollNumber() + " - " + student.getName() + " - " +
                                student.getAge() + " - " + student.getStudentClass() + " - " + student.getAddress());
                    }
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
