package net.htlgkr.krejo.gol;
import java.io.*;

public class TextFileCreator {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("position3.txt");
            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    int randNum = (int) (Math.random() * 2);
                    if (randNum == 0) {
                        writer.print(Cell.ALIVE_CELL);
                    } else {
                        writer.print(Cell.DEAD_CELL);
                    }
                }
                writer.println();
            }
            writer.close();
            System.out.println("Die Textdatei wurde erfolgreich erstellt!");
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der Textdatei: " + e.getMessage());
        }
    }
}
