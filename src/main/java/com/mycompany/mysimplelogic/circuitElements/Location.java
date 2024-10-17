
package com.mycompany.mysimplelogic.circuitElements;

/**
 * A location on a plug board.
 * @author Antoni Jan Nowaczyk
 */
public class Location {
    private final int row;
    private final int column;
    private boolean up;
    
    public Location(int r, int c) {
        this.row = r;
        this.column = c;
    }
    
    public int get_row() {
        return row;
    }
    
    public int get_col() {
        return column;
    }
    
    public void set() {
        up = true;
    }
    
    public boolean get() {
        return up;
    }
}
