package src;

import java.awt.Color;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ToolBar extends JPanel {

    public static enum Tools{
        PENCIL,
        SQUARE,
        CIRCLE
    }

    private static Tools tool = Tools.PENCIL;

    JComboBox<String> tools_combo = new JComboBox<>();

    ToolBar(int x, int y, int width, int height, Color background_color){
        setBounds(x, y, width, height);
        setBackground(background_color);
        setLayout(null);
        setVisible(true);

        createToolsComboBox();
    }

    private void createToolsComboBox(){
        tools_combo.addItem("Pencil");
        tools_combo.addItem("Square");
        tools_combo.addItem("Circle");

        tools_combo.addItemListener(e -> itemListener(e));
    }

    private void itemListener(ItemEvent e){
        
    }

    public static Tools getTool() {
        return tool;
    }

    public static void setTool(Tools tool) {
        ToolBar.tool = tool;
    }

}
