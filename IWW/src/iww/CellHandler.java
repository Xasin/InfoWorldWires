package iww;


import java.awt.*;

/**
 *
 * @author xasin
 */
public class CellHandler {
    
    
    public int getCellTypes() {
        return 4;
    }
    
    public int computeNextState(int currentState, int[] surroundingStates) {
        switch(currentState) {
            case 0:
                return 0;
            case 1:
                if(surroundingStates[2] > 0 && surroundingStates[2] < 3)
                    return 2;
                return 1;
            case 2:
                return 3;
            case 3:
                return 1;
                
            default: 
                return 0;
        }
    }
    
    public Color getCellColor(int type) {
        switch(type) {
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
