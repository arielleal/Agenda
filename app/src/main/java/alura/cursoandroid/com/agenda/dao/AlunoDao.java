package alura.cursoandroid.com.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import alura.cursoandroid.com.agenda.model.Aluno;

public class AlunoDao {

    private final static List<Aluno> alunos = new ArrayList<>();

    private static int contadorDeIds =1;

    public void salvar (Aluno aluno){

        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno,aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a: alunos){
            if (a.getId() == aluno.getId()){
                alunoEncontrado = a;
                break;
            }
        }
        return alunoEncontrado;
    }

  /*  public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }*/

    public void remove (Aluno aluno){
        Aluno alunoRemovido = buscaAlunoPeloId(aluno);
        if (alunoRemovido != null) {
            alunos.remove(alunoRemovido);
        }
    }
}
