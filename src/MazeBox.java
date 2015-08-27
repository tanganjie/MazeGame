
import java.awt.*;
/*
 * 方格类，这是组成迷宫各块的基本元素，用自己的颜色表示块的外观
 */
public class MazeBox implements Cloneable{
	/*
	 * state表示当前方格的表现状态
	 * sysState表示当前方格的系统状态
	 */
	private Enums.color state;
	private Enums.color sysState;
	
	private Dimension size = new Dimension();
	
	
	/*
	 * boxX,boxY分别表示此方格所在的行和列
	 */
	private int boxX = 0, boxY = 0;

	public void setboxX(int x) {
		boxX = x;
	}

	public void setboxY(int y) {
		boxY = y;
	}

	public int getboxX() {
		return boxX;
	}

	public int getboxY() {
		return boxY;
	}

	/*
	 * 方格类的构造函数
	 * 当前方格状态
	 * 以用分别用不同的状态颜色着色
	 */
	public MazeBox(Enums.color state) {
		this.state = state;
	}

	/*
	 * 返回此方格当前的状态(系统)
	 */
	public Enums.color getsysState() {
		return sysState;
	}

	/*
	 * 设置此方格状态(系统)
	 */
	public void setsysState(Enums.color sysState) {
		this.sysState = sysState;
	}

	/*
	 * 设置方格的颜色， 通过改变方格的状态来实现
	 */
	public void setState(Enums.color state) {
		this.state = state;
	}

	/*
	 * 取得当前方格状态
	 */
	public Enums.color getState() {
		return state;
	}

	/*
	 * 得到此方格的尺寸
	 */
	public Dimension getSize() {
		return size;
	}

	/*
	 * 设置方格的尺寸
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}
}
