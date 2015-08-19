package me.bryan;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.AbstractList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends JFrame
{
	public float score = 0;
	int		fps = 120;
	long		time_before;
	long		time_after;
	long		time_end;
	long		time_difference = 0;
	int		ms_wait = 1000/fps;
	Menu menu;
	Graphics2D g2d;
    List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
    List<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
	Player player;
	boolean menuState = true;
	double boostCount = 0.0;
	int count = 1;
	public GameFrame()
	{
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);	
		setFocusable(true);
		this.addKeyListener(new KeyListener() {
	    
			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) 
			{
				player.keyReleased(e);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				player.keyPressed(e);
			}
		});
	}
	
	public static void main( String args[])
	{
		GameFrame gf = new GameFrame();
		gf.player = new Player(gf);
		System.out.println(gf.getWidth()/2);
		if(gf.menuState)
		{
			System.out.println(gf.menuState);
			gf.menu = new Menu(gf);
			gf.add(gf.menu);
			gf.revalidate();
		}
		gf.createBufferStrategy(2);
		while(true)
		{
			gf.time_before = System.currentTimeMillis();
			gf.update();
	        gf.bufferInit();	
			gf.time_after  = System.currentTimeMillis();
			gf.ms_wait = (1000/gf.fps) - (int)(gf.time_after - gf.time_before);
			if(gf.ms_wait < 0)
			{
				gf.ms_wait = 5;
			}
	        try 
	        {
     		    Thread.sleep(gf.ms_wait);
			} 
	        catch (InterruptedException e) 
	        {
			    // TODO Auto-generated catch block
				e.printStackTrace();
	        }

		}
		
	} 
	public void update()
	{
    if(!menuState)
    {
		        if(!(bullets.isEmpty()))
		        {
		        	System.out.println("checking!");
		        	for(Bullet bullet:bullets)
		        	{
		        		bullet.collision(bullet);
		        	}
		        }
		        player.collision();
				player.move();
				for(Bullet bullet:bullets)
				{
				    bullet.move();
				}
			    if(enemies.size() < 10 || enemies.isEmpty())
			    {
			    	for(int i = 0; i <50; i++)
			    	{
			    		enemies.add(new Enemy(this, i));
			    	}
			    }
			    for(Enemy enemy:enemies)
			    {
			    	enemy.move();
			    }
        }
	}
	
	public void bufferInit()
	{
		BufferStrategy bf = this.getBufferStrategy();
		g2d = null;
		do
		{
			try
			{
				g2d = (Graphics2D) bf.getDrawGraphics();
				
				draw(g2d);
			}
			finally
			{
		        g2d.dispose();
			}
			bf.show();
		} while(bf.contentsLost());
	}
	
	public void draw(Graphics2D g2d)
	{
		super.paint(g2d);
		setTitle("SCORE : " + score);
		if(!menuState)
		{
			player.draw(g2d);
			for(Bullet bullet:bullets)
			{
				bullet.draw(g2d);
			}
			for(Enemy enemy:enemies)
			{
				enemy.draw(g2d);
			}
		}
		else
		{
			menu.drawMenu(g2d);
		}
	}

	public void gameOver()
	{
		enemies.clear();
		bullets.clear();
        JOptionPane.showMessageDialog(this, "YOU DIED BITCH\n\nYOU ONLY GET ONE, SON");
        System.exit(0);
	}
}



