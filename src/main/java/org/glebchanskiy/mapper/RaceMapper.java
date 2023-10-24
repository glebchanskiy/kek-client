package org.glebchanskiy.mapper;

import org.glebchanskiy.model.Class;
import org.glebchanskiy.model.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaceMapper implements RowMapper<Race> {
    @Override
    public Race mapRow(ResultSet rs, int rowNum) throws SQLException {
        Race myObject = new Race();
        myObject.setId(rs.getInt("id"));
        myObject.setName(rs.getString("name"));
        myObject.setDescription(rs.getString("description"));
        return myObject;
    }
}
