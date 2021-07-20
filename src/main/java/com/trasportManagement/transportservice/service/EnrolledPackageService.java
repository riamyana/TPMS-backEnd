package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.EnrolledMemberPackage;
import com.trasportManagement.transportservice.model.EnrolledPackage;
import com.trasportManagement.transportservice.repository.EnrolledPackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledPackageService {

    @Qualifier("enrolledPackageRepo")
    @Autowired
    EnrolledPackageRepo enrolledPackageRepo;

    public EnrolledPackage addEnrolledPackage(EnrolledPackage e) {

        int n = enrolledPackageRepo.addEnrolledPackage(e);
        e.setId(n);

        if (n == 0) {
            throw new TPMSCustomException("No record inserted for this Enrolled Package.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return e;
    }

    public EnrolledPackage updateIsActive(EnrolledPackage e){
        int n = enrolledPackageRepo.updateIsActive(e);

        if(n == 0){
            throw new TPMSCustomException("Unable to update Enrolled Package isActive", HttpStatus.NOT_FOUND);
        }

        return e;
    }

    public List<EnrolledPackage> findAllEnrolledPackage() {

        List<EnrolledPackage> enrolled = enrolledPackageRepo.findAllEnrolledPackage();

        if(enrolled.isEmpty()){
            throw  new TPMSCustomException("No enfrolled package found", HttpStatus.NOT_FOUND);
        }

        return  enrolled;
    }

    public List<EnrolledPackage> findEnrolledPackageByPassId(int passId) {

        List<EnrolledPackage> enrolled = enrolledPackageRepo.findEnrolledPackageByPassId(passId);

        if(enrolled.isEmpty()){
            throw  new TPMSCustomException("No enfrolled package found", HttpStatus.NOT_FOUND);
        }

        return  enrolled;
    }

    public List<EnrolledMemberPackage> findEnrolledPkgByPassId(int passId) {
        List<EnrolledMemberPackage> enrolled = enrolledPackageRepo.findEnrolledPkgByPassId(passId);

        if(enrolled.isEmpty()){
            throw  new TPMSCustomException("No enrolled package found", HttpStatus.NOT_FOUND);
        }

        return  enrolled;
    }
}
