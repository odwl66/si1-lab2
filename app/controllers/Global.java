package controllers;

import play.*;

import models.Meta;
import models.dao.GenericDAO;
import play.db.jpa.JPA;

public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Meta meta1 = new Meta("Fazer o lab", 1, 5);
                Meta meta2 = new Meta("Aprender o play", 1, 4);
                Meta meta3 = new Meta("Fazer os testes do lab", 1, 2);
                Meta meta4 = new Meta("Parar de fumar", 3, 5);
                Meta meta5 = new Meta("Dormir mais", 2, 5);
                Meta meta6 = new Meta("Parar de fumar", 3, 5);
                Meta meta7 = new Meta("Emagrecer", 3, 2);
                Meta meta8 = new Meta("Voltar a correr", 3, 4);
                Meta meta9 = new Meta("Namorar mais", 3, 5);
                Meta meta10 = new Meta("Tomar uma grande", 3, 3);

                meta9.setAlcancada(true);
                meta6.setAlcancada(true);

                dao.persist(meta1);
                dao.persist(meta2);
                dao.persist(meta3);
                dao.persist(meta4);
                dao.persist(meta5);
                dao.persist(meta6);
                dao.persist(meta7);
                dao.persist(meta8);
                dao.persist(meta9);
                dao.persist(meta10);

                dao.flush();
            }
        });
    }
}
