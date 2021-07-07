package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofRequirement;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProofRepo {
    List<Proof> findAllProofs();

    int addProof(Proof p);

    int updateProof(int proofId,Proof p);

    boolean deleteProof(int proofId);

    List<Proof> findProofsByMemberTypeId(int memberTypeId);

    int addProofRequirement(ProofRequirement p);

    List<ProofRequirement> findAllProofRequirement();

    int updateProofRequirement(int id, ProofRequirement p);

    boolean deleteProofRequirement(int id);
}
