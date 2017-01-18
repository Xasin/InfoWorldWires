import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Button extends JButton{
  
  private int size    = 20;
  private int margin  = 10;
  private int howMany;
    
  public Button(int i, int j, int howMany, Container cp) {
    this.howMany = howMany;
    
    setBounds(size*i+margin, size*j+margin, size, size);
    setText(howMany+"");
    setMargin(new Insets(2, 2, 2, 2));
    setBorder(BorderFactory.createBevelBorder(0, new Color(0xC0C0C0), Color.DARK_GRAY));
    
    addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        oneButton_ActionPerformed(evt);
      }
    });
    
    cp.add(this);
    
  }
  
  public void oneButton_ActionPerformed(ActionEvent evt) {
    setBorderPainted(false);
    setForeground(Color.RED);
    setBackground(Color.BLACK);
    setText("B");
  } 
} 