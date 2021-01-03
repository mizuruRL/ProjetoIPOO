/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;


/**
 * Class responsible for defining and building the chests.
 * @author André Dias, Margarida Maunu
 */
public class Chest {
    private Artifact[] artifacts;
    private int numberOfArtifacts;
    
    /**
     * Constructor of class Chest.
     */
    public Chest() {
        artifacts = new Artifact[5];
        numberOfArtifacts = 0;
    }

    public Artifact[] getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Artifact[] artifacts) {
        this.artifacts = artifacts;
    }

    public int getNumberOfArtifacts() {
        return numberOfArtifacts;
    }
    
    /**
     * Method that checks if the chest is full.
     * @return - Boolean true if numberOfArtifacts == 5, false if otherwise.
     */
    public boolean isFull() {
        return numberOfArtifacts == 5;
    }
 
    /**
     * Adiciona um artifacto à escolha ao array.
     * @param artifactToAdd Artifacto a ser adicionado.
     */
    public void addArtifact(Artifact artifactToAdd) {
        for (int i = 0; i < artifacts.length; i++) {
            if(artifacts[i] == null) {
                artifacts[i] = artifactToAdd;
                numberOfArtifacts++;
                break;
            }
        }
        
    }

    /**
     * Removes a specified artifact from the chest and sorts the chest to maintain order.
     * @param artifactToRemove - Artifact to be removed.
     */
    public void removeArtifact(Artifact artifactToRemove) {
        for (int i = 0; i < artifacts.length; i++) {
            if(artifacts[i] != null && artifacts[i].getType() == artifactToRemove.getType()) {
                artifacts[i] = null;
                numberOfArtifacts--;
                arraySort();
                break;
            }
        }
    }

    private void arraySort() {
        int idx = 0;
        int chestSize = artifacts.length;
        Artifact[] newArray = new Artifact[chestSize];
        for (int i = 0; i < chestSize ; i++) {
           if(artifacts[i] != null){
               newArray[idx] = artifacts[i];
               idx++;
           }
        }
        artifacts = newArray;
    }

    /**
     * Searches for an artifact through its artifactName.
     * @param artifactName - String of the artifact to search for.
     * @return - an artifact object if found, null if otherwise.
     */
    public Artifact searchArtifact(String artifactName) {
        if(artifactNameIsValid(artifactName)){
            for (Artifact artifact : artifacts) {    
                if(artifact != null && artifactName.equalsIgnoreCase(artifact.getName())) {
                    return artifact;
                }
            }
        }
    return null;
    }
    
    private boolean artifactNameIsValid(String artifactName) {
        if(artifactName == null || artifactName.isEmpty()){
            return false;
        }    
        switch(artifactName.toUpperCase()){
                case "ATTACKPACK": 
                case "DEFENSEPACK": 
                case "MONSTER": 
                case "ALIEN": 
                case "MONEYSMALL":
                case "MONEYMEDIUM": 
                case "MONEYLARGE": 
                case "KEY": 
                case "VAULT": return true;   
            }
    return false;
    }
         
    
    @Override
    public String toString(){
    StringBuilder info = new StringBuilder();
        for (int i = 0; i < numberOfArtifacts; i++) {
            info.append(artifacts[i].toString());
        
        }
    return info.toString();
    }
    
}
