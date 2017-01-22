package iww;

import iww.cellLogic.GameIterator;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author xasin
 */
public class GameWindow extends JPanel implements PositionCall {
    
    private final GameIterator gameLogic;
    private Button[][] gamePixels;
    private Dimension gameSize;
    private PositionCall positionCall;
    
    private Timer timer;
    
    public GameWindow(GameIterator gameLogic) {
        this.gameLogic = gameLogic;
        
        this.gameSize = gameLogic.getSize();
        this.setLayout(new GridLayout(gameSize.height, gameSize.width, 0, 0));
        
        this.gamePixels = new Button[gameSize.width][gameSize.height];
        
        System.out.println("Generating:" + gameSize.width + " " + gameSize.height);
        for(int y=0; y<gameSize.height; y++) {
            for(int x=0; x<gameSize.width; x++) {
                gamePixels[x][y] = new Button(x, y, gameLogic, this);
                this.add(gamePixels[x][y]);
            }
        }
        
        
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                refreshButtons();
            }
        }, 0, 1000/30);
    }
    
    public void click(int x, int y) {
        if(this.positionCall != null)
            this.positionCall.click(x, y);
    }
    
    public void setPositionCall(PositionCall pCall) {
        this.positionCall = pCall;
    }
     
    private void refreshButtons() {   
      for (int x=0; x<gameSize.width; x++)
          for (int y=0; y<gameSize.height; y++)
              gamePixels[x][y].redoColor();
      
      this.repaint();
    }
    
    public GameIterator getGameIterator() {
        return gameLogic;
    }
}
