package br.com.fcoromoto.estudo.collecoes;

import br.com.fcoromoto.estudo.modelo.Aluno;

import java.util.Arrays;
import java.util.Objects;

public class Vetor {

    private Aluno[] alunos;
    private int tamanho = 0;

    public Vetor(int tamanhoAlunos) {
        this.alunos = new Aluno[tamanhoAlunos];
    }

    public Vetor() {
        this(100);
    }

    public void add(Aluno aluno){
        if(Objects.nonNull(aluno)){
            garanteEspaco();
            adicionaNaPosicao(aluno, size());
        }
    }


    public void add(int posicao, Aluno aluno) {
        if(Objects.nonNull(aluno)){

            if(size() == 0){
                adicionaNaPosicao(aluno, posicao);

            }else if(posicao > alunos.length){
                aumentar(posicao * 2);
                adicionaNaPosicao(aluno, posicao);

            }else {
                for(int i = ( size() - 1 ) ; i >= posicao ; i --){
                    realocar( ( i + 1 ), i );
                }
                adicionaNaPosicao(aluno, posicao);
            }
        }
    }

    private void aumentar(int novoTamanho) {
        alunos = Arrays.copyOf(alunos, novoTamanho);
    }

    public Aluno get(int posicao){
        if(posicao > alunos.length || posicao < 0 ){
            throw new IllegalArgumentException("Índice inválido");
        }
        return alunos[posicao];
    }

    public void remove(int posicao){
        if(posicao >= alunos.length || posicao < 0 ){
            return;
        }

        Aluno aluno = get(posicao);
        if(Objects.nonNull(aluno)){

            for(int i = posicao ; i < alunos.length ; i++){

                boolean houveRealocacao = realocar( i, ( i + 1 ) );

                if(houveRealocacao){
                    tamanho--;
                }else {
                    break;
                }
            }
        }
    }

    public boolean contains(Aluno aluno){
        return Arrays.stream(alunos).anyMatch(aluno::equals);
    }

    public int size(){
        return tamanho;
    }

    @Override
    public String toString(){
        return Arrays.toString(alunos);
    }

    private void adicionaNaPosicao(Aluno aluno, int posicao){
        alunos[posicao] = aluno;
        tamanho++;
    }

    private boolean realocar(int posicaoDestino , int posicaoOrigem){
        Aluno alunoRealocado = posicaoOrigem >= alunos.length ? null : alunos[posicaoOrigem];

        alunos[posicaoDestino] = alunoRealocado;

        return Objects.nonNull(alunoRealocado);
    }

    private void garanteEspaco() {
        if(size() == alunos.length){
            aumentar(size() * 2);
        }
    }
}
