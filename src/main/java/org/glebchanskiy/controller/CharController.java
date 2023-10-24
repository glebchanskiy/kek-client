package org.glebchanskiy.controller;

import org.glebchanskiy.dao.*;
import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Char;

public class CharController extends TemplateController {
    public CharController(String route, CharDAO charDAO, RaceDAO raceDAO, CharClassDAO charClassDAO, BackgroundDAO backgroundDAO) {
        super(route);
        this.charDAO = charDAO;
        this.raceDAO = raceDAO;
        this.charClassDAO = charClassDAO;
        this.backgroundDAO = backgroundDAO;
    }

    private final CharDAO charDAO;
    private final RaceDAO raceDAO;
    private final CharClassDAO charClassDAO;
    private final BackgroundDAO backgroundDAO;

    @Override
    public Response getMapping(Request request) {
        var cls = charDAO.findAll();
        model.put("chars", cls);
        System.out.println("classDAO.findAll(): " + cls);
        return template("/char.html");
    }

    @Override
    public Response postMapping(Request request) {
        var formData = request.formData();

        String name = formData.get("name");
        String race = formData.get("race");
        String charClass = formData.get("class");
        String description = formData.get("description");
        String background = formData.get("background");

        if (name != null && description != null && race != null && background != null && charClass != null) {
            Char character = new Char();

            character.setRace(raceDAO.findByName(race));
            character.setCharClass(charClassDAO.findByName(charClass));
            character.setBackground(backgroundDAO.findByName(background));
            character.setDescription(description);
            character.setName(name);
            charDAO.insert(character);
        }

        String deleteName = formData.get("deleteName");

        if (deleteName != null) {
            System.out.println("DELETE = " + deleteName);
            charDAO.deleteByName(deleteName);
        }

        String updateName = formData.get("updateName");
        String updateRace = formData.get("updateRace");
        String updateClass = formData.get("updateClass");
        String updateDescription = formData.get("updateDescription");
        String updateBackground = formData.get("updateBackground");

        if (updateName != null && updateRace != null && updateClass != null && updateDescription != null && updateBackground != null) {
            Char character = new Char();

            character.setRace(raceDAO.findByName(updateRace));
            character.setCharClass(charClassDAO.findByName(updateClass));
            character.setBackground(backgroundDAO.findByName(updateBackground));
            character.setDescription(updateDescription);
            character.setName(updateName);

            charDAO.updateByName(character);
        }

        var cls = charDAO.findAll();
        model.put("chars", cls);

        return template("/char.html");
    }
}
