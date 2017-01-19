package iww.cellLogic;


import java.awt.*;

/**
 *
 * @author xasin
 */
public class CellHandler {
    
    public byte getCellTypes() {
        return 5;
    }
    
    private int countActiveWires(CellField[] cells) {
        int n = 0;
        
        for(CellField c : cells) {
            if(c.getType() == 2)
                n++;
            if(c.getType() == 4 && c.getMetavalues()[0] == 0)
                n++;
        }
        
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
            
            case 4:
                c.nextMetavalues[0] = (byte) ((c.getMetavalues()[0] + 1) % 10);
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
            case 4:
                return new Color(200, (int)((10 - c.getMetavalues()[0])*150/10), 200);
            
            default:
                return Color.LIGHT_GRAY;
        }
    }
}
