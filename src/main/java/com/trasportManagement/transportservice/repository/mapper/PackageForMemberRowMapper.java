package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.PackageForMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageForMemberRowMapper implements RowMapper<PackageForMember> {
    @Override
    public PackageForMember mapRow(ResultSet rs, int i) throws SQLException {
        PackageForMember pm = new PackageForMember();
        pm.setId(rs.getInt("id"));
        pm.setMemberTypeId(rs.getInt("memberTypeId"));
        pm.setName(rs.getString("name"));
        pm.setTransportMode(rs.getString("transportMode"));
        pm.setSubscriptionType(rs.getString("subscriptionType"));
        pm.setCounts(rs.getInt("counts"));
        pm.setValidity(rs.getInt("validity"));
        pm.setBalance(rs.getInt("balance"));
        pm.setPrice(rs.getInt("price"));
        pm.setMemberPackgeId(rs.getInt("memberPackageId"));
        pm.setDiscountStartDate(rs.getDate("discountStartDate"));
        pm.setDiscountEndDate(rs.getDate("discountEndDate"));
        pm.setDiscountPercentage(rs.getInt("discountPercentage"));
        pm.setDiscountDescription(rs.getString("discountDescription"));
        return pm;
    }
}
