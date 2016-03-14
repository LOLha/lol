package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame{
	private GameConfig gameConfig = ConfigFactory.getGameConfig();
	
    public JFrameGame(JPanel jpanel){
    	//面板上加鼠标监听
    	jpanel.addMouseMotionListener(new MouseAdapter(){
    		public void mouseMoved(MouseEvent e){
    			JFrameGame.this.setTitle("hbc"+e.getX()+":"+e.getY());
    		}
    	});
    	this.add(jpanel);
    	this.setSize(gameConfig.getGAME_WIDTH(), gameConfig.getGAME_HEIGHT());
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    }
	
	
}
