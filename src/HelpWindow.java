
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

public class HelpWindow extends JFrame {
	private MyButton returnButton = new MyButton("返回游戏");

	String path = Class.class.getResource("/image/help.jpg").getPath();
	private final Image backImage=new ImageIcon(path).getImage();	//主要的背景图片
	JLabel helpInfo;

	public HelpWindow(String info) {
		JPanel helpPannel= new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(backImage,0,0,null);
			}
		};
		JLabel helpInfo = new JLabel(info);
		helpPannel.setLayout(new BorderLayout(5,0));
		mainUI(helpPannel, helpInfo);
		int windowWidth = 800;                     //获得窗口宽
	    int windowHeight =600;                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;   
	    this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});	

		setTitle("迷宫探险");
		setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
	}


	/*
	 * 主界面及主要事件触发方法
	 */
	public void mainUI(JPanel helpPannel, JLabel helpInfo){
		helpPannel.setLayout(null);		
		helpPannel.add(returnButton);
		returnButton.setBounds(650, 450, 100, 35);
		returnButton.setBackground(Color.BLACK);
		returnButton.setForeground(Color.WHITE);
		returnButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  dispose();
			  }
			});

		add(helpPannel, BorderLayout.CENTER);
		add(helpInfo, BorderLayout.SOUTH);
	}
	}