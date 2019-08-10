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
	
//	JOptionPane.showMessageDialog(null, "请您先登录！");
	
	JPanel panelbutton=new JPanel();
	JButton button1=new JButton("恭喜你注册成功");
	
	public Success() {
		init();
		addListener();
	}
	public void init() {		
		this.setSize(200,100);//窗口大小		
		this.setResizable(false);//窗口大小不可调整
	
		panelbutton.add(button1);
		this.add(panelbutton);
		
		this.setVisible(true);//窗口可见
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
