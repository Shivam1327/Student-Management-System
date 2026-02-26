public class Student {

    int id;
    String name;
    int marks;

    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    String toFileString() {
        return id + "," + name + "," + marks;
    }

    static Student fromFileString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int marks = Integer.parseInt(parts[2]);
        return new Student(id, name, marks);
    }

    void displayData() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student Marks: " + marks);
    }
}