package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    @Qualifier("addressRepo")
    AddressRepo addressRepo;

    public List<Address> findAllMembersAddress(){
        List<Address> addList = addressRepo.findAllMembersAddress();

        if(addList.isEmpty()){
            throw  new TPMSCustomException("No member's address found.", HttpStatus.NOT_FOUND);
        }

        return  addList;
    }

    public List<Address> findMemberAddressById(int memberId){
        List<Address> addList = addressRepo.findMemberAddressById(memberId);

        if(addList.isEmpty()){
            throw  new TPMSCustomException("No Address found for Member Id :", HttpStatus.NOT_FOUND);
        }

        return addList;
    }

    public Address addAddress(Address a) {
        int n = addressRepo.addAddress(a);

        if (n == 0) {
            throw new TPMSCustomException("No record inserted of this address", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return a;
    }

    public Address updateAddress(int memberId,int addressId,Address a) {
        int n = addressRepo.updateAddress(memberId,addressId,a);

        a.setAddressId(addressId);
        a.setMemberId(memberId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update the member's address. Given Address id : " + addressId   + " not found.", HttpStatus.NOT_FOUND);
        }

        return a;
    }

    public Boolean deleteAddress(int memberId,int addressId) {
        if(!addressRepo.deleteAddress(memberId,addressId)){
            throw new TPMSCustomException("Unable to delete member's address. Given address id : " + addressId + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }
}
