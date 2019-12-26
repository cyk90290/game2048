package com.example.myapplication;
import com.example.myapplication.GameConfig.height;
import com.example.myapplication.lib.GameConfig.x;
import com.example.myapplication.lib.GameConfig.y;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;

public class Game2048 extends JFrame implements GameConfig  {
    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.InitUI();

    }

    private Dimension dim;
    private int[][] array;

    /**
     * 定义界面初始化
     */
    public void InitUI() {
        array = new int[4][4];

        GameSave gs = new GameSave();

        int[][] tempArray = gs.openInfo();

        if (tempArray == null) {
            randDate();
        } else {
            array = tempArray;
        }
        dim = new Dimension(540, 700);
        this.setTitle("2048");
        this.setSize(dim);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocus();
        GameListener gl = new GameListener(array, this);
        this.addKeyListener(gl);
        this.addMouseListener(gl);
        this.addMouseMotionListener(gl);

    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        draw2048UI(g);

    }

    public void randDate() {
        Random rand = new Random();
        int r1, c1;
        r1 = rand.nextInt(array.length);
        c1 = rand.nextInt(array[r1].length);

        int r2, c2;
        do {
            r2 = rand.nextInt(array.length);
            c2 = rand.nextInt(array[r1].length);
        } while (r1 == r2 && c1 == c2);
        array[r1][c1] = (rand.nextInt(2) + 1) * 2;
        array[r2][c2] = (rand.nextInt(2) + 1) * 2;
    }
    private Image img;
    private Graphics2D ig;


    public void draw2048UI(Graphics g) {
        img = this.createImage(this.getWidth(), this.getHeight());
        ig = (Graphics2D) img.getGraphics();
        ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ig.setColor(new Color(250, 250, 210));
        ig.fillRoundRect(x, y, x + (dis + size) * 4, y + (dis + size) * 4, arcW,
                arcH);
        ig.setColor(new Color(238, 232, 170));
        for (int r = 0; r < array.length; r++)
            for (int c = 0; c < array[r].length; c++) {
                ig.fillRect(x + dis + (size + dis) * c, y + dis + (size + dis)
                        * r, size, size);
            }
        ig.setColor(new Color(218, 165, 32));

        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                if (array[r][c] != 0) {
                    switch (array[r][c]) {
                        case 2:
                            ig.setColor(new Color(255, 250, 205));
                            break;
                        case 4:
                            ig.setColor(new Color(245, 222, 179));// 240, 230, 140
                            break;
                        case 8:
                            ig.setColor(new Color(240, 230, 140));
                            break;
                        case 16:
                            ig.setColor(new Color(238, 200, 120));
                            break;
                        case 32:
                            ig.setColor(new Color(245, 190, 20));
                            break;
                        case 64:
                            ig.setColor(new Color(218, 165, 32));
                            break;
                        case 128:
                            ig.setColor(new Color(255, 165, 0));
                            break;
                        case 256:
                            ig.setColor(new Color(255, 140, 0));
                            break;
                        case 512:
                            ig.setColor(new Color(205, 133, 63));
                            break;
                        case 1024:
                            ig.setColor(new Color(210, 105, 30));
                            break;
                        case 2048:
                            ig.setColor(new Color(255, 69, 0));
                            break;
                    }
                    ig.fillRect(x + dis + (size + dis) * c, y + dis
                            + (size + dis) * r, size, size);
                    ig.setColor(new Color(85, 107, 100));
                    ig.setFont(new Font("", Font.BOLD, 30));
                    ig.drawString("" + array[r][c], x + dis + 40 + (size + dis)
                            * c, y + dis + 60 + (size + dis) * r);
                }
            }

        }
        ig.setColor(Color.black);
        ig.fillRoundRect(x + 10, y + dis + height, 150, 50, 20, 20);

        ig.fillRoundRect(x + dis + 155, y + dis + height, 150, 50, 20, 20);
        ig.fillRoundRect(x + 2 * dis + 2 * 150, y + dis + height, 150, 50, 20,
                20);

        ig.setFont(new Font("MV Boli", Font.BOLD, 20));
        ig.setColor(Color.white);
        ig.drawString("New Game", x + dis + 10, y + (int) (dis * 2.5) + height);
        ig.drawString("Rank", x + 3 * dis + 162, y + (int) (dis * 2.5) + height);
        ig.drawString("Save", x + (int) (3.7 * dis + 2 * 150 + 15), y
                + (int) (dis * 2.5) + height);
        g.drawImage(img, 0, 0, this);
    }
}

