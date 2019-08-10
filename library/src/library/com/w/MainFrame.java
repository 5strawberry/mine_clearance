package library.com.w;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.com.dao.BookDao;

public class MainFrame extends JFrame {
//	JOptionPane.showMessageDialog(null, "�����ȵ�¼��");	
	Button selectbook=new Button("��ѯͼ��");
	Button updatebook=new Button("����ͼ��");
	Button insertbook=new Button("����ͼ��");
	Button delectbook=new Button("ɾ��ͼ��");
	
	JPanel spanel = new JPanel();
	JPanel ipanel = new JPanel();
	JPanel upanel = new JPanel();
	JPanel dpanel = new JPanel();
	
	GridLayout gridLayout=new GridLayout(4, 1);//���񲼾�
	
	Dimension dim = new Dimension(90, 30);
	public MainFrame() {
		init();
		addListener();
	}
	public void init() {		
		this.setTitle("����ͼ��");
		this.setBounds(300,50,600,600);//����λ�ü���С
		this.setResizable(false);//���ڴ�С���ɵ���
		//this.setBackground(Color.yellow);//���ڱ�����ɫ
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//������Ͻǲ�ű�ʾ���ص�ǰ���ڣ����ͷŴ���ռ�е�������Դ	
		
		this.setLayout(gridLayout);// �������񲼾�
		
		spanel.add(selectbook);
		upanel.add(updatebook);
		dpanel.add(delectbook);
		ipanel.add(insertbook);
		
		spanel.setPreferredSize(dim);
		dpanel.setPreferredSize(dim);
		upanel.setPreferredSize(dim);
		ipanel.setPreferredSize(dim);
		
		this.add(spanel);
		this.add(dpanel);
		this.add(upanel);
		this.add(ipanel);
		
		this.setVisible(true);
	}
	
	private void addListener() {
		//��
		selectbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectBookFrame sb=new selectBookFrame();				
			}
		});
		//��
		insertbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertBookFrame iBookFrame=new insertBookFrame();
			}
		});
		//ɾ
		delectbook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delectBookFrame dbBookFrame=new delectBookFrame();
			}
		});
		//����
		updatebook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateBookFrame ub=new updateBookFrame();
			}
		});
		
	}

}
