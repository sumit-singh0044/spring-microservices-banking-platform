package com.user.dataservice.service;


import com.user.dataservice.dto.DtosRes;
import com.user.dataservice.entity.DataTable;
import com.user.dataservice.repository.DataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class DataServiceTest {

    @Mock
    private DataRepository dataRepositoryMock;

    @InjectMocks
    private DataService dataService;

    @Test
    public void testGetAllData() {
        // Implement your test logic here
        // Arrange
        // Arrange
        DataTable data1 = new DataTable();
        DataTable data2 = new DataTable();

        List<DataTable> mockData = List.of(data1, data2);

        when(dataRepositoryMock.findAll())
                .thenReturn(mockData);

        // Act
        List<DtosRes> result = dataService.getAllData();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify
        verify(dataRepositoryMock).findAll();
    }
    }


