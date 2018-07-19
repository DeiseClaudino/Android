package br.com.alura.ageda;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.ageda.modelo.Aluno;

class AlunoConverter {
    public String converteParaJson(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array().object().key("aluno").array();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
