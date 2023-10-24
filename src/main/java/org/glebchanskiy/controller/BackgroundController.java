package org.glebchanskiy.controller;

import org.glebchanskiy.dao.BackgroundDAO;
import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Background;

public class BackgroundController extends TemplateController {
    public BackgroundController(String route, BackgroundDAO backgroundDAO) {
        super(route);
        this.backgroundDAO = backgroundDAO;
    }

    private final BackgroundDAO backgroundDAO;

    @Override
    public Response getMapping(Request request) {
        var cls = backgroundDAO.findAll();
        model.put("backgrounds", cls);
        return template("/background.html");
    }

    @Override
    public Response postMapping(Request request) {
        var formData = request.formData();

        String name = formData.get("name");
        String description = formData.get("description");
        if (name != null && description != null) {
            backgroundDAO.insert(new Background(name, description));
        }

        String deleteName = formData.get("deleteName");

        String updateName = formData.get("updateName");
        String updateDescription = formData.get("updateDescription");

        if (deleteName != null) {
            System.out.println("DELETE = " + deleteName);
            backgroundDAO.deleteByName(deleteName);
        }

        if (updateName != null && updateDescription != null) {
            System.out.println("UPDATE = " + updateName);
            backgroundDAO.updateByName(updateName, updateDescription);
        }

        var cls = backgroundDAO.findAll();
        model.put("backgrounds", cls);

        return template("/background.html");
    }
}
