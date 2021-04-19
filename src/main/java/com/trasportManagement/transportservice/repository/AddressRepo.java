package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Address;

import java.util.List;

public interface AddressRepo {
    List<Address> findAllMembersAddress();

    List<Address> findMemberAddressById(int memberId);

    int addAddress(Address a);

    //int updateAddress(int memberId,Member m);

    //int deleteMember(int memberId);

}
