package models;

import java.util.List;

public class Utils {
    public int quantidadeDeMetasNaSemana(int semana, List<Meta> metas){
        int cont = 0;
        for (Meta meta: metas){
            if (meta.getSemana() == semana){
                cont++;
            }
        }
        return cont;
    }

    public int quantidadeDeMetasAlcancadasNaSemana(int semana, List<Meta> metas){
        int cont = 0;
        for (Meta meta: metas){
            if (meta.getSemana() == semana && meta.isAlcancada()){
                cont++;
            }
        }
        return cont;
    }

    public int quantidadeDeMetasNaoAlcancadasNaSemana(int semana, List<Meta> metas){
        int cont = 0;
        for (Meta meta: metas){
            if (meta.getSemana() == semana && !meta.isAlcancada()){
                cont++;
            }
        }
        return cont;
    }
}
