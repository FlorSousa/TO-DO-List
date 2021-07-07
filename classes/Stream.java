package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Stream {
    private HandleError handleError = new HandleError();
    public Arq system_save = new Arq();
    private int linha_mudada;
    String basePath = new File("").getAbsolutePath();
    final String path_save_system = basePath+"/data/save_system.txt";
    
    public boolean inicializa(){
    	System.out.println(path_save_system);
        if(procuraArquivo() == true){
            try{
                FileReader arquivo = new FileReader(path_save_system);
                BufferedReader bytesarquivo = new BufferedReader(arquivo);
                String linha = bytesarquivo.readLine();
    
                while(linha != null){
                    system_save.setLinhas_do_Txt(linha);
                    linha = bytesarquivo.readLine();
                }
                bytesarquivo.close();
            }catch(Exception err){
                System.out.println(err);
            }
            return false;
        }else{
            return false;
        }
        
    }

    public String desenhaHeader(){
        String header = "ID___|Atividade_________________________________|PRIORIDADE|AUTOR__________________________________";
        return header;
        
    }
    
    public String ImprimeKanbam(){
        String header  ="Backlog                                              |Desenvolvimento                                      |Completa                                            ";				
        return header;
    }
    
    public String cortaTag(String l) {
    	String linha = "";
    	int estado = 1;
    	for(int k=0;k<l.length();k++) {
    		char atual = l.charAt(k);
    		if(atual != '#') {
    			linha+=atual;
    		}
    	}
    	return linha;
    }
    
    public String imprimelinhas(){
        String total = "";
        int c =0;
    	for(String l:system_save.getLinhas()){
            if(c%2==0) {
            	String noTag = cortaTag(l) ;
            	total+=noTag+"\n";
            }
            c++;
        }
       return total;
    }
    
    public void Escreve(List<String> Linhas){
        try{
            FileWriter fwriter = new FileWriter(path_save_system); 
            BufferedWriter bwriter = new BufferedWriter(fwriter);
            for(String t:Linhas){
                bwriter.write(t+"\n");
                bwriter.flush();
            }
            bwriter.close();
        }catch(Exception err){
            handleError.Input_invalido();
        }
    }
    
    public String getLinhaEspecifica(String id){
        int c =0;
        List<String> linhasValidas = new ArrayList<>();
        boolean error = true;
        String fim ="Não Existe";
        for(String linha:system_save.getLinhas()){
            if(c%2==0){
                linhasValidas.add(linha);
            }
            c++;
        }
        
        String TodosId = pegaId(linhasValidas);
        String[] lista_ids = dividirEConquistar(TodosId);
        int[] arr_dados = verifica_igualdade(lista_ids, id);
        int numero_da_linha = Leitor_de_verifica_igualdade(arr_dados);
        error = (numero_da_linha>=0)?true:false;
        if(error==true){
            String linha_encontrada = linhasValidas.get(numero_da_linha);
            linha_mudada = numero_da_linha;
            fim = linha_encontrada;
        }
        return fim;
        
    }

    private int Leitor_de_verifica_igualdade(int[] dados){
        int index =-1;
        if(dados[1] == 1){
            index = dados[0];
        }
        return index;
    
    }

    private String[] dividirEConquistar(String todos){   
        String buffer = "";
        int numero_de_ids = todos.length()/4;
        String[] arr_id = new String[numero_de_ids];
        int index_atual = 0;
        
        for(int k =0;k<todos.length();k++){
            char letra = todos.charAt(k);
            if(buffer.length()<4){
                buffer+=letra;
            }
            if(buffer.length()==4){
                if(index_atual<numero_de_ids){
                    arr_id[index_atual]= buffer;
                    index_atual++;
                }
                buffer="";
            }
        }
        return arr_id;
    }

    private int[] verifica_igualdade(String[] lista,String IdSelecionado){
        boolean retorno = false;
        int check_adicional =0;
        int numero_index = 0;
        
        int[] conjunto = new int[2];
        for(int k=0;k<lista.length;k++){
        	retorno = (IdSelecionado.equals(lista[k])) ? true : false;
        	if(retorno == true){
                numero_index = k;
                check_adicional =1;
                conjunto[0] = k;
                conjunto[1] = check_adicional;
                linha_mudada = numero_index;
        	}
            else{
                conjunto[0] = numero_index;
                conjunto[1] = check_adicional;
            }
        }
        return conjunto;
    }

    private String pegaId(List<String> linha){
        int estado = 1;
        String todosId = "";
        
        
        for(String l:linha){
            for(int cont =0;cont<=4;cont++){
                char caractere_atual = l.charAt(cont);
                if(estado == 1){
                    if(caractere_atual =='#'){
                        estado = 2;
                    }
                }
                if(estado ==2){
                    if(caractere_atual !='|'){
                        if(caractere_atual != '#'){
                            todosId += caractere_atual;
                        }
                    }
                }
               
            }
        }
     
        return todosId;
    }

    public int getIndex(){
        return linha_mudada;
    }
    

    public boolean procuraArquivo(){
        try{
            FileReader arquivo = new FileReader("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            arquivo.close();
            return true;
        }catch(Exception err){
            handleError.errorArquivo();
            return false;
        }
    }
   
}
