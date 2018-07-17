package br.com.alura.ageda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alura.ageda.ListaAlunosActivity;
import br.com.alura.ageda.modelo.Aluno;

public class AlunosAdapter extends BaseAdapter{


    private final Context context;
    private final List<Aluno> alunos;


    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setText("Item da posição" + position);
        return view;
    }


}
