$( document ).ready(function() {
    $("#form").submit(function() {
        if ($("input['name=descricao'").val() == ""){
            alert("Descrição não deve ser vazia!");
        }
        if ($("input['name=semana'").val() < 1 || $("input['name=semana'").val() > 6){
            alert("Semana deve estar entre 1 e 6");
        }
        if ($("input['name=prioridade'").val() < 0 || $("input['name=prioridade'").val() > 5){
            alert("Prioridade deve estar entre 0 e 5");
        }
    });
});