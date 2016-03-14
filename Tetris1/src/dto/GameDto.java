package dto;

import java.io.Serializable;

import entity.Cube;



public class GameDto implements Serializable{
	/**
	 * ���1����Ϸ��ͼ
	 */
	 private boolean[][] gameMap = new boolean[10][18];
	 /**
	  * ���2����Ϸ��ͼ
	  */
	 private boolean[][] gameMap1 = new boolean[10][18];
		/**
		 * ���1������ķ���
		 */
		private Cube cube;
		/**
		 * ���2�����䷽��
		 */
		private Cube cube1 = null;
		/**
		 * ��һ������
		 */
		private int next;
		/**
		 * Ŀǰ�ȼ�
		 */
		private int level;
		/**
		 * Ŀǰ����
		 */
		private int nowSocre;
		/**
		 * ���ٵ�����
		 */
		private int removeLine;
		/**
		 * �ͻ���ID
		 */
		private int id;
        /**
         * ����Ƿ�����
         */
		private boolean playerOnline = false;
		/**
		 * ��Ϸ�Ƿ�ʼ
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
