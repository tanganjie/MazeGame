import javax.swing.*;

import java.awt.event.*;
import java.util.LinkedList;
import java.awt.*;
/*
 * 主游戏控制类
 */
public class StartGame extends JFrame{
    private static MazeBox nowBox = new MazeBox(Enums.color.NOW);

	final static JLabel info = new JLabel("谢谢");
	public static void setNowBox(MazeBox box) {
		nowBox = box;
	}
	
	private static int rowsOfMap, colsOfMap;
	
	public int getRowsOfmap() {
		return rowsOfMap;
	}

	public int getColsOfmap() {
		return colsOfMap;
	}
	
	public void setRowsAndColsOfmap(int x, int y) {
		rowsOfMap = x;
		colsOfMap = y;
	}
	private Canvas canvas ;
	/*
	 * 得到当前画布
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	public StartGame(int x, int y,int width,int height) {
		setRowsAndColsOfmap(x, y);
		canvas = new Canvas(47, 65);	
		canvas.setCols(getColsOfmap());
		canvas.setRows(getRowsOfmap());
		
		final JMenuBar menuBar = new JMenuBar();				// 定义一个菜单bar
		final JMenu game = new JMenu("游戏"); 					// 定义一个控制菜单
		final JMenu set = new JMenu("设置"); 					// 定义一个设置菜单
		final JMenu help = new JMenu("帮助"); 					// 定义一个帮助菜单
		final JMenu map = new JMenu("选择难度");
		final JMenuItem printWay = new JMenuItem("显示路径"), 
				exitGame = new JMenuItem("退出游戏"), 
				reGame = new JMenuItem("重新开始游戏"), 
				backGame = new JMenuItem("返回主界面"), 
				aboutGame = new JMenuItem("关于"),
				setBlockColor = new JMenuItem("设置砖块色彩"),
				setBackColor = new JMenuItem("设置背景色彩"),
				easy = new JMenuItem("简单"),
				middle = new JMenuItem("中等"),
				difficult = new JMenuItem("困难");
		
		menuBar.add(game);
		game.add(reGame);
		game.addSeparator();
		game.add(printWay);
		game.addSeparator();
		game.add(backGame);
		game.addSeparator();
		game.add(exitGame);
		
		menuBar.add(set);
		set.add(setBlockColor);
		set.add(setBackColor);
		set.addSeparator();
		set.add(map);
		map.add(easy);
		map.add(middle);
		map.add(difficult);
		
		menuBar.add(help);
		help.add(aboutGame);
		
		
		setLayout(new BorderLayout());
		add(getCanvas(), BorderLayout.CENTER);
		add(info, BorderLayout.SOUTH);
		setJMenuBar(menuBar);
		
		
		aboutGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpWindow("制作团队：风云神话");
			}
		});
		
		
		reGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMap(getCanvas());
				nowBox.setState(Enums.color.NOW);
				canvas.repaint();
			}
		});
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRowsAndColsOfmap(29, 39);
				for (int i = 0; i < canvas.getRows(); i++)
					for (int j = 0; j < canvas.getCols(); j++)
						canvas.getBox(i, j).setState(Enums.color.BACK);
				canvas.setCols(getColsOfmap());
				canvas.setRows(getRowsOfmap());
				new CreateMap(getCanvas());
				nowBox.setState(Enums.color.NOW);
				canvas.fanning();
				canvas.repaint();
			}
		});
		
		middle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRowsAndColsOfmap(35, 49);
				canvas.setCols(getColsOfmap());
				canvas.setRows(getRowsOfmap());
				for (int i = 0; i < canvas.getRows(); i++)
					for (int j = 0; j < canvas.getCols(); j++)
						canvas.getBox(i, j).setState(Enums.color.BACK);
				new CreateMap(getCanvas());
				nowBox.setState(Enums.color.NOW);
				canvas.fanning();
				canvas.repaint();
			}
		});
		
		difficult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRowsAndColsOfmap(47, 65);
				for (int i = 0; i < canvas.getRows(); i++)
					for (int j = 0; j < canvas.getCols(); j++)
						canvas.getBox(i, j).setState(Enums.color.BACK);
				canvas.setCols(getColsOfmap());
				canvas.setRows(getRowsOfmap());
				new CreateMap(getCanvas());
				nowBox.setState(Enums.color.NOW);
				canvas.fanning();
				canvas.repaint();
			}
		});
		
		setBlockColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newBlockColor = JColorChooser.showDialog(StartGame.this, "设置砖块颜色", canvas.getBlockColor());
				if (newBlockColor != null) {
					canvas.setStockColor(newBlockColor);
					canvas.repaint();
				}
			}
		});
		
		setBackColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newBackColor = JColorChooser.showDialog(StartGame.this, "设置背景颜色", canvas.getBackColor());
				if (newBackColor != null) {
					canvas.setBackColor(newBackColor);
					canvas.repaint();
				}
			}
		});
		
		backGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MazeGame("制作团队：风云神话");
			}
		});
		
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "谢谢尝试本款游戏！");
				System.exit(0);
			}
		});
		
		
		printWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<XandY> temlist = new LinkedList<XandY>();
				if (Judges.isConnected(canvas.getBox(1, 1), canvas.getBox(canvas.getRows() - 2, canvas.getCols() - 1), canvas, 1)) {
					temlist = Judges.toConnectedXandY(canvas.getBox(1, 1), canvas.getBox(canvas.getRows() - 2, canvas.getCols() - 1), canvas);
					XandY xandy = new XandY();
					int x, y;
					for (int i = 0; i < temlist.size(); i++) {
						xandy = temlist.get(i);
						x = xandy.getX();
						y = xandy.getY();
						canvas.getBox(x, y).setState(Enums.color.WAY);
					}
					nowBox.setState(Enums.color.NOW);
					canvas.repaint();
				}
			}
		});
		/*
		 * 创建新的地图
		 */
		new CreateMap(getCanvas());
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				canvas.repaint();
				int keyValue = e.getKeyCode();
				if (keyValue == KeyEvent.VK_DOWN) {
					if (!(canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2))) {
						if (Judges.isCrossable(canvas.getBox(canvas.getRow(nowBox) + 1, canvas.getCol(nowBox)))) {
							nowBox.setState(Enums.color.BACK);
							setNowBox(canvas.getBox(canvas.getRow(nowBox) + 1, canvas.getCol(nowBox)));
							if (canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2)) {
								JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								for (int i = 0; i < canvas.getRows(); i++)
									for (int j = 0; j < canvas.getCols(); j++)
										canvas.getBox(i, j).setState(Enums.color.BACK);
								canvas.setCols(getColsOfmap());
								canvas.setRows(getRowsOfmap());
								new CreateMap(getCanvas());
								nowBox.setState(Enums.color.NOW);
								canvas.fanning();
								canvas.repaint();
							}
							nowBox.setState(Enums.color.NOW);
							canvas.repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < canvas.getRows(); i++)
							for (int j = 0; j < canvas.getCols(); j++)
								canvas.getBox(i, j).setState(Enums.color.BACK);
						canvas.setCols(getColsOfmap());
						canvas.setRows(getRowsOfmap());
						new CreateMap(getCanvas());
						nowBox.setState(Enums.color.NOW);
						canvas.fanning();
						canvas.repaint();
					}
						
				} else if (keyValue == KeyEvent.VK_UP) {
					if (!(canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2))) {
						if (Judges.isCrossable(canvas.getBox(canvas.getRow(nowBox) - 1, canvas.getCol(nowBox)))) {
							nowBox.setState(Enums.color.BACK);
							setNowBox(canvas.getBox(canvas.getRow(nowBox) - 1, canvas.getCol(nowBox)));
							if (canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2)) {
								JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								for (int i = 0; i < canvas.getRows(); i++)
									for (int j = 0; j < canvas.getCols(); j++)
										canvas.getBox(i, j).setState(Enums.color.BACK);
								canvas.setCols(getColsOfmap());
								canvas.setRows(getRowsOfmap());
								new CreateMap(getCanvas());
								nowBox.setState(Enums.color.NOW);
								canvas.fanning();
								canvas.repaint();
							}
							nowBox.setState(Enums.color.NOW);
							canvas.repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < canvas.getRows(); i++)
							for (int j = 0; j < canvas.getCols(); j++)
								canvas.getBox(i, j).setState(Enums.color.BACK);
						canvas.setCols(getColsOfmap());
						canvas.setRows(getRowsOfmap());
						new CreateMap(getCanvas());
						nowBox.setState(Enums.color.NOW);
						canvas.fanning();
						canvas.repaint();
					}

				} else if (keyValue == KeyEvent.VK_LEFT) {
					if (!(canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2))) {
						if (Judges.isCrossable(canvas.getBox(canvas.getRow(nowBox), canvas.getCol(nowBox) - 1))) {
							nowBox.setState(Enums.color.BACK);
							setNowBox(canvas.getBox(canvas.getRow(nowBox),canvas.getCol(nowBox) - 1));
							if (canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2)) {
								JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								for (int i = 0; i < canvas.getRows(); i++)
									for (int j = 0; j < canvas.getCols(); j++)
										canvas.getBox(i, j).setState(Enums.color.BACK);
								canvas.setCols(getColsOfmap());
								canvas.setRows(getRowsOfmap());
								new CreateMap(getCanvas());
								nowBox.setState(Enums.color.NOW);
								canvas.fanning();
								canvas.repaint();
							}
							nowBox.setState(Enums.color.NOW);
							canvas.repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < canvas.getRows(); i++)
							for (int j = 0; j < canvas.getCols(); j++)
								canvas.getBox(i, j).setState(Enums.color.BACK);
						canvas.setCols(getColsOfmap());
						canvas.setRows(getRowsOfmap());
						new CreateMap(getCanvas());
						nowBox.setState(Enums.color.NOW);
						canvas.fanning();
						canvas.repaint();
					}
				} else if (keyValue == KeyEvent.VK_RIGHT) {
					if (!(canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2))) {
						if (Judges.isCrossable(canvas.getBox(canvas.getRow(nowBox), canvas.getCol(nowBox) + 1))) {
							nowBox.setState(Enums.color.BACK);
							setNowBox(canvas.getBox(canvas.getRow(nowBox),canvas.getCol(nowBox) + 1));
							if (canvas.getRow(nowBox) == (canvas.getRows() - 2) && canvas.getCol(nowBox) == (canvas.getCols() - 2)) {
								JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								for (int i = 0; i < canvas.getRows(); i++)
									for (int j = 0; j < canvas.getCols(); j++)
										canvas.getBox(i, j).setState(Enums.color.BACK);
								canvas.setCols(getColsOfmap());
								canvas.setRows(getRowsOfmap());
								new CreateMap(getCanvas());
								nowBox.setState(Enums.color.NOW);
								canvas.fanning();
								canvas.repaint();
							}
							nowBox.setState(Enums.color.NOW);
							canvas.repaint();
						}

					} else {
						JOptionPane.showMessageDialog(null, "恭喜走出迷宫！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < canvas.getRows(); i++)
							for (int j = 0; j < canvas.getCols(); j++)
								canvas.getBox(i, j).setState(Enums.color.BACK);
						canvas.setCols(getColsOfmap());
						canvas.setRows(getRowsOfmap());
						new CreateMap(getCanvas());
						nowBox.setState(Enums.color.NOW);
						canvas.fanning();
						canvas.repaint();
					}
				}
			}
		}
		);
		

		
		
		int windowWidth = width;                     //获得窗口宽
	    int windowHeight =height;                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;  
		
	    nowBox.setState(Enums.color.NOW);
		setTitle("迷宫探险");
		setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		setVisible(true);
		canvas.fanning();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "谢谢尝试本款游戏！");
				System.exit(0);
			}
		});	
	}	
	
private MyButton difficultButton = new MyButton("困难");
private MyButton middleButton = new MyButton("中等");
private MyButton returnButton = new MyButton("返回");
private MyButton easyButton = new MyButton("简单");

String path = Class.class.getResource("/image/back2.jpg").getPath();
private final Image backImage=new ImageIcon(path).getImage();	//主要的背景图片
JLabel starInfo;

public StartGame(String info) {
	JPanel starPannel= new JPanel(){
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(backImage,0,0,null);
		}
	};
	JLabel starInfo = new JLabel(info);
	starPannel.setLayout(new BorderLayout(5,0));
	mainUI(starPannel, starInfo);
	 
	int windowWidth = 800;                     //获得窗口宽
    int windowHeight =600;                   //获得窗口高
    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
    int screenWidth = screenSize.width;                     //获取屏幕的宽
    int screenHeight = screenSize.height;   
	

	setTitle("迷宫探险");
	setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800, 600);
	setResizable(false);
	setVisible(true);
}


/*
 * 主界面及主要事件触发方法
 */
public void mainUI(JPanel starPannel, JLabel starInfo){
	starPannel.setLayout(null);
	
	starPannel.add(difficultButton);
	starPannel.add(returnButton);
	starPannel.add(middleButton);
	starPannel.add(easyButton);
	
	easyButton.setBounds(650, 330, 100, 35);
	easyButton.setBackground(Color.BLACK);
	easyButton.setForeground(Color.WHITE);
	easyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				new StartGame(29, 39, 800, 600);
			}
		});      
	middleButton.setBounds(650, 380, 100, 35);
	middleButton.setBackground(Color.BLACK);
	middleButton.setForeground(Color.WHITE);
	middleButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new StartGame(35, 49, 800, 600);
		}
	});   
	difficultButton.setBounds(650, 430, 100, 35);
	difficultButton.setBackground(Color.BLACK);
	difficultButton.setForeground(Color.WHITE);
	difficultButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new StartGame(47, 65, 800, 600);
		}
	});   
	
	returnButton.setBounds(650, 480, 100, 35);
	returnButton.setBackground(Color.BLACK);
	returnButton.setForeground(Color.WHITE);
	returnButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  dispose();
		  }
		});
	returnButton.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){
			new MazeGame("制作团队：风云神话");
		}
	});
	add(starPannel, BorderLayout.CENTER);
	add(starInfo, BorderLayout.SOUTH);
}
}
