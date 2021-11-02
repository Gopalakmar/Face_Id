	import java.awt.*;
	import javax.swing.*;
	import javax.swing.event.*;
	import java.awt.event.*;
	import java.sql.*;
	import javax.swing.JPasswordField.*;

	 class Login extends JFrame implements ActionListener
	{
	JLabel l,l1,l2;
	JTextField t1,t2;
	JButton b1,b2;

	Login()
	{
	super("Login Page");

	l=new JLabel("Face Identification Login");
	l.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,20));

	l1=new JLabel(" User Name ");
	l2=new JLabel(" Password ");

	t1=new JTextField(10);
	t2=new JPasswordField(10);
	 
	b1=new JButton(" Submit ");
	b2=new JButton(" Cancel");

	JPanel p1=new JPanel();
	JPanel p2=new JPanel();

	Container c=getContentPane();
	c.setLayout(null);
	c.add(p1);
	c.add(p2);

	setSize(400,500);
	setResizable(false);
	
	java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	java.awt.Rectangle frame=getBounds();
	this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);

	p1.setBounds(20,20,300,300);
	p2.setBounds(20,350,300,100);
	
	l.setBounds(70,10,200,30);

	l1.setBounds(40,80,100,30);
	t1.setBounds(180,80,100,30);

	l2.setBounds(40,160,100,30);
	t2.setBounds(180,160,100,30);

	p1.setLayout(null);
	
	p1.add(l);
	p1.add(l1);p1.add(t1);
	p1.add(l2);p1.add(t2);

	b1.setBounds(50,40,80,30);
	b2.setBounds(200,40,80,30);

	t1.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,18));
	t2.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,18));

	p2.setLayout(null);
	p2.add(b1);p2.add(b2);

	b1.addActionListener(this);
	b2.addActionListener(this);

}

	public void actionPerformed(ActionEvent ae)
	{
	JButton b=(JButton)ae.getSource();
	 if(b==b1)
	{
	 String s1=t1.getText();
	 String s2=t2.getText();
	
	if((t1.getText()).equals("face"))
        {
            if((t2.getText()).equals("face"))
            {
                setVisible(false);
                dispose();
				new FaceMenu().show();
			}
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid password", "Sign In", JOptionPane.WARNING_MESSAGE);
				t2.setText("");
				t2.requestFocus();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Invalid UserName", "Sign In", JOptionPane.WARNING_MESSAGE);
			t1.setText("");
			t1.requestFocus();
			t2.setText("");
        }
    }

		if(b==b2)
		{
			System.exit(0);
		}
	}
}


	public class LoginDemo 
	{
	public static void main(String ar[])
	{
	Login ob1=new Login();
		ob1.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
			ob1.show();
	}
}