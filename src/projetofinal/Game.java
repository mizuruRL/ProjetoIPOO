/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Class responsible for all of the game's logic.
 * @author josea, Adaptado por *André Dias, Margarida Maunu*
 * 
 */
public class Game {
    
    private Player player;
    private PlayGround base;
    private boolean gameOver;
    private Logger gameLog;
    private Statistic stats;
    private Parser parser;
    
    /**
     * Constructor of class Game.
     */
    public Game() {
        base = new PlayGround();
        stats = new Statistic();
        parser = new Parser();
    }
    /**
     * Method responsible for starting the game.
     */
    public void Start(){
        player = new Player(parser.getText("Please insert your username: "));
        gameLog = new Logger(player.getName());
        Sector[] sectors = base.generateBase();
        player.setCurrentSector(sectors [0]);
        gameOver = false;
        System.out.println(greet());
        System.out.println(player.getCurrentSector().toString());
        do {            
            processCommand(parser.getCommand());
        } while (!gameOver);
    }
    
    private String greet() {
        return "\n\nHello, welcome to Planet Escape, the text adventure game!\nYou are trapped in this space base and must find your way out.\nFind the key and use it to open the vault to escape!";
    }
    
    
    /**
     * Method responsible for the pick up mechanic in game.
     * Searches for the artifact in currentSector's chest and adds it to the player's bag.
     * The player can't pick up artifacts of the type VAULT, MONSTER or ALIEN.
     * @param artifactName - Name of the artifact to pick up.
     * @return - String that informs the player about what happened.
     */
    public String pickUp(String artifactName) {
        Chest chest = player.getCurrentSector().getChest();
        Bag bag = player.getBag();
        Artifact artifact = chest.searchArtifact(artifactName);
        
        // verificações
        if(artifact == null)
            return "Artifact not found.\n\n";
        if(artifactIsInvalid(artifact))
            return "You can't pick that up.\n\n";
        if(bag.isFull())
            return "You're overbeared. Try dropping some items first.\n\n";
        if(bag.isUnableToCarry(artifact))
            return "Artifact is too heavy. Try dropping some items first.\n\n";
        
        // ação
        bag.addArtifact(artifact);
        stats.incrementItemsPickedUp(artifactName.toUpperCase());
        chest.removeArtifact(artifact);
        return "Item added!\n\n";     
    }
    
    /**
     * Method responsible for the drop mechanic in game.
     * Searches for the artifact in the player's bag and puts it in currentSector's chest.
     * @param artifactName - Name of the artifact to drop.
     * @return - String with the drop result.
     */
    public String drop(String artifactName) {
        Chest chest = player.getCurrentSector().getChest();
        Bag bag = player.getBag();
        Artifact artifact = bag.searchArtifact(artifactName);
    
        // verificações
        if(artifact == null)
            return "Artifact not found.\n\n";
        if(chest.getNumberOfArtifacts() == 5)
            return "This sector's chest is full.\n\n";
        
        //ação
        chest.addArtifact(artifact);
        bag.removeArtifact(artifact);
        return "Item dropped.\n\n";
    }
    
    /**
     * Method responsible for the use mechanic in game.
     * Searches the player's bag for the artifact and uses it.
     * The player can only use artifacts of the type ATTACKPACK, DEFENSEPACK,
     * MONETARY and KEY.
     * @param artifactName - Name of the artifact to use.
     * @return - String with the use result.
     */
    public String use(String artifactName) {
        Bag bag = player.getBag();
        Chest chest = player.getCurrentSector().getChest();
        Artifact artifact = bag.searchArtifact(artifactName);
        
        // if the artifact doesn't exist.
        if(artifact == null)
            return "Artifact not found.\n\n";
        
        // if the artifact is KEY.
        if(artifact.getType() == ArtifactType.KEY 
                && chest.searchArtifact("Vault") != null){
            return win(); //condição para ganhar.
        }
        
        // if the artifact is ATTACKPACK.
        if(artifact.getType() == ArtifactType.ATTACKPACK) {
            int boostedAttackPower = player.getAttackPoints() + artifact.getAttackPoints();
            
            if(player.getAttackPoints() == player.getMAX_ATTACKPOINTS())
                return "You are already full.\n\n";
            
            if(boostedAttackPower > player.getMAX_ATTACKPOINTS()) {
                player.setAttackPoints(player.getMAX_ATTACKPOINTS());
                stats.incrementItemsUsed(artifact.getName());
                return player.toString();
            }
            player.setAttackPoints(boostedAttackPower);
            stats.incrementItemsUsed(artifactName.toUpperCase());
            bag.removeArtifact(artifact);
            
            return player.toString();
        }
        
        // if the artifact is DEFENSEPACK.
        if(artifact.getType() == ArtifactType.DEFENSEPACK) {
            int boostedDP = player.getDefensePoints() + artifact.getDefensePoints();
            
            if(player.getDefensePoints() == player.getMAX_DEFENSEPOINTS())
                return "You are already full.\n\n";
            
            if(boostedDP > player.getMAX_DEFENSEPOINTS()) {
                player.setDefensePoints(player.getMAX_DEFENSEPOINTS());
                bag.removeArtifact(artifact);
                stats.incrementItemsUsed(artifact.getName());
                return player.toString();
            }
            player.setDefensePoints(boostedDP);
            bag.removeArtifact(artifact);
            stats.incrementItemsUsed(artifact.getName());
            return player.toString();
        }
        
        // if the artifact is MONETARY.
        if(artifact.getType() == ArtifactType.MONETARYSMALL || artifact.getType() == ArtifactType.MONETARYMEDIUM || artifact.getType() == ArtifactType.MONETARYLARGE){
            int boostedXP = artifact.getCoins()/10 + player.getExperiencePoints();
            player.setExperiencePoints(boostedXP);
            bag.removeArtifact(artifact);
            stats.incrementItemsUsed(artifact.getName());
            return player.toString();
        }
            
        return "\n\nYou can't use that here.\n\n";
    }
    
    /**
     * Method responsible for the attack mechanic in game.
     * Searches for the artifact to attack and attacks. If the player has no
     * attackPoints, it wont be able to damage the enemies but the enemies still damage
     * the player. If the player has lower defensePoints than the enemy has attackPoints,
     * the game ends and the player dies. Can only attack ALIEN and MONSTER types.
     * @param artifactName - Name of the artifact to attack.
     * @return - String with information about the attack (damage deal and damage taken).
     */
    public String attack(String artifactName) {
        Chest chest = player.getCurrentSector().getChest();
        Artifact artifact = chest.searchArtifact(artifactName);
        
        if(artifact == null)
            return "Artifact not found.\n\n";
        
        if(artifact.getType() == ArtifactType.ALIEN
            ||artifact.getType() == ArtifactType.MONSTER){
            
            int damageDealt = artifact.getDefensePoints() - player.getAttackPoints();
            int damageTaken;
            int experienceEarned;
            
            if(damageDealt <= 0) {
                damageDealt = artifact.getDefensePoints();
                experienceEarned = artifact.getExperiencePoints();
                player.setAttackPoints(player.getAttackPoints() - damageDealt);
                player.setExperiencePoints(experienceEarned + player.getExperiencePoints());
                chest.removeArtifact(artifact);
                if(artifact.getType() == ArtifactType.ALIEN) stats.incrementEnemiesKilled(artifactName);
                if(artifact.getType() == ArtifactType.MONSTER) stats.incrementEnemiesKilled(artifactName);
                return String.format("Damage dealt: %d\nDamage taken: 0\nExperience earned: %d\n\n", damageDealt, experienceEarned);
            }
            
            if(damageDealt > 0) {
                damageDealt = player.getAttackPoints();
                damageTaken = artifact.getAttackPoints();
                player.setAttackPoints(0);
                player.setDefensePoints(player.getDefensePoints() - damageTaken);
                
                if(player.isDead()) {
                    return lose();
                }
                
                return String.format("Damage dealt: %d\nDamage taken: %d\n\n", damageDealt, damageTaken);
            }
                
        }
        
        return "Invalid Target.\n\n";
    }
    
    /**
     * Method responsible for showing the player all of the stats (pick ups,
     * uses, command uses, enemies killed).
     * @return - String with all of the player's stats.
     */
    public String showStats(){
        return stats.showAllStats();
    }
    
    private String attackPlayer(){
        Chest chest = player.getCurrentSector().getChest();
        Artifact alien = chest.searchArtifact("alien");
        Artifact monster = chest.searchArtifact("monster");
        int damageTaken;
        
        if(alien != null){
            player.setDefensePoints(player.getDefensePoints()-alien.getAttackPoints());
            damageTaken = alien.getAttackPoints();
            if(player.isDead()){
                return lose();
            }
        
        return "Damage taken: " + damageTaken;
        }
        if(monster != null){
            player.setDefensePoints(player.getDefensePoints()-monster.getAttackPoints());
            damageTaken = monster.getAttackPoints();
            if(player.isDead()){
                return lose();
            }
        
        return "Damage taken: " + damageTaken;
        }
        
       return "";     
    }
    
    private String lose() {
        gameOver = true;
        player.setDefensePoints(0);
        return "YOU DIED\n\nTotal time: " + gameLog.totalTime() + "\n\nHere are your final stats:" + player.toString();    
    }
    
    private String win() {
        gameOver = true;
        return "Congratulations! You made it out... Alive!\n\nTotal time: " + gameLog.totalTime() + "\n\n" + player.toString();
    }
    
    /**
     * Method responsible for the move mechanic in game.
     * Checks if the player's selected location is valid and moves the player
     * in said location and updates the player's sector.
     * @param direction - Direction to move to. Possible moves: NORTE, SUL, ESTE, ou OESTE.
     * @return - String with the move result.
     */
    public String move(String direction) {
        Sector sector;
        
        if(direction == null || direction.isEmpty())
            return "Please specify a direction.";
        
        if(direction.equalsIgnoreCase("north")) {
            sector = player.getCurrentSector().getExits(CardinalPoints.NORTH);
            
            if(sector != null) {
                return updateCurrentSector(sector);
            }
        }
        
        if(direction.equalsIgnoreCase("south")) {
            sector = player.getCurrentSector().getExits(CardinalPoints.SOUTH);
            
            if(sector != null) {
                return updateCurrentSector(sector);
            }
        }
        
        if(direction.equalsIgnoreCase("east")) {
            sector = player.getCurrentSector().getExits(CardinalPoints.EAST);
            
            if(sector != null) {
                return updateCurrentSector(sector);
            }
        }
        
         if(direction.equalsIgnoreCase("west")) {
            sector = player.getCurrentSector().getExits(CardinalPoints.WEST);
            
            if(sector != null) {
                return updateCurrentSector(sector);
            }
        }
    return "This sector does not have an exit for the specified direction.\n\n";
    }
    
    /**
     * Method responsible for the see mechanic in game.
     * Inspects the field, depending on what the player chooses to inspect.
     * @param identifier - Identifier about what to inspect. Can be empty, "chest"
     * or "bag".
     * @return - String with information about the identifier.
     */
    public String inspect(String identifier) {
        
        if(identifier == null || identifier.isEmpty())
            return player.getCurrentSector().toString();
        
        if(identifier.equalsIgnoreCase("chest"))
            return player.getCurrentSector().getChest().toString();
        
        if(identifier.equalsIgnoreCase("bag"))
            return player.getBag().toString();
        
        return "Invalid action.\n\n";
    }
    
    /**
     * Method responsible for the myself mechanic in game.
     * Inspects the player and tells the player it's stats (attackPoints, defensePoints,
     * experiencePoints, bag space).
     * @return  - String with the player's information.
     */
    public String inspectSelf() {
        return player.toString();
    }
    
    /**
     * Ends the game by choice.
     * @return - String with a farewell message and credits.
     */
    public String quit() {
        gameOver = true;
        return "\n\nBye! Thanks for playing Planet Escape!\n\nMade by: André Dias and Margarida Maunu.\n\n";
    }
    
    /**
     * Method responsible for the show all mechanic in game.
     * Shows the player every available sector, as well as its exits and chest contents.
     * @return - String with all the sectors information.
     */
    public String showAll() {
        Sector[] sectors = base.getSectors();
        StringBuilder info = new StringBuilder();
        
        for (Sector sector : sectors) {
            info.append(String.format("Sector: \n%s\n\nChest: \n%s\n\n",
                    sector.toString(),sector.getChest().toString()));
        }
        return info.toString();
    }
    /**
     * Method responsible for the show log mechanic in game.
     * Shows the player every command with a timestamp the player did.
     * @return - String with all the info from the log.
     */
    public String showLog() {
        return "Game Log:\n" + gameLog.toString();
    }
    
    /**
     * Method responsible for the show mechanic in game.
     * Shows the player every sector and available exits for each sector.
     * @return - String with all the sectors and exits.
     */
    public String show(){
    Sector[] sectors = base.getSectors();
        StringBuilder info = new StringBuilder();
        
        for (Sector sector : sectors) {
            info.append(String.format("Sector: \n%s\n\n", sector.toString()));
        }
        return info.toString();
    }
    
    private String updateCurrentSector(Sector sector){
        player.setCurrentSector(sector);
        return player.getCurrentSector().getDescription();
    }
    
    private boolean artifactIsInvalid(Artifact artifact) {
        return artifact.getType() == ArtifactType.ALIEN
                || artifact.getType() == ArtifactType.MONSTER
                || artifact.getType() == ArtifactType.VAULT;
    }
    
    private String explainCommand(String command){
        switch(command){
            case "MYSELF":
                return "Syntax: Myself\nCommand that shows your combat stats and number of artifacts in your bag.";
            case "SEE":
                return "Syntax: See\nCommand that shows you your surroundings.\n\n"
                        + "Syntax: See Chest\nCommand that shows you the current sector's chest contents.\n\n"
                        + "Syntax: See Bag\nCommand that shows you your bag contents and current total weight.";
            case "GET":
                return "Syntax: Get {Artifact Name}\nCommand that lets you pick up an artifact from a chest.\nCan't pick up VAULT, MONSTER or ALIEN\n\n";
            case "DROP":
                return "Syntax: Drop {Artifact Name}\nCommand that lets you drop an artifact from your bag.\n\n";
            case "MOVE":
                return "Syntax: Move {Direction}\nCommand that lets you move in a designated location.\nPossible locations: NORTH, SOUTH, EAST, WEST\n\n";
            case "USE":
                return "Syntax: Use {Artifact Name}\nCommand that lets you use an artifact from your bag.\n\n";
            case "ATTACK":
                return "Syntax: Attack {Artifact Name}\nCommand that lets attack an artifact in your current sector's chest.\nCan only attack MONSTER or ALIEN\n\n";
            case "SHOW":
                return "Syntax: Show\nCommand that shows you every single sector, alongside its possible exits.\n\n"
                        + "Syntax: Show All\nCommand that shows you every single sector, alongside its possible exits and chest contents\n\n"
                        + "Syntax: Show Log\nCommand that shows you every command you used, with a timestamp.\n\n"
                        + "Syntax: Show Stats\nCommand that shows you how many interactions you had with artifacts and how many times you used certain commands.\n\n";
            case "HELP":
                return "Syntax: Help\nCommand that shows you all possible commands.\n\n"
                        + "Syntax: Help {Command Name}\nCommand that shows you what that specific command does, in detail, plus its syntax.";
            case "QUIT": 
                return "Syntax: Quit\nCommand that quits the game, whenever you want.\n\n";
        }
    return "Unknown or invalid command.";
    }
    
    private void processCommand(Command command){
        String firstWord;
        String secondWord;

        if(command.isUnknown())
            System.out.println("Unknown Command. Try typing 'Help' for a list of commands.");
        else{
            firstWord = command.getFirstWord().toUpperCase();
            secondWord = command.getSecondWord().toUpperCase();
            switch(firstWord){
                case "MYSELF" :
                    System.out.println(inspectSelf());
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "SEE" :
                    System.out.println(inspect(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "GET" :
                    System.out.println(pickUp(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "DROP" :
                    System.out.println(drop(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "MOVE" :
                    System.out.println(move(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "USE" : 
                    System.out.println(use(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "ATTACK" :
                    System.out.println(attack(secondWord));
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "SHOW" :
                    switch (secondWord){
                        case "":
                            System.out.println(show());
                            break;
                        case "ALL" :
                            System.out.println(showAll());              
                            break;
                        case "LOG" :
                            System.out.println(showLog());                          
                            break;
                        case "STATS" :
                            System.out.println(showStats());
                            break;
                    }
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "HELP" :
                    if(secondWord == null || "".equals(secondWord)){
                        parser.showCommands();
                    }else {
                        System.out.println(explainCommand(secondWord));
                    }
                    gameLog.createTimestamp(firstWord);
                    stats.incrementCommandsUsed(firstWord);
                    break;
                case "QUIT" : 
                    System.out.println(quit());
                    break;
            }
        }   
    }
    
}