package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class ActivatorCell extends CellType {
    public ActivatorCell(CellHandler handler) {
        super(handler, "Activator");
    }
    
    public Color getColor(CellField c) {
        if(c.getMetavalues()[0] == c.getMetavalues()[1])
            return new Color(0, 127, 0);
        else 
            return new Color(100, 255, 100);
    }
    
    private int countActiveControls(int x, int y) {
        int n = 0;
        for(CellField c : cellHandler.getGameLogic().getSurroundingCells(x, y))
            if(c.getType() == 4
                    && c.getMetavalues()[0] != c.getMetavalues()[1])
                n++;
        
        return n;
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        
        if(c.getType() == 4) {
            int n = cellHandler.countActiveCellsFor(x, y);
        
            if(n != 0) {
                c.nextMetavalues[0] = 1;
                c.nextMetavalues[2] = 2;
            }
            else {
                if(c.getMetavalues()[2] != 0)
                    c.nextMetavalues[2]--;
                else 
                    c.nextMetavalues[0] = 0;
            }
            
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
    
    public void applyTo(int x, int y) {
        CellField c = cellHandler.getGameLogic().getCellAt(x, y);
        
        if(c.getType() != 4) {
            c.setMetavalues(new byte[4]);
            c.setType((byte)4);
        }
        else {
            byte[] meta = c.getMetavalues();
            meta[1] ^= 1;
            c.setMetavalues(meta);
        }
    }
}
