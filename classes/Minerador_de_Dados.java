package classes;

public class Minerador_de_Dados {
    public String getAutor(String linha){
    	int estado =1;
    	String result = "";
    	for(int contador_de_letra=0;contador_de_letra<linha.length();contador_de_letra++) {
    		char atual = linha.charAt(contador_de_letra);
    		if(estado ==1) {
    			if(atual =='|') {
    				estado =2;
    			}
    		}else if(estado ==2) {
    			if(atual == '|') {
    				estado =3;
    			}
    		}else if(estado ==3) {
    			if(atual == '|') {
    				estado =4;
    			}
    		}else if(estado ==4) {
    			if(atual !='#') {
    				if(atual !='_') {
    					result+=atual;
    					estado =4;
    				}
    			}
    		}
    			
    	}
    	return result;
    }
    public void getAtividade(){

    }
    
    public String mudancaPrioridade(String linha,String novaPrioridade){
        String linha_final = "";
        int estado =1;
        for(int contador_de_letra =0;contador_de_letra<linha.length();contador_de_letra++){
            char atual = linha.charAt(contador_de_letra);
            if(estado==1){
                if(atual!='|'){
                    linha_final+=atual;
                }else{
                    linha_final+='|';
                    estado =2;
                }
            }else if(estado ==2){
                if(atual!='|'){
                    linha_final+=atual;
                }else{
                    linha_final+='|';
                    estado =3;
                }
            }else if(estado ==3){
                linha_final+=novaPrioridade;
                estado =4;
            }else if(estado==4){
                if(atual!='|'){
                    estado =4;
                }else{
                    linha_final += atual;
                    estado =5;
                }
            }else{
                if(atual!='#'){
                    linha_final+=atual;
                }else{
                    linha_final+='#';
                }
            }
        }
        
        return "#"+linha_final+"#";
    }
    public String getPrioridade(String linha){
        int estado =1;
        String prioridade = "";
    
        for(int k=0;k<linha.length();k++){
            char atual = linha.charAt(k);
            if(estado == 1){
                if(atual != '|'){
                    estado =1;
                }
                else{
                    estado = 2;
                }
            }
            else if(estado ==2){
                if(atual !='|'){
                    estado =2;
                }else{
                    estado =3;
                }
            }else if(estado ==3){
                if(atual!='|'){
                    prioridade+=atual;
                }else{
                    estado =4;
                }
            }
        }
        return prioridade;
    }
}
