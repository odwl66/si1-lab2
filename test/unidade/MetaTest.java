package unidade;

import models.Meta;
import models.dao.GenericDAO;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import java.util.List;
import base.AbstractTest;

public class MetaTest {
    GenericDAO dao = new GenericDAO();

    List<Meta> metas;

    @Test
    public void deveIniciarBDVazio(){
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(0);
    }
}
