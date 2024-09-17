package src.GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.Window;
import src.sth.Mat;

public class ToolBar extends JPanel {

    public static enum Tools{
        PENCIL,
        SQUARE,
        OVAL,
        POLYGON
    }

    private static Tools tool = Tools.PENCIL;

    private static int polygonSides = 3;

    private static JComboBox<String> tools_combo = new JComboBox<>();

    private static JButton colorButton = new JButton();
    private static JButton fillColorButton = new JButton();

    private static JButton undoButton = new JButton();
    private static JButton redoButton = new JButton();

    private static JSpinner strokeSize = new JSpinner();

    private static JSpinner jPolygonSides = new JSpinner();

    private static JButton newCanvas = new JButton();

    public ToolBar(){}

    public ToolBar(int x, int y, int width, int height, Color background_color){

        polygonSides = Mat.clamp(polygonSides, 3, 90);

        setBounds(x, y, width, height);
        setBackground(background_color);
        setLayout(null);
        setVisible(true);

        undoButton.setBounds(0, 0, 75, height);
        undoButton.setText("Undo");
        undoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        undoButton.setEnabled(false);
        undoButton.addActionListener(l -> actionListener(l));
        undoButton.setFocusable(false);
        add(undoButton);

        redoButton.setBounds(undoButton.getX() + undoButton.getWidth() + 15, 0, 75, height);
        redoButton.setText("Redo");
        redoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redoButton.setEnabled(false);
        redoButton.addActionListener(l -> actionListener(l));
        redoButton.setFocusable(false);
        add(redoButton);

        createToolsComboBox();
        tools_combo.setToolTipText("Escolha suas ferramenta de desenho.");
        tools_combo.setBounds(redoButton.getX() + redoButton.getWidth() + 25, 0, 100, height);
        tools_combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tools_combo.setFocusable(false);
        add(tools_combo);

        colorButton.setBackground(Board.getColor());
        colorButton.setBounds(tools_combo.getX() + tools_combo.getWidth() + 25, 0, 50, height);
        colorButton.addActionListener(l -> actionListener(l));
        colorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        colorButton.setFocusPainted(false);
        colorButton.setFocusable(false);
        add(colorButton);

        fillColorButton.setBackground(Color.BLACK);
        fillColorButton.setBounds(colorButton.getX() + colorButton.getWidth() + 25, 0, 50, height);
        fillColorButton.addActionListener(l -> actionListener(l));
        fillColorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fillColorButton.setFocusPainted(false);
        fillColorButton.setFocusable(false);
        add(fillColorButton);

        strokeSize.setBounds(fillColorButton.getX() + fillColorButton.getWidth() + 25, 0, 100, height);
        strokeSize.setValue(1);
        strokeSize.setFocusable(false);
        strokeSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                strokeSize.setValue(Mat.clamp((int) strokeSize.getValue(), 1, 100));
                Board.setStrokeSize((int) strokeSize.getValue());
                strokeSize.transferFocusBackward();
            }
        }
        );
        add(strokeSize);

        jPolygonSides.setBounds(strokeSize.getX() + strokeSize.getWidth() + 25, 0, 100, height);
        jPolygonSides.setValue(3);
        jPolygonSides.setFocusable(false);
        jPolygonSides.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jPolygonSides.setValue(Mat.clamp((int) jPolygonSides.getValue(), 3, 90));
                polygonSides = (int) jPolygonSides.getValue();
                jPolygonSides.transferFocusBackward();
            }
        }
        );
        add(jPolygonSides);
        jPolygonSides.setVisible(false);

        newCanvas.setBounds(jPolygonSides.getX() + jPolygonSides.getWidth() + 15, 0, 75, height);
        newCanvas.setFont(new Font("", Font.PLAIN, 7));
        newCanvas.setText("New Canvas");
        newCanvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // newCanvas.setEnabled(false);
        newCanvas.addActionListener(l -> actionListener(l));
        newCanvas.setFocusable(false);
        add(newCanvas);

    }

    private void createToolsComboBox(){
        tools_combo.addItem("Pencil");
        tools_combo.addItem("Square");
        tools_combo.addItem("Oval");
        tools_combo.addItem("Polygon");
        tools_combo.addActionListener(l -> actionListener(l));
    }

    private void actionListener(ActionEvent l){
        if(l.getSource() == tools_combo){
            jPolygonSides.setVisible(false);
            Window.board.repaint();
            switch ((String) tools_combo.getSelectedItem()) {
                case "Pencil":
                    Board.setTool(Tools.PENCIL);
                    break;
                case "Square":
                    Board.setTool(Tools.SQUARE);
                    break;
                case "Oval":
                    Board.setTool(Tools.OVAL);
                    break;
                case "Polygon":
                    Board.setTool(Tools.POLYGON);
                    jPolygonSides.setVisible(true);
                    Window.board.repaint();
                    break;
                default:
                    break;
            }
        } else if(l.getSource() == colorButton){
            Color color = JColorChooser.showDialog(this, "Contour color", colorButton.getBackground());
            if (color != null) {
                colorButton.setBackground(color);
                Board.setColor(color);
            }
        } else if(l.getSource() == fillColorButton){
            Color color = JColorChooser.showDialog(this, "Fill color", colorButton.getBackground());
            if (color != null) {
                fillColorButton.setBackground(color);
                Board.set_fill_color(color);
            }
        } else if(l.getSource() == undoButton && Board.itemsSize() > 0){
            Board.removeItemAt(Board.itemsSize() - 1);
        } else if(l.getSource() == redoButton && Board.itemsCopySize() > 0){
            Board.dropItemsCopyAt(Board.itemsCopySize() - 1);
        } else if(l.getSource() == newCanvas){
            int choice = JOptionPane.showConfirmDialog(this, "You want to create a new Canvas?", "Create new Canvas", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                Board.clearBoard();
            }
        }
        Window.board.repaint();
    }

    public static Tools getTool() {
        return tool;
    }

    public static void setTool(Tools tool) {
        ToolBar.tool = tool;
    }

    public static int getPolygonSides() {
        return polygonSides;
    }

    public static void setUndoEnabled(boolean enabled){
        undoButton.setEnabled(enabled);
    }

    public static void setRedoEnabled(boolean enabled){
        redoButton.setEnabled(enabled);
    }

}
