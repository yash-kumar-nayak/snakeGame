package snakegame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");

        frame.setBounds(10, 10, 905, 706);
        frame.setResizable(false);

        frame.setLocation(350, 100);
//        Develop by YASH © 2023 MAYA. All rights reserved

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.darkGray);

        frame.add(panel);

        frame.setVisible(true);

    }
}
