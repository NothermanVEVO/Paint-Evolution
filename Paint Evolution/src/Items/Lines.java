package src.Items;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Lines extends GraphicItem{

    private ArrayList<Line2D> linesArray = new ArrayList<>();

    Lines(boolean auto_draw){
        super(auto_draw);
    }

    Lines(boolean auto_draw, ArrayList<Line2D> linesArray){
        super(auto_draw);
        this.linesArray = linesArray;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Line2D line : linesArray) {
            g2.draw(line);
        }
    }
    
}
