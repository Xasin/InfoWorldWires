package iww.cellLogic.cellTypes;

import java.awt.Color;
import iww.cellLogic.*;

/**
 *
 * @author xasin
 */
public class CellType {
    protected final GameIterator gameLogic;
    public final String name;
    
    public CellType(GameIterator logic, String name) {
        this.gameLogic = logic;
        this.name = name;
    }
    
    public Color getColor(CellField c) {
        return Color.darkGray;
    }
    
    public boolean isActiveFor(CellField c) {
        return false;
    }
    
    public boolean computeCell(int x, int y) {
        return false;
    }
    
    public CellField getNewCell() {
        CellField c = new CellField();
        return c;
    }
}
