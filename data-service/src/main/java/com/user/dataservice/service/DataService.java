package com.user.dataservice.service;

import com.user.dataservice.dto.DtosRes;
import com.user.dataservice.entity.DataTable;
import com.user.dataservice.exception.DataAlreadyExistException;
import com.user.dataservice.repository.DataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DataService {

    private final DataRepository dataRepository;

    public List<DtosRes> getAllData() {

        log.info("Fetching all data from the database");
        List<DtosRes> dtosResList = new ArrayList<>();
        List<DataTable> dataTableList = dataRepository.findAll();
        for (DataTable dataTable : dataTableList) {
            DtosRes dtosRes = new DtosRes();
            dtosRes.setId(dataTable.getId());
            dtosRes.setName(dataTable.getName());
            dtosRes.setPhone_number(dataTable.getPhonenumber());
            dtosRes.setDescription(dataTable.getDescription());
            dtosRes.setCreatedBy(dataTable.getCreatedBy());
            dtosResList.add(dtosRes);
        }
        log.info("Retrieved {} data entries", dtosResList.size());
        return dtosResList;
    }

    public DtosRes saveData(DataTable dataTable) {

        log.info("Saving data into the database");
        if (dataRepository
                .findByPhonenumber(dataTable.getPhonenumber())
                .isPresent()) {

            log.error("Data with phone number {} already exists", dataTable.getPhonenumber());
            throw new DataAlreadyExistException(
                    "Data with phone number "
                            + dataTable.getPhonenumber()
                            + " already exists"
            );
        }

        DataTable savedData = dataRepository.save(dataTable);
        DtosRes dtosRes = new DtosRes(
                savedData.getId(),
                savedData.getName(),
                savedData.getPhonenumber(),
                savedData.getDescription(),
                savedData.getCreatedBy()
        );
        log.info("Saved data into the database");



        return dtosRes;
    }

    public DtosRes getDataById(Long id) {

        log.info("Fetching data by ID {} from the database", id);
        DataTable dataTable = dataRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Data with ID {} not found", id);
                    return new RuntimeException("Data with ID " + id + " not found");
                });
        DtosRes dtosRes = new DtosRes(
                dataTable.getId(),
                dataTable.getName(),
                dataTable.getPhonenumber(),
                dataTable.getDescription(),
                dataTable.getCreatedBy()
        );
        log.info("Retrieved data by ID {} from the database", id);
        return dtosRes;
    }

    public DtosRes getDataByQuerParam(String querParam) {
        // Implementation for fetching data by query parameter
        DataTable dataTable = dataRepository.findById(Long.parseLong(querParam))
                .orElseThrow(() -> {
                    log.error("Data with ID {} not found", querParam);
                    return new RuntimeException("Data with ID " + querParam + " not found");
                });
        DtosRes dtosRes = new DtosRes(
                dataTable.getId(),
                dataTable.getName(),
                dataTable.getPhonenumber(),
                dataTable.getDescription(),
                dataTable.getCreatedBy()
        );
        log.info("Retrieved data by query parameter {} from the database", querParam);
        return dtosRes;
    }

    public DtosRes updateData(DataTable dataTable, Long id) {
        log.info("Updating data in the database");
        DataTable existingData = dataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        existingData.setName(dataTable.getName());
        existingData.setPhonenumber(dataTable.getPhonenumber());
        existingData.setDescription(dataTable.getDescription());

        DataTable updatedData = dataRepository.save(existingData);

        return new DtosRes(
                updatedData.getId(),
                updatedData.getName(),
                updatedData.getPhonenumber(),
                updatedData.getDescription(),
                updatedData.getCreatedBy()
        );
    }

    public DtosRes patchData(DataTable dataTable, Long id) {
        log.info("Patching data in the database");
        DataTable existingData = dataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        if (dataTable.getName() != null) {
            existingData.setName(dataTable.getName());
        }
        if (dataTable.getPhonenumber() != null) {
            existingData.setPhonenumber(dataTable.getPhonenumber());
        }
        if (dataTable.getDescription() != null) {
            existingData.setDescription(dataTable.getDescription());
        }

        DataTable patchedData = dataRepository.save(existingData);

        return new DtosRes(
                patchedData.getId(),
                patchedData.getName(),
                patchedData.getPhonenumber(),
                patchedData.getDescription(),
                patchedData.getCreatedBy()
        );
    }
}
