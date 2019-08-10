package library.com.w;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.com.dao.UserDao;
import library.com.model.BookModel;
import library.com.model.UserModel;
import library.com.util.DbUtil;

public class LoginFrame extends JFrame {

	JPasswordField passwordField = new JPasswordField(15);
	JTextField textField = new JTextField(15);
	JComboBox<String> jComboBox=new JComboBox<String>();
	
	Dimension dim = new Dimension(150, 30);
	Font font1=new Font("宋体", Font.BOLD,25);
	Font font2=new Font("宋体", Font.BOLD,15);

	JLabel jLabeljc = new JLabel("用户身份");
	JLabel jLabelid = new JLabel("账号");
	JLabel jLabelpassword = new JLabel("密码");

	JButton buttonlogin = new JButton("登录");
	JButton buttonsetup = new JButton("注册");

	JPanel panelid = new JPanel();// 面板1：账号文本框
	JPanel panelpassword = new JPanel();// 面板2：密码密码框
	JPanel panelbutton = new JPanel();// 面板3：登录注册
	JPanel paneljc = new JPanel();// 面板4：用户身份
	GridLayout gridLayout = new GridLayout(4, 1);// 网格布局三行一列

//	JPanel jpo=(JPanel) this.getContentPane();
	public LoginFrame() {
		init();
		addListener();
	}

//登录注册按钮监听	
	private void addListener() {
		// 登录监听
		buttonlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = textField.getText().toString();
				String password = new String(passwordField.getPassword());
				
				UserDao userDao = new UserDao();
				UserModel userModel=new UserModel();
				DbUtil dbUtil = new DbUtil();
				
				try {
					
					Connection con1 = dbUtil.getCon();
					ResultSet rs = userDao.selectUser(con1,userModel);
					
					while (rs.next()) {
						String userid = rs.getString("user_id");
						String userpassword = rs.getString("user_password");
						
						if (id.equals(userid) && password.equals(userpassword)) {
							MainFrame mainFrame = new MainFrame();							
								
							}
						}
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}

				// MainFrame mainFrame = new MainFrame();
			}
		});

		// 注册监听
		buttonsetup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				SetupFrame setupFrame = new SetupFrame();
			}
		});

	}

	public void init() {
		// JFrame wFrame=new JFrame("登录图书管理系统");
		// Container container=wFrame.getContentPane();//放回此窗口的contentpane对象
		this.setTitle("欢迎使用图书管理系统");
		this.setSize(600, 600);
		
		//使窗口居中
		this.setLocationRelativeTo(null);
		this.setResizable(false);// 窗口大小不可调整
		// this.setVisible(true);//窗口可见
		// this.setBackground(Color.yellow);//窗口背景颜色
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 点击右上角叉号表示隐藏当前窗口，并释放窗体占有的其他资源

		this.setLayout(gridLayout);// 建立网格布局

		
		jComboBox.setFont(font1);
		jLabeljc.setFont(font2);
		jComboBox.setPreferredSize(dim);
		//下拉列表	
		jComboBox.addItem("管理员");
		jComboBox.addItem("普通用户");
		
		// 在面板1中添加账号和账号文本框
		panelid.add(jLabelid);
		panelid.add(textField);
		// 在面板2中添加密码和密码文本框
		panelpassword.add(jLabelpassword);
		panelpassword.add(passwordField);
		/// 在面板3中添加登录和注册按钮
		panelbutton.add(buttonlogin);
		panelbutton.add(buttonsetup);
		//在面板4中添加用户身份	
		paneljc.add(jLabeljc);	
		paneljc.add(jComboBox);
		
		// 在窗口中添加面板4123
		this.add(paneljc);
		this.add(panelid);
		this.add(panelpassword);
		this.add(panelbutton);
		

		this.setVisible(true);// 窗口可见

	}

}
