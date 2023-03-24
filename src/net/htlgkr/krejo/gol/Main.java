package net.htlgkr.krejo.gol;

import java.awt.*;

public class Main {
    private static final int width = 800;
    private static final int height = 800;
    

    private static boolean[][] field = new boolean[height/Cell.CELL_HEIGHT][width/Cell.CELL_WIDTH];

    private static SimpleGraphicsLibrary sgl = new SimpleGraphicsLibrary(width, height, Color.LIGHT_GRAY);


    //Todo: initialPosition aus CSV oda so lesen
    //Todo: Spiellogik mocha

    public static void main(String[] args) {

        for (int y = 0; y < field.length/2; y++) {
            for (int x = 0; x < field[0].length/2; x++) {
                field[y][x] = true;
            }
        }


        printPanelfromField();


    }
    
    public static void printPanelfromField() throws InterruptedException {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x]){
                    sgl.paintCell(y*Cell.CELL_HEIGHT, x*Cell.CELL_WIDTH);
                }
            }
        }
        Thread.sleep(200);
    }
}
