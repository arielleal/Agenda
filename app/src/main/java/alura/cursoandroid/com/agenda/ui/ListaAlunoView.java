package alura.cursoandroid.com.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import alura.cursoandroid.com.agenda.dao.AlunoDao;
import alura.cursoandroid.com.agenda.model.Aluno;
import alura.cursoandroid.com.agenda.ui.Adapter.ListaAlunoAdapter;

public class ListaAlunoView {

    private Context context;
    private final ListaAlunoAdapter adapter;
    private AlunoDao dao;

    public ListaAlunoView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunoAdapter(this.context);
        this.dao = new AlunoDao();

    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não",null)
                .show();
    }

    public void atualizaAluno() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
