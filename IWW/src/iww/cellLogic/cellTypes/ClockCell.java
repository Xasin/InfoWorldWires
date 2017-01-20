package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class ClockCell extends CellType {
    private final byte cycleLength = 2;
    
    public ClockCell(CellHandler handler) {
        super(handler, "Clock");
    }
    
    public Color getColor(CellField c) {
        float clockPhase = (float)(c.getMetavalues()[0])/cycleLength;
        
        if(c.getMetavalues()[1] == 0)
            return new Color(   (float)(0.2*clockPhase + 0.8), 
                                (float)(0.5*clockPhase + 0.1),
                                (float)(0.7 + 0.2*clockPhase));
        else
            return new Color(   (float)(0.2*clockPhase), 
                                (float)(0.5*clockPhase + 0.1),
                                (float)(0.8 + 0.2*clockPhase));
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        return (c.getType() == 1 
                && source.getMetavalues()[0] == cycleLength
                && source.getMetavalues()[1] == 0);            
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        if(c.getType() != 3)
            return false;
        
        byte timer = (byte) (c.getMetavalues()[0] -1);
        if(timer == -1)
            timer = cycleLength;
        c.nextMetavalues[0] = timer;
        
        return false;
    }
}