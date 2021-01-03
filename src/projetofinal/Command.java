package projetofinal;

/**
 * Classe que representa um comando introduzido pelo utilizador
 *
 * @author joao.capinha
 */
public class Command {

    private final String firstWord;
    private final String secondWord;

    public Command(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return ("".equals(firstWord));
    }

    public boolean hasSecondWord() {
        return (!"".equals(secondWord));
    }
    
   
    
}
