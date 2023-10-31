package org.glebchanskiy.controller;

import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Entity;
import org.glebchanskiy.model.Race;
import org.glebchanskiy.requester.Requester;
import org.glebchanskiy.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CrudTemplateController<T extends Entity> extends TemplateController {
    public CrudTemplateController(String route, Class<T> clazz) {
        super(route);
        this.entityModelName = route.substring(1);
        this.requester = new Requester<>(getRoute(), clazz);
        this.clazz = clazz;
    }

    private final Requester<T> requester;
    private final String entityModelName;
    private final Class<T> clazz;

    @Override
    public Response getMapping(Request request) {
        try {
            model.put(entityModelName, requester.findAll());
            return template(getRoute() + ".html");
        } catch (RuntimeException e) {
            System.out.println("KEK - " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Response postMapping(Request request) {
        System.out.println("REQUEST: " + request);
        var formData = request.formData();

        String name = formData.get("name");
        String description = formData.get("description");
        if (name != null && description != null) {
            System.out.println("ON CREATE");
            T entity = null;
            try {
                entity = clazz.newInstance();
                entity.setName(name);
                entity.setDescription(description);
                requester.insert(entity);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        String deleteId = formData.get("deleteId");

        String updateId = formData.get("updateId");
        String updateName = formData.get("updateName");
        String updateDescription = formData.get("updateDescription");

        if (deleteId != null) {
            System.out.println("ON DELETE");
            requester.deleteById(Integer.parseInt(deleteId));
        }

        System.out.println("updateName: " + updateName);
        System.out.println("updateDescription: " + updateDescription);

        if (updateName != null && updateDescription != null && updateId != null) {
            try {
                Constructor<?>[] k = clazz.getConstructors();
                int id = Integer.parseInt(updateId);
                Object obj = k[0].newInstance(id, updateName, updateDescription);
                T entity =  (T) obj;
                requester.update(id, entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        model.put(entityModelName, requester.findAll());

        return template(getRoute() + ".html");
    }
}
