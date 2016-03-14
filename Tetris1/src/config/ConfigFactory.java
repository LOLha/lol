package config;

public class ConfigFactory {
	private static GameConfig gameConfig = null;
	static{
		try {
			gameConfig = new GameConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static GameConfig getGameConfig(){
		return gameConfig;
	}

}
