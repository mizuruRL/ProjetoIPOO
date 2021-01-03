/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Class responsible for defining and building artifacts.
 * @author André Dias, Margarida Maunu
 */
public class Artifact {
    private String artifactName;
    private ArtifactType type;
    private int attackPoints;
    private int defensePoints;
    private double weight;
    private int experiencePoints;
    private int coins;
    
    /**
     * Constructor of class Artifact.
     * @param artifactName - String of the artifact's name.
     * @param type - ArtifactType of the artifact.
     * @param attackPoints - int attackPoints of the artifact.
     * @param defensePoints - int defensePoints of the artifact.
     * @param weight - double weight of the artifact.
     * @param experiencePoints - int experiencePoints of the artifact
     * @param coins - int coins of the artifact.
     */
    public Artifact(String artifactName, ArtifactType type, int attackPoints, int defensePoints, double weight, int experiencePoints, int coins) {
        this.artifactName = artifactName;
        this.type = type;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.weight = weight;
        this.experiencePoints = experiencePoints;
        this.coins = coins;
    }
    
    public String getName() {
        return artifactName;
    }

    public ArtifactType getType() {
        return type;
    }

    public void setType(ArtifactType type) {
        this.type = type;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    /**
     * Checks if artifact is dead.
     * @return - Boolean true if dead, false if otherwise.
     */
    public boolean isDead() {
        return defensePoints <= 0;
    }
    
    @Override
    public String toString(){
        StringBuilder artifact = new StringBuilder();
        artifact.append(String.format("%s :'%s' | ", artifactName, type.getDescription()));
        if(attackPoints != 0) artifact.append(String.format("AP: %d ", attackPoints));
        if(defensePoints != 0) artifact.append(String.format("DP: %d ", defensePoints));
        if(weight != 0) artifact.append(String.format("Weight: %f ", weight));
        if(experiencePoints != 0) artifact.append(String.format("XP: %d ", experiencePoints));
        if(coins != 0) artifact.append(String.format("Coins: %d ", coins));
        artifact.append("\n");
        return artifact.toString();
    }
}
