package iww;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Button extends JButton{

  private final int posX, posY;
  private GameIterator gameLogic;
  private ButtonFunction onButtonPress;
  
  public Button(int x, int y, GameIterator game, ButtonFunction btnPressAction) {
    this.posX = x;
    this.posY = y;
    
    this.gameLogic = game;
    this.onButtonPress = btnPressAction;
    
    setBackground(gameLogic.getCellColorAt(posX, posY));

    addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) {
          onButtonPress.run(posX, posY);
      }
    });    
  }
  
  public void redoColor() {
    setBackground(gameLogic.getCellColorAt(posX, posY));
  } 
} 