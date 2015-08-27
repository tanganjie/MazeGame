import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/*
 * 游戏主界面类；
 */
public class MazeGame extends JFrame{
	String path = Class.class.getResource("/image/back.jpg").getPath();
	private final Image backImage=new ImageIcon(path).getImage();	//主要的背景图片
	
	JLabel mainInfo;		//制作团队信息面板
		
	private MyButton startButton = new MyButton("开始游戏");
	private MyButton helpButton = new MyButton("帮助");
	private MyButton exitButton = new MyButton("退出游戏");
	
	//游戏接入口
	public MazeGame(String info) {
		//System.out.println(path);
		JPanel mainPannel= new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(backImage,0,0,null);
			}
		};
		JLabel mainInfo = new JLabel(info);
		
		setLayout(new BorderLayout(5,0));
		mainUI(mainPannel, mainInfo);
		
		int windowWidth = 800;                     //获得窗口宽
	    int windowHeight =600;                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;   
		
	    addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "谢谢尝试本款游戏！");
				System.exit(0);
			}
		});	
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
	public void mainUI(JPanel mainPannel, JLabel mainInfo){
		mainPannel.setLayout(null);
		
		mainPannel.add(startButton);
		mainPannel.add(exitButton);
		mainPannel.add(helpButton);
		startButton.setBounds(650, 350, 100, 35);
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.WHITE);
		startButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				dispose();
				new StartGame("制作团队：风云神话");
			}
		});
		helpButton.setBounds(650, 400, 100, 35);
		helpButton.setBackground(Color.BLACK);
		helpButton.setForeground(Color.WHITE);
		exitButton.setBounds(650, 450, 100, 35);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.WHITE);
		helpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new HelpWindow("制作团队：风云神话");
			}
		});
		exitButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  JOptionPane.showMessageDialog(null, "谢谢尝试本款游戏！");
				  System.exit(0);
			  }
		});

		add(mainPannel, BorderLayout.CENTER);
		add(mainInfo, BorderLayout.SOUTH);
	}
}