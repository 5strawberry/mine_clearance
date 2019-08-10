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
	JLabel booknumber=new JLabel("ͼ����");
	JLabel bookname=new JLabel("����");
	JLabel bookauthor=new JLabel("����");
	JLabel bookprice=new JLabel("�۸�");
	JLabel publishment=new JLabel("������");
	JLabel borrow=new JLabel("ͼ��״̬");
	 
	JTextField textFieldnumber=new JTextField(20);
	JTextField textFieldname=new JTextField(20);
	JTextField textFieldauthor=new JTextField(20);
	JTextField textFieldaprice=new JTextField(20);
	JTextField textFieldpublish=new JTextField(20);
	JTextField textFieldborrow=new JTextField(20);
	
	JButton  ibutton=new JButton("ȷ�����");
	
	JPanel numberp=new JPanel();
	JPanel namep=new JPanel();
	JPanel authorp=new JPanel();
	JPanel pricep=new JPanel();
	JPanel publishp=new JPanel();
	JPanel borrowp=new JPanel();
	JPanel buttonp=new JPanel();
	
	GridLayout gridLayout=new GridLayout(7, 1);//���񲼾�
	
	public insertBookFrame() {
		init();
		addListener();
	}
	public void init() {
		this.setTitle("����ͼ��");
		this.setBounds(300,150,600,250);//����λ�ü���С
		this.setResizable(false);//���ڴ�С���ɵ���
		//this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ	
		
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
					JOptionPane.showMessageDialog(null,"��������Ϊ��");
				}else if(stringUtil.isEmpty(author)) {
					JOptionPane.showMessageDialog(null,"���߲���Ϊ��");
				}else if(stringUtil.isEmpty(publishment)) {
					JOptionPane.showMessageDialog(null,"�����粻��Ϊ��");
				}else if(stringUtil.isEmpty(price)) {
					JOptionPane.showMessageDialog(null,"�۸���Ϊ��");
				}else if(stringUtil.isEmpty(borrow)) {
					JOptionPane.showMessageDialog(null,"����״̬����Ϊ��");
				}else if(stringUtil.isEmpty(number)) {
					JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
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
						JOptionPane.showMessageDialog(null, "��ӳɹ���");						
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
