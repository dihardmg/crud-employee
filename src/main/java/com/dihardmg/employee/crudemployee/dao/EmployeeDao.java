package com.dihardmg.employee.crudemployee.dao;

import com.dihardmg.employee.crudemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Otorus
 * @since : 12/23/17
 */
@Repository
public interface EmployeeDao extends PagingAndSortingRepository<Employee, String>{
    public Page<Employee> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
