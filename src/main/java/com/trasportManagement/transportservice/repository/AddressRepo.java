package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Address;

import java.util.List;

public interface AddressRepo {
    List<Address> findAllMembersAddress();

    List<Address> findMemberAddressById(int memberId);

    int addAddress(Address a);

    int updateAddress(int memberId,int addressId,Address a);

    int deleteAddress(int memberId,int addressId);

}
