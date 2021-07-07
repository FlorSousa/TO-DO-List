package classes;
import java.util.ArrayList;
import java.util.List;

public class Arq{
    //recebe linhas do txt
    public List<String> Linhas_do_Txt = new ArrayList<>(); 
    private Minerador_de_Dados miner = new Minerador_de_Dados();
    
    public void setLinhas_do_Txt(String linha){
        Linhas_do_Txt.add(linha);
    }
    public void setNovaLinha(int index,String linha){
        Linhas_do_Txt.set(index, linha);
    }
    public List<String> getLinhas(){
        return Linhas_do_Txt;
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
    
    public List<String> getFeitas(){
    	List<String> feitas = new ArrayList<>();
    	
    	for(String l:Linhas_do_Txt) {
    		String linha = miner.getPrioridade(l);
    		if(linha.equals("CONCLUID")) {
    			String result = cortaTag(l);
    			feitas.add(result);
    		}
    	
    	}
    	return feitas;
    }
}