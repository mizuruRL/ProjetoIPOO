/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class responsible for handling the time spent in game and command timestamps.
 * @author André Dias, Margarida Maunu.
 */
public class Logger {
    private LocalTime commandStamp, startTime, endTime;
    private ArrayList<String> gamelog;
    private String playerName;
    
    /**
     * Constructor of class Logger.
     * @param playerName - Name of the player.
     */
    public Logger (String playerName){
        this.playerName = playerName;
        startTime = LocalTime.now();
        gamelog = new ArrayList<>();
    }
    
    /**
     * Method responsible for calculating the total time that has passed since the game started and returning
     * it as a String.
     * @return - String with the elapsed time.
     */
    public String totalTime(){
        int hours = startTime.getHour();
        int minutes = startTime.getMinute();
        int seconds = startTime.getSecond();
        
        endTime = LocalTime.now().minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
        
        return endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
    
    private String timestamp(){
        commandStamp = LocalTime.now();
        return commandStamp.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
    
    /**
     * Method responsible for creating a timestamp for each command the player uses in game,
     * and storing it in an arrayList.
     * @param command - Command the player used.
     */
    public void createTimestamp(String command){
        StringBuilder commandWithTime = new StringBuilder();
        commandWithTime.append(String.format("[%s] %s used: %s", timestamp(), playerName, command));
        gamelog.add(commandWithTime.toString());
    }
    
    @Override
    public String toString(){
        StringBuilder info = new StringBuilder();
        for (String string : gamelog) {
            info.append(string);
            info.append("\n");
        }
        return info.toString();
    }
}

