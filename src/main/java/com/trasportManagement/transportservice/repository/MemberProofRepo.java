package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;

import java.util.List;

public interface MemberProofRepo {

    int addMemberProof(MemberProof mp);

    List<MemberProofsWithMemberDetails> findAllMembersProofDetails();

    List<MemberProofsWithMemberDetails> findMemberProofById(int memberId);
}
