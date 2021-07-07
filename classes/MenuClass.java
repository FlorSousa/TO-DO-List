    package classes;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.*;
    
    public class MenuClass implements Menu {
        public List<Tarefa> tarefas = new ArrayList<>();
        private HandleError handleError = new HandleError();
        private Minerador_de_Dados miner = new Minerador_de_Dados();
        private Boolean estado = true;
        private Boolean MenuInicial = true;
        private Boolean Visualizar = false;
        private Boolean Inserindo = false;
        private Boolean TarefasFeitas = false;
        private Boolean exibeAutores = false;
        private Boolean sair = false;
        private Boolean mudandoPrioridade = false;
        private Stream stream = new Stream();
        private int caracteresAtividade =41;
        private int caracteresPrioridade = 8;
        private int caracteresAutor = 41;
        public boolean Start = true;
        
        @Override
        public boolean Visualizar() {
            String header = stream.desenhaHeader();
            String linhas = stream.imprimelinhas();
            String escolha = JOptionPane.showInputDialog(header+"\n"+"\n"+linhas+"\nA.Inserir nova tarefa\nB.Menu");
            if(escolha == null) {
            	this.MenuInicial = true;
            }
            else if(escolha.equalsIgnoreCase("B")){
                this.MenuInicial = true;
            }else if(escolha.equalsIgnoreCase("A")){
                this.Inserindo = true;
            }
            return this.Visualizar = false;
        }

        public boolean trata_erro(String id, String atividade,String prioridade,String autor){
            boolean Estado = false;
        
            if(id.length()==4){
                if(atividade.length()<=50){
                    if(prioridade.equals("BACKLOG") || prioridade.equals("DESENVOL") || prioridade.equals("CONCLUID")){
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
            for(int cont =0; cont<numeroBranco;cont++){
                    campo += "_";
            }
            return campo;
        }

        String TransformPrioridade(String prioridade){
            if(prioridade.equals("1")){
                return "BACKLOG";
            }else if(prioridade.equals("2")){
                return "DESENVOL";
            }else if(prioridade.equals("3")){
                return "CONCLUID";
            }else{
                return "ERRO";
            }
        }

        @Override
        public boolean InserirNovaTarefa() {
        	String id = JOptionPane.showInputDialog("Crie um ID de 0 a 9999");
        	if(id!=null) {
            boolean Nao_existe_id_igual= (stream.getLinhaEspecifica(id).equalsIgnoreCase("Não existe"))?true:false;
            System.out.println(Nao_existe_id_igual);
            
        	if(Nao_existe_id_igual == false) {
        		JOptionPane.showMessageDialog(null, "ESSE ID JÁ EXISTE");
        	}else {
        		String atividade = JOptionPane.showInputDialog("Digite uma atividade --- Maximo de 50 caracteres");
                String prioridade = JOptionPane.showInputDialog("Qual o estado da tarefa:\n 1-Backlog\n 2-Desenvolvimento\n 3-Concluido");
                prioridade = TransformPrioridade(prioridade);
                String autor = JOptionPane.showInputDialog("Quem está criando essa tarefa?");
                if(trata_erro(id,atividade,prioridade,autor) ==true) {
                	int EmBrancoAtividade = caracteresAtividade - atividade.length();
                    int EmBrancoPrioridade = caracteresPrioridade - prioridade.length();
                    int EmBrancoAutor = caracteresAutor - autor.length();

                    atividade = mudaCampos(atividade,EmBrancoAtividade);
                    prioridade = mudaCampos(prioridade, EmBrancoPrioridade);
                    autor = mudaCampos(autor, EmBrancoAutor);
        
                    Tarefa novaTarefa = new Tarefa(id,atividade,prioridade,autor);
                    tarefas.add(novaTarefa);
                    
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja criar outra tarefa?");
                    
                    if(escolha == 1) {
                    	this.Inserindo = false;
                    	this.MenuInicial = true;
                    }else if(escolha == 0) {
                    	//pass
                    }else {
                    	this.Inserindo = false;
                    	this.MenuInicial = !this.Inserindo;
                    }
                }else {
                	this.MenuInicial = true;
                	this.Inserindo = false;
                }
        	}
            
           }else {
        	   JOptionPane.showMessageDialog(null, "Erro");
        	   this.MenuInicial = true;
        	   this.Inserindo = false;
           }
        	return this.Inserindo;
        }

        @Override
        public boolean MostraMenu() {
        	String escolha = JOptionPane.showInputDialog("Lista TO-DO\nSELECIONE UMA OPï¿½ï¿½O\n A.Visualizar suas tarefas\n B.Inserir nova tarefa\n C.Tarefas Concluidas\n D.Mudar prioridade\n E.Listar por autores\n F.Sair\n ");
            if(escolha == null) {
            	this.sair = true;
            	this.MenuInicial = false;
            }else if(escolha.equalsIgnoreCase("A")){
                    this.Visualizar = true;
            }else if(escolha.equalsIgnoreCase("B")){
                    this.Inserindo = true;
            }else if(escolha.equalsIgnoreCase("C")){
                    this.TarefasFeitas = true;
            }else if(escolha.equalsIgnoreCase("D")) {
            	this.mudandoPrioridade = true;
            	
            }else if(escolha.equalsIgnoreCase("E")) {
            	this.exibeAutores = true;
            }
            else if(escolha.equalsIgnoreCase("F")) {
            		this.Sair();
            	
            }else {
            	handleError.optInvalida();
            }
            return this.MenuInicial = false;
        }
        
        

        @Override
        public void TarefasFeitas() {
            String linhasFeitas ="";
        	for(String atual:stream.system_save.getFeitas()){
        		linhasFeitas +=atual+"\n";
            }

            String escolha = JOptionPane.showInputDialog("TAREFAS CONCLUIDAS:\n"+linhasFeitas+"\nA.Menu\n");
            if(escolha == null) {
            	this.MenuInicial = true;
            	this.TarefasFeitas = false;
            }
            else if(escolha.equalsIgnoreCase("A")){
                this.MenuInicial = true;
                this.TarefasFeitas = false;
            }else{
               
                handleError.optInvalida();
            }
        }

        @Override
        public void ExibirAutores() {
        	String autores = "";
        	for(String atual:stream.system_save.getLinhas()) {
        		autores+= miner.getAutor(atual)+"\n";
        	}
        	String escolha = JOptionPane.showInputDialog(autores+"A.Menu");
        	if(escolha == null) {
        		this.MenuInicial = true;
        	}
        	else if(escolha.equalsIgnoreCase("A")) {
        		this.exibeAutores = false;
        		this.MenuInicial = true;
        	}
        }


        private void MudaPrioridade(String idTarefa,String linha){
            
            String prioridade = miner.getPrioridade(linha);
            String escolha = JOptionPane.showInputDialog("Qual a nova prioridade?\nA.Backlog\nB.Desenvolvimento\nC.Concluida");
            String linha_nova = "";
            int num = 0;
            if(escolha.equalsIgnoreCase("A")) {
            	if(prioridade.equalsIgnoreCase("BACKLOG_")) {
            		JOptionPane.showMessageDialog(null, "Essa tarefa jï¿½ estï¿½ em Backlog");
                    this.mudandoPrioridade = false;

            	}else {
            		linha_nova = miner.mudancaPrioridade(linha, "BACKLOG");
            		num = stream.getIndex();
            		stream.system_save.setNovaLinha(num,linha_nova);
            		JOptionPane.showMessageDialog(null, "Feito, sua atualizaï¿½ï¿½o sera feita ao encerrar o programa, certifique-se de fechar o programa corretamente");
                    this.mudandoPrioridade = false;
                    
            	}
            }else if(escolha.equalsIgnoreCase("B")) {
            	if(prioridade.equalsIgnoreCase("DESENVOL")) {
            		JOptionPane.showMessageDialog(null, "Essa tarefa jï¿½ estï¿½ em Desenvolvimento");
                    this.mudandoPrioridade = false;

            	}else {
            		linha_nova=miner.mudancaPrioridade(linha, "DESENVOL");
            		num = stream.getIndex();
            		stream.system_save.setNovaLinha(num,linha_nova);
            		JOptionPane.showMessageDialog(null, "Feito, sua atualizaï¿½ï¿½o sera feita ao encerrar o programa, certifique-se de fechar o programa corretamente");
                    this.mudandoPrioridade = false;

            	}
            }else if(escolha.equalsIgnoreCase("C")) {
            	if(prioridade.equalsIgnoreCase("CONCLUID")) {
            		JOptionPane.showMessageDialog(null, "Essa tarefa jï¿½ estï¿½ Concluida");
                    this.mudandoPrioridade = false;

            	}else {
            		linha_nova=miner.mudancaPrioridade(linha, "CONCLUID");
            		num = stream.getIndex();
            		stream.system_save.setNovaLinha(num,linha_nova);
            		JOptionPane.showMessageDialog(null, "Feito, sua atualizaï¿½ï¿½o sera feita ao encerrar o programa, certifique-se de fechar o programa corretamente");
                    this.mudandoPrioridade = false;
            	}
            }else {
            	JOptionPane.showMessageDialog(null, "DIGITOU ALGO ERRADO");
                this.mudandoPrioridade = false;
            }
           
           this.MenuInicial = true; 
        }

        @SuppressWarnings("unused")
		@Override
        public void MudarPrioridade() {
            String id = JOptionPane.showInputDialog("Insira o id da tarefa");
            if(id!=null) {
                boolean Nao_existe_id_igual= (stream.getLinhaEspecifica(id).equals("Não Existe"))?true:false;
                if(Nao_existe_id_igual == false) {
                	String linha = stream.getLinhaEspecifica(id);
                	String semTag = stream.cortaTag(linha);
                	String escolha = JOptionPane.showInputDialog(semTag+"\n"+"\nA.Alterar prioridade\nB.Menu");
                	if(escolha.equalsIgnoreCase("A")) {
                		MudaPrioridade(id,semTag);
                	}else if(escolha.equalsIgnoreCase("B")) {
                		this.MenuInicial = true;
                		this.mudandoPrioridade = false;
                	}else {
                		handleError.optInvalida();
                	}
                }
                else {
                	handleError.idNaoExiste();
                	
                }
            }else {
            	this.mudandoPrioridade = false;
            	this.MenuInicial = true;
            }
          
            
        }

        @Override
        public void Sair() {
              
            
            for(Tarefa t:tarefas){
                stream.system_save.setLinhas_do_Txt(t.toString());
            }
            stream.Escreve(stream.system_save.getLinhas());
            JOptionPane.showMessageDialog(null, "Obrigado.Sua lista foi atualizada!");
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
                    }else if(this.exibeAutores == true){
                        //limpa_tela();  
                        ExibirAutores();
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
