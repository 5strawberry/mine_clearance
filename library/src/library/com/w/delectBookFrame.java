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
	JLabel booknumber=new JLabel("ͼ����");
	 
	JTextField textFieldn=new JTextField(20);
	
	JButton  dbutton=new JButton("ȷ��ɾ��");
	
	JPanel numberp=new JPanel();
	JPanel buttonp=new JPanel();
	
	GridLayout gridLayout=new GridLayout(2, 1);//���񲼾�
	
	public delectBookFrame() {
		init();
		addListener();
	}
	public void init(){
		this.setTitle("ɾ��ͼ��");
		this.setBounds(300,150,600,250);//����λ�ü���С
		this.setResizable(false);//���ڴ�С���ɵ���
		//this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ	
		
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
					      JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					  } 					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
