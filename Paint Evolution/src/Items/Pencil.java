package src.Items;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import src.Window;
import src.sth.Vector2;

public class Pencil extends GraphicItem{

    private static ArrayList<Line2D> lines2D = new ArrayList<>();
    private static ArrayList<Lines> lines = new ArrayList<>();

    private static Vector2 initialVector;
    private static Vector2 finalVector;

    private static boolean mouseWasJustReleased;

    public Pencil(boolean auto_draw) {
        super(auto_draw);
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!mouseWasJustReleased){
            for(Lines line : lines){
                line.draw(g2);
            }
            for(Line2D line2D : lines2D){
                g2.draw(line2D);
            }
        } else{
            for(Lines line : lines){
                line.draw(g2);
            }
        }
    }

    public static void addLine(Line2D line){
        //? Correct the spacements between lines
        // if(!lines.isEmpty() && !mouseWasJustReleased && line.getInitialPosition() != lines.get(lines.size() - 1).getFinalPosition()){
        //     addLine(new Line(lines.get(lines.size() - 1).getFinalPosition(), line.getInitialPosition()));
        // }
        if(!lines2D.isEmpty() && !mouseWasJustReleased && ((int) line.getX1() != (int) lines2D.get(lines2D.size() - 1).getX2() || 
            (int) line.getY1() != (int) lines2D.get(lines2D.size() - 1).getY2())){
                addLine(new Line2D.Double(lines2D.get(lines2D.size() - 1).getP2(), line.getP1()));
        }
        lines2D.add(line);
        Window.board.repaint();
        mouseWasJustReleased = false;
    }

    public static void removeLineAt(int index){
        lines.remove(index);
        // lines.linesArray.remove(index);
        Window.board.repaint();
    }

    public static int linesSize(){
        return lines.size();
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

    //? If mouse was just released, it means that the line shouldn't correct itself
    @SuppressWarnings("unchecked")
    public static void setMouseWasJustReleased(boolean mouseWasReleased) {
        Pencil.mouseWasJustReleased = mouseWasReleased;
        lines.add(new Lines(false, (ArrayList<Line2D>) lines2D.clone()));
        lines2D.clear();
        Window.board.repaint();
    }

}
