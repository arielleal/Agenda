package alura.cursoandroid.com.agenda.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import alura.cursoandroid.com.agenda.R;
import alura.cursoandroid.com.agenda.dao.AlunoDao;
import alura.cursoandroid.com.agenda.model.Aluno;
import alura.cursoandroid.com.agenda.ui.Adapter.ListaAlunoAdapter;
import alura.cursoandroid.com.agenda.ui.ListaAlunoView;

import static alura.cursoandroid.com.agenda.ui.Activity.ConstantesActivitys.CHAVE_ALUNO;


public class ListaAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDao dao = new AlunoDao();
    private ListaAlunoAdapter adapter;
    private final ListaAlunoView listaAlunoView = new ListaAlunoView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_aluno_remover){
            listaAlunoView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.floatingActionButton);
        botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunoView.atualizaAluno();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaAlunoView.configuraAdapter(listaDeAlunos);
        configuraListenerDeClickPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {

            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }
}