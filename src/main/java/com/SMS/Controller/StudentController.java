package com.SMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SMS.Modul.Student;
import com.SMS.Service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	//localhost:8080/api/students//
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student newStudent=studentService.addStudent(student);
		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	//localhost:8080/api/student/1
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentByID(@PathVariable Long id){
		Optional<Student> student = studentService.getStudentByID(id);
		return student.map(Value ->
		new ResponseEntity<>(Value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	//localhost:8080/api/student/3
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails ){
		try {
			Student updateStudent = studentService.updateStudent(id, studentDetails);
			return new ResponseEntity<>(updateStudent, HttpStatus.OK);		
		}catch(RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//localhost:8080/api/student/3
	// @DeleteMapping("/api/students/{id}")
	// public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
	// 	try {
	// 		studentService.deleteStudent(id);
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	}catch(RuntimeException e){
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
	// 	}
	// }
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
