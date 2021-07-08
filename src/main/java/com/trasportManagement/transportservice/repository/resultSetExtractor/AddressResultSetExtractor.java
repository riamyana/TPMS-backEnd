package com.trasportManagement.transportservice.repository.resultSetExtractor;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressResultSetExtractor implements ResultSetExtractor<List<MemberWithAddress>> {
    @Override
    public List<MemberWithAddress> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<MemberWithAddress> memberList = new ArrayList<MemberWithAddress>();
        Map<Integer, MemberWithAddress> memberMap = new HashMap<Integer, MemberWithAddress>();
        while(resultSet.next())
        {
            Integer memberId = resultSet.getInt("memberId");
            MemberWithAddress member = memberMap.get(memberId);
            if(member == null)
            {
                //Create object
                member = new MemberWithAddress();
                // Set Object properties
                member.setMemberId(resultSet.getInt("memberId"));
                member.setMemberTypeId(resultSet.getInt("memberTypeId"));
                member.setFirstName(resultSet.getString("firstName"));
                member.setLastName(resultSet.getString("lastName"));
                member.setGender(resultSet.getString("gender"));
                member.setMobileNo(resultSet.getString("mobileNo"));
                member.setDob(resultSet.getDate("dob"));
                member.setAddressList(new ArrayList<Address>());
                //Add object to Map and List
                memberMap.put(memberId, member);
                memberList.add(member);
            }

            Address address = new Address();
            address.setAddressId(resultSet.getInt("addressId"));
            address.setAddLine1(resultSet.getString("addLine1"));
            address.setAddLine2(resultSet.getString("addLine2"));
            address.setCity(resultSet.getString("city"));
            address.setZipCode(resultSet.getString("zipCode"));

            member.getAddressList().add(address);
        }
        return memberList;
    }
}
