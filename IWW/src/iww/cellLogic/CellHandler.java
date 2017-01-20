package iww.cellLogic;


import java.awt.*;
import iww.cellLogic.cellTypes.*;

/**
 *
 * @author xasin
 */
public class CellHandler {
    
    private final int clockLength = 4;
    private final CellType[] cellTypes;
    
    public final GameIterator gameLogic;
    
    public CellHandler(GameIterator logic) {
        this.gameLogic = logic;
        
        cellTypes = new CellType[2];
        cellTypes[0] = new CellType(this, "Empty Cell");
        cellTypes[1] = new WireCell(this);
    }
    
    public byte getCellTypes() {
        return 7;
    }
    
    public int countActiveCellsFor(int x, int y) {
        CellField centerCell = gameLogic.getCellAt(x, y);
        int n = 0;
        
        for(CellField c : gameLogic.getSurroundingCells(x, y))
            if(cellTypes[c.getType()].isActiveFor(centerCell))
                n++;
        
        return n;
    }
    
    public void computeNextState(GameIterator field, int x, int y) {
        for(CellType t : cellTypes) {
            if(t.computeCell(x, y))
                break;
        }
    }
    
    public Color getCellColor(CellField c) {
        return cellTypes[c.getType()].getColor(c);
    }
    
    public String getTypeName(int t) {
        return cellTypes[t].name;
    }
    
    public String[] getTypeNames() {
        String[] names = new String[this.getCellTypes()];
        for(int i=0; i<this.getCellTypes(); i++)
            names[i] = this.getTypeName(i);
        
        return names;
    }
}
