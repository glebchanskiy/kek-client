package org.glebchanskiy;

import org.glebchanskiy.controller.*;
import org.glebchanskiy.dao.*;
import org.glebchanskiy.kek.Configuration;
import org.glebchanskiy.kek.ConnectionsManager;
import org.glebchanskiy.kek.Server;
import org.glebchanskiy.kek.router.Router;
import org.glebchanskiy.kek.router.controllers.impl.ShareFilesController;
import org.glebchanskiy.kek.router.filters.CorsFilter;
import org.glebchanskiy.kek.utils.Mapper;
import org.glebchanskiy.util.DataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.Executors;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {

        var config = Configuration.getInstance(args);
        logger.info("[configuration]:\n{}", config);

        var dataSource = new DataSourceBuilder()
                .setDriverClassName("org.postgresql.Driver")
                .setUrl("jdbc:postgresql://localhost:5432/dnd")
                .setPassword("postgres")
                .setUsername("postgres")
                .build();

        var jdbcTemplate = new JdbcTemplate(dataSource);

        var charDAO = new CharDAO(jdbcTemplate);
        var raceDAO = new RaceDAO(jdbcTemplate);
        var charClassDAO = new CharClassDAO(jdbcTemplate);
        var backgroundDAO = new BackgroundDAO(jdbcTemplate);
        var equipDAO = new EquipmentDAO(jdbcTemplate);


        var router = new Router();

        router.addController(new ShareFilesController("/assets", config));
        router.addController(new ClassController("/class", charClassDAO));
        router.addController(new RaceController("/race", raceDAO));
        router.addController(new EquipmentController("/equip", equipDAO));
        router.addController(new BackgroundController("/back", backgroundDAO));
        router.addController(new CharController("/char", charDAO, raceDAO, charClassDAO, backgroundDAO));
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
