package dto;

import java.io.Serializable;

import entity.Cube;



public class GameDto implements Serializable{
	/**
	 * 玩家1的游戏地图
	 */
	 private boolean[][] gameMap = new boolean[10][18];
	 /**
	  * 玩家2的游戏地图
	  */
	 private boolean[][] gameMap1 = new boolean[10][18];
		/**
		 * 玩家1的下落的方块
		 */
		private Cube cube;
		/**
		 * 玩家2的下落方块
		 */
		private Cube cube1 = null;
		/**
		 * 下一个方块
		 */
		private int next;
		/**
		 * 目前等级
		 */
		private int level;
		/**
		 * 目前分数
		 */
		private int nowSocre;
		/**
		 * 销毁的行数
		 */
		private int removeLine;
		/**
		 * 客户端ID
		 */
		private int id;
        /**
         * 玩家是否在线
         */
		private boolean playerOnline = false;
		/**
		 * 游戏是否开始
		 */
		private boolean start = false;
		
		private boolean gameStart = false;
		
		
		public boolean isGameStart() {
			return gameStart;
		}
		public void setGameStart(boolean gameStart) {
			this.gameStart = gameStart;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public boolean[][] getGameMap() {
			return gameMap;
		}
		public void setGameMap(boolean[][] gameMap) {
			this.gameMap = gameMap;
		}
		public boolean[][] getGameMap1() {
			return gameMap1;
		}
		public void setGameMap1(boolean[][] gameMap1) {
			this.gameMap1 = gameMap1;
		}
		public Cube getCube() {
			return cube;
		}
		public void setCube(Cube cube) {
			this.cube = cube;
		}
		public Cube getCube1() {
			return cube1;
		}
		public void setCube1(Cube cube1) {
			this.cube1 = cube1;
		}
		public int getNext() {
			return next;
		}
		public void setNext(int next) {
			this.next = next;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public int getNowSocre() {
			return nowSocre;
		}
		public void setNowSocre(int nowSocre) {
			this.nowSocre = nowSocre;
		}
		public int getRemoveLine() {
			return removeLine;
		}
		public void setRemoveLine(int removeLine) {
			this.removeLine = removeLine;
		}
		public boolean isPlayerOnline() {
			return playerOnline;
		}
		public void setPlayerOnline(boolean playerOnline) {
			this.playerOnline = playerOnline;
		}
		public boolean isStart() {
			return start;
		}
		public void setStart(boolean start) {
			this.start = start;
		}
		
		
}
