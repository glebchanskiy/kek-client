package org.glebchanskiy.mapper;

import org.glebchanskiy.model.Background;
import org.glebchanskiy.model.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BackgroundMapper implements RowMapper<Background> {
    @Override
    public Background mapRow(ResultSet rs, int rowNum) throws SQLException {
        Background myObject = new Background();
        myObject.setId(rs.getInt("id"));
        myObject.setName(rs.getString("name"));
        myObject.setDescription(rs.getString("description"));
        return myObject;
    }
}
