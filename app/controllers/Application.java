package controllers;

import models.Meta;
import models.dao.GenericDAO;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import java.util.List;

public class Application extends Controller {

    private static Form<Meta> metaForm = Form.form(Meta.class);
    private static final GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result metas() {
        List<Meta> result = dao.findAllByClass(Meta.class);
        return ok(views.html.index.render(result));
    }

    @Transactional
    public static Result index() {
        return metas();
    }

    @Transactional
    public static Result novaMeta() {
        Form<Meta> filledForm = metaForm.bindFromRequest();

        if (filledForm.hasErrors()) {
            List<Meta> result = dao.findAllByClass(Meta.class);

            return badRequest(views.html.index.render(result));
        } else {
            Meta novaMeta = filledForm.get();
            Logger.debug("Criando meta: " + filledForm.data().toString() + " como " + novaMeta.getDescricao());

            dao.persist(novaMeta);

            dao.flush();

            return metas();
        }
    }

    @Transactional
    public static Result deleteMetas(long id) {
        dao.removeById(Meta.class, id);
        dao.flush();
        return metas();
    }

    @Transactional
    public static Result alcancarMeta(long id) {

        Form<Meta> filledForm = metaForm.bindFromRequest();

        if (filledForm.hasErrors()) {
            List<Meta> result = dao.findAllByClass(Meta.class);

            return badRequest(views.html.index.render(result));
        } else {
            Meta meta = dao.findByEntityId(Meta.class, id);
            meta.setAlcancada(true);
            Logger.debug("Deletando meta: " + filledForm.data().toString() + " como " + meta.getDescricao());

            dao.merge(meta);

            dao.flush();

            return metas();
        }
    }
}
