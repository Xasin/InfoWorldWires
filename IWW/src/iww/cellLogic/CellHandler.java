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
    
    private int countActiveCellsFor(int x, int y) {
        CellField centerCell = gameLogic.getCellAt(x, y);
        int n = 0;
        
        for(CellField c : gameLogic.getSurroundingCells(x, y))
            if(cellTypes[c.getType()].isActiveFor(centerCell))
                n++;
        
        return n;
    }
    
    public void computeNextState(GameIterator field, int x, int y) {
        CellField c = field.getCellAt(x, y);
        CellField[] surroundingCells = field.getSurroundingCells(x, y);
        
        int n = countActiveWires(surroundingCells, true);
        switch(c.getType()) {
            case 0:
            break;
            
            case 1:
                if(n > 0 && n < 3) {
                    c.nextType = 2;
                    c.nextMetavalues[3] = 1;
                }
            break;
            
            case 2:
                c.nextType = 3;
                c.nextMetavalues[0] = 2;
            break;
            
            case 3:
                if(--c.nextMetavalues[0] == 0)
                    c.nextType = c.getMetavalues()[3];
            break;
            
            case 4:
            case 5:
                if(n > 2)
                    c.nextType = 5;
                if(n == 1 || n == 2)
                    c.nextType = 4;
                
                c.nextMetavalues[0] = (byte) ((c.getMetavalues()[0] + 1) % this.clockLength);
            break;
            
            case 6:
                int m = countActiveWires(surroundingCells, false);
                if(m == 1 || m == 2) {
                    c.nextType = 2;
                    c.nextMetavalues[0] = 3;
                    c.nextMetavalues[3] = 6;
                }
        }
    }
    
    public Color getCellColor(CellField c) {
        float cP;
        
        switch(c.getType()) {
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.BLACK;
            case 4:
                cP = ((float) c.getMetavalues()[0])/clockLength;
                return new Color((int)(255*(1-cP*0.3)), (int)(255*(1-cP)), (int)(255*(1-cP*0.3)));
            case 5:
                cP = ((float) c.getMetavalues()[0])/clockLength;
                return new Color(0, (int)((1-cP*0.7)*150), (int)((1-cP*0.8)*250));
            case 6:
                return Color.GREEN;
                
            default:
                return Color.LIGHT_GRAY;
        }
    }
    
    public String getTypeName(int t) {
        switch(t) {
            case 0:
                return "Empty Tile";
            case 1:
                return "Wire";
            case 2:
                return "Signal Head";
            case 3:
                return "Signal Tail";
            case 4:
                return "Clock (On)";
            case 5:
                return "Clock (Off)";
            case 6:
                return "Gate Block A";
        }
        
        return "";
    }
    
    public String[] getTypeNames() {
        String[] names = new String[this.getCellTypes()];
        for(int i=0; i<this.getCellTypes(); i++)
            names[i] = this.getTypeName(i);
        
        return names;
    }
}
