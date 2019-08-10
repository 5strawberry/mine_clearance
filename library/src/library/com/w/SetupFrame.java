package library.com.w;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.com.dao.UserDao;
import library.com.model.UserModel;
import library.com.util.DbUtil;
import library.com.util.stringUtil;

public class SetupFrame extends JFrame {
	JLabel labelid=new JLabel("学号");
	JLabel labelename=new JLabel("姓名");
	JLabel labelpassword=new JLabel("设置密码");
	JLabel labelrepassword=new JLabel("确认密码");
		
	JTextField textFieldid=new JTextField(15);
	JPasswordField passwordField=new JPasswordField(15);
	JPasswordField repasswordField=new JPasswordField(15);
	JTextField textFieldname=new JTextField(20);
	JButton buttons=new JButton("确认注册");//注册成功按钮
	
	JPanel panelid=new JPanel();//学号面板
	JPanel panelpassword=new JPanel();//密码面板
	JPanel panelrepassword=new JPanel();//确认密码面板
	JPanel panelname=new JPanel();//姓名面板
	JPanel panelbuttons=new JPanel();//按钮面板
	

	
	GridLayout gridLayout=new GridLayout(5, 1);//网格布局
	
	public SetupFrame() {
		init();
		addListener();
	}
	public void init() {
		this.setTitle("注册账号");
		this.setBounds(300,50,600,600);//窗口位置及大小
		this.setResizable(false);//窗口大小不可调整
		//this.setBackground(Color.yellow);//窗口背景颜色
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击右上角叉号表示隐藏当前窗口，并释放窗体占有的其他资源	
		
		this.setLayout(gridLayout);//网格
		//学号面板
		panelid.add(labelid);
		panelid.add(textFieldid);
		//姓名面板
		panelname.add(labelename);
		panelname.add(textFieldname);
		//密码面板
		panelpassword.add(labelpassword);
		panelpassword.add(passwordField);
		//确定密码面板
		panelrepassword.add(labelrepassword);
		panelrepassword.add(repasswordField);		
		//按钮面板
		panelbuttons.add(buttons);		
		
		this.add(panelid);
		this.add(panelname);
		this.add(panelpassword);
		this.add(panelrepassword);
		this.add(panelbuttons);
		                                            
		this.setVisible(true);
	}
	private void addListener(){
		buttons.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String id = textFieldid.getText();//getText()获取文本框值
				String pw1 = new String(passwordField.getPassword());//getPassword()获取密码框值
				String pw2 = new String(repasswordField.getPassword());
				String name = textFieldname.getText();
				
				if(stringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null,"账号不能为空");
				}else if(stringUtil.isEmpty(pw1)) {
					JOptionPane.showMessageDialog(null,"密码不能为空");
				}else if(stringUtil.isEmpty(pw2)) {
					JOptionPane.showMessageDialog(null,"请重复输入密码");
				}else if(stringUtil.isEmpty(name)) {
					JOptionPane.showMessageDialog(null,"姓名不能为空");
				}else {
					
					if(pw1.equals(pw2)) {//判断密码和确认密码是否一致，==确定的是地址而不是内容
						UserModel userMessage = new UserModel();
						
						userMessage.setUser_id(id);
						userMessage.setUser_name(name);
						userMessage.setUser_password(pw1);
						
						UserDao userDao = new UserDao();
						
						DbUtil dbUtil = new DbUtil();
						Connection con1 =null;
						try {
							con1 = dbUtil.getCon();
							int currentUser = userDao.insertUser(con1, userMessage);
							if(currentUser == 1) {
								//JOptionPane.showMessageDialog(null, "注册成功！");
								Success success=new Success();
							}						
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					//提示窗口
					//Success success=new Success();
					//提示框
					//JOptionPane.showMessageDialog(null, "注册成功！");
					//MainFrame mainFrame=new MainFrame();
				}
			}
		});		
	}
	
	
}
