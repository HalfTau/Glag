package me.bryan;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Bullet extends Entity
{
	Graphics2D g2d;
	GameFrame gf;
	private int x;
	private int y;
	private int xa;
	private int ya;
	private int speed = 30;
	
    public Bullet(GameFrame gf)
    {
    	this.gf = gf;
    	x = gf.player.getX() + gf.player.getWidth()/2;
    	y = gf.player.getY();
    	g2d = gf.g2d;
    }
    public void move()
    {
    	if(y - speed < 0)
    	{
    		gf.bullets.remove(this);
    	}
    	y += -speed;
    }
    public void draw(Graphics2D g2d)
    {
    	g2d.fillRect(x, y, 10, 10);
    }
    public Rectangle returnRectangle()
    {
    	return(new Rectangle(x,y,50,50));
    }
    boolean collision(Bullet bullet)
    {

         for(Enemy enemy:gf.enemies)
         {
        	 if(bullet.returnRectangle().intersects(enemy.returnRectangle()))
        	 {
        		 gf.enemies.remove(enemy);
        		 gf.bullets.remove(bullet);
        	 }
         }
        
 	return false;
    }
}
