package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.MemberRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    @Qualifier("memberRepo")
    MemberRepo memberRepo;

    public Result<List<MemberWithMemberType>> findAllMembers(){
        List<MemberWithMemberType> memberList = memberRepo.findAllMembers();
        if(memberList.size() > 0){
            return new Result<>(200, memberList);
        }
        else{
            return new Result<>(400, "No member found.");
        }
    }

    public Result<MemberWithMemberType> findMemberById(int memberId){
        List<MemberWithMemberType> memberList = memberRepo.findMemberById(memberId);
        if(memberList.size() > 0){
            return new Result<>(200, memberList.get(0));
        }
        else{
            return new Result<>(400, "No member found.");
        }
    }

    public Result<Member> addMember(Member m) {
        int memberId = memberRepo.addMember(m);
        if(memberId == 0){
            return new Result<>(400, "Error in adding member.");
        }else{
            m.setMemberId(memberId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, m);
        }
    }

    public Result<Member> updateMember(int memberId, Member m) {
        int n = memberRepo.updateMember(memberId, m);
        if(n > 0){
            return new Result<>(200, m);
        }
        else{
            return new Result<>(400, "Unable to update. Given member id : " + memberId   + " not found.");
        }
    }

    public Result<Member> deleteMember(int memberId) {
        int n = memberRepo.deleteMember(memberId);
        if(n > 0){
            return new Result<>(200, "Member with id : " + memberId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given member id : " + memberId + " not found.");
        }
    }
}
