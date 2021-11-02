import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class Help extends JFrame implements ActionListener
{
	JTextArea jt;
	JButton b1;
	String str;

	Help()
	{
		super("Help On Project");
		
		Container c=getContentPane();
		c.setLayout(null);
		setSize(400,450);
		setResizable(false);
	
	java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	java.awt.Rectangle frame=getBounds();
	this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);


		jt=new JTextArea();
		
		b1=new JButton("Ok");
		
		jt.setBounds(20,30,350,320);
		str="FACE IDENTIFICATION SYSTEM\n\n\n This System is mainly developed \n for identifying criminals in any police  \n department this process is done by \n\n\t 1. Adding details\n\t 2. Clipping Image\n\t 3. Updating Details\n\t 4. Constructing Image\n\t 5. Find Face\n\n Developed By: Anand & Prathima";
		jt.setText(str);
		jt.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,18));
		jt.setEditable(false);
		b1.setBounds(150,370,80,30);

		c.add(jt);
		c.add(b1);

		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			setVisible(false);
			dispose();
		}
	}
}

class HelpDemo
{
	public static void main(String ar[])
	{
		new Help().show();
	}
}