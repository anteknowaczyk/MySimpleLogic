
package com.mycompany.mysimplelogic.circuitElements;

/**
 * And gate
 * @author Antoni Jan Nowaczyk
 */
public class AndGate extends AbstractGate {

    public AndGate(Location st, Location nd, Location out) {
        super(st, nd, out);
    }

    @Override
    public void eval() {
        if (this.input_st.get() && this.input_nd.get()) {
            this.output.set();
        }
    }
    
}
