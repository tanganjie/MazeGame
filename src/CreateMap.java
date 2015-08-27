
/*
 * 生成地图类
 */

import java.util.*;

public class CreateMap {
	/*
	 * 创建地图构造方法，使用遍历方法
	 * mark作为难度控制参数
	 */
	public CreateMap(Canvas canvas){
		int[][] pointArray = new int[canvas.getRows()][canvas.getCols()];//图的节点记录
		//下面先将地图canvas中全初始化为墙
		for(int i = 0; i < canvas.getRows(); i++){
			for(int j = 0; j < canvas.getCols(); j++){
				canvas.getBox(i, j).setState(Enums.color.STOCK);
			}
		}
		//下面将pointArray中所有点设为未访问状态
		for(int i = 0; i < pointArray.length; i++){
			for(int j = 0; j < pointArray[i].length; j++){
				pointArray[i][j] = 0;
			}
		}
		//将行和列都为奇数的设为必可达点
		for(int i = 0; i < canvas.getRows(); i++){
			for(int j = 0; j < canvas.getCols(); j++){
				if(i % 2 != 0 && j % 2 != 0){
					canvas.getBox(i, j).setState(Enums.color.BACK);
				}
			}
		}
		
		Dfs.dfs(canvas, pointArray, new MyPoint(canvas.getRows() - 2, canvas.getCols() - 2));
		
		
		
		/*
		 * 终点end,开始点nowBox
		 */
		for (int i = 0; i < canvas.getRows(); i++) {
			canvas.getBox(i, 0).setState(Enums.color.STOCK);
			canvas.getBox(i, canvas.getCols() - 1).setState(Enums.color.STOCK);
		}
		for (int i = 0; i < canvas.getCols(); i++) {
			canvas.getBox(0, i).setState(Enums.color.STOCK);
			canvas.getBox(canvas.getRows() - 1, i).setState(Enums.color.STOCK);
		}
		canvas.getBox(canvas.getRows() - 2, canvas.getCols() - 2).setState(
				Enums.color.BACK);
		canvas.getBox(canvas.getRows() - 2, canvas.getCols() - 1).setState(
				Enums.color.END);
		StartGame.setNowBox(canvas.getBox(1, 1));
		
		
	}
}

/*
 * 以下类用于提供方法
 * 其没有带参数的构造方法
 */
class Dfs{
	static LinkedList<MyPoint> pointStack = new LinkedList<MyPoint>();
	static int xTemp, yTemp;
	static MyPoint toPreventDeleteTooMuch = new MyPoint();
	
	/*
	 * 深度优先遍历
	 */
	public static void dfs(Canvas canvas, int[][] pointArray, MyPoint now){
		xTemp = now.getX();
		yTemp = now.getY();
		
		pointArray[xTemp][yTemp] = 1;
		pointStack.addLast(new MyPoint(xTemp, yTemp));
		now = findNextPoint(canvas, pointArray, now);//找到下一个点
		if(!(now.getX() == 0 && now.getY() == 0)){
			if(!(((xTemp - now.getX()) * (xTemp - now.getX()) + (yTemp - now.getY()) * (yTemp - now.getY())) == 4)){
				xTemp = toPreventDeleteTooMuch.getX();
				yTemp = toPreventDeleteTooMuch.getY();
			}
			canvas.getBox((xTemp + now.getX()) / 2, (yTemp + now.getY()) / 2).setState(Enums.color.BACK);
			dfs(canvas, pointArray, now);//递归
		}
		else
			return;
	}

	
	/*
	 * 寻找下一点的方法
	 */
	private static MyPoint findNextPoint(Canvas canvas, int[][] pointArray, MyPoint point){
		Random rand = new Random();
		MyPoint pointTemp = new MyPoint();
		int mark = 0;	//标记，为0则表示成功找到
		
		while(!pointStack.isEmpty() && mark == 0){
			/*
			 * 判断，若是前后左右都不行则退出栈重新开始找
			 */
			if((point.getX() - 2) > 0 && pointArray[point.getX() - 2][point.getY()] == 0
				|| ((point.getX() + 2) < canvas.getRows() && pointArray[point.getX() + 2][point.getY()] == 0)
				|| ((point.getY() - 2) > 0) && pointArray[point.getX()][point.getY() - 2] == 0
				|| ((point.getY() + 2) < canvas.getCols() && pointArray[point.getX()][point.getY() + 2] == 0)
				){
				//用随机种子产生0 - 3当中随机的数，分别是上下左右探测
				switch(rand.nextInt(4)){
				case 0:
					if((point.getX() - 2) > 0 && pointArray[point.getX() - 2][point.getY()] == 0){
						pointTemp = new MyPoint(point.getX() - 2, point.getY());
						mark = 1;
						break;
					}
				case 1:
					if((point.getX() + 2) < canvas.getRows() && pointArray[point.getX() + 2][point.getY()] == 0){
						pointTemp = new MyPoint(point.getX() + 2, point.getY());
						mark = 1;
						break;
					}
				case 2:
					if((point.getY() - 2) > 0 && pointArray[point.getX()][point.getY() - 2] == 0){
						pointTemp = new MyPoint(point.getX(), point.getY() - 2);
						mark = 1;
						break;
					}
				case 3:
					if((point.getY() + 2) < canvas.getCols() && pointArray[point.getX()][point.getY() + 2] == 0){
						pointTemp = new MyPoint(point.getX(), point.getY() + 2);
						mark = 1;
						break;
					}
				}
			}
			else{
				point = pointStack.poll();
				toPreventDeleteTooMuch = point;
			}
		}
		return pointTemp;
		/*
		 * 这个返回值需引用的话，必须先判断此点是否为(0, 0)
		 * 如果是，说明遍历结束
		 */
	}
}

class MyPoint{
	private int x, y;

	public MyPoint() {
		this(0, 0);
	}

	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
