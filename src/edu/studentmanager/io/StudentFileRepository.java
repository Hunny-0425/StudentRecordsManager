package edu.studentmanager.io;

import edu.studentmanager.domain.Student;
import edu.studentmanager.service.StudentService;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class StudentFileRepository {
    private final Path file;

    public StudentFileRepository(Path file) {
        this.file = file;
    }

    public void save(StudentService service) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);

        StringBuilder data = new StringBuilder();
        data.append("id,registrationNo,name,email,javaMarks,dbmsMarks,mathMarks,active\n");
        for (Student s : service.all()) {
            data.append(s.toCsv()).append('\n');
        }
        Files.writeString(file, data.toString());
    }

    public void load(StudentService service) throws IOException {
        if (!Files.exists(file)) return;

        List<String> lines = Files.readAllLines(file);
        service.clear();

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) continue;
            String[] p = lines.get(i).split(",", -1);
            if (p.length != 8) continue;

            Student s = new Student(
                    Integer.parseInt(p[0]), p[1], p[2], p[3],
                    Double.parseDouble(p[4]), Double.parseDouble(p[5]),
                    Double.parseDouble(p[6]), Boolean.parseBoolean(p[7])
            );
            service.add(s);
        }
    }

    public Path getFile() {
        return file;
    }
}
