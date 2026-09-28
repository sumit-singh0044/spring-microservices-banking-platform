package com.user.dataservice.service;

import com.user.dataservice.entity.DataTable;
import com.user.dataservice.repository.DataRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataServiceTest2 {

    @Autowired
    private DataRepository dataRepository;

    @Transactional
    @Test
    public void testDataService() {
        // Test logic for DataService
        DataTable dataTable = new DataTable();

        dataTable = dataRepository.findById(1L).orElseThrow(() -> new RuntimeException("Data not found"));

        dataTable.setName("Updated Name");


    }

}
