
/*
 * 枚举变量
 * BACK表示背景色
 * STOCK表示墙
 * DEAD表示已探测过没有出路的块(系统使用)
 * WAY表示可行路径（系统帮助时用）
 * NOW当前位置(系统和表现公用)
 * END表示出口位置
 * BEEN_HERE表示曾来过这里(系统使用)
 */

public class Enums {
	public enum color{
		BACK, STOCK, DEAD, WAY, NOW, END, BEEN_HERE
	};
	
	public String toString() {
		return 	"枚举变量\n" +
				"BACK表示背景色\n" + 
				"STOCK表示墙\n" +
				"DEAD表示已探测过没有出路的块(系统使用)\n" +
				"WAY表示可行路径（系统帮助时用）\n" +
				"NOW当前位置(系统和表现公用)\n" +
				"END表示出口位置\n" +
				"BEEN_HERE表示曾来过这里(系统内部使用)\n";
	}
}
