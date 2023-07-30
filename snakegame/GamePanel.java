/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private int lengthOfSnake = 3;
    private int move = 0;
    private int score = 0;
    private Boolean left = false;
    private Boolean right = true;
    private Boolean up = false;
    private Boolean down = false;
    private int[] Xpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] Ypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 375, 350, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    private Random random = new Random();
    private int enemyX, enemyY;
    private boolean gameover = false;
    private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
    private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
    private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
    private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy.png"));
    private Timer timer;
    private int delay = 100;

    GamePanel() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
        newEnemy();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24, 10, 852, 55);
        g.drawRect(24, 74, 851, 576);
        snaketitle.paintIcon(this, g, 25, 11);
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

//        intilazied the snake 
        if (move == 0) {
            snakexlength[0] = 100;
            snakexlength[1] = 75;
            snakexlength[2] = 50;
            snakeylength[0] = 100;
            snakeylength[1] = 100;
            snakeylength[2] = 100;

        }

        if (left) {
            leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if (right) {
            rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if (up) {
            upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if (down) {
            downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }

        for (int i = 1; i < lengthOfSnake; i++) {
            snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
        }

        enemy.paintIcon(this, g, enemyX, enemyY);

        if (gameover) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", 300, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press SPACE to Restart", 350, 350);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score : " + score, 750, 30);
        g.drawString("Length : " + lengthOfSnake, 750, 50);

        g.setFont(new Font("Tahoma", Font.BOLD, 13));
        g.setColor(Color.white);
        g.drawString(" Develop by YASH © 2023 MAYA. All rights reserved", 285, 665);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = lengthOfSnake - 1; i > 0; i--) {
            snakexlength[i] = snakexlength[i - 1];
            snakeylength[i] = snakeylength[i - 1];
        }

        if (left) {
            snakexlength[0] = snakexlength[0] - 25;
        }
        if (right) {
            snakexlength[0] = snakexlength[0] + 25;
        }
        if (up) {
            snakeylength[0] = snakeylength[0] - 25;
        }
        if (down) {
            snakeylength[0] = snakeylength[0] + 25;
        }
        if (snakexlength[0] > 850) {
            snakexlength[0] = 25;
        }
        if (snakexlength[0] < 25) {
            snakexlength[0] = 850;
        }
        if (snakeylength[0] > 625) {
            snakeylength[0] = 75;
        }
        if (snakeylength[0] < 75) {
            snakeylength[0] = 625;
        }
        collidewithenemy();
        collidewithebody();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            restart();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && (!right)) {
            left = true;
            right = false;
            up = false;
            down = false;
            move++;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && (!left)) {
            left = false;
            right = true;
            up = false;
            down = false;
            move++;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && (!down)) {
            left = false;
            right = false;
            up = true;
            down = false;
            move++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && (!up)) {
            left = false;
            right = false;
            up = false;
            down = true;
            move++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void newEnemy() {
        enemyX = Xpos[random.nextInt(34)];
        enemyY = Ypos[random.nextInt(23)];
        for (int i = lengthOfSnake - 1; i > 0; i--) {
            if (snakexlength[0] == enemyX && snakeylength[0] == enemyY) {
                newEnemy();
            }
        }

    }

    private void collidewithenemy() {
        if (snakexlength[0] == enemyX && snakeylength[0] == enemyY) {
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }

    private void collidewithebody() {
        for (int i = lengthOfSnake - 1; i > 0; i--) {
            if (snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
                timer.stop();
                gameover = true;
            }
        }
    }

    private void restart() {
        gameover = false;
        move = 0;
        score = 0;
        lengthOfSnake = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        timer.start();
        repaint();
    }

}
