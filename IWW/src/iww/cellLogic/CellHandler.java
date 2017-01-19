package iww.cellLogic;


import java.awt.*;

/**
 *
 * @author xasin
 */
public class CellHandler {
    
    public byte getCellTypes() {
        return 4;
    }
    
    private int countActiveWires(CellField[] cells) {
        int n = 0;
        
        for(CellField c : cells)
            if(c.getType() == 2)
                n++;
        
        return n;
    }
    
    public void computeNextState(GameIterator field, int x, int y) {
        CellField c = field.getCellAt(x, y);
        CellField[] surroundingCells = field.getSurroundingCells(x, y);
        
        switch(c.getType()) {
            case 0:
            break;
            
            case 1:
                int n = countActiveWires(surroundingCells);
                if(n > 0 && n < 3)
                    c.nextType = 2;
            break;
            
            case 2:
                c.nextType = 3;
            break;
            
            case 3:
                c.nextType = 1;
            break;
        }
    }
    
    public Color getCellColor(CellField c) {
        switch(c.getType()) {
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.BLACK;
            
            default:
                return Color.LIGHT_GRAY;
        }
    }
}
