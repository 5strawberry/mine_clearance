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
	Font font1=new Font("����", Font.BOLD,25);
	Font font2=new Font("����", Font.BOLD,15);

	JLabel jLabeljc = new JLabel("�û����");
	JLabel jLabelid = new JLabel("�˺�");
	JLabel jLabelpassword = new JLabel("����");

	JButton buttonlogin = new JButton("��¼");
	JButton buttonsetup = new JButton("ע��");

	JPanel panelid = new JPanel();// ���1���˺��ı���
	JPanel panelpassword = new JPanel();// ���2�����������
	JPanel panelbutton = new JPanel();// ���3����¼ע��
	JPanel paneljc = new JPanel();// ���4���û����
	GridLayout gridLayout = new GridLayout(4, 1);// ���񲼾�����һ��

//	JPanel jpo=(JPanel) this.getContentPane();
	public LoginFrame() {
		init();
		addListener();
	}

//��¼ע�ᰴť����	
	private void addListener() {
		// ��¼����
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

		// ע�����
		buttonsetup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				SetupFrame setupFrame = new SetupFrame();
			}
		});

	}

	public void init() {
		// JFrame wFrame=new JFrame("��¼ͼ�����ϵͳ");
		// Container container=wFrame.getContentPane();//�Żش˴��ڵ�contentpane����
		this.setTitle("��ӭʹ��ͼ�����ϵͳ");
		this.setSize(600, 600);
		
		//ʹ���ھ���
		this.setLocationRelativeTo(null);
		this.setResizable(false);// ���ڴ�С���ɵ���
		// this.setVisible(true);//���ڿɼ�
		// this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ

		this.setLayout(gridLayout);// �������񲼾�

		
		jComboBox.setFont(font1);
		jLabeljc.setFont(font2);
		jComboBox.setPreferredSize(dim);
		//�����б�	
		jComboBox.addItem("����Ա");
		jComboBox.addItem("��ͨ�û�");
		
		// �����1������˺ź��˺��ı���
		panelid.add(jLabelid);
		panelid.add(textField);
		// �����2���������������ı���
		panelpassword.add(jLabelpassword);
		panelpassword.add(passwordField);
		/// �����3����ӵ�¼��ע�ᰴť
		panelbutton.add(buttonlogin);
		panelbutton.add(buttonsetup);
		//�����4������û����	
		paneljc.add(jLabeljc);	
		paneljc.add(jComboBox);
		
		// �ڴ�����������4123
		this.add(paneljc);
		this.add(panelid);
		this.add(panelpassword);
		this.add(panelbutton);
		

		this.setVisible(true);// ���ڿɼ�

	}

}
