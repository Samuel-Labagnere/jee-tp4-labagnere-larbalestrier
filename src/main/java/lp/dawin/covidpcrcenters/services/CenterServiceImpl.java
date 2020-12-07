package lp.dawin.covidpcrcenters.services;

import lp.dawin.covidpcrcenters.model.Center;
import lp.dawin.covidpcrcenters.model.Department;
import lp.dawin.covidpcrcenters.repositories.CenterRepository;
import lp.dawin.covidpcrcenters.repositories.DepartmentRepository;
import lp.dawin.covidpcrcenters.services.dto.CenterDTO;
import lp.dawin.covidpcrcenters.services.dto.DepartmentDTO;
import lp.dawin.covidpcrcenters.services.dto.SaveCenterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CenterServiceImpl implements CenterService {

    static Logger logger = LoggerFactory.getLogger(CenterServiceImpl.class);

    private static Function<Center, CenterDTO> centerEntityToDTO = center -> {
        final CenterDTO dto = new CenterDTO();
        dto.setId(center.getId());
        dto.setFullAddress(center.getFullAddress());
        dto.setIdFiness(center.getIdFiness());
        dto.setIdLegalFiness(center.getIdLegalFiness());
        dto.setLastUpdate(center.getLastUpdate());
        dto.setLatitude(center.getLatitude());
        dto.setLongitude(center.getLongitude());
        dto.setName(center.getName());
        dto.setPhoneNumber(center.getPhoneNumber());
        return dto;
    };

    /**
     * Injected repository
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Injected repository
     */
    private final CenterRepository centerRepository;


    @Autowired
    public CenterServiceImpl(final DepartmentRepository departmentRepository, final CenterRepository centerRepository) {
        this.departmentRepository = departmentRepository;
        this.centerRepository = centerRepository;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        final List<Department> entities = departmentRepository.findAll();
        final Map<String, Long> nbCenterByDepartment = centerRepository.countAllByDepartment();
        return entities.stream().map(e -> {
            final DepartmentDTO dto = new DepartmentDTO();
            dto.setCode(e.getCode());
            dto.setName(e.getName());
            dto.setRegion(e.getRegion());
            dto.setNbCenter(nbCenterByDepartment.getOrDefault(e.getCode(), 0L));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDTO> getDepartmentByCode(final String departmentCode) {
        return departmentRepository.findById(departmentCode).map(e -> {
            final DepartmentDTO dto = new DepartmentDTO();
            dto.setCode(e.getCode());
            dto.setName(e.getName());
            dto.setRegion(e.getRegion());
            dto.setNbCenter(centerRepository.countByDepartment(departmentCode));
            return dto;
        });
    }

    @Override
    public List<CenterDTO> findCentersByDepartment(final String departmentCode) {
        final List<Center> entities = centerRepository.findByDepartment(departmentCode);
        logger.debug("Found {} centers in department {}", entities.size(), departmentCode);
        return entities.stream().map(centerEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CenterDTO> searchCentersByDepartment(final String departmentCode, final String search) {
        final List<Center> entities = centerRepository.searchByDepartment(departmentCode, search);
        logger.debug("Found {} centers in department {} that match query :{}", entities.size(), departmentCode, search);
        return entities.stream().map(centerEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CenterDTO> getCenter(final long centerId) {
        logger.debug("Retrieving center for id {}", centerId);
        final Optional<Center> center = centerRepository.findById(centerId);
        return center.map(centerEntityToDTO);
    }

    @Override
    @Transactional
    public long createCenter(final String departmentCode, final SaveCenterDTO centerDTO) {
        logger.debug("Create center with name {}, add it to department {}", centerDTO.getName(), departmentCode);
        //Retrieve department entity, nullity as been verified in controller
        final Optional<Department> department = departmentRepository.findById(departmentCode);
        final Center center = new Center();
        center.setDepartment(department.get());
        center.setIdFiness(centerDTO.getIdFiness());
        center.setIdLegalFiness(centerDTO.getIdLegalFiness());
        center.setName(centerDTO.getName());
        center.setFullAddress(centerDTO.getFullAddress());
        center.setLastUpdate(LocalDateTime.now());
        center.setLatitude(centerDTO.getLatitude());
        center.setLongitude(centerDTO.getLongitude());
        center.setPhoneNumber(centerDTO.getPhoneNumber());
        centerRepository.save(center);
        logger.debug("Center with name {} created, id is {}", centerDTO.getName(), center.getId());
        return center.getId();
    }

    @Override
    @Transactional
    public void updateCenter(final long id, final SaveCenterDTO centerDTO) {
        logger.debug("Update center with id {}", id);
        final Optional<Center> center = centerRepository.findById(id);
        center.ifPresent(c -> {
            c.setIdFiness(centerDTO.getIdFiness());
            c.setIdLegalFiness(centerDTO.getIdLegalFiness());
            c.setName(centerDTO.getName());
            c.setFullAddress(centerDTO.getFullAddress());
            c.setLastUpdate(LocalDateTime.now());
            c.setLatitude(centerDTO.getLatitude());
            c.setLongitude(centerDTO.getLongitude());
            c.setPhoneNumber(centerDTO.getPhoneNumber());
            centerRepository.save(c);
            logger.debug("Center with id {} updated", id);
        });
    }

    @Override
    @Transactional
    public void deleteCenter(final long centerId) {
        logger.debug("Delete center with id {}", centerId);
        final Optional<Center> center = centerRepository.findById(centerId);
        center.ifPresent(c -> {
            centerRepository.delete(c);
            logger.debug("Center with id {} deleted", centerId);

        });
    }
}
