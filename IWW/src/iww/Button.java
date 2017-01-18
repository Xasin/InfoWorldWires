package iww;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Button extends JButton{
  
  private final int size    = 20;
  private final int margin  = 10;
  private final int posX, posY;
  private GameIterator gameLogic;  
  
  public Button(int x, int y, GameIterator game, Container cp) {
    this.posX = x;
    this.posY = y;
    
    this.gameLogic = game;
    
    setBounds(size*x +margin, size*y +margin, size, size);
    //setMargin(new Insets(2, 2, 2, 2));
    //setBorder(BorderFactory.createBevelBorder(0, new Color(0xC0C0C0), Color.DARK_GRAY));
    
    setBackground(gameLogic.getCellColor(posX, posY));

    addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        oneButton_ActionPerformed(evt);
      }
    });
    
    cp.add(this);
    
  }
  
  public void redoColor() {
    setBackground(gameLogic.getCellColor(posX, posY));
  }
  
  public void oneButton_ActionPerformed(ActionEvent evt) {
    gameLogic.incrementCellType(this.posX, this.posY);
    redoColor();
  } 
} 