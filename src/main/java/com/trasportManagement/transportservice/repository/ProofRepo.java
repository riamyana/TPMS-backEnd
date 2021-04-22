package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProofRepo {
    List<ProofWithMemberType> findAllProofs();
    int addProof(Proof p);

    int updateProof(int proofId,Proof p);

    int deleteProof(int proofId);

    List<Proof> findProofsByMemberTypeId(int memberTypeId);
}
