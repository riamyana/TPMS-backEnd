package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.model.MemberWithMemberType;

import java.util.List;

public interface MemberRepo {

    List<MemberWithMemberType> findAllMembers();

    List<MemberWithMemberType> findMemberById(int memberId);

    int addMember(Member m);

    int updateMember(int memberId,Member m);

    boolean deleteMember(int memberId);

    List<Member> findMembers();

    List<MemberWithAddress> findMemberWithAddress();

    List<Member> findMemberByUserId(int userId);

    List<Member> findMembersWithPassRequest();

    int changePassRequestStatus(int memberId, int status, String description);

    int coutsOfStatus(int status);
}
