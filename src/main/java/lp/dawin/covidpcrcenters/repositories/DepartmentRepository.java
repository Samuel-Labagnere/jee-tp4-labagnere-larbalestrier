package lp.dawin.covidpcrcenters.repositories;

import lp.dawin.covidpcrcenters.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}


