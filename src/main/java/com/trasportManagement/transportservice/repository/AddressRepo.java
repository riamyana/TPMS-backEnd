package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Address;

import java.util.List;

public interface AddressRepo {
    List<Address> findAllMembersAddress();

    List<Address> findMemberAddressById(int memberId);

    int addAddress(Address a);

    int updateAddress(int addressId,int memberId, Address a);

    int deleteAddress(int addressId,int memberId);

}
