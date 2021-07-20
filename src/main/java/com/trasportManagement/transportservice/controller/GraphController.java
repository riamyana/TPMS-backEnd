package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Graph;
import com.trasportManagement.transportservice.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GraphController {
    @Autowired
    GraphService graphService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/last-year-package")
    public ResponseEntity<List<Graph>> lastYearPackages() {
        List<Graph> graphList = graphService.lastYearPackages();

        return new ResponseEntity<>(graphList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/last-year-transport-package")
    public ResponseEntity<List<Graph>> lastYearTransportPackages() {
        List<Graph> graphList = graphService.lastYearTransportPackages();

        return new ResponseEntity<>(graphList, HttpStatus.OK);
    }
}
