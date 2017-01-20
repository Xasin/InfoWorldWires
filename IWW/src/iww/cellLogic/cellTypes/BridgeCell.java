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
    
    public int[] dirOffsets(int dir) {
        int[] offsets = new int[2];
        
        switch(dir%4) {
            case 0:
                offsets[0] = 1;
                break;
            case 1:
                offsets[1] = 1;
                break;
            case 2:
                offsets[0] = -1;
                break;
            case 3:
                offsets[1] = -1;
                break;
        }
        
        return offsets;
    }
    
    public boolean computeCell(int x, int y) {
        GameIterator gLogic = cellHandler.getGameLogic();
        CellField c = gLogic.getCellAt(x, y);
    
        if(c.getType() != 6)
            return false;
        
        CellField[] nearbyCells = new CellField[4];
        
        c.nextMetavalues[0] = 0;
        CellField j;
        for(int i=0; i<4; i++) {
            
            int[] dOffs = dirOffsets(i);
            j = gLogic.getCellAt(x-dOffs[0], y-dOffs[1]);
            if(j.getType() == 1 || j.getType() == 2) {
                
                for(int r = 1; r<50; r++) {
                    
                    j = gLogic.getCellAt(x + r*dOffs[0], y + r*dOffs[1]);
                    
                    if(j.getType() == 4 && j.getMetavalues()[0] != j.getMetavalues()[1]) {
                        c.nextMetavalues[0] = 1;
                        return true;
                    }
                }
            }
        }
        
        return true;
    }
}
