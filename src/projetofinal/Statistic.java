/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.util.HashMap;
import java.util.Map;


/**
 * Class responsible for managing and tracking how many times the player has done
 * actions.
 * @author André Dias, Margarida Maunu.
 */
public class Statistic {
    private HashMap<String, Integer> commandsUsed;
    private HashMap<String, Integer> enemiesKilled;
    private HashMap<String, Integer> itemsUsed;
    private HashMap<String, Integer> itemsPickedUp;
    
    /**
     * Constructor of class Statistic.
     */
    public Statistic(){
        commandsUsed = new HashMap<>();
        enemiesKilled = new HashMap<>();
        itemsUsed = new HashMap<>();
        itemsPickedUp = new HashMap<>();
        startStatistics();
    }
    
    private void startStatistics() {
        commandsUsed.put("MYSELF",0);
        commandsUsed.put("SEE",0);
        commandsUsed.put("GET",0);
        commandsUsed.put("DROP",0);
        commandsUsed.put("MOVE",0);
        commandsUsed.put("ATTACK",0);
        commandsUsed.put("USE",0);
        commandsUsed.put("SHOW",0);
        commandsUsed.put("HELP",0);
        commandsUsed.put("QUIT",0);
        enemiesKilled.put("MONSTER",0);
        enemiesKilled.put("ALIEN",0); 
        itemsPickedUp.put("ATTACKPACK",0);
        itemsUsed.put("ATTACKPACK",0);
        itemsPickedUp.put("DEFENSEPACK",0);
        itemsUsed.put("DEFENSEPACK",0);
        itemsPickedUp.put("MONEYSMALL",0);
        itemsUsed.put("MONEYSMALL",0);
        itemsPickedUp.put("MONEYMEDIUM",0);
        itemsUsed.put("MONEYMEDIUM",0);
        itemsPickedUp.put("MONEYLARGE",0);
        itemsUsed.put("MONEYLARGE",0);
        
    }
    
    /**
     * Method responsible for incrementing a command use once.
     * @param commandName - command to count.
     */
    public void incrementCommandsUsed(String commandName){
        int count;
        if(commandsUsed.containsKey(commandName)){
            count = commandsUsed.get(commandName);
            commandsUsed.put(commandName, count + 1);
        } 
    }
    
    /**
     * Method responsible for incrementing a killed enemy once.
     * @param enemyName - enemy to count.
     */
    public void incrementEnemiesKilled(String enemyName){
        int count;
        if(enemiesKilled.containsKey(enemyName)){
            count = enemiesKilled.get(enemyName);
            enemiesKilled.put(enemyName, count + 1);
        } 
    }
    
    /**
     * Method responsible for incrementing a used item once.
     * @param item - item to count.
     */
    public void incrementItemsUsed(String item){
        int count;
        if(itemsUsed.containsKey(item)){
            count = itemsUsed.get(item);
            commandsUsed.put(item, count + 1);
        } 
    }
    
    /**
     * Method responsible for incrementing a picked up item once.
     * @param item - item to count.
     */
    public void incrementItemsPickedUp(String item){
        int count;
        if(itemsPickedUp.containsKey(item)){
            count = itemsPickedUp.get(item);
            commandsUsed.put(item, count + 1);
        }
    }
    
    private String showCommandStats(){
        StringBuilder stats = new StringBuilder();
        stats.append("\nCommands Used\n\n");
        for (Map.Entry<String, Integer> entry : commandsUsed.entrySet()) {
            
            String commandName = entry.getKey();
            int value = entry.getValue();
            
            stats.append(String.format("%s : %d\n", commandName, value));
        }
        return stats.toString();
    }
    
    private String showEnemiesKilled(){
        StringBuilder stats = new StringBuilder();
        stats.append("\nEnemies Killed\n\n");
        for (Map.Entry<String, Integer> entry : enemiesKilled.entrySet()) {
            
            String enemyName = entry.getKey();
            int value = entry.getValue();
            
            stats.append(String.format("%s : %d\n", enemyName, value));
        }
        return stats.toString();
    }
    
    private String showItemsUsed(){
        StringBuilder stats = new StringBuilder();
        stats.append("\nItems Used\n\n");
        for (Map.Entry<String, Integer> entry : itemsUsed.entrySet()) {
            
            String itemName = entry.getKey();
            int value = entry.getValue();
            
            stats.append(String.format("%s : %d\n", itemName, value));
        }
        return stats.toString();
    }
    
    private String showItemsPickedUp(){
        StringBuilder stats = new StringBuilder();
        stats.append("\nItems Picked Up\n\n");
        for (Map.Entry<String, Integer> entry : itemsPickedUp.entrySet()) {
            
            String itemName = entry.getKey();
            int value = entry.getValue();
            
            stats.append(String.format("%s : %d\n", itemName, value));
        }
        return stats.toString();
    }
    
    /**
     * Method responsible for returning all of the stats in a single String.
     * @return - String with every hashMap.
     */
    public String showAllStats() {
        return showCommandStats() + showEnemiesKilled() + showItemsPickedUp() + showItemsUsed();
    }
    
}
