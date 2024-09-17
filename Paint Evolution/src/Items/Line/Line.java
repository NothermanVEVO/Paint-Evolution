package src.Items.Line;

import java.awt.Graphics2D;

import src.Items.GraphicItem;
import src.sth.Vector2;

public class Line extends GraphicItem{

    private Vector2 initialPosition;
    private Vector2 finalPosition;

    public Line(boolean auto_draw, Vector2 initialPosition, Vector2 finalPosition){
        super(auto_draw);
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawLine((int) initialPosition.x, (int) initialPosition.y, (int) finalPosition.x, (int) finalPosition.y);
    }

    public Vector2 getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Vector2 initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Vector2 getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Vector2 finalPosition) {
        this.finalPosition = finalPosition;
    }

}
