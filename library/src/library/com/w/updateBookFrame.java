package library.com.w;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.Group;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import library.com.dao.BookDao;
import library.com.model.BookModel;
import library.com.util.DbUtil;

public class updateBookFrame extends JFrame {

	JLabel booknumber=new JLabel("ͼ����");
	JLabel bookborrow=new JLabel("ͼ��״̬");
	 
	JTextField textFieldn=new JTextField(20);
	JRadioButton radiozero=new JRadioButton("0");
	JRadioButton radioone=new JRadioButton("1");
	ButtonGroup group=new ButtonGroup();
	
	
	JButton  ubutton=new JButton("ȷ�ϸ���");
	
	JPanel numberp=new JPanel();
	JPanel buttonp=new JPanel();
	JPanel radiop=new JPanel();
	
	GridLayout gridLayout=new GridLayout(3, 1);//���񲼾�
	
	public updateBookFrame() {
		init();
		addListener();
	}
	public void init(){
		this.setTitle("����ͼ��");
		this.setBounds(300,150,600,250);//����λ�ü���С
		this.setResizable(false);//���ڴ�С���ɵ���
		//this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ	
		
		this.setLayout(gridLayout);
		
		group.add(radioone);
		group.add(radiozero);
		
		numberp.add(booknumber);
		numberp.add(textFieldn);
		
		buttonp.add(ubutton);
		
		radiop.add(bookborrow);
		radiop.add(radioone);
		radiop.add(radiozero);
		
		this.add(numberp);
		this.add(buttonp);
		this.add(radiop);
		
		this.setVisible(true);
	}
	private void addListener() {
		ubutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					String number=textFieldn.getText();
					String radioText;
					if(radioone.isSelected()){
					     radioText = radioone.getText();		
					}
					else {
						 radioText = radiozero.getText();	
					}
					BookModel bookModel=new BookModel();
					bookModel.setBorrow(radioText);
					bookModel.setBook_number(number);					
					
					BookDao bookDao=new BookDao();
					
					DbUtil ubUtil = new DbUtil();
					Connection con =null;
					try {
						
						con = ubUtil.getCon();
						int ub=bookDao.updatebook(con,bookModel);						
						if(ub==1) {
						      JOptionPane.showMessageDialog(null, "���³ɹ�");
						  } 					
					}catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
	}
}
