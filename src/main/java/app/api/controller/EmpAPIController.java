package app.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Dept;
import app.entity.Emp;
import app.repository.EmpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

	private final EmpRepository empRepository;
	
	
	
	@GetMapping("/api/emp/{empno}")
	public Emp getEmpById(@PathVariable Integer empno) {
		 return empRepository.findById(empno)
		            .orElseThrow(() -> new EntityNotFoundException("empno: " + empno + "를 찾을 수 없습니다"));
	}
	
}
