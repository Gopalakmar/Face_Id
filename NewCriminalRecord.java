import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.JComboBox.*;
import java.lang.String.*;
import java.awt.image.*;
import javax.imageio.*;

class New extends JFrame implements ActionListener
{
	String imgfile="";
	boolean imgstatus=false;
	String msg[]={"Male","Female"};
	int i;

	ImageIcon ico;
	Image img;
	File f1=null;
	
	JLabel head=new JLabel("New Criminal Form");
	JLabel photo=new JLabel("		Photo	");

	JLabel l1=new JLabel("Enter Criminal Id");
	JLabel l2=new JLabel("First Name");
	JLabel l3=new JLabel("Last Name");
	JLabel l4=new JLabel("Alias Name");
	JLabel l14=new JLabel("D.O.B");
	JLabel l5=new JLabel("Age");
	JLabel l6=new JLabel("Gender");
	JLabel l7=new JLabel("Address");
	JLabel l8=new JLabel("City");
	JLabel l9=new JLabel("State");
	JLabel l10=new JLabel("Arrested Date");
	JLabel l11=new JLabel("Crime Involved In");
	JLabel l13=new JLabel("Image Path");

	JTextField t1=new JTextField(20);
	JTextField t2=new JTextField(20);
	JTextField t3=new JTextField(20);
	JTextField t4=new JTextField(20);
	JTextField t5=new JTextField(20);
	JTextField t6=new JTextField(20);
	JTextField t7=new JTextField(20);
	JTextField t8=new JTextField(20);
	JTextField t9=new JTextField(20);
	JTextField t10=new JTextField(20);
	JTextField t11=new JTextField(20);

	JComboBox c=new JComboBox();
	JComboBox da=new JComboBox();
	JComboBox mo=new JComboBox();
	JComboBox yr=new JComboBox();

	JButton b2=new JButton("Ok");
	JButton b3=new JButton("Exit");
	JButton b4=new JButton(".......");
	JButton b5=new JButton("Clear");
	int criminalid=0;

	Connection con;
	Statement stmt;

	New()
	{
		super("New Criminal Record");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Database d1=new Database();
			con=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
            
			stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select max(cid) from face");
			if(rs.next())
			{
             String crimid=rs.getString(1);
             if(crimid!=null)
			criminalid=Integer.parseInt(crimid);
                }
                criminalid++;		
                }catch(Exception e)
		{
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
		}
			
			Container cp=getContentPane();
			cp.setLayout(null);
			setSize(650,700);
			setResizable(false);

			java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			java.awt.Rectangle frame=getBounds();
			this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);


			head.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,25));
			head.setForeground(Color.blue);
			head.setBounds(200,10,250,30);
			cp.add(head);

			photo.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,20));
			
			photo.setBounds(400,85,200,260);
			photo.setBorder(BorderFactory.createTitledBorder(""));
			cp.add(photo);

			c.addItem("Select");
			for(int i=0;i<2;i++)
				c.addItem(msg[i]);

			da.addItem("dd");        
		    for(int i=1;i<=31;i++)
		    da.addItem(i+"");	  
    				
			mo.addItem("mm");
			String month[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
		    for(int i=0;i<12;i++)
			mo.addItem(month[i]);	  
			
			yr.addItem("yy");
			for(int i=1970;i<=2020;i++)
			yr.addItem(i+"");	  

			t1.setText(criminalid+"");
			t1.setEditable(false);
			
			t2.requestFocus();
			l1.setBounds(20,50,100,30);
			t1.setBounds(120,50,215,30);
	
			l2.setBounds(20,90,100,30);
			t2.setBounds(120,90,215,30);

			l3.setBounds(20,130,100,30);
			t3.setBounds(120,130,215,30);

			l4.setBounds(20,170,100,30);
			t4.setBounds(120,170,215,30);

			l14.setBounds(20,210,100,30);
			da.setBounds(120,210,55,30);
			mo.setBounds(185,210,80,30);
			yr.setBounds(275,210,60,30);

			l5.setBounds(20,250,100,30);
			t5.setBounds(120,250,215,30);

			l6.setBounds(20,290,100,30);
			c.setBounds(120,290,215,30);

			l7.setBounds(20,330,100,30);
			t7.setBounds(120,330,215,30);

			l8.setBounds(20,370,100,30);
			t8.setBounds(120,370,215,30);

			l9.setBounds(20,410,100,30);
			t9.setBounds(120,410,215,30);

			l10.setBounds(20,450,100,30);
			t10.setBounds(120,450,215,30);

			l11.setBounds(20,490,100,30);
			t11.setBounds(120,490,215,30);
	
			l13.setBounds(20,530,100,30);
			t6.setBounds(120,530,215,30);
			b4.setBounds(340,530,80,30);
			
			cp.add(l1);cp.add(t1);
			cp.add(l2);cp.add(t2);
			cp.add(l3);cp.add(t3);
			cp.add(l4);cp.add(t4);
			cp.add(l14);cp.add(da);cp.add(mo);cp.add(yr);
			cp.add(l5);cp.add(t5);
			cp.add(l6);cp.add(c);
			cp.add(l7);cp.add(t7);
			cp.add(l8);cp.add(t8);
			cp.add(l9);cp.add(t9);
			cp.add(l10);cp.add(t10);
			cp.add(l11);cp.add(t11);
			cp.add(l13);cp.add(t6);cp.add(b4);

			b2.setBounds(40,610,85,30);
			b3.setBounds(200,610,85,30);
			b5.setBounds(360,610,85,30);
			cp.add(b2);cp.add(b3);cp.add(b5);

			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			
	}
		public void actionPerformed(ActionEvent ae)
		{
			JButton b=(JButton)ae.getSource();
			if(b==b2)
			{
				try
				{
			int  cid=Integer.parseInt(t1.getText());
			String firstname=t2.getText();
			String lastname=t3.getText();
			String aliasname=t4.getText();
			String dob=da.getSelectedItem().toString();
			int mob=mo.getSelectedIndex();
			String yob=yr.getSelectedItem().toString();
			String age=t5.getText();
			String gender=c.getSelectedItem().toString();
			String address=t7.getText();
			String city=t8.getText();
			String state=t9.getText();
			String arresteddate=t10.getText();
			String crimeinvolved=t11.getText();
			String photo=t6.getText();

			String str="insert into face(cid,fname,lname,aname,dob,age,gender,address,city,state,arresteddate,crimein,photo) values(" + cid + ",'" + firstname + "','" + lastname+ "','" + aliasname + "',to_date('" + dob + "-" + mob + "-" + yob + "','DD-MM-YYYY')," + age + ",'" + gender + "','" + address + "','" + city + "','" + state + "','" + arresteddate + "','" + crimeinvolved + "','" + photo + "')";
            int res=stmt.executeUpdate(str);
			if(res==1)
			JOptionPane.showMessageDialog(this,"Record Inserted");
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,e.getMessage(),"New Criminal", JOptionPane.ERROR_MESSAGE);
			}
				
	}
		
	else if(b==b5)
	{

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
		photo.setIcon(new ImageIcon("Icons\\anand.bmp"));
		c.setSelectedItem("Select");
		da.setSelectedItem("dd");
		mo.setSelectedItem("mo");
		yr.setSelectedItem("yy");
	}
else if(b==b4)
	{
		JFileChooser jfc=new JFileChooser("select a file");
		jfc.showOpenDialog(this);
		imgstatus=true;
		f1=jfc.getSelectedFile();
		t6.setText(f1.getName());
		imgfile=f1.getPath();
		ico=new ImageIcon(imgfile);
		photo.setIcon(ico);
       
	}
else if(b==b3)
{
	setVisible(false);
	dispose();
}
}
	class MyPanel extends Canvas
	{
		Toolkit tkt;
		MyPanel()
		{
			tkt=Toolkit.getDefaultToolkit();
		}
		public void paint(Graphics g)
		{
			if(imgstatus)
			{
				Image img=tkt.getImage(imgfile);
				g.drawImage(img,0,0,this);	
			}
		}
	}
}


class NewCriminalRecord
{
public static void main(String ar[])
{
	New ob=new New();
	ob.show();
}
}
