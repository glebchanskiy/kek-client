package org.glebchanskiy.mapper;

import org.glebchanskiy.model.Background;
import org.glebchanskiy.model.Char;
import org.glebchanskiy.model.Class;
import org.glebchanskiy.model.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CharMapper implements RowMapper<Char> {
    @Override
    public Char mapRow(ResultSet rs, int rowNum) throws SQLException {
//        System.out.println("rowNum: " + rowNum);
//
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        while (rs.next()) {
//            for (int i = 1; i <= columnsNumber; i++) {
//                if (i > 1) System.out.print(",  ");
//                String columnValue = rs.getString(i);
//                System.out.print(columnValue + " " + rsmd.getColumnName(i));
//            }
//            System.out.println("");
//        }

        Char myObject = new Char();
        myObject.setId(rs.getInt(1));
        myObject.setName(rs.getString(2));
        myObject.setDescription(rs.getString(6));
        myObject.setBackground(new Background(rs.getString(8), rs.getString(9)));
        myObject.setCharClass(new Class(rs.getString(11), rs.getString(12)));
        myObject.setRace(new Race(rs.getString(14), rs.getString(14)));
        System.out.println("Char: \n" + myObject);
        return myObject;
    }
}
