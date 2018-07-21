package br.com.alura.ageda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto direto", "Objeto indireto");
        Prova provaPortugues = new Prova("Portugues", "25/06/2018", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações de segundo grau", "trigonometria");
        Prova provaMatematica = new Prova("Matematica", "27/05/2016", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMatematica);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, provas);

        ListView lista =  findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);
    }
}
