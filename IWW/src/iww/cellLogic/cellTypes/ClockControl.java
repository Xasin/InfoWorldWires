package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class ClockControl extends CellType {
    public ClockControl(CellHandler handler) {
        super(handler, "Clock Control");
    }
    
    public Color getColor(CellField c) {
        if(c.getMetavalues()[0] == 0)
            return new Color(0, 127, 0);
        else 
            return new Color(100, 255, 100);
    }
    
    private int countActiveControls(int x, int y) {
        int n = 0;
        for(CellField c : cellHandler.getGameLogic().getSurroundingCells(x, y))
            if(c.getType() == 4
                    && c.getMetavalues()[0] != 0)
                n++;
        
        return n;
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        
        if(c.getType() == 4) {
            if(cellHandler.countActiveCellsFor(x, y) == 0)
                c.nextMetavalues[0] = 0;
            else 
                c.nextMetavalues[0] = 1;
            
            return true;
        }
        
        if(c.getType() == 3) {
            int n = this.countActiveControls(x, y);
            if(n == 1 || n >= 3)
                c.nextMetavalues[1] = 0;
            if(n == 2)
                c.nextMetavalues[1] = 1;
            
            return true;
        }
        
        return false;
    }
}
