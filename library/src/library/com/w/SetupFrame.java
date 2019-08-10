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
	JLabel labelid=new JLabel("ѧ��");
	JLabel labelename=new JLabel("����");
	JLabel labelpassword=new JLabel("��������");
	JLabel labelrepassword=new JLabel("ȷ������");
		
	JTextField textFieldid=new JTextField(15);
	JPasswordField passwordField=new JPasswordField(15);
	JPasswordField repasswordField=new JPasswordField(15);
	JTextField textFieldname=new JTextField(20);
	JButton buttons=new JButton("ȷ��ע��");//ע��ɹ���ť
	
	JPanel panelid=new JPanel();//ѧ�����
	JPanel panelpassword=new JPanel();//�������
	JPanel panelrepassword=new JPanel();//ȷ���������
	JPanel panelname=new JPanel();//�������
	JPanel panelbuttons=new JPanel();//��ť���
	

	
	GridLayout gridLayout=new GridLayout(5, 1);//���񲼾�
	
	public SetupFrame() {
		init();
		addListener();
	}
	public void init() {
		this.setTitle("ע���˺�");
		this.setBounds(300,50,600,600);//����λ�ü���С
		this.setResizable(false);//���ڴ�С���ɵ���
		//this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ	
		
		this.setLayout(gridLayout);//����
		//ѧ�����
		panelid.add(labelid);
		panelid.add(textFieldid);
		//�������
		panelname.add(labelename);
		panelname.add(textFieldname);
		//�������
		panelpassword.add(labelpassword);
		panelpassword.add(passwordField);
		//ȷ���������
		panelrepassword.add(labelrepassword);
		panelrepassword.add(repasswordField);		
		//��ť���
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
				
				String id = textFieldid.getText();//getText()��ȡ�ı���ֵ
				String pw1 = new String(passwordField.getPassword());//getPassword()��ȡ�����ֵ
				String pw2 = new String(repasswordField.getPassword());
				String name = textFieldname.getText();
				
				if(stringUtil.isEmpty(id)) {
					JOptionPane.showMessageDialog(null,"�˺Ų���Ϊ��");
				}else if(stringUtil.isEmpty(pw1)) {
					JOptionPane.showMessageDialog(null,"���벻��Ϊ��");
				}else if(stringUtil.isEmpty(pw2)) {
					JOptionPane.showMessageDialog(null,"���ظ���������");
				}else if(stringUtil.isEmpty(name)) {
					JOptionPane.showMessageDialog(null,"��������Ϊ��");
				}else {
					
					if(pw1.equals(pw2)) {//�ж������ȷ�������Ƿ�һ�£�==ȷ�����ǵ�ַ����������
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
								//JOptionPane.showMessageDialog(null, "ע��ɹ���");
								Success success=new Success();
							}						
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					//��ʾ����
					//Success success=new Success();
					//��ʾ��
					//JOptionPane.showMessageDialog(null, "ע��ɹ���");
					//MainFrame mainFrame=new MainFrame();
				}
			}
		});		
	}
	
	
}
