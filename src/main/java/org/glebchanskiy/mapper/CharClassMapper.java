package org.glebchanskiy.mapper;

import org.glebchanskiy.model.Class;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharClassMapper implements RowMapper<Class> {
    @Override
    public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
        Class myObject = new Class();
        myObject.setId(rs.getInt("id"));
        myObject.setName(rs.getString("name"));
        myObject.setDescription(rs.getString("description"));
        return myObject;
    }
}
