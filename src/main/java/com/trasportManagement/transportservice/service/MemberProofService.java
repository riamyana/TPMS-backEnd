package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.MemberProofRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberProofService {

    @Autowired
    @Qualifier("memberProofRepo")
    MemberProofRepo memberProofRepo;

    public MemberProof addMemberProof(MemberProof mp) {
        int n =memberProofRepo.addMemberProof(mp);

        if (n == 0) {
            throw new TPMSCustomException("No record inserted of Member proof", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return mp;
    }

    public List<MemberProofsWithMemberDetails> findAllMembersProofDetails() {
        List<MemberProofsWithMemberDetails> memberProofList = memberProofRepo.findAllMembersProofDetails();
        if(memberProofList.isEmpty()){
            throw  new TPMSCustomException("No Member's proofs details found.", HttpStatus.NOT_FOUND);
        }

        return memberProofList;
    }

    public List<MemberProofsWithMemberDetails> findMemberProofById(int memberId) {
        List<MemberProofsWithMemberDetails> memberProofList = memberProofRepo.findMemberProofById(memberId);

        if(memberProofList.isEmpty()){
            throw  new TPMSCustomException("No proofs found for member id :"+memberId, HttpStatus.NOT_FOUND);
        }

        return  memberProofList;
    }
}
