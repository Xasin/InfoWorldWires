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
public class GameWindow extends JPanel {
    
    private final GameIterator gameLogic;
    private Button[][] gamePixels;
    private Dimension gameSize;
    
    private Timer timer;
    
    public GameWindow(GameIterator gameLogic) {
        this.gameLogic = gameLogic;
        
        this.gameSize = gameLogic.getSize();
        this.setLayout(new GridLayout(gameSize.height, gameSize.width, 0, 0));
        
        this.gamePixels = new Button[gameSize.width][gameSize.height];
        
        
        ButtonFunction bFunc = new ButtonFunction() {
            public void run(int posX, int posY) {
                System.out.println("Test:" + posX + " " + posY);
                gameLogic.incrementCellType(posX, posY);
            }
        };
        
        System.out.println("Generating:" + gameSize.width + " " + gameSize.height);
        for(int y=0; y<gameSize.height; y++) {
            for(int x=0; x<gameSize.width; x++) {
                gamePixels[x][y] = new Button(x, y, gameLogic, bFunc);
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
    
      
    private void refreshButtons() {   
      for (int x=0; x<gameSize.width; x++)
          for (int y=0; y<gameSize.height; y++)
              gamePixels[x][y].redoColor();
      
      this.repaint();
    }
}
