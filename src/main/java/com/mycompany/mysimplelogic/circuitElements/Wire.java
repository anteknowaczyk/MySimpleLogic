
package com.mycompany.mysimplelogic.circuitElements;

import java.util.ArrayList;

/**
 * A wire.
 * @author Antoni Jan Nowaczyk
 */
public class Wire {
    private ArrayList<Location> span;
    private boolean power;
    
    public Wire() {
        span = new ArrayList<>();
        power = false;
    }
    
    public void add (Location loc) {
        span.add(loc);
    }
    
    public void eval() {
        if (span.get(0).get()) {
            span.get(span.size() - 1).set();
            power = true;
        }
        if (span.get(span.size() - 1).get()) {
            span.get(0).set();
            power = true;
        }
    }
    
    public ArrayList<Location> span() {
        return span;
    }
    
    public boolean powered() {
        return power;
    }
}
