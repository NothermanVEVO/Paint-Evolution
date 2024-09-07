package src;

import java.awt.Color;

import javax.swing.JFrame;

import src.events.Key;

public class Window extends JFrame {

    public static Board board = new Board();
    private static Key key = new Key();

    public Window(String name, int width, int height){
        super(name);
        setSize(width, height);
        setLocationRelativeTo(null);
        setEnabled(true);
        setFocusable(true);
        addKeyListener(key);

        board = new Board(0, 50, width, height, Color.WHITE);
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

}
