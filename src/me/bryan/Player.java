package me.bryan;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;



import javax.swing.ImageIcon;


public class Player extends Entity
{
	GameFrame gf;
	ImageIcon playerI = new ImageIcon(getClass().getResource("/images/Redgalaga.png"));
	private int x;
	private int y;
	private int xa;
	private int ya;
	private int speed = 3;
	private int width = 30;
	private int height = 20;
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getXa()
	{
		return xa;
	}

	public void setXa(int xa)
	{
		this.xa = xa;
	}

	public int getYa()
	{
		return ya;
	}

	public void setYa(int ya)
	{
		this.ya = ya;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public Player(GameFrame gf)
	{
		this.gf = gf;
		this.x = gf.getWidth()/2;
		this.y = gf.getHeight() - 40;
	}

    public void move()
    {
        if(x + xa > 0 && x + xa < gf.getWidth() - 30)
        {
        	x += xa;
        }
        if(y + ya > (gf.getHeight()/2) && y + ya < (gf.getHeight()) )
        {
        	y += ya;
        }
    }
    public void draw(Graphics2D g2d)
    {
    	g2d.drawImage(playerI.getImage(), x, y, gf);
    }

	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			gf.bullets.add(new Bullet(gf));
		}
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			xa = -speed - (int)Math.floor(gf.boostCount);
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			xa = (int) (speed+ Math.floor(gf.boostCount));;
		}
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			ya = (int) (-speed -Math.floor(gf.boostCount));;
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			ya = (int) (speed+ Math.floor(gf.boostCount));;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
		{
			xa = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_W|| e.getKeyCode() == KeyEvent.VK_S)
		{
			ya = 0;
		}
	}
	public Rectangle returnRectangle()
	{
		return(new Rectangle(x,y,playerI.getIconWidth(),playerI.getIconHeight()));
	}
	void collision()
	{
		for(Enemy enemy:gf.enemies)
		{
			if(this.returnRectangle().intersects(enemy.returnRectangle()))
			{
				gf.gameOver();
			}
		}
	}
}
