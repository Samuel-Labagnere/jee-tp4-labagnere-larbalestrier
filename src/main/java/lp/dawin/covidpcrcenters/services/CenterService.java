package lp.dawin.covidpcrcenters.services;

import lp.dawin.covidpcrcenters.services.dto.CenterDTO;
import lp.dawin.covidpcrcenters.services.dto.DepartmentDTO;
import lp.dawin.covidpcrcenters.services.dto.SaveCenterDTO;

import java.util.List;
import java.util.Optional;

public interface CenterService {

    List<DepartmentDTO> getAllDepartments();

    Optional<DepartmentDTO> getDepartmentByCode(String departmentCode);

    List<CenterDTO> findCentersByDepartment(String departmentCode);

    List<CenterDTO> searchCentersByDepartment(String departmentCode, String search);

    Optional<CenterDTO> getCenter(long centerId);

    long createCenter(String departmentCode, SaveCenterDTO center);

    void updateCenter(long id, SaveCenterDTO centerDTO);

    void deleteCenter(long centerId);
}
