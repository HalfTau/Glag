package me.bryan;
import java.awt.Graphics2D;


public abstract class Entity 
{

	
	
    public void draw(Graphics2D g2d, int x, int y, int w, int h)
    {
    	g2d.drawRect(x, y, w, h);
    }
    public void move(int x, int y, int xa, int ya)
    {
    	x = x + xa;
    	y = y + ya;
    }
}
