package app.api.controller;

import app.dto.EmpDto;
import app.entity.Dept;
import app.entity.Emp;
import app.repository.DeptRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;

	@PutMapping("/api/emp/{empno}")
	public EmpDto updateEmp(@RequestBody EmpDto empDto ,
						 @PathVariable Integer empno) {

		Emp emp = empRepository.findById(empno).orElseThrow(() -> new EntityNotFoundException("정보가 존재하지 않습니다."));
		Dept dept = deptRepository.findById(empDto.getDeptno()).orElseThrow(() -> new EntityNotFoundException("정보가 존재하지 않습니다."));

		emp = Emp.builder()
				.empno(empno)
				.ename(empDto.getEname())
				.job(empDto.getJob())
				.mgr(empDto.getMgr())
				.hiredate(empDto.getHiredate())
				.sal(empDto.getSal())
				.comm(empDto.getComm())
				.dept(dept)
				.build();

		Emp updateEmp = empRepository.save(emp);


		return EmpDto.builder()
				.empno(updateEmp.getEmpno())
				.ename(updateEmp.getEname())
				.job(updateEmp.getJob())
				.mgr(updateEmp.getMgr())
				.hiredate(updateEmp.getHiredate())
				.sal(updateEmp.getSal())
				.comm(updateEmp.getComm())
				.deptno(updateEmp.getDept().getDeptno())
				.build();
	}

	@DeleteMapping("/api/emp/{empno}")
	public EmpDto deleteDeptByEmp(@PathVariable Integer empno) {

		Emp emp = empRepository.findById(empno).orElseThrow(() -> new EntityNotFoundException("정보가 존재하지 않습니다."));

		empRepository.delete(emp);

		return EmpDto.builder()
				.empno(empno)
				.ename(emp.getEname())
				.job(emp.getJob())
				.mgr(emp.getMgr())
				.sal(emp.getSal())
				.comm(emp.getComm())
				.deptno(emp.getDept().getDeptno())
				.build();
	}

}
