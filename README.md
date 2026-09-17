# Student Records Manager

A console-based Java SE project for managing student academic records. It demonstrates object-oriented programming, Java Collections, exception handling, Streams, file I/O, and a menu-driven command-line interface.

## Features

- Add student records
- View all students
- Search by ID or name
- Update student details and marks
- Deactivate a student
- Calculate individual averages and grades
- Generate a class academic report
- Sort students by average marks
- Identify the top performer
- Persist records in CSV format
- Automatically load existing records at startup
- Runs entirely from a terminal; no GUI or external framework is required

## Requirements

- Java Development Kit (JDK) 17 or later
- Command Prompt, PowerShell, Terminal, or another command-line shell
- No third-party libraries are required

Check Java:

```bash
java -version
javac -version
```

## Project Structure

```text
StudentRecordsManager/
├── src/
│   └── edu/studentmanager/
│       ├── cli/Main.java
│       ├── domain/Student.java
│       ├── io/StudentFileRepository.java
│       ├── service/StudentService.java
│       └── util/Input.java
├── data/students.csv
├── README.md
├── PROJECT_REPORT.md
└── .gitignore
```

## Compile

Open a terminal in the repository root.

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force bin | Out-Null
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d bin @sources.txt
```

### Linux/macOS

```bash
mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
```

## Run

```bash
java -cp bin edu.studentmanager.cli.Main
```

The program starts by loading `data/students.csv`. Changes can be saved through option 7 or automatically through option 8.

## Example Workflow

1. Start the application.
2. Choose `2` to view the sample records.
3. Choose `1` to add a student.
4. Choose `6` to see the academic report.
5. Choose `7` to save the updated records.
6. Choose `8` to save and exit.

## Java Concepts Demonstrated

- Classes and objects
- Encapsulation using private fields and methods
- Constructor-based object creation
- Collections: `Map`, `List`, `ArrayList`, `LinkedHashMap`
- Generics
- Exception handling
- File handling with `java.nio.file`
- Streams API
- Lambda expressions and method references
- `Optional`
- Sorting using `Comparator`
- Input validation
- Menu-driven control flow

## Data Format

The CSV file uses:

```text
id,registrationNo,name,email,javaMarks,dbmsMarks,mathMarks,active
```

The application recreates Java `Student` objects from these values when it starts.

## Notes

The application intentionally uses Java SE APIs only. It does not require MySQL, Spring, Hibernate, Maven, Gradle, or any external dependency.

## Author

Vaishnavi Rao
B.Tech CSE (Cyber Security and Digital Forensics)
VIT Bhopal
