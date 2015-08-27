
/*
 * 迷宫画布，继承JPanel类
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Canvas extends JPanel {
	
	/*
	 * 设置颜色
	 * 其中，wayColor用于在帮助时显示路径
	 */
	private Color backColor = Color.green;
	private Color stockColor = Color.gray;
	private Color wayColor = Color.ORANGE;	//显示提示路径
	private Color nowColor = Color.red;
	private Color endColor = Color.BLACK;
	
	// 当前颜色
	private Color stateColor;
	
	private int rows, cols;
	private MazeBox[][] boxes;
	private int boxWidth, boxHeight;
	
	/*
	 * 画布类的构造函数
	 * 画布的行数rows
	 * 画布的列数cols
	 */
	public Canvas(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.setBackground(backColor);
		
		boxes = new MazeBox[rows][cols];
		for(int i = 0; i < boxes.length; i++){
			for(int j = 0; j < boxes[i].length; j++){
				boxes[i][j] = new MazeBox(Enums.color.BACK);	// 初始为背景
				boxes[i][j].setsysState(Enums.color.STOCK);	//初始系统不可过
				boxes[i][j].setboxX(i);
				boxes[i][j].setboxY(j);
			}
		}
		
		setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(50, 137, 200)));
	}
	
	/*
	 * 画布类构造函数
	 */
	public Canvas(int rows, int cols, Color backColor){
		this(rows, cols);
		this.stateColor = backColor;
	}
	

	/*
	 * 设置游戏方块色彩
	 */
	public void setBlockColor(Enums.color state) {
		switch (state) {
		case BACK:
			stateColor = backColor;
			break;
		case STOCK:
			stateColor = stockColor;
			break;
		case NOW:
			stateColor = nowColor;
			break;
		case END:
			stateColor = endColor;
			break;
		case WAY :
			stateColor = wayColor;
		}
	}
	
	/*
	 * 更换着色
	 */
	public void setStockColor(Color newColor){
		this.stockColor = newColor;
	}
	public void setBackColor(Color newColor){
		this.backColor = newColor;
	}
	
	/*
	 * 获取游戏方块颜色
	 */
	public Color getBlockColor(){
		return stateColor;
	}
	
	public Color getBackColor(){
		return backColor;
	}
	
	/*
	 * 取得画布中方格的行数
	 */
	public int getRows() {
		return rows;
	}
	public void setRows(int rows){
		this.rows=rows;
	}
	
	/*
	 * 取得画布中方格的列数
	 */
	public int getCols() {
		return cols;
	}
	public void setCols(int cols){
		this.cols=cols;
	}
	
	/*
	 * 得到某一行某一列的方格引用。
	 */
	public MazeBox getBox(int row, int col) {
		if (row < 0 || row > boxes.length - 1 || col < 0
				|| col > boxes[0].length - 1)
			return null;
		return (boxes[row][col]);
	}
	
	/*
	 * 返回方格所在行
	 */
	public int getRow(MazeBox box) {
		return box.getboxX();
	}

	/*
	 * 返回方格所在列
	 */
	public int getCol(MazeBox box) {
		return box.getboxY();
	}
	
	/*
	 * 画组件
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(stateColor);
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				setBlockColor(getBox(i, j).getState());
				g.setColor(stateColor);
				g.fill3DRect(j * boxWidth, i * boxHeight, boxWidth, boxHeight, true);
			}
		}
	}
	
	/*
	 * 自动调整方格大小
	 */
	public void fanning() {
		boxWidth = this.getSize().width / cols;
		boxHeight = this.getSize().height / rows;
	}
}
