package library.com.w;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.InitialContext;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Success extends JFrame {
	
//	JOptionPane.showMessageDialog(null, "�����ȵ�¼��");
	
	JPanel panelbutton=new JPanel();
	JButton button1=new JButton("��ϲ��ע��ɹ�");
	
	public Success() {
		init();
		addListener();
	}
	public void init() {		
		this.setSize(200,100);//���ڴ�С		
		this.setResizable(false);//���ڴ�С���ɵ���
	
		panelbutton.add(button1);
		this.add(panelbutton);
		
		this.setVisible(true);//���ڿɼ�
	}
	private void addListener() {
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginFrame loginFrame=new LoginFrame();
			}
		});
	}
}
