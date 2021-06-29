package classes;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Stream {
    void desenhaHeader(){
        String hasgtag ="#######################################################################################################";
        String vazio =  "#        |                                         |        |                                         #";
        String header = "#   id   |               Atividade                 |  Hora  |                autor                    #";
        String separador = "#________|_________________________________________|________|_________________________________________#";
        System.out.println(hasgtag);
        System.out.println(vazio);
        System.out.println(header);
        System.out.println(separador);
    }
    void imprimelinhas(){
        try{
            Path caminho = Paths.get("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            System.out.println(leitura);
        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    boolean procuraArquivo(){
        boolean retorno = false;
        try{
            Paths.get("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/save_system.txt");
            retorno = true;
            return retorno;
        }catch(Exception err){
            return retorno;
        }
    }
}
