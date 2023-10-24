package org.glebchanskiy.controller;

import org.glebchanskiy.dao.RaceDAO;
import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Race;

public class RaceController extends TemplateController {
    public RaceController(String route, RaceDAO raceDAO) {
        super(route);
        this.raceDAO = raceDAO;
    }

    private final RaceDAO raceDAO;

    @Override
    public Response getMapping(Request request) {
        var cls = raceDAO.findAll();
        model.put("races", cls);
        System.out.println("classDAO.findAll(): " + cls);
        return template("/race.html");
    }

    @Override
    public Response postMapping(Request request) {
        var formData = request.formData();

        String name = formData.get("name");
        String description = formData.get("description");
        if (name != null && description != null) {
            raceDAO.insert(new Race(name, description));
        }

        String deleteName = formData.get("deleteName");

        String updateName = formData.get("updateName");
        String updateDescription = formData.get("updateDescription");

        if (deleteName != null) {
            System.out.println("DELETE = " + deleteName);
            raceDAO.deleteByName(deleteName);
        }

        if (updateName != null && updateDescription != null) {
            System.out.println("UPDATE = " + updateName);
            raceDAO.updateByName(updateName, updateDescription);
        }

        var cls = raceDAO.findAll();
        model.put("races", cls);

        return template("/race.html");
    }
}
