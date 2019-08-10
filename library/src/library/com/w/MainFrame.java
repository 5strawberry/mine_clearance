package library.com.w;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.com.dao.BookDao;

public class MainFrame extends JFrame {
//	JOptionPane.showMessageDialog(null, "请您先登录！");	
	Button selectbook=new Button("查询图书");
	Button updatebook=new Button("更新图书");
	Button insertbook=new Button("增加图书");
	Button delectbook=new Button("删除图书");
	
	JPanel spanel = new JPanel();
	JPanel ipanel = new JPanel();
	JPanel upanel = new JPanel();
	JPanel dpanel = new JPanel();
	
	GridLayout gridLayout=new GridLayout(4, 1);//网格布局
	
	Dimension dim = new Dimension(90, 30);
	public MainFrame() {
		init();
		addListener();
	}
	public void init() {		
		this.setTitle("查找图书");
		this.setBounds(300,50,600,600);//窗口位置及大小
		this.setResizable(false);//窗口大小不可调整
		//this.setBackground(Color.yellow);//窗口背景颜色
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击右上角叉号表示隐藏当前窗口，并释放窗体占有的其他资源	
		
		this.setLayout(gridLayout);// 建立网格布局
		
		spanel.add(selectbook);
		upanel.add(updatebook);
		dpanel.add(delectbook);
		ipanel.add(insertbook);
		
		spanel.setPreferredSize(dim);
		dpanel.setPreferredSize(dim);
		upanel.setPreferredSize(dim);
		ipanel.setPreferredSize(dim);
		
		this.add(spanel);
		this.add(dpanel);
		this.add(upanel);
		this.add(ipanel);
		
		this.setVisible(true);
	}
	
	private void addListener() {
		//查
		selectbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectBookFrame sb=new selectBookFrame();				
			}
		});
		//增
		insertbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertBookFrame iBookFrame=new insertBookFrame();
			}
		});
		//删
		delectbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delectBookFrame dbBookFrame=new delectBookFrame();
			}
		});
		//更新
		updatebook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateBookFrame ub=new updateBookFrame();
			}
		});
		
	}

}
