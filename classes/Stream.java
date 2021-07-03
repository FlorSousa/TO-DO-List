package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stream {
    private HandleError handleError = new HandleError();

    void desenhaHeader(){
        String hashtag ="#######################################################################################################";
        String vazio =  "#        |                                         |        |                                         #";
        String separador = "#________|_________________________________________|________|_________________________________________#";
        String header = "#   id   |               Atividade                 | PRIOR. |                autor                    #";
        System.out.println(hashtag);
        System.out.println(vazio);
        System.out.println(header);
        System.out.println(separador);
    }
    void ImprimeKanbam(){
        String hashtag ="##########################################################################################################################################################";
        String header = "#Backlog                                          |Desenvolvimento                                    |Completa                                          #";
        String vazio =  "#                                                 |                                                   |                                                  #";
        String separado="#_________________________________________________|___________________________________________________|__________________________________________________#"; 
        System.out.println(hashtag);
        System.out.println(vazio);
        System.out.println(header);
        System.out.println(separado);
    }
    void imprimelinhas(){
        try{
            FileReader arquivo = new FileReader("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            BufferedReader bytesarquivo = new BufferedReader(arquivo);
            String linha = bytesarquivo.readLine();

            while(linha != null){
                System.out.println(linha);
                linha = bytesarquivo.readLine();
            }
            bytesarquivo.close();
        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    void Escreve(String Linha){
        try{
            //adiciona Linha
            //adiciona separador
        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    String getLinhaEspecifica(String id){
        int c =0;
        List<String> linhasValidas = new ArrayList<>();
        String[] arr = new String[10];
        boolean error = true;
        String fim ="Não existe";
        try{
            FileReader arquivo = new FileReader("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            BufferedReader bytesarquivo = new BufferedReader(arquivo);
            String linha = bytesarquivo.readLine();

            while(linha != null){
                if(c%2==0){
                    linhasValidas.add(linha);
                }
                linha = bytesarquivo.readLine();
                c++;
            }
            bytesarquivo.close();

        }catch(Exception err){
            System.out.println(err);
        }
        String TodosId = pegaId(linhasValidas);
        String[] lista_ids = dividirEConquistar(TodosId);
        int[] arr_dados = verifica_igualdade(lista_ids, id);
        int numero_da_linha = Leitor_de_verifica_igualdade(arr_dados);
        error = (numero_da_linha>0)? false:true;
        if(error==false){
            String linha_encontrada = linhasValidas.get(numero_da_linha);
            fim = linha_encontrada;
        }

        return fim;
        
    }

    int Leitor_de_verifica_igualdade(int[] dados){
        int index =-1;
        if(dados[1] == 1){
            index = dados[0];
        }
        return index;
    
    }

    String[] dividirEConquistar(String todos){   
        String buffer = "";
        int numero_de_ids = todos.length()/8;
        int index_atual = 0;
        String[] arr_id = new String[numero_de_ids];
        
        for(int k =0;k<todos.length();k++){
            char letra = todos.charAt(k);
            if(buffer.length()<8){
                buffer+=letra;
            }
            if(buffer.length()==8){
                if(index_atual<numero_de_ids){
                    arr_id[index_atual]= buffer;
                    index_atual++;
                }
                buffer="";
            }
        }
        return arr_id;
    }

    int[] verifica_igualdade(String[] lista,String IdSelecionado){
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
            }
            else{
                conjunto[0] = numero_index;
                conjunto[1] = check_adicional;
            }
        }
        return conjunto;
    }

    String pegaId(List<String> linha){
        int estado = 1;
        String todosId = "";
        for(String l:linha){
            for(int cont =0;cont<10;cont++){
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

    

    boolean procuraArquivo(){
        try{
            FileReader arquivo = new FileReader("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            return true;
        }catch(Exception err){
            handleError.errorArquivo();
            return false;
        }
    }
}
