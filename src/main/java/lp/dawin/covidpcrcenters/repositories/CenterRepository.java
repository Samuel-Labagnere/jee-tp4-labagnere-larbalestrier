package lp.dawin.covidpcrcenters.repositories;

import lp.dawin.covidpcrcenters.model.Center;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CenterRepository {

    List<Center> findByDepartment(String departmentCode);
    List<Center> searchByDepartment(String departmentCode, String search);

    Long countByDepartment(String departmentCode);

    Map<String, Long> countAllByDepartment();

    Collection<Center> findAll() throws DataAccessException;

    Optional<Center> findById(long id) throws DataAccessException;

    void save(Center center) throws DataAccessException;

    void delete(Center center) throws DataAccessException;
}
