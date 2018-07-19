package br.com.alura.ageda;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.ageda.modelo.Aluno;

class AlunoConverter {
    public String converteParaJson(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();
        try {
            for (Aluno aluno : alunos) {
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
