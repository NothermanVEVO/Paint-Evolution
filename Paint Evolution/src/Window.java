package src;

import java.awt.Color;

import javax.swing.JFrame;

import src.GUI.Board;
import src.GUI.ToolBar;

public class Window extends JFrame {

    public static Board board = new Board();
    public static ToolBar toolBar = new ToolBar();

    public Window(String name, int width, int height){
        super(name);
        setSize(width, height);
        setLocationRelativeTo(null);
        setEnabled(true);
        setFocusable(true);

        setResizable(false); //TODO: IMPLEMENT RESIZABLE

        toolBar = new ToolBar(0, 0, width, 50, Color.GRAY);
        add(toolBar);
        board = new Board(0, 50, width, height, Color.WHITE);
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        while (Board.itemsSize() > 0) {
            Board.removeItemAt(0);
        }

        ToolBar.setUndoEnabled(false);
        ToolBar.setRedoEnabled(false);

    }

}
