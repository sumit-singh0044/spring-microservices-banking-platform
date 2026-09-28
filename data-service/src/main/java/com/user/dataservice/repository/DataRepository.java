package com.user.dataservice.repository;

import com.user.dataservice.entity.DataTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DataRepository extends JpaRepository<DataTable, Long> {

    Optional<DataTable> findByPhonenumber(int phoneNumber);

    Optional<DataTable> findById(Long id);

}
