package unidade;

import models.Meta;
import models.dao.GenericDAO;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import base.AbstractTest;
import play.Logger;

public class MetaTest extends AbstractTest{


    GenericDAO dao = new GenericDAO();

    List<Meta> metas;

    @Test
    public void deveIniciarBDVazio() {
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(0);
    }

    @Test
    public void deveAdicionarMeta() throws Exception {
        Meta m1 = new Meta("Terminar os testes", 1, 5);
        dao.persist(m1);

        metas = dao.findAllByClass(Meta.class); //consulta o bd
        assertThat(metas.size()).isEqualTo(1);
        assertThat(metas.get(0).getDescricao()).isEqualTo("Terminar os testes");
    }

    @Test
    public void deveAlcancarMeta() throws Exception {
        Meta m1 = new Meta("Terminar os testes", 1, 5);
        dao.persist(m1);

        metas = dao.findAllByClass(Meta.class);

        assertFalse(metas.get(0).isAlcancada());

        metas.get(0).setAlcancada(true);

        assertTrue(metas.get(0).isAlcancada());
    }

    @Test
    public void deveOrdenarMetas() throws Exception {
        Meta meta1 = new Meta("Fazer o lab", 4, 5);
        Meta meta2 = new Meta("Aprender o play", 1, 4);
        Meta meta3 = new Meta("Fazer os testes do lab", 1, 2);
        Meta meta4 = new Meta("Estudar mais", 3, 5);
        Meta meta5 = new Meta("Dormir mais", 5, 5);
        Meta meta6 = new Meta("Parar de fumar", 2, 1);
        Meta meta7 = new Meta("Emagrecer", 3, 2);
        Meta meta8 = new Meta("Voltar a correr", 3, 4);
        Meta meta9 = new Meta("Namorar mais", 5,1);
        Meta meta10 = new Meta("Tomar uma grande", 4, 3);

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

        metas = dao.findAllByClass(Meta.class);

        Collections.sort(metas);

        assertThat(metas.get(0).getDescricao()).isEqualTo("Aprender o play");
        assertThat(metas.get(1).getDescricao()).isEqualTo("Fazer os testes do lab");
        assertThat(metas.get(2).getDescricao()).isEqualTo("Parar de fumar");
        assertThat(metas.get(3).getDescricao()).isEqualTo("Estudar mais");
        assertThat(metas.get(4).getDescricao()).isEqualTo("Voltar a correr");
        assertThat(metas.get(5).getDescricao()).isEqualTo("Emagrecer");
        assertThat(metas.get(6).getDescricao()).isEqualTo("Fazer o lab");
        assertThat(metas.get(7).getDescricao()).isEqualTo("Tomar uma grande");
        assertThat(metas.get(8).getDescricao()).isEqualTo("Dormir mais");
        assertThat(metas.get(9).getDescricao()).isEqualTo("Namorar mais");

    }
}
