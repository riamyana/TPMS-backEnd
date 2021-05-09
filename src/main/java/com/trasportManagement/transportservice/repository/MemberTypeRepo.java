package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTypeRepo {

    List<MemberType> findAllMemberTypes();

    List<MemberType> findMemberTypeById(int memberTypeId);

    int addMemberType(MemberType m);

    int updateMemberType(int memberTypeId,MemberType m);

    boolean deleteMemberType(int memberTypeId);
}
