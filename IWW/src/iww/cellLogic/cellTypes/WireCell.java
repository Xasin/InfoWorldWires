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
        return false;
    }
}
