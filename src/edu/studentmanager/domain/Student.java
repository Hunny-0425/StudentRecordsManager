package edu.studentmanager.domain;

public class Student {
    private final int id;
    private String registrationNo;
    private String name;
    private String email;
    private double javaMarks;
    private double dbmsMarks;
    private double mathMarks;
    private boolean active;

    public Student(int id, String registrationNo, String name, String email,
                   double javaMarks, double dbmsMarks, double mathMarks, boolean active) {
        this.id = id;
        this.registrationNo = registrationNo;
        this.name = name;
        this.email = email;
        setMarks(javaMarks, dbmsMarks, mathMarks);
        this.active = active;
    }

    public int getId() { return id; }
    public String getRegistrationNo() { return registrationNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getJavaMarks() { return javaMarks; }
    public double getDbmsMarks() { return dbmsMarks; }
    public double getMathMarks() { return mathMarks; }
    public boolean isActive() { return active; }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = requireText(registrationNo, "Registration number");
    }

    public void setName(String name) {
        this.name = requireText(name, "Name");
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        this.email = email;
    }

    public void setMarks(double javaMarks, double dbmsMarks, double mathMarks) {
        validateMark(javaMarks);
        validateMark(dbmsMarks);
        validateMark(mathMarks);
        this.javaMarks = javaMarks;
        this.dbmsMarks = dbmsMarks;
        this.mathMarks = mathMarks;
    }

    public void deactivate() { active = false; }
    public void activate() { active = true; }

    public double average() {
        return (javaMarks + dbmsMarks + mathMarks) / 3.0;
    }

    public String grade() {
        double avg = average();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    private static void validateMark(double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public String toCsv() {
        return id + "," + registrationNo + "," + name.replace(",", " ") + "," +
               email + "," + javaMarks + "," + dbmsMarks + "," + mathMarks + "," + active;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-13s %-20s %-28s %6.1f %6.1f %6.1f %7.2f %-3s %-7s",
                id, registrationNo, name, email, javaMarks, dbmsMarks, mathMarks,
                average(), grade(), active ? "Active" : "Inactive");
    }
}
