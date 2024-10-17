
package com.mycompany.mysimplelogic.circuitElements;

/**
 * An abstract logic gate.
 * @author Antoni Jan Nowaczyk
 */
public abstract class AbstractGate {
    Location input_st;
    Location input_nd;
    Location output;
    
    public AbstractGate(Location st, Location nd, Location out) {
        input_st = st;
        input_nd = nd;
        output = out;
    }
    
    public Location getLocation() {
        return input_st;
    }
    
    public Location Output() {
        return output;
    }
    
    public abstract void eval();
}
