package com.trasportManagement.transportservice.repository.resultSetExtractor;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageByTransportExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, PackageByTransportDTO> packageMap = new HashMap<>();

        while(rs.next()){
            int id = rs.getInt("transportId");
            PackageByTransportDTO pt = packageMap.get(id);
            if(pt == null){
                pt = new PackageByTransportDTO();
                pt.setId(rs.getInt("transportId"));
                pt.setName(rs.getString("transportMode"));
                packageMap.put(id, pt);
            }

            List packageList = pt.getPackageList();

            if(packageList == null){
                packageList = new ArrayList<Package>();
                pt.setPackageList(packageList);
            }

            Package p = new Package();
            p.setId(rs.getInt("packageId"));
            p.setName(rs.getString("packageName"));
            p.setValidity(rs.getInt("validity"));
            p.setBalance(rs.getInt("balance"));
            p.setPrice(rs.getInt("price"));

            packageList.add(p);
        }

//        System.out.println(packageMap.values());
        return new ArrayList<PackageByTransportDTO>(packageMap.values());
    }
}
