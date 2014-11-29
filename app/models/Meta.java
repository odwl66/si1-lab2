package models;

/**
 * Created by orion on 29/11/14.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Meta")
public class Meta implements Serializable, Comparable<Meta>{

    @Id
    @Column
    private String descricao;
    @Column
    private int semana;
    @Column
    private int prioridade;
    @Column
    private boolean alcancada;

    public Meta(String descricao, int semana, int prioridade) throws Exception {
        setDescricao(descricao);
        setSemana(semana);
        setPrioridade(prioridade);
        setAlcancada(false);
    }

    public Meta(){

    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) throws Exception{
        if (semana < 1 || semana > 6){
            throw new Exception("Apenas semanas entre 1 e 6 são aceitas!");
        }
        this.semana = semana;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) throws Exception{
        if (prioridade < 0 || prioridade > 5){
            throw new Exception("A prioridade deve estar entre 0 e 5!");
        }
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws Exception{
        if (descricao.length() == 0){
            throw new Exception("Descrição não deve ser vazia!");
        }
        this.descricao = descricao;
    }

    public boolean isAlcancada() {
        return alcancada;
    }

    public void setAlcancada(boolean alcancada) {
        this.alcancada = alcancada;
    }

    //As metas estão ordenadas pela semana em que planejei cumpri-las, e, em seguida, por prioridade.
    @Override
    public int compareTo(Meta meta) {

        int comparacao1 = this.getSemana() - meta.getSemana();

        if (comparacao1 == 0){
            int comparacao2 = meta.getPrioridade() - this.getPrioridade();

            if (comparacao2 == 0){
                return this.getDescricao().compareTo(meta.getDescricao());
            }

            return comparacao2;
        }

        return comparacao1;
    }
}