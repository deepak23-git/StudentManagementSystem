package com.SMS.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SMS.Modul.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
