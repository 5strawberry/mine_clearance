package library.com.w;

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
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import library.com.dao.BookDao;
import library.com.model.BookModel;
import library.com.model.UserModel;
import library.com.util.DbUtil;
import library.com.util.stringUtil;

public class insertBookFrame extends JFrame{
	JLabel booknumber=new JLabel("图书编号");
	JLabel bookname=new JLabel("书名");
	JLabel bookauthor=new JLabel("作者");
	JLabel bookprice=new JLabel("价格");
	JLabel publishment=new JLabel("出版社");
	JLabel borrow=new JLabel("图书状态");
	 
	JTextField textFieldnumber=new JTextField(20);
	JTextField textFieldname=new JTextField(20);
	JTextField textFieldauthor=new JTextField(20);
	JTextField textFieldaprice=new JTextField(20);
	JTextField textFieldpublish=new JTextField(20);
	JTextField textFieldborrow=new JTextField(20);
	
	JButton  ibutton=new JButton("确认添加");
	
	JPanel numberp=new JPanel();
	JPanel namep=new JPanel();
	JPanel authorp=new JPanel();
	JPanel pricep=new JPanel();
	JPanel publishp=new JPanel();
	JPanel borrowp=new JPanel();
	JPanel buttonp=new JPanel();
	
	GridLayout gridLayout=new GridLayout(7, 1);//网格布局
	
	public insertBookFrame() {
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
		
		pricep.add(bookprice);
		pricep.add(textFieldaprice);
		
		publishp.add(publishment);
		publishp.add(textFieldpublish);
		
		borrowp.add(borrow);
		borrowp.add(textFieldborrow);		
		
		buttonp.add(ibutton);
		
		this.add(numberp);
		this.add(namep);
		this.add(authorp);		
		this.add(publishp);
		this.add(pricep);
		this.add(borrowp);
		this.add(buttonp);
		
		this.setVisible(true);
	}
	private void addListener() {
		ibutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String number = textFieldnumber.getText();
				String name = textFieldname.getText();
				String author=textFieldauthor.getText();
				String publishment=textFieldpublish.getText();
				String price=textFieldaprice.getText();
				String borrow=textFieldborrow.getText();
						
				if(stringUtil.isEmpty(name)) {
					JOptionPane.showMessageDialog(null,"书名不能为空");
				}else if(stringUtil.isEmpty(author)) {
					JOptionPane.showMessageDialog(null,"作者不能为空");
				}else if(stringUtil.isEmpty(publishment)) {
					JOptionPane.showMessageDialog(null,"出版社不能为空");
				}else if(stringUtil.isEmpty(price)) {
					JOptionPane.showMessageDialog(null,"价格不能为空");
				}else if(stringUtil.isEmpty(borrow)) {
					JOptionPane.showMessageDialog(null,"借书状态不能为空");
				}else if(stringUtil.isEmpty(number)) {
					JOptionPane.showMessageDialog(null,"图书编号不能为空");
				}else {
				BookModel bookMessage = new BookModel();
				
				bookMessage.setBook_number(number);
				bookMessage.setBook_name(name);				
				bookMessage.setBook_author(author);
				bookMessage.setPublishment(publishment);
				bookMessage.setBook_prince(price);
				bookMessage.setBorrow(borrow);
				
				BookDao bookDao=new BookDao();
				DbUtil dbUtil = new DbUtil();
				Connection con1 =null;
				try {
					con1 = dbUtil.getCon();					
					int currentbook = bookDao.insertBook(con1, bookMessage);
					System.out.println(currentbook);
					if(currentbook == 1) {
						JOptionPane.showMessageDialog(null, "添加成功！");						
					}						
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		});
	}
	
}
