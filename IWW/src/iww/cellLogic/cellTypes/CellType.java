package iww.cellLogic.cellTypes;

import java.awt.Color;
import iww.cellLogic.*;

/**
 *
 * @author xasin
 */
public class CellType {
    protected final CellHandler cellHandler;
    public final String name;
    
    public CellType(CellHandler handler, String name) {
        this.cellHandler = handler;
        this.name = name;
    }
    
    public Color getColor(CellField c) {
        return Color.darkGray;
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        return false;
    }
    
    public boolean computeCell(int x, int y) {
        return false;
    }
    
    public CellField getNewCell() {
        CellField c = new CellField();
        return c;
    }
    
    public void applyTo(int x, int y) {
        cellHandler.getGameLogic().setCell(x, y, this.getNewCell());
    }
}
