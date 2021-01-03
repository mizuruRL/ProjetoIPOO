/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Class responsible for defining, managing and building the player.
 * @author André Dias, Margarida Maunu
 */
public class Player {
    private String name;
    
    private Bag bag;
    
    private int attackPoints;
    private int defensePoints;
    private int experiencePoints;
    private Sector currentSector;
    
    private final int MAX_ATTACKPOINTS;
    private final int MAX_DEFENSEPOINTS;
    
    /**
     * Constructor of class Player.
     * @param name - Player's username.
     */
    public Player(String name) {
        this.name = name;
        MAX_DEFENSEPOINTS = 100;
        MAX_ATTACKPOINTS = 100;
        attackPoints = MAX_ATTACKPOINTS;
        defensePoints = MAX_DEFENSEPOINTS;
        experiencePoints = 0;
        bag = new Bag();
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        if(attackPoints < 0) {
            this.attackPoints = 0;
        }else {
        this.attackPoints = attackPoints;
        }
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        if(defensePoints < 0) {
            this.defensePoints = 0;
        }else {
        this.defensePoints = defensePoints;
        }
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Sector getCurrentSector() {
        return currentSector;
    }

    public void setCurrentSector(Sector currentSector) {
        this.currentSector = currentSector;
    }
    
    public Bag getBag() {
        return bag;
    }
    
    public int getMAX_ATTACKPOINTS() {
        return MAX_ATTACKPOINTS;
    }
    
    public int getMAX_DEFENSEPOINTS() {
        return MAX_DEFENSEPOINTS;
    }
    
    public void setBag(Bag bag) {
        this.bag = bag;
    }
    
    /**
     * Method responsible for checking if the player is dead.
     * @return - boolean true if defensePoints are 0, false if otherwise.
     */
    public boolean isDead() {
        return defensePoints <= 0;
    }
    
    @Override
    public String toString(){
        StringBuilder description = new StringBuilder();
        if(!isDead()){
        description.append(String.format("Hello %s", name));
        description.append("\n\n");
        description.append("Here's your stats!:");
        }
        description.append("\n\n");
        description.append(String.format("ap: %d\ndp: %d\nxp: %d", attackPoints, defensePoints, experiencePoints));
        description.append("\n\n");
        description.append(String.format("Your bag has %d artifacts.", bag.getNumberOfArtifacts()));
        description.append("\n\n");
        return description.toString();
    
    }
    
    
}
