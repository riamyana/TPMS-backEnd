package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberTypeProof;
import com.trasportManagement.transportservice.repository.MemberTypeProofRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberTypeProofService {

    @Autowired
    @Qualifier("memberTypeProof")
    MemberTypeProofRepo memberTypeProofRepo;

    public MemberTypeProof addMemberTypePackage(MemberTypeProof mp) {

        int id = memberTypeProofRepo.addMemberTypeProof(mp);
        mp.setId(id);

//        if (n == 0) {
//            throw new TPMSCustomException("No record inserted of this Member Type Package.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return mp;

    }

    public MemberTypeProof updateMemberTypePackage(int id, MemberTypeProof mp) {
        int n = memberTypeProofRepo.updateMemberTypeProof(id, mp);
        mp.setId(id);

        if(n == 0){
            throw  new TPMSCustomException("Unable to update. Given member package id : " + mp.getId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return mp;
    }

    public Boolean deleteMemberTypePackage(int id) {
        if(!memberTypeProofRepo.deleteMemberTypeProof(id)){
            throw new TPMSCustomException("Unable to delete. Given member package id : " + id   + " not found.", HttpStatus.NOT_FOUND);
        }

        return true;
    }
}
