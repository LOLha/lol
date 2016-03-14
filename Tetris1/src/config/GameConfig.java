package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏的配置文件
 * @author 胡必成
 *
 */
public class GameConfig {
    /**
     * 游戏配置文件 
     */
	private String cfg = "config/cfg.xml";
	/**
	 * 游戏框架长
	 */
	private int GAME_WIDTH;
	/**
	 * 游戏框架宽
	 */
	private int GAME_HEIGHT;
	/**
	 * 游戏主题配置集合
	 */
	private List<LayerConfig>  layerConfig;
	/**
	 * 内边距
	 */
	private int PADDING;
 	/**
 	 * 边框尺寸
 	 */
	private int SIZE;
	/**
	 * 方块长
	 */
	private int ROW_SIZE;
	
	private SystemConfig systemConfig;
	
	
	public GameConfig(){
		SAXReader read = new SAXReader();
		try {
			Document document = read.read(cfg);
			/**
			 * 获得根结点
			 */
			Element game =  document.getRootElement();
			/**
			 * frame结点
			 */
			Element frame = game.element("frame");
			/**
			 * 初始化界面属性
			 */
			initFrame( frame);
			/**
			 * System结点 
			 */
			Element system = game.element("system");
			/**
			 * 初使化系统属性
			 */
			initSystem(system);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	private void initFrame(Element frame){
		this.GAME_WIDTH = Integer.parseInt(frame.attributeValue("w"));
		this.GAME_HEIGHT = Integer.parseInt(frame.attributeValue("h"));
		this.ROW_SIZE = Integer.parseInt(frame.attributeValue("row"));
		this.PADDING = Integer.parseInt(frame.attributeValue("padding"));
		this.SIZE = Integer.parseInt(frame.attributeValue("size"));
		List<Element> layers = frame.elements("layer");
		layerConfig = new ArrayList<LayerConfig>(layers.size());
		for(Element e:layers){
			LayerConfig config = new LayerConfig(
					e.attributeValue("class"),
					Integer.parseInt(e.attributeValue("x")),
					Integer.parseInt(e.attributeValue("y")),
					Integer.parseInt(e.attributeValue("w")),
					Integer.parseInt(e.attributeValue("h"))
					);
			
			layerConfig.add(config);
		}
	}
	private void initSystem(Element system){
	     this.systemConfig = new SystemConfig(system);
	}
	public int getGAME_WIDTH() {
		return GAME_WIDTH;
	}
	public int getGAME_HEIGHT() {
		return GAME_HEIGHT;
	}
	public List<LayerConfig> getLayerConfig() {
		return layerConfig;
	}
	public void setLayerConfig(List<LayerConfig> layerConfig) {
		this.layerConfig = layerConfig;
	}
	public int getPADDING() {
		return PADDING;
	}
	public void setPADDING(int pADDING) {
		PADDING = pADDING;
	}
	public int getSIZE() {
		return SIZE;
	}
	public void setSIZE(int sIZE) {
		SIZE = sIZE;
	}
	public int getROW_SIZE() {
		return ROW_SIZE;
	}
	public void setROW_SIZE(int rOW_SIZE) {
		ROW_SIZE = rOW_SIZE;
	}
	public SystemConfig getSystemConfig() {
		return systemConfig;
	}
	
	
}
