package library.com.w;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.com.dao.BookDao;
import library.com.model.BookModel;
import library.com.util.DbUtil;

public class delectBookFrame extends JFrame{
	JLabel booknumber=new JLabel("图书编号");
	 
	JTextField textFieldn=new JTextField(20);
	
	JButton  dbutton=new JButton("确认删除");
	
	JPanel numberp=new JPanel();
	JPanel buttonp=new JPanel();
	
	GridLayout gridLayout=new GridLayout(2, 1);//网格布局
	
	public delectBookFrame() {
		init();
		addListener();
	}
	public void init(){
		this.setTitle("删除图书");
		this.setBounds(300,150,600,250);//窗口位置及大小
		this.setResizable(false);//窗口大小不可调整
		//this.setBackground(Color.yellow);//窗口背景颜色
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//点击右上角叉号表示隐藏当前窗口，并释放窗体占有的其他资源	
		
		this.setLayout(gridLayout);
		
		numberp.add(booknumber);
		numberp.add(textFieldn);
		
		buttonp.add(dbutton);
		
		this.add(numberp);
		this.add(buttonp);
		
		this.setVisible(true);
	}
	private void addListener() {
		dbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String number=textFieldn.getText();
							
				BookModel bookModel=new BookModel();
				bookModel.setBook_number(number);
				
				BookDao bookDao=new BookDao();
				
				DbUtil dbUtil = new DbUtil();
				Connection con =null;
				try {
					
					con = dbUtil.getCon();
					int db=bookDao.deletebook(con,bookModel);
					
					if(db==1) {
					      JOptionPane.showMessageDialog(null, "删除成功");
					  } 					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
