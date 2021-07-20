package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="graphRepoImpl")
public class GraphRepoImpl implements GraphRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Graph> lastYearPackages() {
        final String SQL = "SELECT tbl.name, tbl.y from( SELECT p.name as name,count(*) as y FROM EnrolledPackage as ep, Package as p WHERE YEAR(start) = YEAR(CURDATE()) - 1 AND p.id = ep.packageId GROUP BY packageId) tbl ORDER BY `tbl`.`y` DESC LIMIT 5";

        List<Graph> graphList = jdbcTemplate.query(SQL, (rs, i) ->
                new Graph(
                        rs.getString("name"),
                        rs.getInt("y")
                )
        );

        return graphList;
    }

    @Override
    public List<Graph> lastYearTransportPackages() {
        final String SQL = "SELECT tbl.name, tbl.y from (SELECT p.transportMode as name, COUNT(*) as y FROM EnrolledPackage AS ep, Package AS p where ep.packageId = p.id AND YEAR(start) = YEAR(CURDATE()) - 1 GROUP BY p.transportMode) as tbl ORDER BY y DESC LIMIT 5";

        List<Graph> graphList = jdbcTemplate.query(SQL, (rs, i) ->
                new Graph(
                        rs.getString("name"),
                        rs.getInt("y")
                )
        );

        return graphList;
    }
}
