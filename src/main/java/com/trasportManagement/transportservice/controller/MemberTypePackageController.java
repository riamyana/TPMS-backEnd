package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypePackageDTO;
import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import com.trasportManagement.transportservice.service.MemberTypePackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberTypePackageController {

    @Autowired
    MemberTypePackageService memberTypePackageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/member-packages")
    public ResponseEntity<MemberTypePackage> addPackage(@RequestBody(required=true) MemberTypePackage mp) {
        MemberTypePackage result = memberTypePackageService.addMemberTypePackage(mp);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/member-packages/{id}")
    public ResponseEntity<MemberTypePackage> updatePackage(@PathVariable int id,@RequestBody(required=true) MemberTypePackage mp) {
        MemberTypePackage result = memberTypePackageService.updateMemberTypePackage(id, mp);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/member-packages/{id}")
    public ResponseEntity<Boolean> deletePackage(@PathVariable int id) {
        Boolean result = memberTypePackageService.deleteMemberTypePackage(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member-packages/{packageId}")
    public ResponseEntity<List<MemberTypePackageDTO>> findMemberPackageById(@PathVariable int packageId){
        List<MemberTypePackageDTO> passResult =memberTypePackageService.findMemberPackageById(packageId);
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }
}
