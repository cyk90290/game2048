package com.example.lib;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class GameListener extends KeyAdapter implements MouseListener,
        MouseMotionListener, GameConfig  {private int[][] array;
    private Game2048 game;


    public GameListener(int[][] array, Game2048 game) {
        this.array = array;
        this.game = game;

    }

    public void rData() {
        Random rand = new Random();
        int r, c;
        do {
            r = rand.nextInt(array.length);
            c = rand.nextInt(array[r].length);
        } while (array[r][c] != 0);

        array[r][c] = (rand.nextInt(2) + 1) * 2;
    }

    public void keyPressed(KeyEvent e) {
        int number = 0;
        int code = e.getKeyCode();
        switch (code) {
            case 37:
                for (int r = 0; r < array.length; r++)
                    for (int c = 0; c < array[r].length; c++)
                        if (array[r][c] != 0)
                            for (int c1 = c + 1; c1 < array[r].length; c1++)
                                if (array[r][c] == array[r][c1]) {
                                    array[r][c] *= 2;
                                    array[r][c1] = 0;
                                    number++;

                                    break;
                                } else if (array[r][c1] != 0) {
                                    break;
                                }

                for (int r = 0; r < array.length; r++)
                    for (int c = 0; c < array[r].length; c++)
                        if (array[r][c] == 0) {
                            for (int c1 = c + 1; c1 < array[r].length; c1++) {
                                if (array[r][c1] != 0) {
                                    array[r][c] = array[r][c1];
                                    array[r][c1] = 0;
                                    number++;
                                    break;
                                }
                            }
                        }
                break;
            case 38:
                for (int c = 0; c < array[0].length; c++)
                    for (int r = 0; r < array.length; r++)
                        if (array[r][c] != 0)
                            for (int r1 = r + 1; r1 < array.length; r1++)
                                if (array[r1][c] == array[r][c]) {
                                    array[r][c] *= 2;
                                    array[r1][c] = 0;
                                    number++;

                                    break;
                                } else if (array[r1][c] != 0)
                                    break;

                for (int c = 0; c < array[0].length; c++)
                    for (int r = 0; r < array.length; r++)
                        if (array[r][c] == 0) {
                            for (int r1 = r + 1; r1 < array.length; r1++) {
                                if (array[r1][c] != 0) {
                                    array[r][c] = array[r1][c];
                                    array[r1][c] = 0;
                                    number++;
                                    break;
                                }
                            }
                        }

                break;
            case 39:

                for (int r = 0; r < array.length; r++)
                    for (int c = array[r].length - 1; c >= 0; c--)
                        if (array[r][c] != 0)
                            for (int c1 = c - 1; c1 >= 0; c1--)
                                if (array[r][c] == array[r][c1]) {
                                    array[r][c] *= 2;
                                    array[r][c1] = 0;
                                    number++;

                                    break;
                                } else if (array[r][c1] != 0) {
                                    break;
                                }
                for (int r = 0; r < array.length; r++)
                    for (int c = array[r].length - 1; c >= 0; c--)
                        if (array[r][c] == 0) {
                            for (int c1 = c - 1; c1 >= 0; c1--) {
                                if (array[r][c1] != 0) {
                                    array[r][c] = array[r][c1];
                                    array[r][c1] = 0;
                                    number++;
                                    break;
                                }
                            }

                        }
                break;
            case 40:// 向下

                for (int c = 0; c < array[0].length; c++)
                    for (int r = array.length - 1; r >= 0; r--)
                        if (array[r][c] != 0)
                            for (int r1 = r - 1; r1 >= 0; r1--)
                                if (array[r1][c] == array[r][c]) {
                                    array[r][c] *= 2;
                                    array[r1][c] = 0;
                                    number++;

                                    break;
                                } else if (array[r1][c] != 0)
                                    break;

                for (int c = 0; c < array[0].length; c++)
                    for (int r = array.length - 1; r >= 0; r--)
                        if (array[r][c] == 0) {
                            for (int r1 = r - 1; r1 >= 0; r1--) {
                                if (array[r1][c] != 0) {
                                    array[r][c] = array[r1][c];
                                    array[r1][c] = 0;
                                    number++;
                                    break;
                                }
                            }
                        }

                break;
        }
        if (number > 0) {

            rData();

            game.repaint();

        } else {
            int count = 0;
            int max = array[0][0];
            for (int r = 0; r < array.length; r++) {
                for (int c = 0; c < array[r].length; c++) {
                    if (array[r][c] != 0) {
                        if (c + 1 < array[r].length
                                && array[r][c + 1] == array[r][c]) {
                            count++;
                        }
                        if (r + 1 < array.length
                                && array[r + 1][c] == array[r][c]) {
                            count++;
                        }
                    } else {
                        count++;

                    }
                    if (array[r][c] > max)
                        max = array[r][c];
                }
            }
            if (count == 0) {
                Graphics g = game.getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(Color.darkGray);
                g.setFont(new Font("MV Boli", Font.BOLD, 83));
                g.drawString("Game Over!", 20, game.getHeight() / 2 - 40);

                game.removeKeyListener(this);

                int option = JOptionPane.showConfirmDialog(game, "Record?",
                        "RankingList", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    String name = JOptionPane.showInputDialog(game, "User：",
                            "RankingList", JOptionPane.YES_NO_OPTION);
                    User user = new User(name, max);

                    GameSave gs = new GameSave();
                    boolean b = gs.saveRankingList(user);

                    if (b) {
                        JOptionPane.showMessageDialog(game, "");
                    } else {
                        JOptionPane.showMessageDialog(game, "");
                    }
                }
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Graphics2D ig = (Graphics2D) game.getGraphics();

        if (mx > (x + 10) && mx < (x + 160) && my > y + dis + height
                && my < y + dis + height + 50) {
            ig.setColor(Color.LIGHT_GRAY);

            ig.fillRoundRect(x + 10, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("New Game", x + dis + 10, y + (int) (dis * 2.5)
                    + height);

        }
        if (mx > x + dis + 155 && mx < x + dis + 155 + 150
                && my > y + dis + height && my < y + dis + height + 50) {
            ig.setColor(Color.LIGHT_GRAY);
            ig.fillRoundRect(x + dis + 155, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Rank", x + 3 * dis + 162, y + (int) (dis * 2.5)
                    + height);
        }
        if (mx > x + 2 * dis + 2 * 150 && mx < x + 2 * dis + 2 * 150 + 150
                && my > y + dis + height && my < y + dis + height + 50) {
            ig.setColor(Color.LIGHT_GRAY);
            ig.fillRoundRect(x + 2 * dis + 300, y + dis + height, 150, 50, 20,
                    20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Save", x + (int) (3.7 * dis + 315), y
                    + (int) (dis * 2.5) + height);
        }
    }
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Graphics2D ig = (Graphics2D) game.getGraphics();
        if (mx > (x + 10) && mx < (x + 160) && my > y + dis + height
                && my < y + dis + height + 50) {
            int option = JOptionPane.showConfirmDialog(game,
                    "Start a new game ~(*0*~)?", "new game",
                    JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        array[i][j] = 0;
                Random rand = new Random();
                int r1, c1;
                r1 = rand.nextInt(array.length);
                c1 = rand.nextInt(array[r1].length);

                int r2, c2;//
                do {
                    r2 = rand.nextInt(array.length);
                    c2 = rand.nextInt(array[r1].length);
                } while (r1 == r2 && c1 == c2);
                array[r1][c1] = (rand.nextInt(2) + 1) * 2;
                array[r2][c2] = (rand.nextInt(2) + 1) * 2;


            }

            game.paint(game.getGraphics());

            game.addKeyListener(this);
        }

        if (mx > x + dis + 155 && mx < x + dis + 155 + 150
                && my > y + dis + height && my < y + dis + height + 50) {
            JOptionPane.showMessageDialog(game, "Check the Rank ~>_<~");
            GameSave gs = new GameSave();
            ArrayList list = gs.openRankingList();

            JFrame frame = new JFrame();
            frame.setTitle("Ranking");
            frame.setSize(300, 700);
            frame.setDefaultCloseOperation(2);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.setResizable(false);

            JLabel lab = new JLabel("User               Max        ");
            lab.setBounds(30, 60, 400, 60);
            frame.add(lab);

            for (int i = 0; i < list.size(); i++) {
                User user = (User) list.get(i);
                JLabel label = new JLabel(user.toString());
                label.setBounds(30, 60 + (i + 1) * 50, 400, 60);
                frame.add(label);
            }

            frame.setVisible(true);

        }
        // 游戏存档按钮x + 2 * distance + 2 * 150, y + distance + height,150, 50
        if (mx > x + 2 * dis + 2 * 150 && mx < x + 2 * dis + 2 * 150 + 150
                && my > y + dis + height && my < y + dis + height + 50) {// 表示点击的是开始游戏按钮

            GameSave gs = new GameSave();
            boolean b = gs.saveInfo(array);
            if (b)
                JOptionPane.showMessageDialog(game, "Success in Saving ^_^");
            else
                JOptionPane.showMessageDialog(game, "Failure in Saving ~_~");
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Graphics2D ig = (Graphics2D) game.getGraphics();
        if (mx > (x + 10) && mx < (x + 160) && my > y + dis + height
                && my < y + dis + height + 50) {
            ig.setColor(Color.GRAY);
            ig.fillRoundRect(x + 10, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("New Game", x + dis + 10, y + (int) (dis * 2.5)
                    + height);

        } else {
            ig.setColor(Color.black);

            ig.fillRoundRect(x + 10, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("New Game", x + dis + 10, y + (int) (dis * 2.5)
                    + height);
        }
        if (mx > x + dis + 155 && mx < x + dis + 155 + 150
                && my > y + dis + height && my < y + dis + height + 50) {
            ig.setColor(Color.GRAY);
            ig.fillRoundRect(x + dis + 155, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Rank", x + 3 * dis + 162, y + (int) (dis * 2.5)
                    + height);
        } else {
            ig.setColor(Color.black);

            ig.fillRoundRect(x + dis + 155, y + dis + height, 150, 50, 20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Rank", x + 3 * dis + 162, y + (int) (dis * 2.5)
                    + height);
        }
        if (mx > x + 2 * dis + 2 * 150 && mx < x + 2 * dis + 2 * 150 + 150
                && my > y + dis + height && my < y + dis + height + 50) {
            ig.setColor(Color.GRAY);
            ig.fillRoundRect(x + 2 * dis + 300, y + dis + height, 150, 50, 20,
                    20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Save", x + (int) (3.7 * dis + 315), y
                    + (int) (dis * 2.5) + height);
        } else {
            ig.setColor(Color.black);
            ig.fillRoundRect(x + 2 * dis + 2 * 150, y + dis + height, 150, 50,
                    20, 20);
            ig.setFont(new Font("MV Boli", Font.BOLD, 20));
            ig.setColor(Color.white);
            ig.drawString("Save", x + (int) (3.7 * dis + 315), y
                    + (int) (dis * 2.5) + height);
        }
    }

}