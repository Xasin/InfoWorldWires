package iww.cellLogic;

import iww.cellLogic.CellHandler;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author xasin
 */
public class GameIterator {
 
    private CellField[][] cellStates;
    private CellHandler cellHandle;
    private int fieldX, fieldY;
    
    private Timer gameTick;
    private float cTicks, tickrate;
    private int leftTicks;
    
    private CellField penType;
    
    public GameIterator(int x, int y, CellHandler cellHandle) {
        this.cellStates = new CellField[x][y];
        for(int i=0; i<x; i++)
            for(int j=0; j<y; j++)
                cellStates[i][j] = new CellField();
        
        this.fieldX = x;
        this.fieldY = y;
        
        this.cellHandle = cellHandle;
        this.cellHandle.setGameLogic(this);
        
        gameTick = new Timer(true);
        gameTick.schedule(new TimerTask() {
            public void run() {
               timerTick();
            }
        }, 0, 10);
    }
    
    private void timerTick() {
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
    
    public void setTickrate(float tickrate) {
        this.tickrate = tickrate/100;
    }
    public void doNTicks(int n) {
        this.leftTicks = (n < 0) ? -1 : n;
    }
    
    public Dimension getSize() {
        Dimension d = new Dimension();
        d.height = this.fieldY;
        d.width = this.fieldX;
        
        return d;
    }
    
    public CellHandler getCellHandler() {
        return this.cellHandle;
    }
    
    public byte getCellTypeAt(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return 0;
        
        return cellStates[x][y].getType();
    }
    public CellField getCellAt(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return new CellField();
        
        return cellStates[x][y];      
    }
    
    public Color getCellColor(CellField t) {
        return this.cellHandle.getCellColor(t);
    }
    public Color getCellColorAt(int x, int y) {
        return this.getCellColor(this.getCellAt(x, y));
    }
    
    public CellField[] getSurroundingCells(int x, int y) {
        CellField[] cellArray = new CellField[8];
        
        for(int i=0; i<8; i++) {
            int j = (i > 3) ? i+1 : i;
            
            cellArray[i] = this.getCellAt(x +j%3 -1, y +j/3 -1);
        }
        
        return cellArray;
    }
    
    public void setPenType(CellField t) {
        if(t.getType() >= cellHandle.getCellTypes())
            return;
        
        this.penType = t;
    }
    
    public void incrementCellType(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return;
        
        this.cellStates[x][y].setType((byte) ((this.getCellTypeAt(x, y) + 1) % cellHandle.getCellTypes()));
    }
    public void applyCellType(int x, int y) {
       this.setCell(x, y, this.penType);
    }
    
    public void setCell(int x, int y, CellField t) {
        if(x >= this.fieldX || x < 0 || y >= this.fieldY || y < 0 || t.getType() >= cellHandle.getCellTypes())
            return;
        
        this.cellStates[x][y] = new CellField(t);
    }
    public void setCellType(int x, int y, byte t) {
        if(x >= this.fieldX || x < 0 || y >= this.fieldY || y < 0 || t >= cellHandle.getCellTypes())
            return;
        
        this.cellStates[x][y].setType(t);
        this.cellStates[x][y].setMetavalues(new byte[4]);
    }
    
    public void tick() {
        for(int x=0; x<fieldX; x++)
            for(int y=0; y<fieldY; y++) {
                cellHandle.computeNextState(x, y);
            }
        
        for(CellField cr[] : cellStates)
            for(CellField c : cr)
                c.applyNextState();
        
    }
    
    public void tick(int num) {        
        if(num < 1)
            return;
        
        for(int i=0; i<num; i++) {
            this.tick();
        }

    }
}
