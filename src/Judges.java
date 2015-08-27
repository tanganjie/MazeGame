
import java.util.*;

/*
 * 各种判断类
 * 判断方格是否可以通过
 * 判断两个方块间是否有可行路径
 */
public class Judges {
	/*
	 * 判断当前的方格是否可以通过方法
	 */
	public static boolean isCrossable(MazeBox box){
		if (box.getState() == Enums.color.STOCK)
			return false;
		else
			return true;
	}
	
	/*
	 * 下面的方法用来判断两个方格是否可以有可行路径
	 */
	public static LinkedList<XandY> toConnectedXandY(MazeBox boxA, MazeBox boxB, Canvas canvas){
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() != Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.WAY); // 如果不为砖块，标记为空
				}
			}
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() == Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.STOCK); // 如果为砖块，标记为不可过
				}
			}
		int iStar = boxA.getboxX(), jStar = boxA.getboxY();
		int iEnd = boxB.getboxX(), jEnd = boxB.getboxY();
		int i = iStar, j = jStar;
		char path;
		int ans = 0;
		LinkedList<Character> stack = new LinkedList<Character>(); // 初始化一个栈，用来存放走过的路径
		LinkedList<XandY> stackXandY = new LinkedList<XandY>();
		canvas.getBox(i, j).setsysState(Enums.color.NOW);
		while (i >= iStar && i <= iEnd && j >= jStar && j <= jEnd) {// 开始遍历
			if (i == iEnd && j == jEnd) {// 到达终点，两点间有通路
				ans = 1;
				break;
			}
			if ((j + 1) <= jEnd
					&& canvas.getBox(i, j + 1).getsysState() == Enums.color.WAY) {// 向右探测一步
				canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
				stackXandY.offerLast(new XandY(i, j));
				j++;
				canvas.getBox(i, j).setsysState(Enums.color.NOW);
				stack.offerLast('R'); // 将路径的方位存储
			} else {
				if ((i + 1) <= iEnd
						&& canvas.getBox(i + 1, j).getsysState() == Enums.color.WAY) {// 向下探测一步
					canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
					stackXandY.offerLast(new XandY(i, j));
					i++;
					canvas.getBox(i, j).setsysState(Enums.color.NOW);
					stack.offerLast('D'); // 将路径的方位存储
				} else {
					if ((i - 1) >= iStar
							&& canvas.getBox(i - 1, j).getsysState() == Enums.color.WAY) {// 向上探测一步
						canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
						stackXandY.offerLast(new XandY(i, j));
						i--;
						canvas.getBox(i, j).setsysState(Enums.color.NOW);
						stack.offerLast('U'); // 将路径的方位存储
					} else {
						if ((j - 1) >= jStar
								&& canvas.getBox(i, j - 1).getsysState() == Enums.color.WAY) {// 向左探测一步
							canvas.getBox(i, j).setsysState(
									Enums.color.BEEN_HERE);
							stackXandY.offerLast(new XandY(i, j));
							j--;
							canvas.getBox(i, j).setsysState(Enums.color.NOW);
							stack.offerLast('L'); // 将路径的方位存储
						} else {// 遇到障碍往回走一步
							if (stack.isEmpty()) { // 判断是否回到起点了,是则退出遍历
								ans = 0;
								break;
							} else {
								if (stack.peekLast() != null) {// 判断能否取回上一步路径,取出不为空表示可以取出
									path = stack.pollLast();
									stackXandY.pollLast();
									switch (path) {
									case 'L':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										j++;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'U':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										i++;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'D':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										i--;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'R':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										j--;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									}
								}
							}
						}
					}
				}
			}

		}
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() != Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.WAY); // 如果不为砖块，标记为空
				}
			}
		if (ans == 1)
			return stackXandY;
		else
			return null;
	}
	
	
	public static boolean isConnected(MazeBox boxA, MazeBox boxB, Canvas canvas, int tem){
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() != Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.WAY); // 如果不为砖块，标记为空
				}
			}
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() == Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.STOCK); // 如果为砖块，标记为不可过
				}
			}
		int iStar = boxA.getboxX(), jStar = boxA.getboxY();
		int iEnd = boxB.getboxX(), jEnd = boxB.getboxY();
		int i = iStar, j = jStar;
		char path;
		int ans = 0;
		LinkedList<Character> stack = new LinkedList<Character>(); // 初始化一个栈，用来存放走过的路径
		canvas.getBox(i, j).setsysState(Enums.color.NOW);
		while (i >= iStar && i <= iEnd && j >= jStar && j <= jEnd) {// 开始遍历
			if (i == iEnd && j == jEnd) {// 到达终点，两点间有通路
				ans = 1;
				break;
			}
			if ((j + 1) <= jEnd
					&& canvas.getBox(i, j + 1).getsysState() == Enums.color.WAY) {// 向右探测一步
				canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
				j++;
				canvas.getBox(i, j).setsysState(Enums.color.NOW);
				stack.offerLast('R'); // 将路径的方位存储
			} else {
				if ((i + 1) <= iEnd
						&& canvas.getBox(i + 1, j).getsysState() == Enums.color.WAY) {// 向下探测一步
					canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
					i++;
					canvas.getBox(i, j).setsysState(Enums.color.NOW);
					stack.offerLast('D'); // 将路径的方位存储
				} else {
					if ((i - 1) >= iStar
							&& canvas.getBox(i - 1, j).getsysState() == Enums.color.WAY) {// 向上探测一步
						canvas.getBox(i, j).setsysState(Enums.color.BEEN_HERE);
						i--;
						canvas.getBox(i, j).setsysState(Enums.color.NOW);
						stack.offerLast('U'); // 将路径的方位存储
					} else {
						if ((j - 1) >= jStar
								&& canvas.getBox(i, j - 1).getsysState() == Enums.color.WAY) {// 向左探测一步
							canvas.getBox(i, j).setsysState(
									Enums.color.BEEN_HERE);
							j--;
							canvas.getBox(i, j).setsysState(Enums.color.NOW);
							stack.offerLast('L'); // 将路径的方位存储
						} else {// 遇到障碍往回走一步
							if (stack.isEmpty()) { // 判断是否回到起点了,是则退出遍历
								ans = 0;
								break;
							} else {
								if (stack.peekLast() != null) {// 判断能否取回上一步路径,取出不为空表示可以取出
									path = stack.pollLast();
									switch (path) {
									case 'L':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										j++;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'U':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										i++;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'D':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										i--;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									case 'R':
										canvas.getBox(i, j).setsysState(
												Enums.color.DEAD);// 标记为死
										j--;// 回退一步
										canvas.getBox(i, j).setsysState(
												Enums.color.NOW);
										break;
									}
								}
							}
						}
					}
				}
			}

		}
		for (int i1 = 1; i1 < canvas.getRows(); i1++)
			// 清除痕迹
			for (int j1 = 1; j1 < canvas.getCols(); j1++) {
				if (canvas.getBox(i1, j1).getState() != Enums.color.STOCK) {
					canvas.getBox(i1, j1).setsysState(Enums.color.WAY); // 如果不为砖块，标记为空
				}
			}
		if (ans == 1)

			return true;
		else
			return false;
	}


}
