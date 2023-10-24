package org.glebchanskiy.mapper;

import org.glebchanskiy.model.Equip;
import org.glebchanskiy.model.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipsMapper implements RowMapper<Equip> {
    @Override
    public Equip mapRow(ResultSet rs, int rowNum) throws SQLException {
        Equip myObject = new Equip();
        myObject.setId(rs.getInt("id"));
        myObject.setName(rs.getString("name"));
        myObject.setDescription(rs.getString("description"));
        return myObject;
    }
}
