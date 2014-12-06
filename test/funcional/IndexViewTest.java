package funcional;

import base.AbstractTest;
import models.Meta;
import models.Utils;
import models.dao.GenericDAO;
import org.junit.Test;
import play.mvc.Result;
import play.twirl.api.Html;
import views.html.index;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static play.test.Helpers.*;


/**
 * Created by orion on 03/12/14.
 */
public class IndexViewTest extends AbstractTest{
    GenericDAO dao = new GenericDAO();

    List<Meta> metas = new LinkedList<Meta>();
    Utils util = new Utils();

    @Test
    public void deveCadastrarMeta(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "1");
        formulario.put("prioridade", "3");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);
        Meta meta = metas.get(0);

        assertThat(metas.size()).isEqualTo(1);
        assertThat(meta.getDescricao()).isEqualTo("Meta 1");
        assertThat(meta.getPrioridade()).isEqualTo(3);
        assertFalse(meta.isAlcancada());
        assertThat(meta.getSemana()).isEqualTo(1);
    }

    @Test
    public void naoDeveCadastrarMetaComDescricaoVazia(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "");
        formulario.put("semana", "1");
        formulario.put("prioridade", "3");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void naoDeveCadastrarMetaComSemanaMenorQue1(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "0");
        formulario.put("prioridade", "3");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void naoDeveCadastrarMetaComSemanaMaiorQue6(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "7");
        formulario.put("prioridade", "3");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void naoDeveCadastrarMetaComPrioridadeNegativa(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "3");
        formulario.put("prioridade", "-1");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void naoDeveCadastrarMetaComPrioridadeAcimaDe5(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "3");
        formulario.put("prioridade", "6");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void deveAparecerNoHTML(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "3");
        formulario.put("prioridade", "4");

        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        Html html = index.render(metas, util);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Metas Semanais - 1 meta(s)");
        assertThat(contentAsString(html)).contains("Semana 3 - 0/1");
        assertThat(contentAsString(html)).contains("Meta 1 (4)");
    }

    @Test
    public void deveDeletarNoHTML() throws Exception {
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("descricao", "Meta 1");
        formData.put("semana", "4");
        formData.put("prioridade", "4");

        List<Meta> metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);

        Result result1 = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formData));
        dao.flush();

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(1);

        Result result = callAction(controllers.routes.ref.Application.deleteMetas(metas.get(0).getId()), fakeRequest().withFormUrlEncodedBody(formData));
        dao.flush();
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void deveCumprirMeta(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "3");
        formulario.put("prioridade", "5");
        Result result = callAction(controllers.routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        Html html = index.render(metas, util);
        assertThat(contentAsString(html)).contains("Concluir");

        assertFalse(metas.get(0).isAlcancada());

        Meta meta = metas.get(0);

        meta.setAlcancada(true);
        dao.merge(meta);
        dao.flush();
        metas = dao.findAllByClass(Meta.class);
        html = index.render(metas, util);
        assertThat(contentAsString(html)).doesNotContain("Concluir");
    }
}
