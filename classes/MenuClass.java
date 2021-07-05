package classes;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MenuClass implements Menu {
    public List<Tarefa> tarefas = new ArrayList<>();
    private HandleError handleError = new HandleError();
    public  HandleFeitas handleFeitas = new HandleFeitas();
    private Minerador_de_Dados miner = new Minerador_de_Dados();
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
    private int caracteresId=8;
    private int caracteresAtividade =50;
    private int caracteresPrioridade = 8;
    private int caracteresAutor = 50;
    public boolean Start = true;

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
    
        if(id.length()>=0 && id.length()<9){
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
        boolean Nao_existe_id_igual= (stream.getLinhaEspecifica(id).equals("Não existe"))?true:false;
        if(Nao_existe_id_igual==true){
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

    
                System.out.println("Deseja criar outra tarefa?(S/N)");
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
        }else{
            System.out.println("Esse ID já existe");
            return this.MenuInicial = false;
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
    public void limpa_tela(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    
        }catch(Exception err){
            System.err.println(err);
        }
    }

    @Override
    public void TarefasFeitas() {
        for(String atual:handleFeitas.retornaFeitos()){
            System.out.println(atual);
        }

        System.out.println("    A.Menu Inicial");
        String escolha = leitor.nextLine();
        if(escolha.equalsIgnoreCase("A")){
            this.MenuInicial = true;
        }else{
            limpa_tela();
            handleError.optInvalida();
        }
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


    private void MudaPrioridade(String idTarefa,String linha){
        //Acessa a linha, retira o valor que está em prioridade
        //Muda para a nova prioridade
        //Sobrescreve a linha
        
        String prioridade = miner.getPrioridade(linha);
        System.out.println("Qual a nova prioridade?:");
        if(prioridade.equalsIgnoreCase("BACKLOG ")){
            System.out.println("    A.Desenvolvimento");
            System.out.println("    B.Concluida");
            String escolha = leitor.nextLine();
            if(escolha.equalsIgnoreCase("A")){
                String novaLinha = miner.mudancaPrioridade(linha,"DESENVOL");
                int index = stream.getIndex();
                stream.system_save.setNovaLinha(index,novaLinha);
            }else if(escolha.equalsIgnoreCase("B")){
                String novaLinha = miner.mudancaPrioridade(linha,"CONCLUID");
                int index = stream.getIndex();
                stream.system_save.setNovaLinha(index,novaLinha);
            }else{
                handleError.optInvalida();
            }
        }else if(prioridade.equals("DESENVOL")){
            System.out.println("    A.Backlog");
            System.out.println("    B.Concluida");
            String escolha = leitor.nextLine();
            if(escolha.equalsIgnoreCase("A")){
                String novaLinha = miner.mudancaPrioridade(linha,"BACKLOG ");
                int index = stream.getIndex();
                stream.system_save.setNovaLinha(index,novaLinha);
            }else if(escolha.equalsIgnoreCase("B")){
                String novaLinha = miner.mudancaPrioridade(linha,"CONCLUID");
                int index = stream.getIndex();
                stream.system_save.setNovaLinha(index,novaLinha);
            }else{
                handleError.optInvalida();
            }
        }else{
            System.out.println("    A.Backlog");
            System.out.println("    B.Desenvolvimento");
            String escolha = leitor.nextLine();
            if(escolha.equalsIgnoreCase("A")){
                miner.mudancaPrioridade(linha,"BACKLOG ");
            }else if(escolha.equalsIgnoreCase("B")){
                miner.mudancaPrioridade(linha,"DESENVOL");
            }else{
                handleError.optInvalida();
            }
        }
    }

    @Override
    public void MudarPrioridade() {
        System.out.println("Digite o ID da tarefa");
        String id = leitor.nextLine();
        String linha = stream.getLinhaEspecifica(id);
        if(linha == "err"){
            handleError.Input_invalido();
        }else{
            limpa_tela();
            System.out.println(linha);
            System.out.println("    A.Alterar prioridade");
            System.out.println("    B.Sair");
            String escolha = leitor.nextLine();
            if(escolha.equalsIgnoreCase("A")){
                MudaPrioridade(id, linha);
            }else if(escolha.equalsIgnoreCase("B")){
                this.MenuInicial = true;
                this.mudandoPrioridade = false;
            }else{
                handleError.optInvalida();
            }
        }
        
        
    }

    @Override
    public void Sair() {
        limpa_tela();  

        for(Tarefa t:tarefas){
            stream.system_save.setLinhas_do_Txt(t.toString());
        }
        stream.Escreve(stream.system_save.getLinhas());
        System.out.println("Obrigado por utilizar"); 
        this.estado = false;

        
    }

    public void main(){
        while(this.estado == true){
            if(this.Start == true){
                this.Start = stream.inicializa();
            }
            if(this.MenuInicial == true){
                //limpa_tela();  
                MostraMenu();
            }
            else{
                if(this.Visualizar == true){
                    //limpa_tela();  
                    Visualizar();
                }else if(this.Inserindo == true){
                    //limpa_tela();  
                    InserirNovaTarefa();
                }else if(this.TarefasFeitas == true){
                    //limpa_tela();  
                    TarefasFeitas();
                }else if(this.Kanbam == true){
                    //limpa_tela();  
                    Kanbam();
                }else if(this.mudandoPrioridade == true){
                    //limpa_tela();  
                    MudarPrioridade();
                }
                else if(this.sair == true){
                    Sair();
                }
            }
        }
    }

    
}
