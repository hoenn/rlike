package screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import rlike.Creature;
import rlike.Line;
import rlike.Point;

public class TargettingBasedScreen implements Screen
{

	protected Creature player;
	protected String caption;
	private int sx;
	private int sy;
	private int x;
	private int y;

	public TargettingBasedScreen(Creature player, String caption, int sx, int sy)
	{
		this.player = player;
		this.caption = caption;
		this.sx = sx;
		this.sy = sy;
	}
	public void displayOutput(AsciiPanel terminal) {
	    for (Point p : new Line(sx, sy, sx + x, sy + y)){
	        if (p.x < 0 || p.x >= 80 || p.y < 0 || p.y >= 24)
	            continue;
	        
	        terminal.write('*', p.x, p.y, AsciiPanel.cyan);
	    }
	    
	    terminal.clear(' ', 0, 23, 80, 1);
	    terminal.write(caption, 0, 23);
	}
	public Screen respondToUserInput(KeyEvent key) {
        int px = x;
        int py = y;

        switch (key.getKeyCode()){
        case KeyEvent.VK_LEFT: x--; break;
        case KeyEvent.VK_RIGHT: x++; break;
        case KeyEvent.VK_UP:y--; break;
        case KeyEvent.VK_DOWN: y++; break;

        case KeyEvent.VK_ENTER: selectWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y); return null;
        case KeyEvent.VK_ESCAPE: return null;
        }
    
        if (!isAcceptable(player.x + x, player.y + y)){
            x = px;
            y = py;
        }
    
        enterWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y);
    
        return this;
    }
	public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
    }
	public boolean isAcceptable(int x, int y) {
        return true;
    }
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY){
    }
}