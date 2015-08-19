package me.bryan;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Enemy
{
	GameFrame gf;
	int i;
	private int x=0;
	private int y;
	private int xa;
	private int ya;
	private int speed = 3;
	
    public Enemy(GameFrame gf, int i)
    {
    	this.gf = gf;
    	Random randx = new Random();
    	x = randx.nextInt((80 + (i * 80)) + (i * 80));
    	y = 0;
    }
    public void move()
    {
    	int boost = speed + (int)Math.floor(gf.boostCount);
    	ya = boost;
        if(y + ya > gf.getHeight())
        {
            gf.score += (1/gf.enemies.size());
            gf.enemies.remove(this);

        }

        gf.boostCount += 0.000005;
        y = y+ya;


    }
    
    public void draw(Graphics2D g2d)
    {
    	g2d.fillRect(x, y, 40, 20);
    }
    public Rectangle returnRectangle()
    {
    	return(new Rectangle(x,y,40,20));
    }
    
    public boolean collision()
    {
        if(this.returnRectangle().intersects(gf.player.returnRectangle()))
        {
        	//gf.GameOver();
        }
    	return false;
    }
    
}
