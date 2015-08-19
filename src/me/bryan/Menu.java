package me.bryan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Menu extends JPanel implements ActionListener
{
	GameFrame gf;
	JButton buttonPlay;
	JButton buttonAbout;
	String Title = "JUMP TO LIGHTSPEED";
	boolean aboutState = false;
    public Menu(GameFrame gf)
    {
    	System.out.println("hit the menu constructor");
    	this.setLayout(new BorderLayout());
    	this.gf = gf;
    	JPanel pnlSouth = new JPanel();
    	pnlSouth.setLayout(new BorderLayout());
        buttonPlay = new JButton("Play");
    	buttonPlay.addActionListener(this);
    	buttonAbout = new JButton("What is this?!");
    	buttonAbout.addActionListener(this);
    	pnlSouth.add(buttonPlay, BorderLayout.WEST);
    	pnlSouth.add(buttonAbout, BorderLayout.EAST);
    	pnlSouth.setBackground(Color.black);
    	this.add(pnlSouth, BorderLayout.SOUTH);

    }
    public void drawMenu(Graphics2D g2d)
    {
    	g2d.drawString(Title, (gf.getWidth()/2) - (Title.length() + (Title.length() * 2)) , 60);
    	
    	if(aboutState)
    	{
    		g2d.drawString("some shit", 200, 200);
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
	    if(e.getSource() == buttonPlay)
	    {
	    	JOptionPane.showMessageDialog(this, "playtime");
	    	gf.menuState = false;
	    	gf.remove(this);
	    	gf.menu = null;
	    }
	    if(e.getSource() == buttonAbout)
	    {
            aboutState = true;
            
	    }
	}
	
}
