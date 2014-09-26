import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

SnowFlake [] snow;
int xloc1, yloc1;
int xloc2, yloc2;
int r,g,b;
boolean left;
public void setup()
{
  background(0);
  size(800,400);
  snow = new SnowFlake[1000];
  for(int i=0;i<snow.length;i++)
  {
    snow[i] = new SnowFlake();
  }
  frameRate(120);
  xloc1 = mouseX;
  xloc2 = 0;
  yloc1 = mouseY;
  yloc2 = 0;
  left = true;
  r = 255;
  g = 0;
  b = 0;
}
public void draw()
{
  for(int i=0;i<snow.length;i++)
  {
    snow[i].lookDown();
    snow[i].erase();
    snow[i].move();
    snow[i].flip();
    snow[i].show();
    snow[i].wrap();
  }
  if(key == 'r')
  {
    r = 255;
    g = 0;
    b = 0;
  }
  else if(key == 'b')
  {
    r = 0;
    b = 255;
    g = 0;
  }
  else if(key == 'g')
  {
    r = 0;
    g = 255;
    b = 0;
  }
  textAlign(CENTER);
  textSize(32);
  fill(r,g,b);
  text("Merry Christmas!",400,50);
}
public void mouseDragged()
{
  int siz = 10;
  noStroke();
  if(mouseButton == LEFT) 
  {  
    fill(r,g,b);
  }
  else if(mouseButton == RIGHT)
  {
    fill(0);
    siz = 30;
  }
  ellipse(mouseX,mouseY,siz,siz);
}

class SnowFlake
{
  //class member variable declarations
  int x,y,width;
  boolean isMoving;
  SnowFlake()
  {
    //class member variable initializations
    x = (int)(Math.random()*800);
    y = (int)(Math.random()*400);
    width = 5;
    isMoving = true;
  }
  public void show()
  {
    stroke(0);
    fill(255);
    ellipse(x,y,width,5);
  }
  public void flip()
  {
    if(width >= 5)
    {
      width--;
    }
    else if(width <= 1)
    {
      width++;
    }
  }
  public void lookDown()
  {
    if(get(x,y+6) == color(255,0,0) || get(x,y+6) == color(255) || get(x,y+6) == color(0,255,0)||get(x,y+6) == color(0,0,255))
    {
      isMoving = false;
    }
    else
    {
      isMoving = true;
    }
  }
  public void erase()
  {
    fill(0);
    ellipse(x,y,7,7);
  }
  public void move()
  {
    if(isMoving)
    {
      y++;
    }
  }
  public void wrap()
  {
    if(y+5>400)
    {
      y = (int)(Math.random()*10-20);
      x = (int)(Math.random()*800);
    }
  }
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
