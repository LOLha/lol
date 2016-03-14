package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import Control.PlayerControl;
import Server.GameServer;
import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import dto.GameDto;

public class JPanelGame extends JPanel{
	/**
	 * 游戏配置
	 */
	private static GameConfig gameConfig = ConfigFactory.getGameConfig();
	/**
	 * 框架集合
	 */
	private List<Layer> layers ;
	/**
	 * 配置文件集合
	 */
	private List<LayerConfig> layerConfig;
	/**
	 * 套接字
	 */
	private Socket socket;
	/**
	 * 玩家的id
	 */
	private int id;
	/**
	 * 客户端udpPort
	 */
	public static int udpPort = new Random().nextInt(2000);
	/**
	 * 数剧源
	 */
	private GameDto dto;
	/**
	 * 客户端
	 */
	private NetClient nc;
	/**
	 * 
	 * @param dto
	 */
	private Image image;
	
	public JPanelGame(GameDto dto){
		this.dto = dto;
		initLayer(dto);
		initNet();
	}

	private void initNet() {
		try{
			socket = new Socket("127.0.0.1",GameServer.TCP_PORT);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
		    this.id = dis.readInt();
		    this.dto.setId(id);
		    nc = new NetClient(this.dto,udpPort);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(socket!=null){
				try{
					socket.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
    
	
	
	//Spring IOC核心通过反射来生成对象
	private void initLayer(GameDto dto) {
		//获得界面配置
	    layerConfig = gameConfig.getLayerConfig();
	    //界面集合
	    layers = new ArrayList<Layer>(layerConfig.size());
	    try{
	    	for(LayerConfig layer:layerConfig){
	    	Class c = Class.forName(layer.getClassName());
	        Constructor c1 = c.getConstructor(int.class,int.class,int.class,int.class);
	    	Layer l = (Layer)c1.newInstance(
	    			layer.getX(),
	    			layer.getY(),
	    			layer.getW(),
	    			layer.getH()
	    			);	
	    	 l.setDto(dto);
	    	 layers.add(l);
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	public void setControl(PlayerControl playerControl){
		this.addKeyListener((playerControl));
		this.addMouseListener(playerControl);
	}
	public void paint(Graphics g){
		for(int i=0;i<layers.size();layers.get(i++).paint(g));
		this.requestFocus();
	}
	@Override
	public void update(Graphics g){
		if(image == null){
			image = this.createImage(gameConfig.getGAME_WIDTH(), gameConfig.getGAME_HEIGHT());
		}
		Graphics g0 = image.getGraphics();
		g0.fillRect(0, 0, gameConfig.getGAME_WIDTH(), gameConfig.getGAME_HEIGHT());
		super.paint(g0);
		g.drawImage(image, 0, 0,gameConfig.getGAME_WIDTH(),gameConfig.getGAME_HEIGHT(),null);
		
	}
	
	
 public NetClient getNc(){
	 return nc;
 }

public GameDto getDto() {
	return dto;
}
 
}
