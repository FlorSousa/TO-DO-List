package classes;

public class Minerador_de_Dados {
    public void getAutor(){

    }
    public void getAtividade(){

    }
    public void mudancaPrioridade(String linha,String novaPrioridade){
        //inverso de getPrioridade
        String metade_inicial = "";
        String metade_final = "";
        int estado =1;
        for(int contador_de_letra =0;contador_de_letra<linha.length();contador_de_letra++){
            char atual = linha.charAt(contador_de_letra);
            if(estado==1){
                if(atual!='|'){
                    metade_inicial+=atual;
                }else{
                    metade_inicial+='|';
                    estado =2;
                }
            }else if(estado ==2){
                if(atual!='|'){
                    metade_inicial+=atual;
                }else{
                    metade_inicial+='|';
                    estado =3;
                }
            }else if(estado ==3){
                //entender
            }else{
                metade_inicial+=atual;
            }
        }
        System.out.println(metade_inicial);

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
