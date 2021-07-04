package classes;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class handleFeitas {
    List<String> Linhas = new ArrayList<>();

    public List<String> retornaFeitos(){
        try{
            FileReader arquivo = new FileReader("C:/Users/getui/Desktop/RPG_Texto/TO-DO-List/data/tarefasRealizadas.txt");
            BufferedReader bytesarquivo = new BufferedReader(arquivo);
            String linha = bytesarquivo.readLine();

            while(linha != null){
                Linhas.add(linha);
                linha = bytesarquivo.readLine();
            }
            bytesarquivo.close();
        }catch(Exception err){
            System.out.println(err);
        }
        return Linhas;
    }

    public void Header(){

    }
}
