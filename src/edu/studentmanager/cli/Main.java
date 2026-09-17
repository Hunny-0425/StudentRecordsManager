package edu.studentmanager.cli;

import edu.studentmanager.domain.Student;
import edu.studentmanager.io.StudentFileRepository;
import edu.studentmanager.service.StudentService;
import edu.studentmanager.util.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private final StudentService service = new StudentService();
    private final StudentFileRepository repository =
            new StudentFileRepository(Path.of("data", "students.csv"));
    private final Input input = new Input();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        loadData();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = input.text("Choose an option: ");

            try {
                switch (choice) {
                    case "1" -> addStudent();
                    case "2" -> listStudents(service.all());
                    case "3" -> searchStudent();
                    case "4" -> updateStudent();
                    case "5" -> deactivateStudent();
                    case "6" -> showResults();
                    case "7" -> saveData();
                    case "8" -> {
                        saveData();
                        running = false;
                        System.out.println("Thank you for using Student Records Manager.");
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

            if (running) input.pause();
        }
    }

    private void printMenu() {
        System.out.println("\n==============================================");
        System.out.println("       STUDENT RECORDS MANAGER");
        System.out.println("==============================================");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Search student");
        System.out.println("4. Update student");
        System.out.println("5. Deactivate student");
        System.out.println("6. Academic report");
        System.out.println("7. Save records");
        System.out.println("8. Save and exit");
        System.out.println("==============================================");
    }

    private void addStudent() {
        int id = input.integer("Student ID: ");
        String reg = input.text("Registration number: ");
        String name = input.text("Name: ");
        String email = input.text("Email: ");
        double javaMarks = input.decimal("Java marks: ");
        double dbmsMarks = input.decimal("DBMS marks: ");
        double mathMarks = input.decimal("Mathematics marks: ");

        service.add(new Student(id, reg, name, email, javaMarks, dbmsMarks, mathMarks, true));
        System.out.println("Student added successfully.");
    }

    private void listStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No records available.");
            return;
        }
        System.out.printf("%-4s %-13s %-20s %-28s %6s %6s %6s %7s %-3s %-7s%n",
                "ID", "Reg No", "Name", "Email", "Java", "DBMS", "Math", "Avg", "G", "Status");
        System.out.println("-".repeat(120));
        students.forEach(System.out::println);
    }

    private void searchStudent() {
        String query = input.text("Enter ID or part of name: ");
        try {
            int id = Integer.parseInt(query);
            Student s = service.findById(id);
            if (s == null) System.out.println("Student not found.");
            else listStudents(List.of(s));
        } catch (NumberFormatException e) {
            listStudents(service.findByName(query));
        }
    }

    private void updateStudent() {
        int id = input.integer("Enter student ID: ");
        Student s = service.findById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new values.");
        s.setName(input.text("Name: "));
        s.setEmail(input.text("Email: "));
        double javaMarks = input.decimal("Java marks: ");
        double dbmsMarks = input.decimal("DBMS marks: ");
        double mathMarks = input.decimal("Mathematics marks: ");
        s.setMarks(javaMarks, dbmsMarks, mathMarks);
        System.out.println("Student updated.");
    }

    private void deactivateStudent() {
        int id = input.integer("Enter student ID: ");
        Student s = service.findById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        s.deactivate();
        System.out.println("Student deactivated.");
    }

    private void showResults() {
        System.out.printf("Total students : %d%n", service.size());
        System.out.printf("Class average  : %.2f%n", service.classAverage());

        service.topper().ifPresent(s ->
                System.out.printf("Top performer  : %s (%.2f, Grade %s)%n",
                        s.getName(), s.average(), s.grade()));

        System.out.println("\nStudents ranked by average:");
        listStudents(service.sortedByAverageDescending());
    }

    private void loadData() {
        try {
            repository.load(service);
            System.out.println("Loaded " + service.size() + " student record(s).");
        } catch (Exception e) {
            System.out.println("Could not load existing data. Starting with an empty record set.");
        }
    }

    private void saveData() throws IOException {
        repository.save(service);
        System.out.println("Records saved to " + repository.getFile());
    }
}
