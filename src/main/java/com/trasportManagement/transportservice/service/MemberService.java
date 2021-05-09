package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.MemberRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    @Qualifier("memberRepo")
    MemberRepo memberRepo;

    public List<MemberWithMemberType> findAllMembers(){

        List<MemberWithMemberType> memberList = memberRepo.findAllMembers();

        if(memberList.isEmpty()){
            throw  new TPMSCustomException("No member found.", HttpStatus.NOT_FOUND);
        }

        return memberList;
    }

    //single data
    public List<MemberWithMemberType> findMemberById(int memberId){
        List<MemberWithMemberType> memberList = memberRepo.findMemberById(memberId);

        if(memberList.isEmpty()){
            throw  new TPMSCustomException("No subscription type found", HttpStatus.NOT_FOUND);
        }

        return  memberList;
    }

    public Member addMember(Member m) {
        int n = memberRepo.addMember(m);
        if(n == 0){
            throw new TPMSCustomException("No record inserted of this Member", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return m;
    }

    public Member updateMember(int memberId, Member m) {
        int n = memberRepo.updateMember(memberId, m);

        m.setMemberId(memberId);

        if(n == 0){
            throw  new TPMSCustomException("Unable to update. Given member id : " + memberId   + " not found.", HttpStatus.NOT_FOUND);
        }

        return m;
    }

    public Boolean deleteMember(int memberId) {
        if(!memberRepo.deleteMember(memberId)){
            throw new TPMSCustomException("Unable to delete. Given member id : " + memberId + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

}
