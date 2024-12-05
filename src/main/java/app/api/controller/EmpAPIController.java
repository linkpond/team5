package app.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {

//	private final EmpService empService;
	private final EmpRepository empRepository;
	
	@GetMapping("/emps")
	public List<Emp> getEmps() {
//		List<Emp> emps = empService.getEmps();
		List<Emp> emps = empRepository.findAll();
		return emps;
	}
}
