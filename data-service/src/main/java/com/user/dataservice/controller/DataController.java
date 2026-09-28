package com.user.dataservice.controller;

import com.user.dataservice.dto.DtosRes;
import com.user.dataservice.entity.DataTable;
import com.user.dataservice.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
@Slf4j
@AllArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping
    public ResponseEntity<List<DtosRes>> getData() {

        log.info("Getting data from database");
        List<DtosRes> dtosResList = dataService.getAllData();
        return ResponseEntity.status(200).body(dtosResList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtosRes> getDataById(@PathVariable Long id) {

        log.info("Getting data by ID from database");
        DtosRes dtosRes = dataService.getDataById(id);
        return ResponseEntity.status(200).body(dtosRes);
    }

    @GetMapping("/search")
    public ResponseEntity<DtosRes> getDataByQuerParam(@RequestParam String querParam) {

        log.info("Getting data by query parameter from database");
        DtosRes dtosRes = dataService.getDataByQuerParam(querParam);
        return ResponseEntity.status(200).body(dtosRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtosRes> updateData(@PathVariable Long id, @RequestBody DataTable dataTable) {

        log.info("Updating data in database");
        DtosRes updatedData = dataService.updateData(dataTable , id);
        return ResponseEntity.status(200).body(updatedData);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DtosRes> patchData(@PathVariable Long id, @RequestBody DataTable dataTable) {

        log.info("Patching data in database");
        DtosRes patchedData = dataService.patchData(dataTable , id);
        return ResponseEntity.status(200).body(patchedData);
    }

    @PostMapping
    public ResponseEntity<DtosRes> addData(@RequestBody DataTable dataTable) {

        log.info("Adding data into database");
        DtosRes savedData = dataService.saveData(dataTable);
        return ResponseEntity.status(201).body(savedData);

//        sahana.vh@in.experis.com
//        9866164712
//        hr@maivin.in
    }

}
