/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import readfile.ReadFile;
import java.util.Random;

/**
 * Class responsible for generating the play field (playground).
 * @author josea. Adaptado por André Dias, Margarida Maunu
 */
public class PlayGround {

    private Random randomNumberGenerator;
    
    private final int MAX_SECTORS = 26;
    private final int MAX_ARTIFACTS = 30;

    private Sector[] sectors;
    private Artifact[] artifacts;
    
    /**
     * Constructor of class PlayGround. 
     */
    public PlayGround() {
        sectors = new Sector[MAX_SECTORS];
        artifacts = new Artifact[MAX_ARTIFACTS];
        randomNumberGenerator = new Random(System.currentTimeMillis());
    }

    /**
      * Abre o ficheiro, lê todas as linhas deste e guarda num array de 'String'
     * (cada linha do ficheiro, corresponde a uma posição no array de 'String') 
     * no fim fecha o ficheiro.
     * 
     * @return devolve um array de 'String' com todas as linhas do ficheiro
     * @see generateBase()
     */
    private String[] readBaseFromFile() {        
        String[] lines = new String[MAX_SECTORS];
        
        ReadFile rf = new ReadFile();
        rf.open("Base_Espacial.txt");

        String line;
        int idx = 0;

        line = rf.readOneLine();
        while (line != null) {
            lines[idx++] = line;
            line = rf.readOneLine();
        }
        rf.close();
        
        return lines;
    }
    
    public Sector[] getSectors(){
        return sectors;
    }
    /**
     * Method responsible for generating the entire planetary base.
     * @return - Sector array with all of the sectors built.
     */
    public Sector[] generateBase() {
        int randomArtifact = 0;

        // Corresponde às linhas do ficheiro
        String[] lines = readBaseFromFile();
        // Cada linha do ficheiro tem um conjunto de valores
        String[] values;
        // Criar as instancias de Sector
        for (String line : lines) {
            values = proccessFileLine(line); // parte a linha do ficheiro num array de valores
            if (values != null) {
                sectors[Integer.parseInt(values[0])] = new Sector(values[1], values[2]);
            }
        
        }
       // Estabelecer as saídas de cada um dos sectores
        for (int i = 0; i < sectors.length; i++) {
            values = proccessFileLine(lines[i]);
            if (values != null) {
                // Norte
                if (values[3] != null) {
                    sectors[i].setExits(CardinalPoints.NORTH, sectors[Integer.parseInt(values[3])]);
                }
                // Este
                if (values[4] != null) {
                    sectors[i].setExits(CardinalPoints.EAST, sectors[Integer.parseInt(values[4])]);
                }
                // Sul
                if (values[5] != null) {
                    sectors[i].setExits(CardinalPoints.SOUTH, sectors[Integer.parseInt(values[5])]);
                }
                // Oeste
                if (values[6] != null) {
                    sectors[i].setExits(CardinalPoints.WEST, sectors[Integer.parseInt(values[6])]);
                }

            }
        }
        
        // Construir os artifactos. 
        createArtifacts();
        
        // Sort them into random sectors.
        for (int i = 0; i < 30; i++) {
            int randomSector = randomNumberGenerator.nextInt(MAX_SECTORS);
            Chest chest = sectors[randomSector].getChest();
            chest.addArtifact(artifacts[i]);
            sectors[randomSector].setChest(chest);
        }
        
        return sectors;
    }

    /**
     * Com o método split() da String, é considerado menos eficiente
     *
     * @param str
     * @return
     */
    private String[] proccessFileLine(String str) {

        if (str == null) {
            return null;
        }

        String[] values = str.split(",");
        //values[0] posição no array
        //values[1] Identificação da Base (e.g., SA-L0)
        //values[2] Descrição
        values[0] = values[0].replace("\"", "").trim();
        values[1] = values[1].replace("\"", "").trim();
        values[2] = values[2].replace("\"", "").trim();

        // Saídas para os Sectores a: Norte, Este, Sul, Oeste (values[3], 4, 5, e 6 respetivamente)
        for (int i = 3; i <= 6; i++) {
            values[i] = values[i].replace("\"", "").trim();
            if (values[i].isEmpty()) {
                values[i] = null;
            }
        }

        return values;

    }
    
    private int nextIntRange(int minimum, int maximum) {
        return (randomNumberGenerator.nextInt((maximum - minimum) + 1) + minimum);

    }
    
    private void createArtifacts(){
        
        artifacts[0] = new Artifact("ATTACKPACK", ArtifactType.ATTACKPACK, nextIntRange(50, 100), 0, 0.5, 0, 0);//Attack Pack
        artifacts[1] = new Artifact("ATTACKPACK", ArtifactType.ATTACKPACK, nextIntRange(50, 100), 0, 0.5, 0, 0);//Attack Pack
        artifacts[2] = new Artifact("ATTACKPACK", ArtifactType.ATTACKPACK, nextIntRange(50, 100), 0, 0.5, 0, 0);//Attack Pack
        artifacts[3] = new Artifact("ATTACKPACK", ArtifactType.ATTACKPACK, nextIntRange(50, 100), 0, 0.5, 0, 0);//Attack Pack
        artifacts[4] = new Artifact("DEFENSEPACK", ArtifactType.DEFENSEPACK, 0, nextIntRange(50, 100), 0.5, 0, 0);//Defense Pack
        artifacts[5] = new Artifact("DEFENSEPACK", ArtifactType.DEFENSEPACK, 0, nextIntRange(50, 100), 0.5, 0, 0);//Defense Pack
        artifacts[6] = new Artifact("DEFENSEPACK", ArtifactType.DEFENSEPACK, 0, nextIntRange(50, 100), 0.5, 0, 0);//Defense Pack
        artifacts[7] = new Artifact("DEFENSEPACK", ArtifactType.DEFENSEPACK, 0, nextIntRange(50, 100), 0.5, 0, 0);//Defense Pack
        artifacts[8] = new Artifact("MONSTER", ArtifactType.MONSTER, nextIntRange(50, 100), nextIntRange(50, 100), 0, nextIntRange(3, 4), 0);//Monster
        artifacts[9] = new Artifact("MONSTER", ArtifactType.MONSTER, nextIntRange(50, 100), nextIntRange(50, 100), 0, nextIntRange(3, 4), 0);//Monster
        artifacts[10] = new Artifact("MONSTER", ArtifactType.MONSTER, nextIntRange(50, 100), nextIntRange(50, 100), 0, nextIntRange(3, 4), 0);//Monster
        artifacts[11] = new Artifact("MONSTER", ArtifactType.MONSTER, nextIntRange(50, 100), nextIntRange(50, 100), 0, nextIntRange(3, 4), 0);//Monster
        artifacts[12] = new Artifact("ALIEN", ArtifactType.ALIEN, nextIntRange(25, 50), nextIntRange(25, 50), 0, nextIntRange(1, 2), 0);//Alien
        artifacts[13] = new Artifact("ALIEN", ArtifactType.ALIEN, nextIntRange(25, 50), nextIntRange(25, 50), 0, nextIntRange(1, 2), 0);//Alien
        artifacts[14] = new Artifact("ALIEN", ArtifactType.ALIEN, nextIntRange(25, 50), nextIntRange(25, 50), 0, nextIntRange(1, 2), 0);//Alien
        artifacts[15] = new Artifact("ALIEN", ArtifactType.ALIEN, nextIntRange(25, 50), nextIntRange(25, 50), 0, nextIntRange(1, 2), 0);//Alien
        artifacts[16] = new Artifact("MONEYSMALL", ArtifactType.MONETARYSMALL, 0, 0, 0.1, 0, 10);//Monetary Small
        artifacts[17] = new Artifact("MONEYSMALL", ArtifactType.MONETARYSMALL, 0, 0, 0.1, 0, 10);//Monetary Small
        artifacts[18] = new Artifact("MONEYSMALL", ArtifactType.MONETARYSMALL, 0, 0, 0.1, 0, 10);//Monetary Small
        artifacts[19] = new Artifact("MONEYSMALL", ArtifactType.MONETARYSMALL, 0, 0, 0.1, 0, 10);//Monetary Small
        artifacts[20] = new Artifact("MONEYMEDIUM", ArtifactType.MONETARYMEDIUM, 0, 0, 0.2, 0, 20);//Monetary Medium
        artifacts[21] = new Artifact("MONEYMEDIUM", ArtifactType.MONETARYMEDIUM, 0, 0, 0.2, 0, 20);//Monetary Medium
        artifacts[22] = new Artifact("MONEYMEDIUM", ArtifactType.MONETARYMEDIUM, 0, 0, 0.2, 0, 20);//Monetary Medium
        artifacts[23] = new Artifact("MONEYMEDIUM", ArtifactType.MONETARYMEDIUM, 0, 0, 0.2, 0, 20);//Monetary Medium
        artifacts[24] = new Artifact("MONEYLARGE", ArtifactType.MONETARYLARGE, 0, 0, 0.3, 0, 30);//Monetary Large
        artifacts[25] = new Artifact("MONEYLARGE", ArtifactType.MONETARYLARGE, 0, 0, 0.3, 0, 30);//Monetary Large
        artifacts[26] = new Artifact("MONEYLARGE", ArtifactType.MONETARYLARGE, 0, 0, 0.3, 0, 30);//Monetary Large
        artifacts[27] = new Artifact("MONEYLARGE", ArtifactType.MONETARYLARGE, 0, 0, 0.3, 0, 30);//Monetary Large
        artifacts[28] = new Artifact("KEY", ArtifactType.KEY, 0, 0, 0.5, 0, 0);//Key
        artifacts[29] = new Artifact("VAULT", ArtifactType.VAULT, 0, 0, 0, 0, 0);//Vault
    }

}
