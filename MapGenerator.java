package DemoGame2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MapGenerator{
  int map[][];
  int brickh,brickw;
  MapGenerator(int row, int col){
    map=new int[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        map[i][j]=1;
      }
    }
    brickw=700/col;
    brickh=210/row;
  }
  public void draw(Graphics2D g){
    for(int i=0;i<map.length;i++){
      for(int j=0;j<map[0].length;j++){
        if(map[i][j]>0){
          //System.out.print("Hellooo");
          g.setColor(Color.white);
          g.fillRect(j*brickw+100,i*brickh+70,brickw,brickh);
          g.setStroke(new BasicStroke(3));
          g.setColor(Color.black);
          g.drawRect(j*brickw+100,i*brickh+70,brickw,brickh);
        }
      }
    }
  }

  public void setBricksValue(int value,int row,int col)
    {
        map[row][col] = value;

    }
}