package net.htlgkr.krejo.gol;

import java.awt.*;
import java.io.File;
import java.util.Scanner;


public class Main {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static boolean[][] field = new boolean[HEIGHT / Cell.HEIGHT][WIDTH / Cell.WIDTH];
    private static boolean[][] futureField = new boolean[HEIGHT / Cell.HEIGHT][WIDTH / Cell.WIDTH];

    private static SimpleGraphicsLibrary sgl = new SimpleGraphicsLibrary(WIDTH, HEIGHT, Color.LIGHT_GRAY);

    private static final File FILE = new File("src/position3.txt");

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
            printPanelFromField(field);

            Thread.sleep(0);

            createNextGeneration();
            copyFields();
        }
    }

    private static void createNextGeneration() {

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                int aliveNeighbours = getAliveNeighbours(y, x);

                if (aliveNeighbours == 3) {
                    futureField[y][x] = true;
                } else if (aliveNeighbours == 2) {
                    if (field[y][x]) {
                        futureField[y][x] = true;
                    } else {
                        futureField[y][x] = false;
                    }
                } else {
                    futureField[y][x] = false;
                }
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

                int neighbourY = y + i;
                int neighbourX = x + j;

                if (neighbourX < 0 || neighbourX >= WIDTH/Cell.WIDTH || neighbourY < 0 || neighbourY >= HEIGHT/Cell.HEIGHT) {
                    continue;
                }

                if (field[neighbourY][neighbourX]) {
                    aliveNeighbours++;
                }
            }
        }
        return aliveNeighbours;
    }

    private static void copyFields() {
        for (int y = 0; y < futureField.length; y++) {
            for (int x = 0; x < futureField[y].length; x++) {
                if (futureField[y][x]) {
                    field[y][x] = true;
                } else {
                    field[y][x] = false;
                }
            }
        }
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

    public static void printPanelFromField(boolean[][] field) {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x]) {
                    sgl.paintCell(y * Cell.HEIGHT, x * Cell.WIDTH, Cell.ALIVE_COLOR);
                } else {
                    sgl.paintCell(y * Cell.HEIGHT, x * Cell.WIDTH, Cell.DEAD_COLOR);
                }
            }
        }
    }
}
