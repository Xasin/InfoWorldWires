package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class WireCell extends CellType {
    public WireCell(CellHandler handler) {
        super(handler, "Wire");
    }

    public Color getColor(CellField c) {
        return Color.YELLOW;
    }
    
    public boolean computeCell(int x, int y) {
        CellField c = this.cellHandler.gameLogic.getCellAt(x, y);
        if(c.getType() != 1)
            return false;
        
        int n = this.cellHandler.countActiveCellsFor(x, y);
        if(n == 1 || n == 2) {
            c.nextType = 2;
            c.nextMetavalues[3] = 1;
        }
        
        return true;
    }
}
