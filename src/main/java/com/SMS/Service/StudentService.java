package com.SMS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SMS.Modul.Student;
import com.SMS.Repositry.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
    public StudentService(StudentRepository studentRepository) {
    	this.studentRepository = studentRepository;
    }
    
    public Student addStudent(Student student) {
    	return studentRepository.save(student);
    }
    public List<Student> getAllStudents(){
    	return studentRepository.findAll();
    }
    public Optional<Student> getStudentByID(Long id){
    	return studentRepository.findById(id);
    }
    
    public Student updateStudent(Long id, Student studentDetails) {
    	Optional<Student> availableStudent = studentRepository.findById(id);
    	if(availableStudent.isPresent()) {
    		Student existingStudent = availableStudent.get();
    		existingStudent.setName(studentDetails.getName());
    		existingStudent.setEmail(studentDetails.getEmail());
    		existingStudent.setAge(studentDetails.getAge());
    		return studentRepository.save(existingStudent);
    	}else {
    		throw new RuntimeException("Student Not Found Witn ID: " + id);
    	}
    }
    // public void deleteStudent(Long id) {
    // 	if(studentRepository.existsById(id)) {
    // 		studentRepository.deleteById(id);
    // 	}else {
    // 		throw new RuntimeException("Student Not Found With ID: " +id);
    // 	}
    // }
	 public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }
}
