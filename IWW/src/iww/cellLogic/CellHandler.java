package iww.cellLogic;


import java.awt.*;
import iww.cellLogic.cellTypes.*;

/**
 *
 * @author xasin
 */
public class CellHandler {
    
    private final CellType[] cellTypes;
    private GameIterator gameLogic;
    
    public CellHandler() {
        cellTypes = new CellType[7];
        cellTypes[0] = new CellType(this, "Empty Cell");
        cellTypes[1] = new WireCell(this);
        cellTypes[2] = new SignalCell(this);
        cellTypes[3] = new ClockCell(this);
        cellTypes[4] = new ActivatorCell(this);
        cellTypes[5] = new RandomCell(this);
        cellTypes[6] = new BridgeCell(this);
    }
    public void setGameLogic(GameIterator logic) {
        this.gameLogic = logic;
    }
    public GameIterator getGameLogic() {
        return this.gameLogic;
    }
    
    public byte getCellTypes() {
        return (byte)cellTypes.length;
    }
    
    public void applyCellType(int x, int y, int t) {
        if(t >= this.getCellTypes())
            return;
        
        cellTypes[t].applyTo(x, y);
    }
    
    public int countActiveCellsFor(int x, int y) {
        CellField centerCell = gameLogic.getCellAt(x, y);
        int n = 0;
        
        for(CellField c : gameLogic.getSurroundingCells(x, y))
            if(cellTypes[c.getType()].isActiveFor(centerCell, c))
                n++;
        
        return n;
    }
    
    public void computeNextState(int x, int y) {
        for(CellType t : cellTypes) {
            t.computeCell(x, y);
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
