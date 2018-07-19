package br.com.alura.ageda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.alura.ageda.converter.AlunoConverter;
import br.com.alura.ageda.dao.AlunoDao;
import br.com.alura.ageda.modelo.Aluno;

public class EnviaAlunosTask extends AsyncTask<Object , Object, String> {
    private Context context;


    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {

        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJson(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return "Envio feito  com sucesso!";
    }

    @Override
    protected void onPostExecute(String resposta) {

    Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}

