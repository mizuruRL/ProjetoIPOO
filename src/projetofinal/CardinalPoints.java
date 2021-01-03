/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 * Classe enumerada para definir os pontos cardeais para cada setor.
 * @author josea.
 */
public enum CardinalPoints {
    
    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return "Norte";
            case EAST:
                return "Este";
            case SOUTH:
                return "Sul";
            case WEST:
                return "Oeste";

            default:
                throw new AssertionError();
        }
    }
    
    
}
