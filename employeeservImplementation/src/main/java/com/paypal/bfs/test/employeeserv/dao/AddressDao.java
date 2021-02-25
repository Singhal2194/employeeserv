package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressDao extends JpaRepository<AddressEntity, Integer> {

    Optional<AddressEntity> findByEmpId(Integer integer);
}
