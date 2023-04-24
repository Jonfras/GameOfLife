package net.htlgkr.krejo.gol;

import java.awt.*;

public class Cell {
    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;

    public static final char DEAD_CELL = '-';
    public static final char ALIVE_CELL = '*';

    public static final Color ALIVE_COLOR = Color.black;
    public static final Color DEAD_COLOR = Color.white;


    public static boolean[] deserialzeLine(String line) throws RuntimeException{
        boolean[] row = new boolean[line.length()];

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == ALIVE_CELL){
                row[i] = true;
            } else if (character == DEAD_CELL){
                row[i] = false;
            } else {
                throw new RuntimeException();
            }
        }
        return row;
    }

}
