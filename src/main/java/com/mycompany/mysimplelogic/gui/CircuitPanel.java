
package com.mycompany.mysimplelogic.gui;

import com.mycompany.mysimplelogic.circuitElements.AbstractGate;
import com.mycompany.mysimplelogic.circuitElements.AndGate;
import com.mycompany.mysimplelogic.circuitElements.Location;
import com.mycompany.mysimplelogic.circuitElements.Wire;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author Antoni Jan Nowaczyk
 */
public class CircuitPanel extends javax.swing.JPanel {
    
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    
    private int gridsize = 25;
    private int offsetX = 0;
    private int offsetY = 0;
    
    private int nrOfRows = WIDTH / gridsize;
    private int nrOfCols = HEIGHT / gridsize;
    
    private int highlightX = nrOfRows / 2;
    private int highlightY = nrOfCols / 2;
    
    private ArrayList<AbstractGate> gates;
    private ArrayList<ArrayList<Location>> grid;
    private ArrayList<Location> powered;
    private ArrayList<Wire> wires;
    private Wire currentlyBuilt;
    /**
     * Creates new form circuitPanel
     */
    public CircuitPanel() {
        grid = new ArrayList<>();
        gates = new ArrayList<>();
        powered = new ArrayList<>();
        wires = new ArrayList<>();
        
        for (int i = 0; i < nrOfRows; i++) {
            ArrayList<Location> row = new ArrayList<>();
            for (int j = 0; j < nrOfCols; j++) {
                row.add(new Location(i, j));
            }
            grid.add(row);
        }
        
        this.setSize(WIDTH, HEIGHT);
        initComponents();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //Paint grid.
        g.setColor(Color.GRAY);
        for (ArrayList<Location> row : grid) {
            for (Location loc : row) {
                g.drawRect(loc.get_row() * gridsize, loc.get_col() * gridsize, gridsize, gridsize);
            }
        }
        //Paint gates.
        g.setColor(Color.BLUE);
        for (AbstractGate gate : gates) {
            g.fillRect(gate.getLocation().get_row() * gridsize, gate.getLocation().get_col() * gridsize, gridsize, 2 * gridsize);
            g.fillOval(gate.getLocation().get_row() * gridsize - 5, (gate.getLocation().get_col() + 1) * gridsize - 5, 10, 10);
        }
        //Paint wires
        for (Wire w : wires) {
        if (w.powered()) {
            g.setColor(Color.PINK);
        } else {
            g.setColor(Color.BLACK);
        }
            for (int i = 1; i < w.span().size(); i++) {
                Location first = w.span().get(i - 1);
                Location second = w.span().get(i);
                //Right to left
                if (first.get_row() > second.get_row()) {
                    Location temp = first;
                    first = second;
                    second = temp;
                }
                //Bottom up
                if (w.span().get(i - 1).get_col() > w.span().get(i).get_col()) {
                    Location temp = first;
                    first = second;
                    second = temp;
                }
                g.fillRect(first.get_row() * gridsize - 1, first.get_col() * gridsize - 1,
                        (second.get_row() - first.get_row()) * gridsize + 2,
                        (second.get_col() - first.get_col()) * gridsize + 2);
            }
        }
        //Paint powered cells.
        for (Location loc : powered) {
            g.setColor(Color.PINK);
            g.fillOval(loc.get_row() * gridsize - 5, loc.get_col() * gridsize - 5, 10, 10);
        }
        //Paint highlight.
        g.setColor(Color.RED);
        g.fillOval(highlightX * gridsize - 5, highlightY * gridsize - 5, 10, 10);
    }
    
    public void moveHighlight(int x, int y) {
        highlightX += x;
        highlightY += y;
        if (currentlyBuilt != null) {
            currentlyBuilt.add(grid.get(highlightX).get(highlightY));
        }
    }
    
    public void addGate(String type) {
        AbstractGate newGate;
        Location st = grid.get(highlightX).get(highlightY);
        Location nd =  grid.get(highlightX).get(highlightY + 2);
        Location out = grid.get(highlightX + 1).get(highlightY + 1);
        switch (type) {
            case "AND" -> newGate = new AndGate(st, nd, out);
            default -> { newGate = null; }
        }
        gates.add(newGate);
    }
    
    public void powerUp() {
        grid.get(highlightX).get(highlightY).set();
        powered.add(grid.get(highlightX).get(highlightY));
    }
    
    public void rebool() {
        for (AbstractGate gate : gates) {
            gate.eval();
            if (gate.Output().get()){
                powered.add(gate.Output());
            }
        }
        for (Wire w : wires) {
            w.eval();
            if (w.powered()) {
                powered.add(w.span().get(w.span().size() - 1));
            }
        }
    }
    
    public void startWire() {
        Location start = grid.get(highlightX).get(highlightY);
        Wire newWire = new Wire();
        newWire.add(start);
        
        wires.add(newWire);
        currentlyBuilt = newWire;
    }
    
    public void stopWire() {
        currentlyBuilt = null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
