package projetofinal;
/**
 * Class responsible for managing command actions.
 * @author joao.capinha *edited by André Dias, Margarida Maunu.*
 */
public class CommandActions {

    public CommandActions() {
    }

    /**
     * Metodo que validaa se o comando recebido é igual a um dos comandos
     * reconhecidos pelo programa. Não é case sensitive.
     *
     * @param command
     * @return
     */
    public boolean isCommand(String command) {
        return (command.equalsIgnoreCase("MYSELF")
                || command.equalsIgnoreCase("SEE")               
                || command.equalsIgnoreCase("GET")
                || command.equalsIgnoreCase("DROP")
                || command.equalsIgnoreCase("MOVE")
                || command.equalsIgnoreCase("ATTACK")
                || command.equalsIgnoreCase("USE")
                || command.equalsIgnoreCase("SHOW")
                || command.equalsIgnoreCase("HELP")
                || command.equalsIgnoreCase("QUIT"));

    }

    /**
     * Metodo responsavel pela apresentação da listagem de todos os comandos
     * possiveis.
     */
    public void showAllCommands() {
        System.out.println("\nYou can enter one of the following valid actions:"
                + "\n\t> ATTACK {Artifact Name}"
                + "\n\t> GET {Artifact Name}"
                + "\n\t> DROP {Artifact Name}"
                + "\n\t> USE {Artifact Name}"
                + "\n\t> MOVE {NORTH, SOUTH, EAST, WEST}"
                + "\n\t> SEE {CHEST, BAG}"
                + "\n\t> SEE"
                + "\n\t> MYSELF"
                + "\n\t> SHOW"
                + "\n\t> SHOW {ALL, STATS, LOG}"
                + "\n\t> HELP"
                + "\n\t> HELP {Command Name}"
                + "\n\t> QUIT\n");
    }

}
