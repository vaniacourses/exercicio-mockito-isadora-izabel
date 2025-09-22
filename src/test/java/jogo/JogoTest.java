package jogo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JogoTest {

    @Test
    @DisplayName("Vitória no primeiro turno (soma 7)")
    public void vitoriaPrimeiroTurnoCom7() {
        Dado dado1 = mock(Dado.class);
        Dado dado2 = mock(Dado.class);
        Jogador jogador = mock(Jogador.class);

        // Simula que o jogador tira 7 logo no primeiro lançamento
        when(jogador.lancar(dado1, dado2)).thenReturn(7);

        Jogo jogo = new Jogo(jogador, dado1, dado2);
        assertTrue(jogo.jogo(), "Se a soma for 7 no primeiro turno, deve vencer");
    }

    @Test
    @DisplayName("Derrota no primeiro turno (soma 2)")
    public void derrotaPrimeiroTurnoCom2() {
        Dado dado1 = mock(Dado.class);
        Dado dado2 = mock(Dado.class);
        Jogador jogador = mock(Jogador.class);

        when(jogador.lancar(dado1, dado2)).thenReturn(2);

        Jogo jogo = new Jogo(jogador, dado1, dado2);
        assertFalse(jogo.jogo(), "Se a soma for 2 no primeiro turno, deve perder");
    }

    @Test
    @DisplayName("Vitória no segundo turno (faz ponto e depois repete)")
    public void vitoriaSegundoTurno() {
        Dado dado1 = mock(Dado.class);
        Dado dado2 = mock(Dado.class);
        Jogador jogador = mock(Jogador.class);

        // Primeira jogada = 5 (ponto), depois 6 (continua), depois 5 (ganha)
        when(jogador.lancar(dado1, dado2)).thenReturn(5, 6, 5);

        Jogo jogo = new Jogo(jogador, dado1, dado2);
        assertTrue(jogo.jogo(), "Deve vencer se repetir o ponto antes do 7");
    }

    @Test
    @DisplayName("Derrota no segundo turno (faz ponto e depois sai 7)")
    public void derrotaSegundoTurno() {
        Dado dado1 = mock(Dado.class);
        Dado dado2 = mock(Dado.class);
        Jogador jogador = mock(Jogador.class);

        // Primeira jogada = 5 (ponto), depois 7 (perde)
        when(jogador.lancar(dado1, dado2)).thenReturn(5, 7);

        Jogo jogo = new Jogo(jogador, dado1, dado2);
        assertFalse(jogo.jogo(), "Deve perder se sair 7 antes de repetir o ponto");
    }
}
