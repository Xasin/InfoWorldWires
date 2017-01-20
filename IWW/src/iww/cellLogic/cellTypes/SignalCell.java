package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class SignalCell extends CellType {
    private final int signalLength = 2;
    
    public SignalCell(CellHandler handler) {
        super(handler, "Signal Cell");
    }
    
    public Color getColor(CellField c) {
        if(c.getMetavalues()[0] == 0)
            return Color.RED;
        else 
            return Color.ORANGE;
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        if(source.getMetavalues()[0] == 0)
            return true;
        else 
            return false;
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        
        if(c.getType() == 1) {
            int n = cellHandler.countActiveCellsFor(x, y);
            if(n == 1 || n == 2) {
                c.nextType = 2;
                c.nextMetavalues[0] = 0;
                c.nextMetavalues[3] = 1;
                return true;
            }
        }
        
        if(c.getType() == 2) {
            byte timer = (byte) (c.getMetavalues()[0] + 1);
            c.nextMetavalues[0] = timer;

            if(timer == signalLength) {
                c.nextMetavalues[0] = 0;
                c.nextType = c.getMetavalues()[3];
                return true;
            }
        }
        
        return false;
    }
    
}
