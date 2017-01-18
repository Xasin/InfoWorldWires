package iww;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author xasin
 */
public class GameIterator {
 
    private int[][] cellStates;
    private CellHandler cellHandle;
    private int fieldX, fieldY;
    
    private Timer gameTick;
    private float cTicks, tickrate;
    private int leftTicks;
    
    public GameIterator(int x, int y, CellHandler cellHandle) {
        this.cellStates = new int[x][y];
        this.fieldX = x;
        this.fieldY = y;
        
        this.cellHandle = cellHandle;
        
        gameTick = new Timer(true);
        gameTick.schedule(new TimerTask() {
            public void run() {
                if(leftTicks == 0) return;
                
                cTicks += tickrate;
                
                int toDoTicks = (int)Math.floor(cTicks);
                if(toDoTicks == 0) return;
                
                cTicks -= toDoTicks;
                
                if(leftTicks == -1) {
                    tick(toDoTicks);
                }
                else if(leftTicks >= toDoTicks) {
                    tick(toDoTicks);
                    leftTicks -= toDoTicks;
                }
                else {
                    tick(leftTicks);
                    leftTicks = 0;
                }

            }
        }, 0, 10);
    }
    
    public void setTickrate(float tickrate) {
        this.tickrate = tickrate/100;
    }
    public void doNTicks(int n) {
        this.leftTicks = (n < 0) ? -1 : n;
    }
    
    public int getCellTypes() {
        return this.cellHandle.getCellTypes();
    }
    
    public int getCellTypeAt(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return 0;
        
        return cellStates[x][y];
    }
    
    public Color getCellColor(int t) {
        return this.cellHandle.getCellColor(t);
    }
    public Color getCellColorAt(int x, int y) {
        return this.getCellColor(this.getCellTypeAt(x, y));
    }
    
    public int[] getSurroundingCellTypes(int x, int y) {
        int[] cellTypes = new int[cellHandle.getCellTypes()];
        for(int i=0; i<cellTypes.length; i++) 
            cellTypes[i] = 0;
        
        // Return falls es außerhalb des Feldes liegt.
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return cellTypes;
        
        for(int i=0; i<9; i++)
            if(i != 4)
                cellTypes[getCellTypeAt(x + i%3 -1, y + i/3 -1)]++;
    
        return cellTypes;
    }
    
    public void incrementCellType(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return;
        this.cellStates[x][y] = (this.cellStates[x][y] + 1) % cellHandle.getCellTypes();
    }
    
    public void setCellType(int x, int y, int t) {
        if(x >= this.fieldX || x < 0 || y >= this.fieldY || y < 0 || t >= cellHandle.getCellTypes())
            return;
        
        this.cellStates[x][y] = t;
    }
    
    public void tick() {
        int[][] cellStatesNext = new int[fieldX][fieldY];
        
        for(int x=0; x<fieldX; x++)
            for(int y=0; y<fieldY; y++) {
                cellStatesNext[x][y] = cellHandle.computeNextState(cellStates[x][y], getSurroundingCellTypes(x, y));
            }
        
        this.cellStates = cellStatesNext;
    }
    
    public void tick(int num) {        
        if(num < 1)
            return;
        
        for(int i=0; i<num; i++) {
            this.tick();
        }

    }
}
