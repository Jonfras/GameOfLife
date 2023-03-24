package net.htlgkr.krejo.gol;

import javax.swing.*;
import java.awt.*;
//                int index = y * width + x;


public class SimpleGraphicsLibrary extends JPanel {
    private final int width;
    private final int height;
    private final Color background;
    private int[] pixels;

    Graphics2D graphics2D;

    public SimpleGraphicsLibrary(int width, int height, Color background) {
        this.width = width;
        this.height = height;
        this.background = background;
        clear();

        JFrame frame = new JFrame("Simple Graphics Library");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    public void setPixel(int x, int y, Color color) {
        int index = y * width + x;
        pixels[index] = color.getRGB();
        repaint();
    }

    public void clear() {
        this.pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                pixels[index] = background.getRGB();
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                Color color = new Color(pixels[index]);
                g.setColor(color);
                g.drawLine(x, y, x, y);
            }
        }
    }


    public void paintCell(int yCord, int xCord){
        for (int i = yCord; i < yCord + Cell.CELL_HEIGHT; i++) {
            for (int j = xCord; j < xCord + Cell.CELL_WIDTH; j++) {
                setPixel(j,i, Color.black);
            }
        }
        repaint();
    }



}
