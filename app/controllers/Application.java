package controllers;

import models.Meta;
import models.dao.GenericDAO;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.Collections;
import java.util.List;

public class Application extends Controller {

    private static Form<Meta> metaForm = Form.form(Meta.class);
    private static final GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        List<Meta> metas = dao.findAllByClass(Meta.class);
        Collections.sort(metas);
        return ok(views.html.index.render(metas));
    }

    @Transactional
    public static Result novaMeta() {
        // O formulário dos Livros Preenchidos
        Form<Meta> filledForm = metaForm.bindFromRequest();

        if (filledForm.hasErrors()) {
            List<Meta> result = dao.findAllByClass(Meta.class);

            return badRequest(views.html.index.render(result));
        } else {
            Meta novaMeta = filledForm.get();
            Logger.debug("Criando meta: " + filledForm.data().toString() + " como " + novaMeta.getDescricao());
            // Persiste o Livro criado
            dao.persist(novaMeta);
            // Espelha no Banco de Dados
            dao.flush();
            /*
             * Usar routes.Application.<uma action> é uma forma de
             * evitar colocar rotas literais (ex: "/books")
             * hard-coded no código. Dessa forma, se mudamos no
             * arquivo routes, continua funcionando.
             */
            return redirect(routes.Application.index());
        }
    }

//    public static Result metas() {
//        List<Meta> result = dao.findAllByClass(Meta.class);
//        return ok(views.html.index.render(result));
//    }
}
