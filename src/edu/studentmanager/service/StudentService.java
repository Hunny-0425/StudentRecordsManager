package edu.studentmanager.service;

import edu.studentmanager.domain.Student;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final Map<Integer, Student> students = new LinkedHashMap<>();

    public void add(Student student) {
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("A student with this ID already exists.");
        }
        students.put(student.getId(), student);
    }

    public Student findById(int id) {
        return students.get(id);
    }

    public List<Student> findByName(String text) {
        String key = text.toLowerCase();
        return students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    public List<Student> all() {
        return new ArrayList<>(students.values());
    }

    public List<Student> activeStudents() {
        return students.values().stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
    }

    public void remove(int id) {
        if (students.remove(id) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
    }

    public List<Student> sortedByAverageDescending() {
        return students.values().stream()
                .sorted(Comparator.comparingDouble(Student::average).reversed())
                .collect(Collectors.toList());
    }

    public double classAverage() {
        return students.values().stream()
                .mapToDouble(Student::average)
                .average().orElse(0);
    }

    public Optional<Student> topper() {
        return students.values().stream()
                .max(Comparator.comparingDouble(Student::average));
    }

    public int size() {
        return students.size();
    }

    public void clear() {
        students.clear();
    }
}
