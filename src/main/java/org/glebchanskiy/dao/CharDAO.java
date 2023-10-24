package org.glebchanskiy.dao;

import org.glebchanskiy.mapper.CharMapper;
import org.glebchanskiy.mapper.EquipsMapper;
import org.glebchanskiy.model.Char;
import org.glebchanskiy.model.Equip;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CharDAO {
    private final JdbcTemplate jdbcTemplate;

    public CharDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Char character) {
        System.out.println("character: " + character);
        this.jdbcTemplate.update(
                "INSERT INTO character (name, description, background_id, class_id, race_id) VALUES (?,?,?,?,?)",
                character.getName(),
                character.getDescription(),
                character.getBackground().getId(),
                character.getCharClass().getId(),
                character.getRace().getId());
    }

    public void deleteByName(String name) {
        this.jdbcTemplate.update(
                "DELETE FROM character WHERE name = ?", name);
    }

    public void updateByName(Char character) {
        this.jdbcTemplate.update(
                "UPDATE character SET description = ?, race_id = ?, class_id = ?, background_id = ? WHERE name = ?",
                character.getDescription(),
                character.getRace().getId(),
                character.getCharClass().getId(),
                character.getBackground().getId(),
                character.getName());
    }


    public Char findById(int id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM character WHERE id = ?", Char.class, id);
    }

    public List<Equip> findEquipsByCharId(int id) {
        return this.jdbcTemplate.query("SELECT e.id, e.name, e.description FROM equipment as e JOIN public.characterequipment c on e.id = c.equipment_id WHERE character_id = ?", new Object[] {id}, new EquipsMapper());
    }




    public List<Char> findAll() {
        var list = this.jdbcTemplate.query(
                "SELECT * FROM character JOIN public.background b on character.background_id = b.id JOIN public.class c on character.class_id = c.id JOIN public.race r on r.id = character.race_id", new CharMapper());
        System.out.println(list);
        for (Char c : list) {
            c.setEquips(findEquipsByCharId(c.getId()));
        }
        return list;
    }
}
