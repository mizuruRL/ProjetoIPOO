/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Enumerated class responsible for defining every artifact type and each of its descriptions.
 * @author André Dias, Margarida Maunu
 */
public enum ArtifactType {
    ATTACKPACK("Boosts AP."), 
    DEFENSEPACK("Boosts DP"), 
    MONSTER("Stronger Enemy."), 
    ALIEN("Weaker Enemy."),
    MONETARYSMALL("Turns coins into XP!"), 
    MONETARYMEDIUM("Turns coins into XP!"), 
    MONETARYLARGE("Turns coins into XP!"), 
    KEY("They Key to open the vault!"),
    VAULT("The Vault. A key is required to open it...");
    
    private final String description;
    
    /**
     * Constructor of class ArtifactType.
     * @param description - ArtifactType's description.
     */
    private ArtifactType(String description) {
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    
    
}