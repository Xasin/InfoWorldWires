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
        int fR = 180;
        return new Color(127-fR/2 + (int)(fR*Math.random()),
                         127-fR/2 + (int)(fR*Math.random()),
                         127-fR/2 + (int)(fR*Math.random()));
    }
    
    public boolean isActiveFor(CellField c, CellField source) {
        return (Math.random()*100) <= (byte)(source.getMetavalues()[0]);
    }
    
    public boolean computeCell(int x, int y) {
        return false;
    }
    
    public CellField getNewCell() {
        CellField c = new CellField();
        c.setType((byte)5);
        byte[] newMetas = {30, 0, 0, 0};
        c.setMetavalues(newMetas);
        
        return c;
    }
}
