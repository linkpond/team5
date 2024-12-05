package app.dto;

import java.time.LocalDate;

import app.entity.Emp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class EmpDto {
	
	private Integer empno;

	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	private LocalDate hiredate;
	
	private Double sal;
	
	private Double comm;
	
	private Integer deptno;
	
	public static EmpDto from(Emp emp) {
		return new EmpDto(emp.getEmpno(),
						emp.getEname(),
						emp.getJob(),
						emp.getMgr(),
						emp.getHiredate(),
						emp.getSal(),
						emp.getComm(),
						emp.getDept().getDeptno());
	}
}