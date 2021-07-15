
package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.repository.MemberTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTypeService {

    @Autowired
    @Qualifier("memberTypeRepo")
    MemberTypeRepo memberTypeRepo;

    public List<MemberType> findAllMemberTypes() {
        List<MemberType> memberTypeList = memberTypeRepo.findAllMemberTypes();
//        System.out.println("Size : " + memberList.size());
        if(memberTypeList.isEmpty()){
            throw  new TPMSCustomException("No MemberType found.", HttpStatus.NOT_FOUND);
        }

        return  memberTypeList;
    }

    public List<MemberType> findMemberTypeById(int memberTypeId) {
        List<MemberType> memberTypeList = memberTypeRepo.findMemberTypeById(memberTypeId);
        if(memberTypeList.isEmpty()) {
            throw new TPMSCustomException("No MemberType found with memberTypeId : " + memberTypeId, HttpStatus.NOT_FOUND);
        }

        return  memberTypeList;
    }

    public MemberType addMemberType(MemberType m) {
        int n = memberTypeRepo.addMemberType(m);
        m.setMemberTypeId(n);
        if(n == 0){
            throw new TPMSCustomException("No record inserted of this Member type", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return m;
    }

    public MemberType updateMemberType(int memberTypeId, MemberType m) {
        int n = memberTypeRepo.updateMemberType(memberTypeId, m);
        m.setMemberTypeId(memberTypeId);

        if(n == 0){
            throw  new TPMSCustomException("Unable to Update. Given memberType id : " + memberTypeId   + " not found.", HttpStatus.NOT_FOUND);
        }

        return m;
    }

    public Boolean deleteMemberType(int memberTypeId) {
        if(!memberTypeRepo.deleteMemberType(memberTypeId)){
            throw new TPMSCustomException("Unable to delete. Given memberType id : " + memberTypeId + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

}
