package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.repository.PassRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {

    @Autowired
    @Qualifier("passRepo")
    PassRepo passRepo;

    public Result<Pass> addPass(Pass p) {
        int passId = passRepo.addPass(p);
        if(passId == 0){
            return new Result<>(400, "Error in adding pass details.");
        }else{
            p.setPassId(passId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, p);
        }
    }

    public Pass updatePass(int passId, Pass p) {
        int n = passRepo.updatePass(passId,p);
        p.setPassId(passId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given pass id : " + p.getPassId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deletePass(int passId) {
        if(!passRepo.deletePass(passId)){
            throw new TPMSCustomException("Unable to delete. Given pass id : " + passId   + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

//    public Result<Date> findExpiryBySerialNo(Long serialNo){
//        Date date = passRepo.findExpiryBySerialNo(serialNo);
//
//        if(date == null){
//            return new Result<>(400, "Unable to get expiry date. Given serial number : " + serialNo   + " not found.");
//        }
//        return new Result<>(200, date);
//    }

    public List<PassWithMemberDetails> findMemberPassById(int passId) {
        List<PassWithMemberDetails> memberPass = passRepo.findMemberPassById(passId);
        if(memberPass.isEmpty()){
            throw  new TPMSCustomException("No pass found for pass id: "+passId, HttpStatus.NOT_FOUND);
        }

        return memberPass;
    }

    public List<PassWithMemberDetails> findAllMemberPasses() {
        List<PassWithMemberDetails> memberPasses = passRepo.findAllMemberPasses();
        if(memberPasses.isEmpty()){
            throw  new TPMSCustomException("No pass found.", HttpStatus.NOT_FOUND);
        }

        return memberPasses;
    }

    public List<PassWithMemberDetails> findMemberPassesByMemberId(int memberId) {
        List<PassWithMemberDetails> memberPasses = passRepo.findMemberPassesByMemberId(memberId);
        if(memberPasses.isEmpty()){
            throw  new TPMSCustomException("No pass found.", HttpStatus.NOT_FOUND);
        }

        return memberPasses;
    }

    public List<PassWithMemberDetails> findMemberPassBySerialNo(Long serialNo) {
        List<PassWithMemberDetails> memberPass = passRepo.findMemberPassBySerialNo(serialNo);
        if(memberPass.isEmpty()){
            throw  new TPMSCustomException("No pass found for serial no.: "+serialNo, HttpStatus.NOT_FOUND);
        }

        return memberPass;
    }

    public List<Pass> findAllPasses() {
        List<Pass> passes = passRepo.findAllPasses();
        if(passes.isEmpty()){
            throw  new TPMSCustomException("No pass found.", HttpStatus.NOT_FOUND);
        }

        return passes;
    }
}
