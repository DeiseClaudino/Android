package br.com.alura.ageda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import br.com.alura.ageda.dao.AlunoDao;
import br.com.alura.ageda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        final Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null){
            helper.preencheFormulario(aluno);
        }

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String caminhoFoto = getExternalFilesDir(null) + "/" +System.currentTimeMillis() + "foto.jpg";
                File arquivoFile = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFile));
                startActivityForResult(intentCamera, 567);
            }
        });


    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 567){



        }else if (requestCode == 678){


        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();

                AlunoDao dao = new AlunoDao(this);

                if(aluno.getId() != null){
                    dao.altera(aluno);

                }else {

                    dao.insere(aluno);
                }

                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome()+" Salvo!", Toast.LENGTH_SHORT).show();

                finish();

                break;
        }

            return super.onOptionsItemSelected(item);

    }

}
