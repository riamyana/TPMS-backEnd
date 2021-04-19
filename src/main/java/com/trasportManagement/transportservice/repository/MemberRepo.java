package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.model.MemberWithMemberType;

import java.util.List;

public interface MemberRepo {

    List<MemberWithMemberType> findAllMembers();

    List<MemberWithMemberType> findMemberById(int memberId);

    int addMember(Member m);

    int updateMember(int memberId,Member m);

    int deleteMember(int memberId);
}
