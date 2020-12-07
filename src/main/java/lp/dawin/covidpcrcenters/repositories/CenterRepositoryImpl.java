package lp.dawin.covidpcrcenters.repositories;

import lp.dawin.covidpcrcenters.model.Center;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
public class CenterRepositoryImpl implements CenterRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Center> findByDepartment(final String departmentCode) {
        return em.createQuery("SELECT c FROM Center c where c.department.code = :code ORDER BY c.name")
                .setParameter("code", departmentCode)
                .getResultList();
    }

    @Override
    public List<Center> searchByDepartment(final String departmentCode, final String search) {
        String hql = "SELECT c FROM Center c where c.department.code = :code";
        if (Strings.isNotBlank(search)) {
            hql += " AND lower(concat(c.name,c.idFiness,c.idLegalFiness,c.fullAddress)) like :search";
        }
        hql += " ORDER BY c.name";

        final Query query = em.createQuery(hql);
        query.setParameter("code", departmentCode);
        if (Strings.isNotBlank(search)) {
            query.setParameter("search", "%" + search.toLowerCase() + "%");
        }

        return query.getResultList();
    }

    @Override
    public Long countByDepartment(final String departmentCode) {
        final Object result = em.createQuery("SELECT count(c) FROM Center c where c.department.code = :code")
                .setParameter("code", departmentCode)
                .getSingleResult();

        return result == null ? 0 : (Long) result;
    }

    @Override
    public Map<String, Long> countAllByDepartment() {
        final List<Object[]> results = em.createQuery("SELECT COUNT(c.id), c.department.code FROM Center c GROUP BY c.department")
                .getResultList();
        Map<String, Long> map = new HashMap<>();
        for (Object[] result : results) {
            map.put((String) result[1], (Long) result[0]);
        }
        return map;
    }

    @Override
    public Optional<Center> findById(long id) {
        return Optional.ofNullable(this.em.find(Center.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Center> findAll() {
        return this.em.createQuery("SELECT c FROM Center c").getResultList();
    }

    @Override
    public void save(Center center) {
        if (center.getId() == 0) {
            this.em.persist(center);
        } else {
            this.em.merge(center);
        }
    }

    @Override
    public void delete(Center center) {
        this.em.remove(this.em.contains(center) ? center : this.em.merge(center));
    }

}
