package src.Items;

import java.awt.Color;
import java.awt.Graphics2D;

import src.GUI.Board;
import src.sth.Vector2;
import src.sth.Vector2D;

public abstract class GraphicItem {

    protected double x;
    protected double y;
    protected Vector2 scale = Vector2D.ONE.getVct();
    protected double angle = 0;
    protected Color color;
    protected Color fill_color;
    protected double strokeSize = 1;
    protected boolean is_pseudo;

    protected GraphicItem(boolean auto_draw){
        if(auto_draw) Board.addItem(this);
        this.color = Board.getColor();
        this.fill_color = Board.get_fill_color();
        this.strokeSize = Board.getStrokeSize();
    }

    public abstract void draw(Graphics2D g2);

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color get_fill_color() {
        return fill_color;
    }

    public void set_fill_color(Color fill_color) {
        this.fill_color = fill_color;
    }

    public double getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(double strokeSize) {
        this.strokeSize = strokeSize;
    }

    public boolean is_pseudo() {
        return is_pseudo;
    }

    public void set_pseudo(boolean is_pseudo) {
        this.is_pseudo = is_pseudo;
    }

    public Vector2 getVector2(){
        return new Vector2(x, y);
    }

}
