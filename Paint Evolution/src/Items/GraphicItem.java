package src.Items;

import java.awt.Color;
import java.awt.Graphics2D;
import src.sth.Vector2;
import src.Board;

public abstract class GraphicItem {

    protected Color color;
    protected Vector2 size;

    GraphicItem(boolean auto_draw){
        if(auto_draw) Board.addItem(this);
    }

    public abstract void draw(Graphics2D g2);

}
