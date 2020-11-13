package br.com.fcoromoto.estudo.collecoes;

import br.com.fcoromoto.estudo.modelo.Aluno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class VetorTest {

    @Test
    void adiciona() {
        Aluno joao = new Aluno("joao");
        Aluno maria = new Aluno("maria");

        Vetor vetor = new Vetor();
        vetor.add(joao);
        vetor.add(maria);

        assertEquals(2, vetor.size());
        assertTrue(vetor.contains(joao));
        assertTrue(vetor.contains(maria));
    }

    @Test
    void adicionaArrayList() {
        Aluno joao = new Aluno("joao");
        Aluno maria = new Aluno("maria");

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(joao);
        alunos.add(maria);

        assertEquals(2, alunos.size());
        assertTrue(alunos.contains(joao));
        assertTrue(alunos.contains(maria));
    }

    @Test
    void adiciona200() {
        testeAdiciona(200);
    }

    @Test
    void adiciona500() {
        testeAdiciona(500);
    }

    @Test
    void adicionaNullo() {
        Vetor vetor = new Vetor();
        vetor.add(null);

        assertEquals(0, vetor.size());
    }

    @Test
    void adicionaInformandoPosicao(){
        Vetor vetor = criaVectorPreenchidoComTamanho(1);

        Aluno novoAluno = new Aluno("novo aluno");
        vetor.add(0, novoAluno);

        assertEquals(2, vetor.size());
        assertEquals(true, vetor.contains(novoAluno));
        assertEquals(novoAluno, vetor.get(0));
    }

    @Test
    void adicionaInformandoPosicaoMaiorTamanho(){
        Vetor vetor = criaVectorPreenchidoComTamanho(100);

        Aluno novoAluno = new Aluno("novo aluno");
        vetor.add(200, novoAluno);

        assertEquals(101, vetor.size());
        assertEquals(true, vetor.contains(novoAluno));
        assertEquals(novoAluno, vetor.get(200));
    }

    @Test
    void adicionaPrimeiroAlunoPosicaoZero(){
        Vetor vetor = new Vetor();

        Aluno novoAluno = new Aluno("novo aluno");
        vetor.add(0, novoAluno);

        assertEquals(1, vetor.size());
        assertEquals(true, vetor.contains(novoAluno));
        assertEquals(novoAluno, vetor.get(0));
    }

    @Test
    void containsPosicaoVaziaNoInicio(){
        Vetor vetor = criaVectorPreenchidoComTamanho(3);

        vetor.remove(0);
        assertTrue(vetor.contains(new Aluno("aluno 3")));
    }

    @Test
    void pega() {
        Aluno maria = new Aluno("maria");

        Vetor vetor = new Vetor();
        vetor.add(maria);

        assertEquals(maria, vetor.get(0));
    }

    @Test
    void pegaSemTerAdicionado() {
        Vetor vetor = new Vetor();
        assertNull(vetor.get(0));
    }

    @Test
    void pegaComPosicaoMaiorTamanho() {
        Exception exception = assertExceptions(() -> new Vetor().get(200));
        assertEquals("Índice inválido", exception.getMessage());
    }

    @Test
    void pegaComPosicaoNegativa() {
        Exception exception = assertExceptions(() -> new Vetor().get(-1));
        assertEquals("Índice inválido", exception.getMessage());
    }

    @Test
    void remove() {
        Aluno joao = new Aluno("joao");
        Aluno maria = new Aluno("maria");

        Vetor vetor = new Vetor();
        vetor.add(joao);
        vetor.add(maria);
        vetor.remove(0);

        assertEquals(1, vetor.size());
        assertFalse(vetor.contains(joao));
        assertTrue(vetor.contains(maria));
    }

    @Test
    void removeAlunoMeio() {
        Aluno joao = new Aluno("joao");
        Aluno maria = new Aluno("maria");
        Aluno pedro = new Aluno("pedro");

        Vetor vetor = new Vetor(3);
        vetor.add(joao);
        vetor.add(maria);
        vetor.add(pedro);

        vetor.remove(1);

        assertEquals(2, vetor.size());
        assertFalse(vetor.contains(maria));
        assertTrue(vetor.contains(joao));
        assertTrue(vetor.contains(pedro));
        assertEquals(pedro, vetor.get(1));
    }

    @Test
    void removeSemTerAdicionado() {
        Vetor vetor = new Vetor();
        vetor.remove(0);

        assertEquals(0, vetor.size());
    }

    @Test
    void removePosicaoVazia() {
        Aluno joao = new Aluno("joao");
        Vetor vetor = new Vetor();
        vetor.add(joao);
        vetor.remove(100);

        assertEquals(1, vetor.size());
    }

    @Test
    void removeComPosicaoNegativa() {
        Aluno joao = new Aluno("joao");
        Vetor vetor = new Vetor();
        vetor.add(joao);
        vetor.remove(-1);

        assertEquals(1, vetor.size());
    }

    @Test
    void testToString() {

        Aluno joao = new Aluno("joao");
        Aluno maria = new Aluno("maria");

        Vetor vetor = new Vetor();
        vetor.add(joao);
        vetor.add(maria);

        String toString = vetor.toString();
        assertTrue(toString.contains("maria"));
        assertTrue(toString.contains("joao"));
    }

    private void testeAdiciona(int tamanho){
        Vetor vetor = criaVectorPreenchidoComTamanho(tamanho);

        assertEquals(tamanho, vetor.size());
        assertTrue(vetor.contains(new Aluno("aluno 1")));
        assertTrue(vetor.contains(new Aluno("aluno "+tamanho)));
    }

    private Vetor criaVectorPreenchidoComTamanho(int tamanho) {
        Vetor vetor = new Vetor();
        for(int i = 1; i <= tamanho; i ++){
            vetor.add(new Aluno("aluno "+ (i)));
        }
        return vetor;
    }

    private Exception assertExceptions(Executable executable){
        return assertThrows(IllegalArgumentException.class, executable);
    }
}