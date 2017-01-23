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
        return new Color(   0, 
                            (c.getMetavalues()[1] >= 2 ? 0 : 100) + (isActive(c) ? 100 : 0), 
                            (isActive(c) || c.getMetavalues()[1] >= 2 ? 200 : 100));
    }
    
    private boolean isActive(CellField c) {
        if(c.getMetavalues()[1] == 2)
            return false;
        return c.getMetavalues()[0] != c.getMetavalues()[1];
    }
    
    @Override
    public boolean isActiveFor(CellField c, CellField source) {
        if(c.getType() == 4)
            return false;
        return isActive(source);
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
                    else if(j.getType() == 6 && j.getMetavalues()[1] == 2)
                        return true;
                }
            }
        }
        
        return true;
    }
    
    public CellField getNewCell() {
        CellField c = new CellField();
        c.setType((byte)6);
        return c;
    }
    
    public void applyTo(int x, int y) {
        GameIterator gameLogic = cellHandler.getGameLogic();
        CellField c = gameLogic.getCellAt(x, y);
        
        if(c.getType() == 6) {
           byte[] metas = c.getMetavalues();
           metas[1] = (byte)((metas[1] + 1) % 3);
           c.setMetavalues(metas);
        }
        else
            gameLogic.setCell(x, y, this.getNewCell());
    }
}
