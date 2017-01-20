package iww.cellLogic.cellTypes;

import iww.cellLogic.*;
import java.awt.Color;

/**
 *
 * @author xasin
 */
public class RandomCell extends CellType {
    public RandomCell(CellHandler handler) {
        super(handler, "Random Output");
    }
    
    public Color getColor(CellField c) {
        int fR = 100;
        return new Color(127-fR/2 + (int)(fR*Math.random()),
                         127-fR/2 + (int)(fR*Math.random()),
                         127-fR/2 + (int)(fR*Math.random()));
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        return (Math.random() <= 0.2);
    }
    
    public boolean computeCell(int x, int y) {
        return false;
    }
}
