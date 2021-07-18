package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypePackageDTO;
import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberTypePackageExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, MemberTypePackageDTO> memberPackageMap = new HashMap<>();

        while(rs.next()){
            int id = rs.getInt("id");
            MemberTypePackageDTO mpt = memberPackageMap.get(id);
            if(mpt == null){
                mpt = new MemberTypePackageDTO();
                mpt.setId(rs.getInt("id"));
                mpt.setName(rs.getString("name"));
                mpt.setTransportMode(rs.getString("transportMode"));
                mpt.setSubscriptionType(rs.getString("subscriptionType"));
                mpt.setCounts(rs.getInt("counts"));
                mpt.setValidity(rs.getInt("validity"));
                mpt.setBalance(rs.getInt("balance"));
                mpt.setPrice(rs.getInt("price"));
                memberPackageMap.put(id, mpt);
            }

            List memberTypePackagesList = mpt.getMemberTypePackages();

            if(memberTypePackagesList == null){
                memberTypePackagesList = new ArrayList<Package>();
                mpt.setMemberTypePackages(memberTypePackagesList);
            }

            MemberTypePackage mp = new MemberTypePackage();
            mp.setId(rs.getInt("memberPacakgeId"));
            mp.setPackageId(rs.getInt("packageId"));
            mp.setMemberTypeId(rs.getInt("memberTypeId"));
            mp.setDiscountStartDate(rs.getDate("discountStartDate"));
            mp.setDiscountEndDate(rs.getDate("discountEndDate"));
            mp.setDiscountPercentage(rs.getInt("discountPercentage"));
            mp.setDiscountDescription(rs.getString("discountDescription"));

            memberTypePackagesList.add(mp);
        }
        return new ArrayList<MemberTypePackageDTO>(memberPackageMap.values());
    }
}
