package Suduko;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Sudoku extends JPanel{
	
	public static final int BLOCK_SIZE = 30;
	private static char[][] board = new char[9][9];
	private static boolean[][] board1 = new boolean[9][9];
	public static final int ROWS = 9;
	public static final int COLS = 9;
	
	public Sudoku(){
		initPanel();
	    	
	}
	private void initPanel() {
	    initBoard(board);
	    this.addMouseListener(new mouseAction());
	    this.addMouseMotionListener(new mouseAction());
	    
	   
		
	}
	public void paint(Graphics g){
	     g.setColor(Color.WHITE);
	     g.fillRect(0, 0,300, 300);
	     g.setColor(Color.BLACK);
	     for(int i=0;i<10;i++){
	    	 if(i%3==0) g.setColor(Color.yellow);
	    	g.drawLine(5, 5+i*BLOCK_SIZE, 275,5+i*BLOCK_SIZE);
	    	g.setColor(Color.black);
	     }
	     for(int i=0;i<10;i++){
	    	 if(i%3==0)g.setColor(Color.yellow);
	    	 g.drawLine(5+i*BLOCK_SIZE, 5, 5+i*BLOCK_SIZE, 275);
	    	 g.setColor(Color.black);
	     }
	     g.setColor(Color.white);
	     for(int i=0;i<board1.length;i++){
	    	 for(int j=0;j<board1[i].length;j++){
	    		 if(board1[i][j]){
	    			 g.setColor(Color.gray);
	    			 g.fillRect(5+i*BLOCK_SIZE, 5+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
	    		 }	 
	    	 }
	     }
	     for(int i=0;i<board.length;i++){
	    	 for(int j=0;j<board[i].length;j++){
	    		if(board[i][j] !='.'){
	    			g.setColor(Color.red);
	    			g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
	    			System.out.println(i+"."+j);
	    			g.drawString(board[i][j]-'0'+"", 13+i*BLOCK_SIZE, 30+j*BLOCK_SIZE);
	    		}
	    		
	    		 
	    		 
	    	 }
	     }
		
		
	}
	

	public static void main(String[] args){
		 final JFrame jframe = new JFrame("数独");
		final Sudoku game = new Sudoku();
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("菜单");
		JMenuItem jmi = new JMenuItem("开始");
		JMenuItem jmi1 = new JMenuItem("重来");
		jmi1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initBoard(board);
				game.repaint();
			}
		});
		
		jmi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                   Algorithms s = new Algorithms();
                   long a = System.currentTimeMillis();
                   s.solveSudoku(board);
                   long b = System.currentTimeMillis();
                   game.repaint();
                   JOptionPane.showMessageDialog(jframe, "共用时"+(b-a)+"毫秒");
			}
			
		});
		jm.add(jmi);
		jm.add(jmi1);
		jmb.add(jm);
	   
		
		jframe.add(game);
		jframe.setSize(300, 350);
		jframe.setJMenuBar(jmb);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.addKeyListener(new keyAction(board,board1,game));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e) {
			  jframe.setTitle("数独"+e.getX()+":"+e.getY());
			}
			
		});
		jframe.setVisible(true);
		
	}
	
	private class mouseAction extends  MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			clear(board1);
			int x = e.getX();
			int y = e.getY();
			int sudoX = 0;
			int sudoY = 0;
			for(int i=0;i<ROWS;i++){
				if(5+BLOCK_SIZE*i<x&&x<(i+1)*BLOCK_SIZE+5){
					sudoX = i;
				}
			}
			for(int i=0;i<COLS;i++){
				if(5+BLOCK_SIZE*i<y&&y<(i+1)*BLOCK_SIZE+5){
					sudoY = i;
				}
			}
			board1[sudoX][sudoY] = true;
			Sudoku.this.repaint();
	       System.out.println(sudoX+":"+sudoY);		
		}
		
	
		
	}
	
	private static class keyAction extends KeyAdapter{
       private char[][] board;
		private boolean[][] board1;
		private Sudoku game;
	   public keyAction(char[][] board,boolean[][] board1,Sudoku game) {
               this.board = board;
               this.board1 = board1;
               this.game = game;
	   }

		@Override
		public void keyPressed(KeyEvent e) {
		    for(int i=0;i<board1.length;i++){
		    	for(int j=0;j<board1[i].length;j++){
		    		if(board1[i][j]){
		    			if(e.getKeyCode()>=49&&e.getKeyCode()<=57){
		    			board[i][j] = e.getKeyChar();
		    			}
		    			if(e.getKeyCode()==48){
		    				board[i][j] = '.';
		    			}
		    		}
		    	}
		    }
			
			game.repaint();
		}
		
		
		
		
	}
	
	
	private void clear(boolean[][] board1){
		for(int i=0;i<board1.length;i++){
			for(int j=0;j<board1[i].length;j++){
				board1[i][j] = false;
			}
		}
		
	}
     
	private static  void initBoard(char[][] board){
		for(int i=0;i<board.length;i++){
			for(int j =0;j<board[i].length;j++){
				board[i][j] = '.';
			}
		}
	}
	
	
	
}
