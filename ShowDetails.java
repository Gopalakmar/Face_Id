import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import java.sql.*;

class Show1 extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	JLabel head,photo;
	JComboBox c1;
	JTextField  t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
	JButton b1,b2;

	Connection cn;
    Statement stmt;

	public Show1()
	{
		super("Show Details Form");
			
		Container c=getContentPane();
		c.setLayout(null);
		setSize(700,710);
		
		java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Rectangle frame=getBounds();
		this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);
		
		setResizable(false);

		head=new JLabel("Criminal Details Form");
		head.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,25));
		head.setForeground(Color.blue);
		
		photo=new JLabel("");

		l1=new JLabel("Criminal Id");
		l2=new JLabel("First Name");
		l3=new JLabel("Last Name");
		l4=new JLabel("Alias Name");
		l5=new JLabel("D.O.B");
		l6=new JLabel("Age");
		l7=new JLabel("Gender");
		l8=new JLabel("Address");
		l9=new JLabel("City");
		l10=new JLabel("State");
		l11=new JLabel("Arrested Date");
		l12=new JLabel("Crime Involved ");
		
		t2=new JTextField(20);
		t3=new JTextField(20);
		t4=new JTextField(20);
		t5=new JTextField(20);
		t6=new JTextField(20);
		t7=new JTextField(20);
		t8=new JTextField(20);
		t9=new JTextField(20);
		t10=new JTextField(20);
		t11=new JTextField(20);
		t12=new JTextField(20);

		c1=new JComboBox();
		
		b1=new JButton("Clear");
		b2=new JButton("Close");
		
		try
        {
        
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Database d1=new Database();
			cn=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
            
			stmt=cn.createStatement();

        ResultSet rs=stmt.executeQuery("select cid from face");
        
		c1.removeAllItems();
        c1.addItem("Select Id");
        while(rs.next())
        {
           c1.addItem(rs.getString(1));
        }
        rs.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
        }
		head.setBounds(200,10,250,30);

		l1.setBounds(20,50,100,30);
		c1.setBounds(140,50,150,30);

		l2.setBounds(20,100,100,30);
		t2.setBounds(140,100,150,30);

		l3.setBounds(20,150,100,30);
		t3.setBounds(140,150,150,30);

		l4.setBounds(20,200,100,30);
		t4.setBounds(140,200,150,30);
		
		l5.setBounds(20,250,100,30);
		t5.setBounds(140,250,150,30);
		
		l6.setBounds(20,300,100,30);
		t6.setBounds(140,300,150,30);
		photo.setBounds(400,100,400,300);

		l7.setBounds(20,350,100,30);
		t7.setBounds(140,350,150,30);
		
		l8.setBounds(20,400,100,30);
		t8.setBounds(140,400,150,30);
		
		l9.setBounds(20,450,100,30);
		t9.setBounds(140,450,150,30);

		l10.setBounds(20,500,100,30);
		t10.setBounds(140,500,150,30);

		l11.setBounds(20,550,100,30);
		t11.setBounds(140,550,150,30);

		l12.setBounds(20,600,100,30);
		t12.setBounds(140,600,150,30);
		
		l2.setVisible(false);
		t2.setVisible(false);

		l3.setVisible(false);
		t3.setVisible(false);

		l4.setVisible(false);
		t4.setVisible(false);

		l5.setVisible(false);
		t5.setVisible(false);

		l6.setVisible(false);
		t6.setVisible(false);

		l7.setVisible(false);
		t7.setVisible(false);

		l8.setVisible(false);
		t8.setVisible(false);
		
		l9.setVisible(false);
		t9.setVisible(false);

		l10.setVisible(false);
		t10.setVisible(false);

		l11.setVisible(false);
		t11.setVisible(false);
		
		l12.setVisible(false);
		t12.setVisible(false);

		photo.setVisible(false);

		b1.setBounds(180,640,100,30);
		b2.setBounds(360,640,100,30);

		c.add(head);
		c.add(l1);c.add(c1);
		c.add(l2);c.add(t2);
		c.add(l3);c.add(t3);
		c.add(l4);c.add(t4);
		c.add(l5);c.add(t5);
		c.add(l6);c.add(t6);c.add(photo);
		c.add(l7);c.add(t7);
		c.add(l8);c.add(t8);
		c.add(l9);c.add(t9);
		c.add(l10);c.add(t10);
		c.add(l11);c.add(t11);
		c.add(l12);c.add(t12);
		
		c.add(b1);c.add(b2);
		c1.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		}
		public void actionPerformed(ActionEvent ae)
	{
			if(ae.getSource()==c1)
		{
		if(c1.getSelectedIndex()!=0)
        {
        try
        {

            String cid=c1.getSelectedItem().toString();
            ResultSet rs=stmt.executeQuery("select cid, fname, lname, aname, dob, age, gender, address, city, state, arresteddate, crimein, photo from face where cid="+cid);
            if(rs.next())
            {
				l2.setVisible(true);
				t2.setVisible(true);

				l3.setVisible(true);
				t3.setVisible(true);

				l4.setVisible(true);
				t4.setVisible(true);

				l5.setVisible(true);
				t5.setVisible(true);

				l6.setVisible(true);
				t6.setVisible(true);

				l7.setVisible(true);
				t7.setVisible(true);

				l8.setVisible(true);
				t8.setVisible(true);

				l9.setVisible(true);
				t9.setVisible(true);

				l10.setVisible(true);
				t10.setVisible(true);

				l11.setVisible(true);
				t11.setVisible(true);

				l12.setVisible(true);
				t12.setVisible(true);

				photo.setVisible(true);

                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
                t7.setText(rs.getString(7));
                t8.setText(rs.getString(8));
                t9.setText(rs.getString(9));
                t10.setText(rs.getString(10));
                t11.setText(rs.getString(11));
				t12.setText(rs.getString(12));
                String photofile=rs.getString(13);
                ImageIcon icophoto=new ImageIcon("Images\\"+photofile);
                photo.setIcon(icophoto);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
	if(ae.getSource()==b1)
		{
		c1.setSelectedItem("Select Id");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
		t9.setText("");
		t10.setText("");
		t11.setText("");
		t12.setText("");
		photo.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
	if(ae.getSource()==b2)
		{
		setVisible(false);
		dispose();
		}
	}
}
class ShowDetails
{
	public static void main(String ar[])
	{
		new Show1().show();
	}
}