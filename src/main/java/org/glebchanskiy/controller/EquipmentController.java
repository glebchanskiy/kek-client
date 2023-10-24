package org.glebchanskiy.controller;

import org.glebchanskiy.dao.EquipmentDAO;
import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.Equip;

public class EquipmentController extends TemplateController {
    public EquipmentController(String route, EquipmentDAO equipmentDAO) {
        super(route);
        this.equipmentDAO = equipmentDAO;
    }

    private final EquipmentDAO equipmentDAO;

    @Override
    public Response getMapping(Request request) {
        var cls = equipmentDAO.findAll();
        model.put("equips", cls);
        System.out.println("classDAO.findAll(): " + cls);
        return template("/equip.html");
    }

    @Override
    public Response postMapping(Request request) {
        var formData = request.formData();

        String name = formData.get("name");
        String description = formData.get("description");

        String deleteName = formData.get("deleteName");

        String updateName = formData.get("updateName");
        String updateDescription = formData.get("updateDescription");

        if (name != null && description != null) {
            equipmentDAO.insert(new Equip(name, description));
        }

        if (deleteName != null) {
            System.out.println("DELETE = " + deleteName);
            equipmentDAO.deleteByName(deleteName);
        }

        if (updateName != null && updateDescription != null) {
            System.out.println("UPDATE = " + updateName);
            equipmentDAO.updateByName(updateName, updateDescription);
        }

        System.out.println(formData);
        System.out.println("SKIP");

        var cls = equipmentDAO.findAll();
        model.put("equips", cls);

        return template("/equip.html");
    }
}
