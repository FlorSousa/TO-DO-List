package classes;

public class HandleError {
    public void errorArquivo(){
        System.out.println("O arquivo não foi encontrado e irá ser criado outra vez");
        //cria arquivo
    }
    public void optInvalida(){
        System.out.println("Opção Invalida");
    }
    public void limiteUltrapassado(){
        System.out.println("Você digitou mais que 50 caracteres");
    }
    public void idMuitoGrande(){
        System.out.println("ID fora dos limites");
    }
    public void LinhaErrada(){
        System.out.println("ERRO NO ARQUIVO SAVE");
    }
    public void Input_invalido(){
        System.out.println("VOCÊ DIGITOU ALGO INVALIDO");
    }
    public void Ex_err(Exception err){
        System.out.println(err);
    }
    public void save_err(){
        System.out.println("Erro ao salvar arquivo !");
    }
}
