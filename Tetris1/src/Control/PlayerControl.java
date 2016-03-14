package Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerControl extends KeyAdapter implements MouseListener{
   
	/**
	 * ”Œœ∑øÿ÷∆
	 */
	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl){
		this.gameControl = gameControl;
	}
	
	
	public void keyPressed(KeyEvent e){
		
	   gameControl.actionByKeyCode(e.getKeyCode());	
	   System.out.println(e.getKeyCode());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
          gameControl.mousePressed(e);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
}
