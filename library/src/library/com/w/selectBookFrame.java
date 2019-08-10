package library.com.w;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.com.dao.BookDao;
import library.com.util.DbUtil;


public class selectBookFrame extends JFrame{
	JLabel booknumber=new JLabel("图书编号");
	JLabel bookname=new JLabel("书名");
	JLabel bookauthor=new JLabel("作者");
	 
	JTextField textFieldnumber=new JTextField(20);
	JTextField textFieldname=new JTextField(20);
	JTextField textFieldauthor=new JTextField(20);
	JButton  sbutton=new JButton("确认查找");
	
	JPanel numberp=new JPanel();
	JPanel namep=new JPanel();
	JPanel authorp=new JPanel();
	JPanel buttonp=new JPanel();
	
	GridLayout gridLayout=new GridLayout(4, 1);//网格布局
	
	public selectBookFrame(){
		init();
		addListener();
	}
	public void init() {
		this.setTitle("查找图书");
		this.setBounds(300,150,600,250);//窗口位置及大小
		this.setResizable(false);//窗口大小不可调整
		//this.setBackground(Color.yellow);//窗口背景颜色
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击右上角叉号表示隐藏当前窗口，并释放窗体占有的其他资源	
		
		this.setLayout(gridLayout);
		
		numberp.add(booknumber);
		numberp.add(textFieldnumber);
		
		namep.add(bookname);
		namep.add(textFieldname);
		
		authorp.add(bookauthor);
		authorp.add(textFieldauthor);
		
		buttonp.add(sbutton);
		
		this.add(numberp);
		this.add(namep);
		this.add(authorp);
		this.add(buttonp);
		
		this.setVisible(true);

	}
	private void addListener() {
		sbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String getnumber= textFieldnumber.getText();
				String getname= textFieldname.getText();
				String getauthor= textFieldauthor.getText();
				
				
				BookDao bookDao=new BookDao();
				DbUtil dbUtil = new DbUtil();
				try {
					Connection con1 = dbUtil.getCon();
					ResultSet rs = bookDao.selectBook();
					while (rs.next()) {
						String bname=rs.getString(2);
						String bnumber=rs.getString(3);
						String bauthor=rs.getString(1);
						String getborrow=rs.getString(6);
					
						if(getnumber.equals(bnumber)&&getname.equals(bname)&&getauthor.equals(bauthor)) {
							JOptionPane.showMessageDialog(null, getborrow);	
							
						}
						else if(getnumber.equals(null)||getname.equals(null)||getauthor.equals(null)){
							JOptionPane.showMessageDialog(null, "请确认输入信息");
							break;
						}
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private Object getborrow() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
