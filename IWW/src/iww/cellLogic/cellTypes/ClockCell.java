package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class ClockCell extends CellType {
    public ClockCell(CellHandler handler) {
        super(handler, "Clock");
    }
    
    public Color getColor(CellField c) {
        float clockPhase = ((float)c.getMetavalues()[0])/c.getMetavalues()[2];
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
                && source.getMetavalues()[0] == source.getMetavalues()[2]
                && source.getMetavalues()[1] == 0);            
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        if(c.getType() != 3)
            return false;
        
        byte timer = (byte) (c.getMetavalues()[0] -1);
        if(timer <= 0)
            timer = c.getMetavalues()[2];
        c.nextMetavalues[0] = timer;
        
        return false;
    }
    
    public void applyTo(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        
        if(c.getType() != 3) {
            byte[] metas = new byte[4];
            metas[2] = 3;
            c.setMetavalues(metas);
            c.setType((byte)3);
        }
        else
            c.nextMetavalues[2]++;
    }
}
