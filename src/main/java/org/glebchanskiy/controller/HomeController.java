package org.glebchanskiy.controller;

import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;

public class HomeController extends TemplateController {
    public HomeController(String route) {
        super(route);
    }


    @Override
    public Response getMapping(Request request) {
        return template("/index.html");
    }

    @Override
    public Response postMapping(Request request) {
        return template("/index.html");
    }
}
