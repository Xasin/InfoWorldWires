package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;
/**
 *
 * @author xasin
 */
public class BridgeCell extends CellType {
    public BridgeCell(CellHandler handle) {
        super(handle, "Bridge End");
    }
    
    public Color getColor(CellField c) {
        int i = c.getMetavalues()[0] + 1;
        return new Color(0, 100*i, 100*i);
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        if(c.getType() == 4)
            return false;
        return source.getMetavalues()[0] == 1;
    }
    
    public boolean computeCell(int x, int y) {
        GameIterator gLogic = cellHandler.getGameLogic();
        CellField c = gLogic.getCellAt(x, y);
    
        if(c.getType() != 6)
            return false;
        
        CellField[] nearbyCells = new CellField[4];
        
        nearbyCells[0] = gLogic.getCellAt(x + 2, y);
        nearbyCells[1] = gLogic.getCellAt(x, y + 2);
        nearbyCells[2] = gLogic.getCellAt(x - 2, y);
        nearbyCells[3] = gLogic.getCellAt(x, y - 2);
        
        c.nextMetavalues[0] = 0;
        
        for(CellField i : nearbyCells)
            if(i.getType() == 4 && i.getMetavalues()[0] != 0) {
                c.nextMetavalues[0] = 1;
                return true;
            }
        
        return true;
    }
}
