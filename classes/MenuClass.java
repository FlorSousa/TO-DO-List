package classes;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class MenuClass implements Menu {
    private Boolean estado = true;
    private Boolean MenuInicial = true;
    private Boolean Visualizar = false;
    private Boolean Inserindo = false;
    private Boolean TarefasFeitas = false;
    private Boolean Kanbam = false;
    private Boolean sair = false;
    private Boolean mudandoPrioridade = false;
    private Scanner leitor = new Scanner(System.in);
    private Stream stream = new Stream();
    public List<Tarefa> tarefas = new ArrayList<>();
    private HandleError handleError = new HandleError();
    private int caracteresId=8;
    private int caracteresAtividade =50;
    private int caracteresPrioridade = 8;
    private int caracteresAutor = 50;

    @Override
    public boolean Visualizar() {
        stream.desenhaHeader();
        stream.imprimelinhas();
        System.out.println("   A.Voltar ao menu principal");
        System.out.println("   B.Inserir nova tarefa");
        String escolha = leitor.nextLine();
        if(escolha.equalsIgnoreCase("A")){
            this.MenuInicial = true;
        }else if(escolha.equalsIgnoreCase("B")){
            this.Inserindo = true;
        }
        return this.Visualizar = false;
    }

    public boolean trata_erro(String id, String atividade,String prioridade,String autor){
        boolean Estado = false;
        int novo_id = Integer.parseInt(id);
        if(novo_id>=0 && novo_id<=3){
            if(atividade.length()<=50){
                if(prioridade.equals("BACKLOG") || prioridade.equals("DESENVOL") || prioridade.equals("COPLETA")){
                    Estado = true;
                }else{
                    handleError.optInvalida();
                    return false;
                }
            }else{
                handleError.limiteUltrapassado();
                return false;
            }
        }else{
            handleError.idMuitoGrande();
            return false;
        }
        return Estado;
    }

    String mudaCampos(String campo,int numeroBranco){
        for(int cont =0; cont<=numeroBranco;cont++){
            campo += " ";
        }
        return campo;
    }

    String TransformPrioridade(String prioridade){
        if(prioridade.equals("1")){
            return "BACKLOG";
        }else if(prioridade.equals("2")){
            return "DESENVOL";
        }else if(prioridade.equals("3")){
            return "COMPLETA";
        }else{
            return "ERRO";
        }
    }

    @Override
    public boolean InserirNovaTarefa() {
        System.out.println("Crie um ID de 0 a 10000000");
        String id = leitor.nextLine();
        System.out.println("Digite uma Atividade --- Max caracteres:50");
        String atividade = leitor.nextLine();
        System.out.println("Digite qual o estado da tarefa: 1-BACKLOG, 2-DESENVOLVIMENTO,3-COMPLETA");
        String prioridade = leitor.nextLine();
        prioridade = TransformPrioridade(prioridade);
        System.out.println("Quem está criando essa tarefa?");
        String autor = leitor.nextLine();
        
        if(trata_erro(id,atividade,prioridade,autor) == true){
            
            int EmBrancoId = caracteresId - id.length();
            int EmBrancoAtividade = caracteresAtividade - atividade.length();
            int EmBrancoPrioridade = caracteresPrioridade - prioridade.length();
            int EmBrancoAutor = caracteresAutor - autor.length();
            
            id = mudaCampos(id,EmBrancoId);
            atividade = mudaCampos(atividade,EmBrancoAtividade);
            prioridade = mudaCampos(prioridade, EmBrancoPrioridade);
            autor = mudaCampos(autor, EmBrancoAutor);

            Tarefa novaTarefa = new Tarefa(id,atividade,prioridade,autor);
            tarefas.add(novaTarefa);

            //Escreve em save_system
            stream.Escreve(novaTarefa.toString());

            System.out.println("Deseja criar outra tarefa?");
            String escolha = leitor.nextLine();
            
            if(escolha.equalsIgnoreCase("N")){
                this.Inserindo = false;
                this.MenuInicial = true;
            }else{
                this.Inserindo = true;
                this.MenuInicial = false;
            }
            return this.MenuInicial;
        
        }else{
            return this.MenuInicial = true;
        }
        
    }

    @Override
    public boolean MostraMenu() {
        System.out.println("Lista TO-DO");
        System.out.println("Selecione uma opção para continuar");
        System.out.println("   A.Visualizar suas tarefas");
        System.out.println("   B.Inserir nova tarefa");
        System.out.println("   C.Tarefas concluidas");
        System.out.println("   D.Quadro Kanbam");
        System.out.println("   E.Sair");
        String escolha = leitor.nextLine();
        if(escolha.equalsIgnoreCase("A")){
                this.Visualizar = true;
        }else if(escolha.equalsIgnoreCase("B")){
                this.Inserindo = true;
        }else if(escolha.equalsIgnoreCase("C")){
                this.TarefasFeitas = true;
        }else if(escolha.equalsIgnoreCase("D")){
                this.Kanbam = true;
        }else if(escolha.equalsIgnoreCase("E")){
                this.sair = true;
        }
        return this.MenuInicial = false;
    }

    @Override
    public void TarefasFeitas() {
        String linha = stream.getLinhaEspecifica("00000001");
        System.out.println(linha);
    }

    @Override
    public boolean Kanbam() {
        stream.ImprimeKanbam();      
        System.out.println("    A.Para mudar prioridade");
        System.out.println("    B.Menu inicial");
        String escolha = leitor.nextLine();
        if(escolha.equalsIgnoreCase("A")){
             this.mudandoPrioridade = true;
             return this.Kanbam = false;
        }else if(escolha.equalsIgnoreCase("B")){
            this.Kanbam = false;
            return this.MenuInicial = true;
        }else{
            handleError.optInvalida();
            return this.Kanbam = true;
        }
        
    }

    void UpPrioridade(String idTarefa,String linha){
        //Acessa a linha, retira o valor que está em prioridade
        //Verifica qual seria a proxima prioridade
        //Muda para a proxima prioridade
        //Sobrescreve a linha
    }

    void BackPrioridade(String idTarefa, String linha){
        //Acessa a linha, retira o valor que está em prioridade
        //Verifica qual seria a anterior prioridade
        //Muda para a anterior prioridade
        //Sobrescreve a linha
    }

    @Override
    public void MudarPrioridade() {
        System.out.println("Digite o ID da tarefa");
        String id = leitor.nextLine();
        String linha = stream.getLinhaEspecifica(id);
        if(linha == "err"){
            handleError.Input_invalido();
        }else{
            System.out.println(linha);
            System.out.println("    A.Avançar prioridade");
            System.out.println("    B.Retroceder prioridade");
            String escolha = leitor.nextLine();
            if(escolha.equalsIgnoreCase("A")){
                UpPrioridade(id, linha);
            }else if(escolha.equalsIgnoreCase("B")){
                BackPrioridade(id, linha);
            }else{
                handleError.optInvalida();
            }
        }
        
        
    }

    @Override
    public void Sair() {
        System.out.println("saindo");   
        this.estado = false;

        
    }

    public void main(){
        while(this.estado == true){
            if(this.MenuInicial == true){
                MostraMenu();
            }
            else{
                if(this.Visualizar == true){
                    Visualizar();
                }else if(this.Inserindo == true){
                    InserirNovaTarefa();
                }else if(this.TarefasFeitas == true){
                    TarefasFeitas();
                }else if(this.Kanbam == true){
                    Kanbam();
                }else if(this.mudandoPrioridade == true){
                    MudarPrioridade();
                }
                else if(this.sair == true){
                    Sair();
                }
            }
        }
    }

    
}
