package src.Items;

public abstract class Shape extends GraphicItem{

    Shape(boolean auto_draw) {
        super(auto_draw);
    }
    protected int x;
    protected int y;
    protected int width;
    protected int height;

}
