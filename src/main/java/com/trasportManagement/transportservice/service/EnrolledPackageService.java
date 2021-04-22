package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.EnrolledPackage;
import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.repository.EnrolledPackageRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledPackageService {

    @Qualifier("enrolledPackageRepo")
    @Autowired
    EnrolledPackageRepo enrolledPackageRepo;

    public Result<EnrolledPackage> addEnrolledPackage(EnrolledPackage e) {

        int result = enrolledPackageRepo.addEnrolledPackage(e);

        if(result == 0){
            return new Result<>(400, "Error in adding enrolled package mode details.");
        }

        e.setId(result);
        return new Result<>(201, e);
    }

    public Result<EnrolledPackage> updateEnrolledPackage(int id, EnrolledPackage e) {

        int rows = enrolledPackageRepo.updateEnrolledPackage(id,e);

        if(rows > 0){
            return new Result<>(201, e);
        }

        return new Result<>(400, "Unable to update. Given Transport Mode id : " + e.getId()   + " not found.");
    }

    public Result<EnrolledPackage> deleteEnrolledPackage(int id) {
        int rows = enrolledPackageRepo.deleteEnrolledPackage(id);

        if(rows > 0){
            return new Result<>(200, "Enrolled package with id : " + id + " deleted successfully.");
        }

        return new Result<>(400, "Unable to delete. Given enrolled package id : " + id   + " not found.");
    }

    public Result<List<EnrolledPackage>> findAllEnrolledPackage() {

        List<EnrolledPackage> enrolled = enrolledPackageRepo.findAllEnrolledPackage();

        if (enrolled.size() > 0) {
            return new Result<>(200, enrolled);
        }

        return new Result<>(400, "No transport mode found.");
    }


}
