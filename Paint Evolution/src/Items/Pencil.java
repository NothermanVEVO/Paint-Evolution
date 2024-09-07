package src.Items;

import java.awt.Graphics2D;
import java.util.ArrayList;

import src.sth.Vector2;

public class Pencil extends GraphicItem{

    private static ArrayList<Line> lines = new ArrayList<>();

    private static Vector2 initialVector;
    private static Vector2 finalVector;

    private static boolean mouseWasJustReleased;

    @Override
    public void draw(Graphics2D g2) {
        for(Line line : lines){
            line.draw(g2);
        }
    }

    public static void addLine(Line line){
        // Correct the spacements between lines
        if(!lines.isEmpty() && !mouseWasJustReleased && line.getInitialPosition() != lines.get(lines.size() - 1).getFinalPosition()){
            addLine(new Line(lines.get(lines.size() - 1).getFinalPosition(), line.getInitialPosition()));
        }
        lines.add(line);
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

    // If mouse was just released, it means that the line shouldn't correct itself
    public static void setMouseWasJustReleased(boolean mouseWasReleased) {
        Pencil.mouseWasJustReleased = mouseWasReleased;
    }

}
