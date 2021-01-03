/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.util.ArrayList;

/**
 * Classe responsible for building and managing the player's bag. The Bag is
 * an arrayList of artifact objects.
 * @author André Dias, Margarida Maunu.
 */
public class Bag {
    private ArrayList <Artifact> artifacts;
    private double weight;
    private final double MAX_WEIGHT;
    
    /**
     * Constructor of class Bag.
     */
    public Bag() {
        artifacts = new ArrayList <>();
        MAX_WEIGHT = 2.0;
    }

    public ArrayList <Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList <Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public double getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public int getNumberOfArtifacts() {
        return artifacts.size();
    }
    
    /**
     * Method that checks if the bag is full.
     * @return - boolean true if bag reached its MAX_WEIGHT, false if otherwise.
     */
    public boolean isFull() {
        return getMAX_WEIGHT() <= getWeight();
    }
    
    /**
     * Method that checks if the bag is unable to carry a said artifact due to
     * weight limit constraints.
     * @param artifact - Artifact to check if is carriable.
     * @return - boolean true if the artifact is too heavy to carry, false if otherwise.
     */
    public boolean isUnableToCarry(Artifact artifact) {
        return artifact.getWeight() + getWeight() > getMAX_WEIGHT();
    }
    
    /**
     * Method that adds an artifact to the bag and adds its weight.
     * @param artifact - Artifact to add to bag.
     */
    public void addArtifact(Artifact artifact) {
        if(artifact != null){
            artifacts.add(artifact);
            weight += artifact.getWeight();
        }
    }
    
    /**
     * Method that removes an artifact from the bag and removes its weight.
     * @param artifact - Artifact to remove from bag.
     */
    public void removeArtifact(Artifact artifact) {
        if(artifact != null){
            artifacts.remove(artifact);
            weight -= artifact.getWeight();
        }
    }
    
    /**
     * Method that searches for an artifact through its artifactName in the bag.
     * @param artifactName - Artifact name to search for.
     * @return - Artifact object if found, null if otherwise.
     */
    public Artifact searchArtifact(String artifactName) {
        if(artifactName == null || artifactName.isEmpty())
            return null;
        for (Artifact artifact : artifacts) {
            if(artifactName.equalsIgnoreCase(artifact.getName()))
                return artifact;
        }
        return null;
    }
    
    @Override
    public String toString(){
    StringBuilder info = new StringBuilder();
    info.append("\nYour Bag:");
    info.append("\n\n");
        for (Artifact artifact : artifacts) {
            info.append(artifact.toString());
        }
    info.append(String.format("\nWeight capacity: %f/%f",weight, MAX_WEIGHT ));
    return info.toString();
    }
}
