
/*
 * 用于暂存必可达点坐标
 */

public class XandY {
	private int x, y;

	public XandY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public XandY() {
		this(1, 1);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}