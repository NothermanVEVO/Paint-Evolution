package src.Items.Tools;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import java.awt.event.MouseEvent;

import src.Window;
import src.GUI.Board;
import src.Items.Line.Lines;
import src.events.Key;
import src.sth.Vector2;

public class Pencil extends Tool{

    private static ArrayList<Line2D> lines2D = new ArrayList<>();
    private static Lines pseudoLine = new Lines(true);

    private static Vector2 initialVector;
    private static Vector2 finalVector;

    private static boolean mouseWasJustReleased;
    private static boolean altWasJustReleased;

    public Pencil() {
        pseudoLine.set_pseudo(true);
    }

    public static void addLine(Line2D line){
        //? Correct the spacements between lines
        if(!lines2D.isEmpty() && !mouseWasJustReleased && ((int) line.getX1() != (int) lines2D.get(lines2D.size() - 1).getX2() || 
            (int) line.getY1() != (int) lines2D.get(lines2D.size() - 1).getY2())){
                addLine(new Line2D.Double(lines2D.get(lines2D.size() - 1).getP2(), line.getP1()));
        }
        lines2D.add(line);
        pseudoLine.setLines2DArray(lines2D);
        if (Board.itemsContains(pseudoLine)) {
            Window.board.grabFocus();
            Window.board.repaint();
        } else{
            Board.addItem(pseudoLine);
        }
        mouseWasJustReleased = false;
    }

    public static Vector2 getInitialVector() {
        return initialVector;
    }

    public static void setInitialVector(Vector2 initialVector) {
        Pencil.initialVector = initialVector;
    }

    public static Vector2 getFinalVector() {
        return finalVector;
    }

    public static void setFinalVector(Vector2 finalVector) {
        Pencil.finalVector = finalVector;
    }

    @Override
    public void mouseWasJustPressed(MouseEvent e) {
        pseudoLine.setColor(Board.getColor());
        pseudoLine.setStrokeSize(Board.getStrokeSize());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void mouseWasJustReleased(MouseEvent e) {
        Pencil.setInitialVector(null);
        //? If mouse was just released, it means that the line shouldn't correct itself
        Pencil.mouseWasJustReleased = true;
        new Lines(true, (ArrayList<Line2D>) lines2D.clone()).setAngle(pseudoLine.getAngle());
        lines2D.clear();
        Board.removeItem(pseudoLine);
        pseudoLine.setAngle(0);
        Window.board.grabFocus();
        Window.board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Key.is_alt_pressed()) {
            altWasJustReleased = true;
            Window.board.grabFocus();
            Window.board.repaint();
            return;
        }
        if (altWasJustReleased) {
            mouseWasJustReleased(e);
            altWasJustReleased = false;
        }
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(Pencil.getInitialVector() == null){
            Pencil.setInitialVector(new Vector2(mouseX, mouseY));
            return;
        }
        Pencil.addLine(new Line2D.Double(Pencil.getInitialVector().x, Pencil.getInitialVector().y, mouseX, mouseY));
        Pencil.setInitialVector(null);
    }

}
