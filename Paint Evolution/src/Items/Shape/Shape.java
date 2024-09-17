package src.Items.Shape;

import src.Items.GraphicItem;

public abstract class Shape extends GraphicItem{

    protected double width;
    protected double height;
    protected double size;

    protected Shape(boolean auto_draw) {
        super(auto_draw);
    }

    protected Shape(boolean auto_draw, double x, double y, double width, double height){
        super(auto_draw);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

}
