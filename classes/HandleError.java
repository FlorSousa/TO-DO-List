package classes;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class HandleError {
    public void errorArquivo(){
        System.out.println("O arquivo nÃ£o foi encontrado e irÃ¡ ser criado outra vez");
        JOptionPane.showMessageDialog(null, "O arquivo não foi encontrado!");
        try {
            File file = new File("c://hello.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void optInvalida(){
        JOptionPane.showMessageDialog(null, "Opção Invalida");
    }
    public void limiteUltrapassado(){
        JOptionPane.showMessageDialog(null, "Você digitou mais que 50 caracteres");
    }
    public void idMuitoGrande(){
        JOptionPane.showMessageDialog(null, "ID fora dos limites");
    }
    public void LinhaErrada(){
        JOptionPane.showMessageDialog(null, "Erro na recuperação do arquivo");
    }
    public void Input_invalido(){
        JOptionPane.showMessageDialog(null, "Você digitou algo invalido");
    }
    public void Ex_err(Exception err){
        JOptionPane.showMessageDialog(null, err);
    }
    public void save_err(){
        System.out.println("Erro ao salvar arquivo !");
        JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo");
    }
    public void idNaoExiste() {
    	JOptionPane.showMessageDialog(null, "Esse Id não existe");
    }
}
