package src.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.Window;
import src.GUI.ToolBar.Tools;
import src.Items.GraphicItem;
import src.Items.Tools.Pencil;
import src.Items.Tools.PencilShape;
import src.Items.Tools.Tool;
import src.Items.Tools.PencilShape.Shapes;
import src.events.Key;
import src.events.Mouse;

public class Board extends JPanel {

    private static Mouse mouse = new Mouse();

    private static Tool current_tool;

    private static Pencil pencil;
    private static PencilShape pencilSquare;
    private static PencilShape pencilOval;
    private static PencilShape pencilPolygon;

    private static ArrayList<GraphicItem> items = new ArrayList<>();
    private static ArrayList<GraphicItem> itemsCopy = new ArrayList<>();

    private static Color color = Color.BLACK;
    private static Color fill_color = new Color(0, 0, 0, 0);

    private static int strokeSize = 1;

    Key key = new Key();

    public Board(){}

    public Board(int x, int y, int width, int height, Color background_color){
        setBounds(x, y, width, height);
        setBackground(background_color);
        setLayout(null);
        setVisible(true);

        setEnabled(true);
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(key);

        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        createTools();
        current_tool = pencil;

        grabFocus();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(GraphicItem item : items){
            // g2.scale(2, 2);
            // g2.scale(item.getScale().x, item.getScale().y);
            g2.rotate(item.getAngle(), item.getX(), item.getY());
            double scale_factor = (item.getScale().x + item.getScale().y) / 2;
            g2.setStroke(new BasicStroke((float) (item.getStrokeSize() * scale_factor)));
            g2.setColor(item.getColor());
            item.draw(g2);
            g2.rotate(-item.getAngle(), item.getX(), item.getY());

            // g2.scale(1 / item.getScale().x, 1 / item.getScale().y);
            // g2.scale(0.5, 0.5);
        }
        g2.dispose();
    }

    public void createTools(){
        pencil = new Pencil();
        pencilSquare = new PencilShape(Shapes.SQUARE);
        pencilOval = new PencilShape(Shapes.OVAL);
        pencilPolygon = new PencilShape(Shapes.POLYGON);
    }

    public static Tool getCurrentTool() {
        return current_tool;
    }

    public static void setTool(Tools tool){
        switch (tool) {
            case PENCIL:
                current_tool = pencil;
                break;
            case SQUARE:
                current_tool = pencilSquare;
                PencilShape.setCurrentShape(Shapes.SQUARE);
                break;
            case OVAL:
                current_tool = pencilOval;
                PencilShape.setCurrentShape(Shapes.OVAL);
                break;
            case POLYGON:
                current_tool = pencilPolygon;
                PencilShape.setCurrentShape(Shapes.POLYGON);
                break;
            default:
                break;
        }
    }

    public static void addItem(GraphicItem item){
        items.add(item);
        itemsCopy.clear();
        if (!item.is_pseudo()) {
            ToolBar.setUndoEnabled(true);
            ToolBar.setRedoEnabled(false);
        }
        Window.board.grabFocus();
        Window.board.repaint();
    }

    public static void removeItemAt(int index){
        boolean canCopy = !items.get(index).is_pseudo();
        if(canCopy && !itemsCopy.contains(items.get(index))){
            itemsCopy.add(items.get(index));
            ToolBar.setRedoEnabled(true);
        }
        items.remove(index);
        if (Board.itemsSize() == 0) {
            ToolBar.setUndoEnabled(false);
        }
        Window.board.grabFocus();
        Window.board.repaint();
    }

    public static void removeItem(Object object){
        if(!itemsContains(object)){
            return;
        }
        boolean canCopy = !items.get(items.indexOf(object)).is_pseudo();
        if(canCopy && !itemsCopy.contains(object)){
            itemsCopy.add(items.get(items.indexOf(object)));
            ToolBar.setRedoEnabled(true);
        }
        items.remove(object);
        if (Board.itemsSize() == 0) {
            ToolBar.setUndoEnabled(false);
        }
        Window.board.grabFocus();
        Window.board.repaint();
    }

    public static GraphicItem getItemAt(int index){
        return items.get(index);
    }

    public static int itemsSize(){
        // System.out.println("------------------------");
        // for (GraphicItem item1 : items) {
        //     System.out.println(item1);
        // }
        return items.size();
    }

    public static boolean itemsContains(Object object){
        if(items.size() == 0) return false;
        return items.contains(object);
    }

    public static void dropItemsCopyAt(int index){
        items.add(itemsCopy.get(index));
        ToolBar.setUndoEnabled(true);
        itemsCopy.remove(index);
        if (itemsCopySize() == 0) {
            ToolBar.setRedoEnabled(false);
        }
        Window.board.grabFocus();
        Window.board.repaint();
    }

    public static void clearBoard(){
        items.clear();
        itemsCopy.clear();
        color = Color.BLACK;
        fill_color = new Color(0, 0, 0, 0);
        strokeSize = 1;
        ToolBar.reset();
        Window.board.grabFocus();
        Window.board.repaint();
    }

    public static int itemsCopySize(){
        return itemsCopy.size();
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        Board.color = color;
    }

    public static Color get_fill_color() {
        return fill_color;
    }

    public static void set_fill_color(Color fill_color) {
        Board.fill_color = fill_color;
    }

    public static int getStrokeSize() {
        return strokeSize;
    }

    public static void setStrokeSize(int strokeSize) {
        Board.strokeSize = strokeSize;
    }

}
