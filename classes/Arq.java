package classes;
import java.util.ArrayList;
import java.util.List;

public class Arq{
    //recebe linhas do txt
    public List<String> Linhas_do_Txt = new ArrayList<>(); 

    public void setLinhas_do_Txt(String linha){
        Linhas_do_Txt.add(linha);
    }
    public void setNovaLinha(int index,String linha){
        Linhas_do_Txt.set(index, linha);
    }
    public List<String> getLinhas(){
        return Linhas_do_Txt;
    }
}