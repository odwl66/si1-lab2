package funcional;

import base.AbstractTest;
import models.Meta;
import models.dao.GenericDAO;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;



/**
 * Created by orion on 03/12/14.
 */
public class IndexViewTest extends AbstractTest{
    GenericDAO dao = new GenericDAO();

    List<Meta> metas = new LinkedList<Meta>();

    @Test
    public void deveCadastrarMeta(){
        Map<String, String> formulario = new HashMap<String, String>();
        formulario.put("descricao", "Meta 1");
        formulario.put("semana", "1");
        formulario.put("prioridade", "3");
        //Result result = callAction(routes.ref.Application.novaMeta(), fakeRequest().withFormUrlEncodedBody(formulario));

        metas = dao.findAllByClass(Meta.class);

        assertThat(metas.size()).isEqualTo(0);

    }
}
