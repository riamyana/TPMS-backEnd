package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofRequirement;
import com.trasportManagement.transportservice.repository.ProofRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProofService {
    @Autowired
    @Qualifier("proofRepo")
    ProofRepo proofRepo;

    public List<Proof> findAllProofs(){
        List<Proof> proofList = proofRepo.findAllProofs();
        if(proofList.isEmpty()){
            throw  new TPMSCustomException("No proofs found.", HttpStatus.NOT_FOUND);
        }

        return proofList;
    }

    public List<Proof> findProofsByMemberTypeId(int memberTypeId) {
        List<Proof> proofList = proofRepo.findProofsByMemberTypeId(memberTypeId);
        if(proofList.isEmpty()) {
            throw new TPMSCustomException("No proofs found for MemberType ID : " + memberTypeId, HttpStatus.NOT_FOUND);
        }

        return proofList;
    }

    public Proof addProof(Proof p) {
        int n = proofRepo.addProof(p);
        p.setProofId(n);
        if(n == 0){
            throw new TPMSCustomException("No record inserted for this Proof", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        p.setProofId(n);
        return p;
    }

    public Proof updateProof(int proofId, Proof p) {
        int n = proofRepo.updateProof(proofId, p);
        p.setProofId(proofId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given proof id : " + proofId   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deleteProof(int proofId) {
        if(!proofRepo.deleteProof(proofId)){
            throw new TPMSCustomException("Unable to delete. Given proof id : " + proofId + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    public ProofRequirement addProofRequirement(ProofRequirement p) {
        int n = proofRepo.addProofRequirement(p);
        if(n == 0){
            throw new TPMSCustomException("No record inserted for this Proof Requirement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        p.setId(n);
        return p;
    }

    public List<ProofRequirement> findAllProofRequirement(){
        List<ProofRequirement> proofList = proofRepo.findAllProofRequirement();
        if(proofList.isEmpty()){
            throw  new TPMSCustomException("No Proofs Requirements found.", HttpStatus.NOT_FOUND);
        }

        return proofList;
    }

    public ProofRequirement updateProofRequirement(int id, ProofRequirement p) {
        int n = proofRepo.updateProofRequirement(id, p);
        p.setId(id);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given proof requirement id : " + id   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deleteProofRequirement(int id) {
        if(!proofRepo.deleteProofRequirement(id)){
            throw new TPMSCustomException("Unable to delete. Given proof requirement id : " + id + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

}
