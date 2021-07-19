package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypePackageDTO;
import com.trasportManagement.transportservice.model.PackageForMember;
import com.trasportManagement.transportservice.repository.MemberTypePackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypePackageService {

    @Autowired
    @Qualifier("MemberTypePackageRepo")
    MemberTypePackageRepo memberTypePackageRepo;

    public MemberTypePackage addMemberTypePackage(MemberTypePackage mp) {

        int id = memberTypePackageRepo.addMemberTypePackage(mp);
        mp.setId(id);

//        if (n == 0) {
//            throw new TPMSCustomException("No record inserted of this Member Type Package.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return mp;

    }

    public MemberTypePackage updateMemberTypePackage(int id, MemberTypePackage mp) {
        int n = memberTypePackageRepo.updateMemberTypePackage(id, mp);
        mp.setId(id);

        if(n == 0){
            throw  new TPMSCustomException("Unable to update. Given member package id : " + mp.getId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return mp;
    }

    public Boolean deleteMemberTypePackage(int id) {
        if(!memberTypePackageRepo.deleteMemberTypePackage(id)){
            throw new TPMSCustomException("Unable to delete. Given member package id : " + id   + " not found.", HttpStatus.NOT_FOUND);
        }

        return true;
    }

    public List<MemberTypePackageDTO> findMemberPackageById(int packageId) {
        List<MemberTypePackageDTO> result = memberTypePackageRepo.findMemberPackageById(packageId);
        if(result.isEmpty()){
            throw  new TPMSCustomException("No package found.", HttpStatus.NOT_FOUND);
        }

        return result;
    }

    public List<PackageForMember> findMemberPackageByMemberId(int memberId) {
        List<PackageForMember> result = memberTypePackageRepo.findMemberPackageByMemberId(memberId);
        if(result.isEmpty()){
            throw  new TPMSCustomException("No package found.", HttpStatus.NOT_FOUND);
        }

        return result;
    }

    public List<PackageForMember> findAllMemberPackage() {
        List<PackageForMember> result = memberTypePackageRepo.findAllMemberPackage();
        if(result.isEmpty()){
            throw  new TPMSCustomException("No package found.", HttpStatus.NOT_FOUND);
        }

        return result;
    }
}
