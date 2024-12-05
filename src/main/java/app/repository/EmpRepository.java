package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Emp;
import jakarta.transaction.Transactional;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>{
	


}

