package classes;

public class ExibeDados {
    public void ExibeTudo(){
        Stream stream_obj = new Stream();
        HandleError handle = new HandleError();
        if(stream_obj.procuraArquivo() == true){
            try{
                stream_obj.desenhaHeader();
                stream_obj.imprimelinhas();

            } catch(Exception erro){
                System.out.println(erro);
            }
        }
        else{
            handle.errorArquivo();
        }
    }
}
