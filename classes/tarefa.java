package classes;

public class tarefa {
    private String id;
    private String Atividade;
    private String Hora;
    private String Autor; 

    public tarefa(Integer id,String Atividade,String Hora,String Autor){
        this.id = id.toString();
        this.Atividade = Atividade;
        this.Hora = Hora;
        this.Autor = Autor;
    }

    public void SalvaTarefa(){
        //pass
    }

}
