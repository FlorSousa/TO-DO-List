package classes;

public class HandleError {
    void errorArquivo(){
        System.out.println("O arquivo não foi encontrado e irá ser criado outra vez");
        //cria arquivo
    }
    void optInvalida(){
        System.out.println("Opção Invalida");
    }
    void limiteUltrapassado(){
        System.out.println("Você digitou mais que 50 caracteres");
    }
    void idMuitoGrande(){
        System.out.println("ID fora dos limites");
    }
    void LinhaErrada(){
        System.out.println("ERRO NO ARQUIVO SAVE");
    }
    void Input_invalido(){
        System.out.println("VOCÊ DIGITOU ALGO INVALIDO");
    }
}
