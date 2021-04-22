package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.repository.PassRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public Result<Pass> updatePass(int passId, Pass p) {
        int rows = passRepo.updatePass(passId,p);
        if(rows > 0){
            return new Result<>(200, p);
        }
        else{
            return new Result<>(400, "Unable to update. Given member id : " + p.getPassId()   + " not found.");
        }
    }

    public Result<Pass> deletePass(int passId) {
        int rows = passRepo.deletePass(passId);
        if(rows > 0){
            return new Result<>(200, "Pass with id : " + passId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given pass id : " + passId   + " not found.");
        }
    }

//    public Result<Date> findExpiryBySerialNo(Long serialNo){
//        Date date = passRepo.findExpiryBySerialNo(serialNo);
//
//        if(date == null){
//            return new Result<>(400, "Unable to get expiry date. Given serial number : " + serialNo   + " not found.");
//        }
//        return new Result<>(200, date);
//    }

    public Result<List<PassWithMemberDetails>> findMemberPassById(int passId) {
        List<PassWithMemberDetails> memberPass = passRepo.findMemberPassById(passId);
        if(memberPass.size() > 0){
            return new Result<>(200, memberPass);
        }
        else{
            return new Result<>(400, "No pass found.");
        }
    }

    public Result<List<PassWithMemberDetails>> findAllMemberPasses() {
        List<PassWithMemberDetails> memberPasses = passRepo.findAllMemberPasses();
        if(memberPasses.size() > 0){
            return new Result<>(200, memberPasses);
        }
        else{
            return new Result<>(400, "No pass found.");
        }
    }

    public Result<List<PassWithMemberDetails>> findMemberPassBySerialNo(Long serialNo) {
        List<PassWithMemberDetails> memberPass = passRepo.findMemberPassBySerialNo(serialNo);
        if(memberPass.size() > 0){
            return new Result<>(200, memberPass);
        }
        else{
            return new Result<>(400, "No pass found.");
        }
    }

    public Result<List<Pass>> findAllPasses() {
        List<Pass> passes = passRepo.findAllPasses();
        if(passes.size() > 0){
            return new Result<>(200, passes);
        }
        else{
            return new Result<>(400, "No pass found.");
        }
    }
}
