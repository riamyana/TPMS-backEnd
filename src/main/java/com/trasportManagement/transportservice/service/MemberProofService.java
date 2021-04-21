package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.MemberProofRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberProofService {

    @Autowired
    @Qualifier("memberProofRepo")
    MemberProofRepo memberProofRepo;

    public Result<MemberProof> addMemberProof(MemberProof mp) {
        int memberProofId = memberProofRepo.addMemberProof(mp);
        if(memberProofId == 0){
            return new Result<>(400, "Error in adding member's Proof data.");
        }else{
            mp.setMemProofId(memberProofId);
            return new Result<>(201, mp);
        }
    }

    public Result<List<MemberProofsWithMemberDetails>> findAllMembersProofDetails() {
        List<MemberProofsWithMemberDetails> memberProofDetailList = memberProofRepo.findAllMembersProofDetails();
        if(memberProofDetailList.size() > 0){
            return new Result<>(200, memberProofDetailList);
        }
        else{
            return new Result<>(400, "No Member's proofs details found.");
        }
    }

    public Result<List<MemberProofsWithMemberDetails>> findMemberProofById(int memberId) {
        List<MemberProofsWithMemberDetails> memberProofList = memberProofRepo.findMemberProofById(memberId);
        if(memberProofList.size() > 0){
            return new Result<>(200, memberProofList);
        }
        else{
            return new Result<>(400, "No proofs found for member id :"+memberId);
        }
    }
}
