package alura.cursoandroid.com.agenda;

import android.app.Application;

import alura.cursoandroid.com.agenda.dao.AlunoDao;
import alura.cursoandroid.com.agenda.model.Aluno;


public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        AlunoDao dao = new AlunoDao();
        dao.salvar(new Aluno("Ariel", "991686171", "@leal"));
        dao.salvar(new Aluno("Mila", "991686171", "@campana"));
    }
}
