package org.glebchanskiy.controller;

import org.glebchanskiy.dao.CharClassDAO;
import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Class;


public class ClassController extends TemplateController {
    public ClassController(String route, CharClassDAO classDAO) {
        super(route);
        this.classDAO = classDAO;
    }

    private final CharClassDAO classDAO;

    @Override
    public Response getMapping(Request request) {
        var cls = classDAO.findAll();
        model.put("classes", cls);
        System.out.println("classDAO.findAll(): " + cls);
        return template("/class.html");
    }

    @Override
    public Response postMapping(Request request) {
        var formData = request.formData();

        String name = formData.get("name");
        String description = formData.get("description");
        if (name != null && description != null) {
            classDAO.insert(new Class(name, description));
        }

        String deleteName = formData.get("deleteName");

        String updateName = formData.get("updateName");
        String updateDescription = formData.get("updateDescription");

        if (deleteName != null) {
            System.out.println("DELETE = " + deleteName);
            classDAO.deleteByName(deleteName);
        }

        if (updateName != null && updateDescription != null) {
            System.out.println("UPDATE = " + updateName);
            classDAO.updateByName(updateName, updateDescription);
        }

        var cls = classDAO.findAll();
        model.put("classes", cls);

        return template("/class.html");
    }
}
