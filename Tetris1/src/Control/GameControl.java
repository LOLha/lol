package Control;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ui.JPanelGame;
import Service.GameService;

public class GameControl {
   /**
    * 游戏面板
    */
	private JPanelGame jpanel;
	/**
	 * 游戏业务逻辑
	 */
	private GameService gameService;
	/**
	 *绘制线程 
	 */
	/**
	* 游戏控置的关联hashMap	
	*/
	Map<Integer,String> gameControlMap ;
	public GameControl(JPanelGame jpanel,GameService gameService){
		this.jpanel = jpanel;
	    this.gameService = gameService;
	    gameControlMap = new HashMap<Integer,String>();
	    gameControlMap.put(87, "keyUp");
	    gameControlMap.put(83, "keyDown");
	    gameControlMap.put(65, "keyLeft");
	    gameControlMap.put(68, "keyRight");
	    gameControlMap.put(80, "gamePause");
	}
	
	
	public void actionByKeyCode(int keyCode) {
		System.out.println(keyCode);
		if(!gameControlMap.containsKey(keyCode)) return;
		String methodName = gameControlMap.get(keyCode);
		Class game = gameService.getClass();
		try {
			Method method = game.getMethod(methodName);
			method.invoke(gameService);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}


	public void mousePressed(MouseEvent e) {
	     int x = e.getX();
	     int y = e.getY();
	     if(x>523&&y>294&&x<582&&y<320){
	    	paintThread p = new paintThread();
	    	
	    	ExecutorService executor = Executors.newFixedThreadPool(2);
	    	
	    	executor.execute(p);
	    	gameService.sendStartMsg();
	     }
		
		
	}
    private class paintThread extends Thread{
    	public void run(){
    		jpanel.repaint();
    		while(true){
    			try{
    			Thread.sleep(200);
    		    if(!jpanel.getDto().isStart()) continue;
    		   
    			gameService.keyDown();
    			
    			
    		    jpanel.repaint();
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	
    }
   
}
