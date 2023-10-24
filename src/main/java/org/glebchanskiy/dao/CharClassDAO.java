package org.glebchanskiy.dao;

import org.glebchanskiy.mapper.CharClassMapper;
import org.glebchanskiy.mapper.RaceMapper;
import org.glebchanskiy.model.Class;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CharClassDAO {

    private final JdbcTemplate jdbcTemplate;

    public CharClassDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Class charClass) {
        this.jdbcTemplate.update(
                "INSERT INTO class (name, description) VALUES (?,?)", charClass.getName(), charClass.getDescription());
    }

    public void deleteByName(String name) {
        this.jdbcTemplate.update(
                "DELETE FROM class WHERE name = ?", name);
    }

    public void updateByName(String name, String description) {
        this.jdbcTemplate.update(
                "UPDATE class SET description = ?  WHERE name = ?", description, name);
    }

    public Class findById(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM class WHERE id = ?", Class.class, id);
    }

    public Class findByName(String name) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM class WHERE name = ?", new Object[] {name}, new CharClassMapper());
    }



    public List<Class> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM class", new CharClassMapper());
    }
}
