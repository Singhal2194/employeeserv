package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<AddressEntity, Integer> {
}
