package projetofinal;
import java.util.Scanner;


/**
 *
 * @author joao.capinha
 */
public class Parser {

    private CommandActions commands;  // Classe responsavel pela validação dos comandos válidos
    private Scanner reader;

    public Parser() {
        commands = new CommandActions();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine = "";   // Armazena String completa introduzida
        String word1;
        String word2;

        System.out.print("> ");
        inputLine = reader.nextLine();

        word1 = readWord(inputLine, 0); // Obter primeira palavra
        word2 = readWord(inputLine, 1); // Obter segunda palavra

        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command("", word2);
        }
    }

    /**
     * Metodo que recebe uma String e um index, e devolve a palavra referente a
     * essa mesma posição na String.
     *
     * @param string
     * @param index
     * @return
     */
    private String readWord(String string, int index) {
        String allWords[] = string.split(" ");
        String word = "";

        if (allWords.length-1 >= index) {
            word = allWords[index];
        }
        return word;
    }

    /**
     * Metodo que apresenta todos os comandos reconhecidos pela aplicação.
     */
    public void showCommands() {
        commands.showAllCommands();
    }
    
     public String getText(String question){
        System.out.println(question);
        return reader.nextLine();
    }
}
