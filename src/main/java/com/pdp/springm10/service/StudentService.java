package com.pdp.springm10.service;

import com.pdp.springm10.entity.Student;
import com.pdp.springm10.repository.StudentRepository;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  20:05
 **/
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Cacheable(value = "students", key = "#id")
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @CachePut(value = "students", key = "#id")
    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @SneakyThrows
    @Cacheable(value = "students", key = "#root.methodName")
    public List<Student> getAllStudents() {
        TimeUnit.SECONDS.sleep(3);
        return studentRepository.findAll();
    }
}