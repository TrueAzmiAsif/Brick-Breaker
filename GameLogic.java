package DemoGame2;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameLogic extends JPanel implements KeyListener, ActionListener{
  boolean play=false;
  int delay=8;
  int score=0;
  int ballx=500,bally=500;
  int playx=490;
  int ballxdir=-1;
  int ballydir=-2;
  int totalbricks=21;
  Timer Timer;
  MapGenerator brick;
  GameLogic(){
    brick=new MapGenerator(3,7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    Timer = new Timer(delay, this);
        Timer.start();
  }
  public void paint(Graphics g){
    g.setColor(Color.black);
    g.fillRect(3,3,993,657);
    brick.draw((Graphics2D) g);
    g.setColor(Color.red);
    g.fillRect(0,0,3,700);
    g.fillRect(0,0,1000,3);
    g.fillRect(981,0,3,700);

    g.setColor(Color.white);
    g.setFont(new Font("serrif",Font.BOLD,25));
    g.drawString("Score: "+score,800,30);

    g.setColor(Color.red);
    g.fillOval(ballx,bally,20,20);

    g.setColor(Color.white);
    g.fillRect(playx,635,150,15);
    if (bally > 650) {
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over", 400, 350);
            g.setColor(Color.white);            
            g.drawString("Score: " + score, 400, 380);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 400, 420);
        }
    if(totalbricks == 0){
            play = false;
            ballydir = -2;
            ballxdir = -1;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    You Won!!!: "+score,400,350);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 400, 380);


        }
    g.dispose();
  }

  @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if (play) {
            if (new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(playx,635,150,15))) {
                ballydir = -ballydir;
            }

            A:
            for (int i = 0; i < brick.map.length; i++) {
                for (int j = 0; j < brick.map[0].length; j++) {
                    if (brick.map[i][j] > 0) {
                        int brickX = j * brick.brickw + 100;
                        int brickY = i * brick.brickh + 70;
                        int bricksWidth = brick.brickw;
                        int bricksHeight = brick.brickh;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballx, bally, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            brick.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballx + 19 <= brickrect.x || ballx + 1 >= brickrect.x + bricksWidth) {
                                ballxdir = -ballxdir;
                            } else {
                                ballydir = -ballydir;
                            }
                            break A;
                        }
                    }


                }
            }


            ballx += ballxdir;
            bally += ballydir;
            if (ballx < 0) {
                ballxdir = -ballxdir;
            }
            if (bally < 0) {
                ballydir = -ballydir;
            }
            if (ballx > 965) {
                ballxdir = -ballxdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playx >= 840) {
                playx = 840;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playx < 10) {
                playx = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballx = 500;
                bally = 500;
                ballxdir = -1;
                ballydir = -2;
                score = 0;
                playx = 490;
                totalbricks = 21;
                brick = new MapGenerator(3, 7);

                repaint();
            }
        }


        }
  public void moveRight ()
        {
            play = true;
            playx += 20;
        }
        public void moveLeft ()
        {
            play = true;
            playx -= 20;
        }
}