package app.api.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Dept;
import app.entity.Emp;
import app.dto.EmpDto;
import app.repository.EmpRepository;
import app.repository.DeptRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {

//	private final EmpService empService;
	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;

	@GetMapping("/emps")
	public List<Emp> getEmps() {
//		List<Emp> emps = empService.getEmps();
		List<Emp> emps = empRepository.findAll();
		return emps;
	}

	@GetMapping("/emp/{empno}")
	public Emp getEmpById(@PathVariable Integer empno) {
		 return empRepository.findById(empno)
		            .orElseThrow(() -> new EntityNotFoundException("empno: " + empno + "를 찾을 수 없습니다"));
	}
	
  @PostMapping("/emp")
	public EmpDto registerEmp(@RequestBody EmpDto newEmp) {

		Dept dept = deptRepository.findById(newEmp.getDeptno()).orElseThrow(null);
		Emp emp = Emp.builder()
						.empno(newEmp.getEmpno())
						.ename(newEmp.getEname())
						.job(newEmp.getJob())
						.mgr(newEmp.getMgr())
						.hiredate(newEmp.getHiredate())
						.sal(newEmp.getSal())
						.comm(newEmp.getComm())
						.dept(dept)
						.build();
		Emp resultEmp = empRepository.save(emp);
		EmpDto empDto = EmpDto.from(resultEmp);
		
		return empDto;
	}

}
