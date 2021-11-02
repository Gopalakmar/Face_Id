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
import javax.swing.JOptionPane.*;

class Update extends JFrame implements ActionListener
{
	String imgfile="";
	boolean imgstatus=false;
	String msg[]={"Male","Female"};
	int i;
	String photofile="";

	ImageIcon ico;
	Image img;
	File f1=null;
	
	JLabel head=new JLabel("Update Criminal Form");
	JLabel photo=new JLabel("		Photo	");

	JLabel l1=new JLabel("Criminal Id");
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
	
	JComboBox idc=new JComboBox();
	JComboBox c=new JComboBox();
	JComboBox da=new JComboBox();
	JComboBox mo=new JComboBox();
	JComboBox yr=new JComboBox();

	JButton b2=new JButton("Update");
	JButton b3=new JButton("Exit");
	JButton b4=new JButton("Replace photo");
	JButton b5=new JButton("Clear");
	int criminalid=1;

	Connection con;
	Statement stmt;

	Update()
	{
		super("Update Criminal Form");
		try
		{
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Database d1=new Database();
		con=DriverManager.getConnection("jdbc:odbc:face","system","tiger");
		stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select cid from face");
        
		idc.removeAllItems();
        idc.addItem("Select Id");
        while(rs.next())
        {
           idc.addItem(rs.getString(1));
        }
        rs.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
        }
			
			Container cp=getContentPane();
			cp.setLayout(null);
			setSize(700,650);
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

			l1.setBounds(20,50,100,30);
			idc.setBounds(120,50,155,30);
	
			l2.setBounds(20,90,100,30);
			t2.setBounds(120,90,155,30);

			l3.setBounds(20,130,100,30);
			t3.setBounds(120,130,155,30);

			l4.setBounds(20,170,100,30);
			t4.setBounds(120,170,155,30);

			l14.setBounds(20,210,100,30);
			da.setBounds(120,210,45,30);
			mo.setBounds(170,210,50,30);
			yr.setBounds(225,210,45,30);

			l5.setBounds(20,250,100,30);
			t5.setBounds(120,250,155,30);

			l6.setBounds(20,290,100,30);
			c.setBounds(120,290,155,30);

			l7.setBounds(20,330,100,30);
			t7.setBounds(120,330,155,30);

			l8.setBounds(20,370,100,30);
			t8.setBounds(120,370,155,30);

			l9.setBounds(20,410,100,30);
			t9.setBounds(120,410,155,30);

			l11.setBounds(20,450,100,30);
			t11.setBounds(120,450,155,30);
	
			l13.setBounds(20,490,100,30);
			t6.setBounds(120,490,155,30);
			b4.setBounds(285,490,120,30);
			
			cp.add(l1);cp.add(idc);
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

			b2.setBounds(40,550,85,30);
			b3.setBounds(250,550,85,30);
			b5.setBounds(460,550,85,30);
			cp.add(b2);cp.add(b3);cp.add(b5);

			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			idc.addActionListener(this);

			b4.setEnabled(false);
			
			
	}
		public void actionPerformed(ActionEvent ae)
		{
			
			if(ae.getSource()==b2)
			{
				 try
	{
            int cid=Integer.parseInt(idc.getSelectedItem().toString());
            String fname=t2.getText();
            String lname=t3.getText();
            String aname=t4.getText();
			int dob=Integer.parseInt(da.getSelectedItem().toString());
            String mob=mo.getSelectedItem().toString();
            int yob=Integer.parseInt(yr.getSelectedItem().toString());
            
            int age=Integer.parseInt(t5.getText());
			String gender=c.getSelectedItem().toString();
            String address=t7.getText();
            String city=t8.getText();
            String state=t9.getText();
            
            String crime=t11.getText();
            String str="Update face set fname='" + fname + "',lname='" + lname + "',aname='" + aname + "',dob=to_date('"+ dob + "-" + mob + "-" + yob + "','DD-Month-YYYY'), age= " + age + ",gender='" + gender + "',address='" + address + "',city='" + city + "',state='" + state + "',crimein='" + crime + "',photo='" + photofile + "' where cid=" + cid;

            int res=stmt.executeUpdate(str);

			if(res==1)
                JOptionPane.showMessageDialog(this, "Record Updated Successfully");
        }
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(this,e.getMessage(), "Updating the Criminal Details", JOptionPane.ERROR_MESSAGE);
	}
    }
		
	else if(ae.getSource()==b5)
	{
		idc.setSelectedItem("Select Id");
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
else if(ae.getSource()==b4)
	{
		JFileChooser jfc=new JFileChooser("select a file");
		jfc.showOpenDialog(this);
		f1=jfc.getSelectedFile();
        photofile=f1.getName();
		ImageIcon ico=new ImageIcon("Images\\"+photofile);
		photo.setIcon(ico);
       
	}
else if(ae.getSource()==b3)
{
	setVisible(false);
	dispose();
}
if(ae.getSource()==idc)
{
	if(idc.getSelectedIndex()!=0)
        {
        try
        {
            String cid=idc.getSelectedItem().toString();
            ResultSet rs=stmt.executeQuery("select cid, fname, lname, aname, to_char(dob,'DD'), to_char(dob,'MM'), to_char(dob,'YYYY'), age, gender, address, city, state, arresteddate, crimein, photo from face where cid="+cid);
            if(rs.next())
            {
				b4.setEnabled(true);
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
       
				int date=Integer.parseInt(rs.getString(5));
                int mont=Integer.parseInt(rs.getString(6));
                int yer=Integer.parseInt(rs.getString(7));
                int yrind=yer-1970+1;
                
				da.setSelectedIndex(date);
                mo.setSelectedIndex(mont);
                yr.setSelectedIndex(yrind);
                
				t5.setText(rs.getString(8));
                c.setSelectedItem(rs.getString(9));
                t7.setText(rs.getString(10));
                t8.setText(rs.getString(11));
                t9.setText(rs.getString(12));
				
                t11.setText(rs.getString(14));
				String pr=rs.getString(15);
				t6.setText(pr);
                photofile=pr;
                
				ImageIcon icophoto=new ImageIcon("Images\\"+photofile);
                photo.setIcon(icophoto);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
}
	
}


class UpdateDetails
{
public static void main(String ar[])
{
	Update ob=new Update();
	ob.show();
	ob.addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
      });
		
	}
}
