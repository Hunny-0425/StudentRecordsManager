# Project Report
## Student Records Manager

**Course:** Programming in Java  
**Project Type:** Console-based Java SE application  
**Student:** Vaishnavi Rao
**Registration Number:** 24BCY10015  
**Programme:** B.Tech CSE (Cyber Security and Digital Forensics)  
**Institution:** VIT Bhopal  

## 1. Executive Summary

Student Records Manager is a command-line Java application developed to manage student academic records. It allows an administrator to add, view, search, update and deactivate records. The application also calculates averages and grades, generates a simple academic report, ranks students by average marks, and identifies the top performer.

The project was designed around Java Standard Edition so that it can be compiled and executed directly from a terminal without external frameworks or a graphical interface.

## 2. Objectives

1. Build a practical application using core Java.
2. Apply object-oriented programming principles.
3. Use Java Collections to manage records efficiently.
4. Use Streams and lambda expressions for filtering, sorting and aggregation.
5. Implement file persistence using CSV.
6. Provide input validation and exception handling.
7. Make the application executable through command-line commands.

## 3. System Design

The application is separated into four logical areas:

- **domain:** contains the `Student` entity and its academic behaviour.
- **service:** contains business operations and the in-memory student collection.
- **io:** loads and saves records using the Java NIO file API.
- **cli:** provides the menu-driven user interface.
- **util:** provides reusable console input handling.

This separation keeps data representation, business logic, persistence and user interaction distinct.

## 4. Main Functionalities

### 4.1 Student Management

The system supports creation, reading, updating and deactivation of student records. Each student has an ID, registration number, name, email, marks in three subjects and an active status.

### 4.2 Search

Users can search by an exact numeric student ID or by a partial name. Name searches are case-insensitive.

### 4.3 Academic Processing

The application calculates the average of Java, DBMS and Mathematics marks. A letter grade is derived from the average. Students can be displayed in descending order of average marks.

### 4.4 Reporting

The academic report displays total records, class average and the top-performing student.

### 4.5 Persistence

Records are stored in `data/students.csv`. Java NIO (`Path`, `Files`) is used for reading and writing the file. This means data remains available after the program is closed.

## 5. Java Concepts Used

### Object-Oriented Programming

`Student` is a class containing private state and public methods. Encapsulation prevents direct modification of internal fields and centralizes validation inside setter methods.

### Collections

A `LinkedHashMap<Integer, Student>` is used by `StudentService`. The key is the student ID and the value is the corresponding `Student` object.

### Generics

Generic types such as `Map<Integer, Student>` and `List<Student>` provide compile-time type safety.

### Streams and Lambda Expressions

Streams are used for operations such as filtering active students, finding the class average and sorting by average marks.

### Exception Handling

Invalid numeric input is handled in the input utility. Business validation errors such as duplicate IDs, invalid marks and invalid email addresses are reported without terminating the application.

### File I/O

The project uses `java.nio.file.Files` and `Path` for CSV persistence.

### Optional

The topper operation returns an `Optional<Student>` so that an empty data set can be represented safely.

## 6. Data Schema

The persistent CSV file contains:

| Field | Description |
|---|---|
| id | Unique student identifier |
| registrationNo | University registration number |
| name | Student name |
| email | Student email |
| javaMarks | Java marks from 0 to 100 |
| dbmsMarks | DBMS marks from 0 to 100 |
| mathMarks | Mathematics marks from 0 to 100 |
| active | Current record status |

## 7. Execution

The project requires JDK 17 or later and no external dependency.

Compile:

```bash
mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
```

Run:

```bash
java -cp bin edu.studentmanager.cli.Main
```

On Windows PowerShell, the equivalent recursive source-list command is:

```powershell
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d bin @sources.txt
java -cp bin edu.studentmanager.cli.Main
```

## 8. Testing

The application should be tested with the following cases:

1. Start with the supplied CSV data and verify records are displayed.
2. Add a student with valid marks.
3. Attempt to add the same ID and verify the duplicate-ID validation.
4. Enter marks outside 0–100 and verify validation.
5. Search using an existing ID.
6. Search using part of a student's name.
7. Update a student's marks and verify the new average.
8. Deactivate a student and verify the status changes.
9. Generate the academic report and verify the class average and topper.
10. Save, close and restart the application to verify that data persists.

## 9. Limitations and Future Enhancements

The current version intentionally uses a CSV file rather than a database. Future versions could add:

- Multiple courses and enrollment management
- User authentication and roles
- Import/export validation with detailed error reports
- A database layer
- Unit tests using JUnit
- More detailed transcript generation
- Course-wise analytics

## 10. Conclusion

Student Records Manager demonstrates how Java SE can be used to build a complete command-line application. The project combines object-oriented design, collections, validation, streams, exception handling and file persistence into one practical system. Its terminal-based execution makes the application straightforward to compile, test and evaluate.
