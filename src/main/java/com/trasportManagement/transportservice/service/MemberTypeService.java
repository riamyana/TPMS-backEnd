
package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.repository.MemberTypeRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypeService {

    @Autowired
    @Qualifier("memberTypeRepo")
    MemberTypeRepo memberTypeRepo;

    public Result<List<MemberType>> findAllMemberTypes() {
        List<MemberType> memberTypeList = memberTypeRepo.findAllMemberTypes();
//        System.out.println("Size : " + memberList.size());
        if(memberTypeList.size() > 0){
            return new Result<>(200,memberTypeList);
        }
        else{
            return new Result<>(400, "No MemberType found.");
        }

    }

    public Result<MemberType> findMemberTypeById(int memberTypeId) {
        List<MemberType> memberTypeList = memberTypeRepo.findMemberTypeById(memberTypeId);
        if(memberTypeList.size() > 0){
            return new Result<>(200,memberTypeList.get(0));
        }
        else{
            return new Result<>(400, "No MemberType found with memberTypeId : " + memberTypeId);
        }

    }

    public Result<MemberType> addMemberType(MemberType m) {
        int memberTypeId = memberTypeRepo.addMemberType(m);
        if(memberTypeId == 0){
            return new Result<>(400, "Error in adding memberTypeId.");
        }else{
            m.setMemberTypeId(memberTypeId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, m);
        }
    }

    public Result<MemberType> updateMemberType(int memberTypeId, MemberType m) {
        int n = memberTypeRepo.updateMemberType(memberTypeId, m);
        if(n > 0){
            return new Result<>(200, m);
        }
        else{
            return new Result<>(400, "Unable to Update. Given memberType id : " + memberTypeId   + " not found.");
        }
    }

    public Result<MemberType> deleteMemberType(int memberTypeId) {
        int n = memberTypeRepo.deleteMemberType(memberTypeId);
        if(n > 0){
            return new Result<>(200, "MemberType with id : " + memberTypeId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given memberType id : " + memberTypeId + " not found.");
        }
    }
}
