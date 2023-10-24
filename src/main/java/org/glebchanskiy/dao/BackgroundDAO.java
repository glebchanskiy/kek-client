package org.glebchanskiy.dao;

import org.glebchanskiy.mapper.BackgroundMapper;
import org.glebchanskiy.mapper.EquipsMapper;
import org.glebchanskiy.mapper.RaceMapper;
import org.glebchanskiy.model.Background;
import org.glebchanskiy.model.Equip;
import org.glebchanskiy.model.Race;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BackgroundDAO {
    private final JdbcTemplate jdbcTemplate;

    public BackgroundDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Background race) {
        this.jdbcTemplate.update(
                "INSERT INTO background (name, description) VALUES (?,?)", race.getName(), race.getDescription());
    }

    public void deleteByName(String name) {
        this.jdbcTemplate.update(
                "DELETE FROM background WHERE name = ?", name);
    }

    public void updateByName(String name, String description) {
        this.jdbcTemplate.update(
                "UPDATE background SET description = ?  WHERE name = ?", description, name);
    }

    public Background findById(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM background WHERE id = ?", Background.class, id);
    }

    public Background findByName(String name) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM background WHERE name = ?", new Object[] {name}, new BackgroundMapper());
    }

    public List<Background> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM background", new BackgroundMapper());
    }
}
