package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.repository.AddressRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    @Qualifier("addressRepo")
    AddressRepo addressRepo;

    public Result<List<Address>> findAllMembersAddress(){
        List<Address> addList = addressRepo.findAllMembersAddress();
        if(addList.size() > 0){
            return new Result<>(200, addList);
        }
        else{
            return new Result<>(400, "No member's address found.");
        }
    }

    public Result<List<Address>> findMemberAddressById(int memberId){
        List<Address> addList = addressRepo.findMemberAddressById(memberId);
        if(addList.size() > 0){
            return new Result<>(200, addList);
        }
        else{
            return new Result<>(400, "No Address found for Member Id :" + memberId);
        }
    }

    public Result<Address> addAddress(Address a) {
        int addressId = addressRepo.addAddress(a);
        if(addressId == 0){
            return new Result<>(400, "Error in adding address of member.");
        }else{
            a.setAddressId(addressId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, a);
        }
    }

    public Result<Address> updateAddress(int addressId,int memberId, Address a) {
        int n = addressRepo.updateAddress(addressId,memberId,a);
        if(n > 0){
            return new Result<>(200, a);
        }
        else{
            return new Result<>(400, "Unable to update the member's address. Given Address id : " + addressId   + " not found.");
        }
    }

    public Result<Address> deleteAddress(int addressId,int memberId) {
        int n = addressRepo.deleteAddress(addressId,memberId);
        if(n > 0){
            return new Result<>(200, "Address of member with address id : " + addressId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete member's address. Given address id : " + addressId + " not found.");
        }
    }
}
