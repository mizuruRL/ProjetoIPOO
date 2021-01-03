/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Classe responsável pela caracterização de cada setor do jogo.
 * @author josea. Adaptado por André Dias, Margarida Maunu
 */
public class Sector {
    
    private String id;
    private String description;
    private Sector[] exits;
    private Chest chest;

    public Sector(String id, String description) {
        this.id = id;
        this.description = description;
        chest = new Chest();
        exits = new Sector[4];
    }
    
    public Sector getExits(CardinalPoints cardinalPoint){
        return exits[cardinalPoint.ordinal()];
    }
    
    public void setExits(CardinalPoints cp, Sector sector){
        exits[cp.ordinal()] = sector;
    }
    
    public void setChest(Chest chest) {
        this.chest = chest;
    }
    
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Sector[] getExits() {
        return exits;
    }
    
    public Chest getChest() {
        return chest;
    }
    
    @Override
    public String toString() {
        CardinalPoints[] cardinalPoints = CardinalPoints.values();
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(id);
        sb.append(", ");
        sb.append(description);
        sb.append("\n");
        sb.append("---SAIDAS---\n");
        
        for (int i = 0; i < exits.length; i++) {
           Sector exit = exits[i];
           if (exit!=null){
              sb.append("Saída:");
              sb.append(cardinalPoints[i].toString());
              sb.append(", ");
              
              sb.append(exit.getId());
              sb.append(", ");
              sb.append(exit.getDescription());              
              sb.append("\n");
          } 
        }
        
        return sb.toString();
    } 
}
