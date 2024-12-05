package app.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.EmpDto;
import app.entity.Dept;
import app.entity.Emp;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {

	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;
	
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
