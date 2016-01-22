import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import screens.Screen;
import screens.StartScreen;

public class ApplicationMain extends JFrame implements KeyListener{
    private static final long serialVersionUID = 1060623638149583738L;
    private Screen screen;

    private AsciiPanel terminal;

    public ApplicationMain(){
        super();
        terminal = new AsciiPanel();
        add(terminal);
        screen = new StartScreen();
        pack();
        addKeyListener(this);
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