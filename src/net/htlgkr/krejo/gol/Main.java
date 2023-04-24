package net.htlgkr.krejo.gol;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int width = 800;
    private static final int height = 800;


    private static boolean[][] field = new boolean[height / Cell.HEIGHT][width / Cell.WIDTH];
    private static boolean[][] futureField = new boolean[height / Cell.HEIGHT][width / Cell.WIDTH];

    private static SimpleGraphicsLibrary sgl = new SimpleGraphicsLibrary(width, height, Color.LIGHT_GRAY);

    private static final File FILE = new File("src/position1.txt");

    //Todo: Spiellogik mocha


    public static void main(String[] args) {

        createStartPosition(FILE);
        try {
            startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startGame() throws InterruptedException {
        while (true) {

            printPanelfromField(field);

            createNextPosition();

            field = futureField;
            //todo: überleg da do de reihenfolge und zuweisung wonnst futurefield nimmst und won ned
            Thread.sleep(1000);
        }
    }

    private static void createNextPosition() {

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                int aliveNeighbours = getAliveNeighbours(y, x);

                if (aliveNeighbours == 3){
                    futureField[y][x] = true;

                } else if (aliveNeighbours == 2){
                    futureField[y][x] = field[y][x];

                } else {
                    futureField[y][x] = false;
                }


//                //Any live cell with fewer than two live neighbours dies (referred to as underpopulation).
//                //Any live cell with more than three live neighbours dies (referred to as overpopulation).
//                if (aliveNeighbours < 2 || 3 < aliveNeighbours) {
//                    futureField[y][x] = false;
//
//                    //Any dead cell with exactly three live neighbours comes to life.
//                } else if (!field[y][x] && aliveNeighbours == 3) {
//                    futureField[y][x] = true;
//
//                    //Any live cell with two or three live neighbours lives, unchanged, to the next generation.
//                } else if (field[y][x]) {
//                    futureField[y][x] = true;
//                }
            }
        }
    }

    private static int getAliveNeighbours(int y, int x) {
        int aliveNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int xx = x + i;
                int yy = y + j;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height) {
                    continue;
                }
                try {
                    if (field[xx][yy]) {
                        aliveNeighbours++;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
        return aliveNeighbours;
    }


    private static void createStartPosition(File file) {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; fileScanner.hasNext(); i++) {
            try {
                field[i] = Cell.deserialzeLine(fileScanner.nextLine());
            } catch (RuntimeException e) {
                System.err.println("Incorrect config in " + file);
            }
        }

    }

    public static void printPanelfromField(boolean[][] field) {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x]) {
                    sgl.paintCell(y * Cell.HEIGHT, x * Cell.WIDTH);
                }
            }
        }

    }

}
