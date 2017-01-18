/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iww;

/**
 *
 * @author xasin
 */
public class GameIterator {
 
    private int[][] cellStates;
    private CellHandler cellHandle;
    private int fieldX, fieldY;
    
    public GameIterator(int x, int y, CellHandler cellHandle) {
        this.cellStates = new int[x][y];
        this.fieldX = x;
        this.fieldY = y;
        
        this.cellHandle = cellHandle;
    }
    
    public int getCellType(int x, int y) {
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return 0;
        
        return cellStates[x][y];
    }
    
    public int[] getSurroundingCellTypes(int x, int y) {
        int[] cellTypes = new int[cellHandle.getCellTypes()];
        for(int i=0; i<cellTypes.length; i++) 
            cellTypes[i] = 0;
        
        // Return falls es außerhalb des Feldes liegt.
        if(x > (this.fieldX-1) || x < 0 || y > (this.fieldY-1) || y < 0)
            return cellTypes;
        
        for(int i=0; i<9; i++) {
            if(i != 4)
                cellTypes[getCellType(i%3, i/3)]++;
        }
    
        return cellTypes;
    }
    
    public void incrementCellType(int x, int y) {
        this.cellStates[x][y] = (this.cellStates[x][y] + 1) % cellHandle.getCellTypes();
    }
    
    public void tick() {
        int[][] cellStatesNext = new int[fieldX][fieldY];
        
        for(int x=0; x<fieldX; x++)
            for(int y=0; y<fieldY; y++) {
                cellStatesNext[x][y] = cellHandle.computeNextState(getSurroundingCellTypes(x, y));
            }
        
        this.cellStates = cellStatesNext;
    }
    
    public void tick(int num) {
        if(num < 0 || num > 1000000)
            return;
        
        for(int i=0; i<num; i++)
            this.tick();
    }
}
