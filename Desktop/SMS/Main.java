import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        loadFromFile(students);

        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter Student ID: ");
                int id = sc.nextInt();

                System.out.print("Enter Student Name: ");
                String name = sc.next();

                System.out.print("Enter Student Marks: ");
                int marks = sc.nextInt();

                students.add(new Student(id, name, marks));
                saveToFile(students);

                System.out.println("Student added successfully!");

            } else if (choice == 2) {

                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    for (Student s : students) {
                        s.displayData();
                        System.out.println("-------------------");
                    }
                }

            } else if (choice == 3) {

                System.out.print("Enter Student ID to search: ");
                int searchId = sc.nextInt();
                boolean found = false;

                for (Student s : students) {
                    if (s.id == searchId) {
                        s.displayData();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found.");
                }

            } else if (choice == 4) {

                System.out.print("Enter Student ID to delete: ");
                int deleteId = sc.nextInt();
                boolean removed = false;

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).id == deleteId) {
                        students.remove(i);
                        removed = true;
                        saveToFile(students);
                        System.out.println("Student deleted successfully!");
                        break;
                    }
                }

                if (!removed) {
                    System.out.println("Student not found.");
                }

            } else if (choice == 5) {
                System.out.println("Exiting program...");
            } else {
                System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    // -------- FILE METHODS --------

    static void saveToFile(ArrayList<Student> students) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (Student s : students) {
                fw.write(s.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    static void loadFromFile(ArrayList<Student> students) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
    }
}