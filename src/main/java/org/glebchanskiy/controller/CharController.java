package org.glebchanskiy.controller;

import org.glebchanskiy.kek.router.controllers.TemplateController;
import org.glebchanskiy.kek.utils.Request;
import org.glebchanskiy.kek.utils.Response;
import org.glebchanskiy.model.*;
import org.glebchanskiy.model.Class;
import org.glebchanskiy.requester.Requester;

public class CharController extends TemplateController {
    public CharController(String route) {
        super(route);
        this.charRequester = new Requester<>(getRoute(), Char.class);
        this.raceRequester = new Requester<>("/races", Race.class);
        this.charClassRequester = new Requester<>("/classes", Class.class);
        this.backRequester = new Requester<>("/backs", Background.class);
        this.equipRequester = new Requester<>("/equips", Equip.class);
    }

    private final Requester<Char> charRequester;
    private final Requester<Race> raceRequester;
    private final Requester<Class> charClassRequester;
    private final Requester<Background> backRequester;
    private final Requester<Equip> equipRequester;

    @Override
    public Response getMapping(Request request) {
        model.put("chars", charRequester.findAll());
        return template( getRoute() + ".html");
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
            System.out.println("character: " + character);

            character.setRace(raceRequester.findByName(race));
            System.out.println("character: " + character);
            character.setCharClass(charClassRequester.findByName(charClass));
            System.out.println("character: " + character);
            character.setBackground(backRequester.findByName(background));
            System.out.println("character: " + character);
            character.setDescription(description);
            System.out.println("character: " + character);
            character.setName(name);
            System.out.println("character: " + character);

            charRequester.insert(character);
        }

        String deleteId = formData.get("deleteId");

        if (deleteId != null) {
            System.out.println("DELETE = " + deleteId);
            charRequester.deleteById(Integer.parseInt(deleteId));
        }

        String updateId = formData.get("updateId");
        String updateName = formData.get("updateName");
        String updateRace = formData.get("updateRace");
        String updateClass = formData.get("updateClass");
        String updateDescription = formData.get("updateDescription");
        String updateBackground = formData.get("updateBackground");

        System.out.println(updateId);
        System.out.println(updateName);
        System.out.println(updateRace);
        System.out.println(updateClass);
        System.out.println(updateDescription);
        System.out.println(updateBackground);

        if (updateId != null && updateName != null && updateRace != null && updateClass != null && updateDescription != null && updateBackground != null) {
            Char character = new Char();
            character.setId(Integer.parseInt(updateId));
            System.out.println("character: " + character);
            character.setRace(raceRequester.findByName(updateRace));
            System.out.println("character: " + character);
            character.setCharClass(charClassRequester.findByName(updateClass));
            System.out.println("character: " + character);
            character.setBackground(backRequester.findByName(updateBackground));
            System.out.println("character: " + character);
            character.setDescription(updateDescription);
            System.out.println("character: " + character);
            character.setName(updateName);

            System.out.println("character: " + character);
            charRequester.update(Integer.parseInt(updateId), character);
        }

        var cls = charRequester.findAll();
        model.put("chars", cls);

        return template(getRoute() + ".html");
    }
}
