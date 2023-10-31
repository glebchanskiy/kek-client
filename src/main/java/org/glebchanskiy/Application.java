package org.glebchanskiy;

import org.glebchanskiy.controller.*;
import org.glebchanskiy.kek.Configuration;
import org.glebchanskiy.kek.ConnectionsManager;
import org.glebchanskiy.kek.Server;
import org.glebchanskiy.kek.router.Router;
import org.glebchanskiy.kek.router.controllers.impl.ShareFilesController;
import org.glebchanskiy.kek.router.filters.CorsFilter;
import org.glebchanskiy.kek.utils.Mapper;
import org.glebchanskiy.model.*;
import org.glebchanskiy.model.Class;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) throws IOException, IllegalAccessException {

        var config = Configuration.getInstance(args);
        config.setPort(88);
//        Path path = Path.of("/Users/glebchanskiy/subjects/aipos/kek-client/src/main/resources/server.config.yml");
//        var config = Configuration.load(path);
        System.out.println(config);
        logger.info("[configuration]:\n{}", config);

//        var dataSource = new DataSourceBuilder()
//                .setDriverClassName("org.postgresql.Driver")
//                .setUrl("jdbc:postgresql://localhost:5432/dnd")
//                .setPassword("postgres")
//                .setUsername("postgres")
//                .build();
//
//        var jdbcTemplate = new JdbcTemplate(dataSource);
////
//        var charDAO = new CharDAO(jdbcTemplate);
//        var raceDAO = new RaceDAO(jdbcTemplate);
//        var charClassDAO = new CharClassDAO(jdbcTemplate);
//        var backgroundDAO = new BackgroundDAO(jdbcTemplate);
//        var equipDAO = new EquipmentDAO(jdbcTemplate);


        var router = new Router();

        router.addController(new ShareFilesController("/assets*", config));
        router.addController(new CrudTemplateController<>("/classes", Class.class));
        router.addController(new CrudTemplateController<>("/races", Race.class));
        router.addController(new CrudTemplateController<>("/equips", Equip.class));
        router.addController(new CrudTemplateController<>("/backs", Background.class));
        router.addController(new CharController("/chars"));
        router.addController(new HomeController("/home"));


        var server = Server.builder()
                .filter(new CorsFilter())
                .connectionsManager(new ConnectionsManager())
                .executorService(Executors.newFixedThreadPool(10))
                .mapper(new Mapper())
                .router(router)
                .build();

        server.run();
    }
}
