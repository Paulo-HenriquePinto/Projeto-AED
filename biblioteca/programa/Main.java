/*
 * Universidade Federal de Sergipe - Departamento de Computação
 * Algoritmo e Estrutura de Dados I - 2026.1 - T01
 * Trabalho da Segunda Unidade - Grupo G8
 *
 * Sistema de gerenciamento de biblioteca pública
 * Estrutura usada: Lista Duplamente Encadeada
 *
 * Integrantes:
 *   - Lauren Victoria Ferreira Roma
 *   - Paulo Henrique Sa de Oliveira Pinto
 *   - Matheus Nascimento dos Santos
 *   - Gabriel Felipe Vilela Marti
 *   - Jose Antonio Rodrigues Santos
 */


package programa;

import programa.controle.Central;

/**
 Classe principal da aplicação.
 Responsável por iniciar o sistema, carregar os dados armazenados e exibir a tela inicial para o usuário.
 */
public class Main {

    /**
     Ponto de entrada da aplicação.
     */
    public static void main(String[] args) {

        // Cria o controlador central responsável por gerenciar todas as telas e recursos da aplicação
        Central app = new Central();

        // Carrega os dados salvos anteriormente
        app.CarregarMemoria();

        // Exibe a tela inicial do sistema
        app.VerTelaPrimaria(true);
    }
}
