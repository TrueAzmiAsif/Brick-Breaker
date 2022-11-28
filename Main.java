package DemoGame2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    public static void main(String[] args) {
    JFrame obj=new JFrame();
      GameLogic game=new GameLogic();
      obj.setBounds(0,0,1000,700);
      obj.setTitle("The Brick Breaker");
      obj.setResizable(false);
      obj.setVisible(true);
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      obj.add(game);
  }
}
