package Suduko;

public class Algorithms {
	  int rowValid[][] = new int[9][10] ;//rowValid[i][j]表示第i行数字j是否已经使用
	  int columnValid[][] = new int[9][10];//columnValid[i][j]表示第i列数字j是否已经使用
	  int subBoardValid[][] = new int[9][10];
     public void solveSudoku(char[][] board) {
        for(int i=0;i<9;i++){
        	for(int j=0;j<9;j++){
        		if(board[i][j]!='.'){
        			fill(i,j,board[i][j]-'0');
        		}
        	} 
        }
	     solver(board,0);
    }
	public void fill(int row, int col, int val) {
		rowValid[row][val] = 1;
	    columnValid[col][val] = 1;
	    subBoardValid[row/3*3+col/3][val] = 1;
	}
	public  void clear(int row,int col,int val){
		rowValid[row][val] = 0;
	    columnValid[col][val] = 0;
	    subBoardValid[row/3*3+col/3][val] = 0;
		
	}
	private boolean solver(char[][] board, int index) {
		if(index>80) return true;
		int row = index/9;
		int col = index - 9*row;
		if(board[row][col]!='.'){return solver(board,index+1);}
		for(char i='1';i<='9';i++){
			if(isValide(row,col,i-'0')){
				board[row][col] = i;
				fill(row,col,i-'0');
				if(solver(board,index+1))return true;
				clear(row,col,i-'0');
			}
		}
		board[row][col] = '.';
		return false;
	}
	public boolean isValide(int row,int col,int val){
		 if(rowValid[row][val] == 0 &&
			       columnValid[col][val] == 0 &&
			       subBoardValid[row/3*3+col/3][val] == 0)
			       return true;
			    return false;
	}
}
