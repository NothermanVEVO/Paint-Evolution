package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.Items.GraphicItem;
import src.Items.Pencil;
import src.events.Mouse;

public class Board extends JPanel {

    private static Mouse mouse = new Mouse();

    Pencil pencil;

    private static ArrayList<GraphicItem> items = new ArrayList<>();

    public Board(){}

    public Board(int x, int y, int width, int height, Color background_color){
        setBounds(x, y, width, height);
        setBackground(background_color);
        setLayout(null);
        setVisible(true);

        setEnabled(true);
        setFocusable(true);
        setDoubleBuffered(true);

        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        createTools();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(GraphicItem item : items){
            item.draw(g2);
        }
        g2.dispose();
    }

    public static void addItem(GraphicItem item){
        items.add(item);
        Window.board.repaint();
    }

    public static void removeItemAt(int index){
        System.out.println(items.get(index));
        items.remove(index);
        Window.board.repaint();
    }

    public static int itemsSize(){
        return items.size();
    }

    public void createTools(){
        pencil = new Pencil(true);
    }

}
