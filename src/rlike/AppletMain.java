package rlike;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import screens.Screen;
import screens.StartScreen;

public class AppletMain extends Applet {
    private static final long serialVersionUID = 2560255315130084198L;

    private AsciiPanel terminal;
    private static final String TITLE = "KarinaQuest";
    private Screen screen;


    public AppletMain(){
        super();
        terminal = new AsciiPanel();
        add(terminal);
        screen = new StartScreen();
        addKeyListener(new KeyListener());
        
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        repaint();
    }

    public void repaint() {
    	terminal.clear();
    	screen.displayOutput(terminal);
    	super.repaint();
    }
    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
    class KeyListener extends KeyAdapter {
    	public KeyListener() {
    		
    	}
    	@Override
    	public void keyPressed(KeyEvent e) {
    		screen = screen.respondToUserInput(e);
    		repaint();
    	}
    	@Override
    	public void keyReleased(KeyEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	@Override
    	public void keyTyped(KeyEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    }
	
}