package com.nmc.itschool.repository.custom.impl;

import com.demo.crudemployee.entity.EmployeeEntity;
import com.demo.crudemployee.repository.custom.EmployeeRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
@Transactional
public class EmployeeRepositoryCustomImpl
        extends BaseRepositoryImpl<EmployeeEntity, UUID>
        implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public EmployeeRepositoryCustomImpl() {
        super(EmployeeEntity.class);
    }

//    @Override
//    @Transactional
//    public List<EmployeeEntity> searchEmployee(EmployeeSCO employeeSCO) {
//        TypedQuery<EmployeeEntity> typedQuery = createCriteriaQuery(employeeSCO);
//        List<EmployeeEntity> result = (List<EmployeeEntity>) findByCriteria(typedQuery, employeeSCO);
//        return result;
//    }
//
//    private TypedQuery<EmployeeEntity> createCriteriaQuery(EmployeeSCO employeeSCO) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<EmployeeEntity> query = builder.createQuery(EmployeeEntity.class);
//
//        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        Join<EmployeeEntity, JobEntity> jobJoin = root.join("jobEntity");
//
//        CriteriaQueryEngine criteriaQueryEngine = new CriteriaQueryEngine(builder, predicates, root)
//                .appendSearchText(employeeSCO.getEmail(), "email")
//                .appendSearchText(employeeSCO.getFirstName(), "firstName")
//                .appendJoinedSearchText(employeeSCO.getJobName(), "jobName", jobJoin)
//                .appendDeleteFlag(employeeSCO.isDeleted());
//
//        predicates = criteriaQueryEngine.getPredicates();
//
//        query.where(predicates.toArray(new Predicate[0]));
//
//        return em.createQuery(query);
//    }

}
