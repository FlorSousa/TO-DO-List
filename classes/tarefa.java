package classes;

public class Tarefa {
    private String id;
    private String Atividade;
    private String Prioridade;
    private String Autor; 

    public Tarefa(String id,String Atividade,String Prioridade,String Autor){
        this.id = id;
        this.Atividade = Atividade;
        this.Prioridade = Prioridade;
        this.Autor = Autor;
    }

    public String getId(){
        return this.id;
    }

    public String getAtividade(){
        return this.Atividade;
    }

    public String getPrioridade(){
        return this.Prioridade;
    }

    public String getAutor(){
        return this.Autor;
    }

    @Override
    public String toString(){
        return "#"+getId() +"|"+getAtividade() +"|"+ getPrioridade() + "|"+getAutor()+"#"+"\n"+"#____|_________________________________________|________|_________________________________________#";
    }

}
