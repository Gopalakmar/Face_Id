
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.awt.image.*;

class ClipImageAnand extends JFrame implements ActionListener
{
  Connection cn;
  Statement stmt;

  int x1,x2,y1,y2;
  int w,h,pixels[];

  Image orgimg=null;
  Image clipimg=null;

  SourceImageCanvas sourceimgcan=new SourceImageCanvas();
  ClipImagePanel clipimgcan=new ClipImagePanel();

  JButton btnForeHead,btnEyes,btnNose,btnLips,btnChin,btnHair,btnSave,btnCancel,btnPhoto,btnClip;
  JButton clearHair,clearForeHead,clearEyes,clearNose,clearLips,clearChin,btnClear;
  JComboBox jcbCrimId;
  
  String imgfile="";
  boolean imgstatus=false;
  boolean clipstatus=false;

  JLabel lblHair,lblForeHead,lblEyes,lblNose,lblLips,lblChin,note;
  int htHair=0, htForeHead=0, htEyes=0, htNose=0, htLips=0, htChin=0;

  File f1;
  String selcrimid="";
  
  ClipImageAnand()
  {
	super("Clipping Face Screen");
	Container cp=getContentPane();
	cp.setLayout(null);
	setSize(800,700);
	
	java.awt.Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	java.awt.Rectangle frame=getBounds();
	this.setLocation((screen.width-frame.width)/2,(screen.height-frame.height)/2);

	clearHair=new JButton("Clear Hair");
	clearHair.setToolTipText("Clear Hair Clip");
	clearForeHead=new JButton("Clear Forehead");
	clearForeHead.setToolTipText("Clear Forehead Clip");
	clearEyes=new JButton("Clear Eyes");
	clearEyes.setToolTipText("Clear Eyes Clip");
	clearNose=new JButton("Clear Nose");
	clearNose.setToolTipText("Clear Nose Clip");
	clearLips=new JButton("Clear Lips");
	clearLips.setToolTipText("Clear Lips Clip");
	clearChin=new JButton("Clear Chin");
	clearChin.setToolTipText("Clear Chin Clip");
	
	btnForeHead=new JButton("Forehead ");
	btnEyes=new JButton("Eyes ");
	btnNose=new JButton("Nose");
	btnLips=new JButton("Lips ");
	btnChin=new JButton("Chin");
	btnHair=new JButton("Hair ");
	btnPhoto=new JButton("Photo");
	btnSave=new JButton("Save");
	btnCancel=new JButton("Cancel");
	btnClear=new JButton("Clear");

	jcbCrimId=new JComboBox();
   
	btnHair.setBounds(20,25,120,30);
	btnForeHead.setBounds(150,25,120,30);
	btnEyes.setBounds(280,25,120,30);
	btnNose.setBounds(410,25,120,30);
	btnLips.setBounds(540,25,120,30);
	btnChin.setBounds(670,25,120,30);
	
	clearHair.setBounds(20,580,120,30);
	clearForeHead.setBounds(150,580,120,30);
	clearEyes.setBounds(280,580,120,30);
	clearNose.setBounds(410,580,120,30);
	clearLips.setBounds(540,580,120,30);
	clearChin.setBounds(670,580,120,30);
	
	
	btnClear.setBounds(660,520,100,30);
	cp.add(clearForeHead);
	cp.add(clearEyes);
	cp.add(clearNose);
	cp.add(clearLips);
	cp.add(clearChin);
	cp.add(clearHair);


	btnSave.setBounds(400,520,100,30);
	cp.add(btnSave);

	btnCancel.setBounds(530,520,100,30);
	cp.add(btnCancel);	
	
	cp.add(btnClear);
	
	jcbCrimId.setBounds(20,520,120,30);
	cp.add(jcbCrimId);

	note=new JLabel("Note: Minimize the screen once");
	note.setFont(new Font("Times New Roman",Font.BOLD/Font.ITALIC,15));

	btnPhoto.setBounds(170,520,100,30);
	cp.add(btnPhoto);
	cp.add(btnForeHead);
	cp.add(btnEyes);
	cp.add(btnNose);
	cp.add(btnLips);
	cp.add(btnChin);
	cp.add(btnHair);
	
	note.setBounds(20,620,200,30);
	cp.add(note);
	sourceimgcan.setBounds(40,95,250,300);
	cp.add(sourceimgcan);
	
	lblHair=new JLabel("			              ");
	lblHair.setBounds(420,100,190,80);
	cp.add(lblHair);

	lblForeHead=new JLabel("                   ");
	lblForeHead.setBounds(420,160,190,80);
	cp.add(lblForeHead);

	lblEyes=new JLabel("                    ");
	lblEyes.setBounds(420,220,190,80);
	cp.add(lblEyes);

	lblNose=new JLabel("                    ");
	lblNose.setBounds(420,280,190,80);
	cp.add(lblNose);

	lblLips=new JLabel("                    ");
	lblLips.setBounds(420,340,190,80);
	cp.add(lblLips);

	lblChin=new JLabel("                    ");
	lblChin.setBounds(420,400,190,80);
	cp.add(lblChin);
    
	btnForeHead.addActionListener(this);   
    btnEyes.addActionListener(this);
    btnNose.addActionListener(this);
    btnLips.addActionListener(this);
    btnChin.addActionListener(this);
    btnHair.addActionListener(this);
	btnSave.addActionListener(this);
	btnCancel.addActionListener(this);
	btnPhoto.addActionListener(this);
	jcbCrimId.addItem("Select Criminal");

	btnClear.addActionListener(this);
	clearHair.addActionListener(this);
	clearForeHead.addActionListener(this);
	clearEyes.addActionListener(this);
	clearNose.addActionListener(this);
	clearLips.addActionListener(this);
	clearChin.addActionListener(this);
    
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Database d1=new Database();
		cn=DriverManager.getConnection("jdbc:odbc:face",d1.username,d1.password);
		stmt=cn.createStatement();
		ResultSet rs=stmt.executeQuery("select cid from face");
		while(rs.next())
		{
			jcbCrimId.addItem(rs.getString(1));
		}
	}catch(Exception e)
	{ 
		JOptionPane.showMessageDialog(this,e.getMessage(), "Clipping Image", JOptionPane.ERROR_MESSAGE);
	}

	}

	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==btnPhoto)
		{
			selcrimid=jcbCrimId.getSelectedItem().toString();
			if(selcrimid.equalsIgnoreCase("Select Criminal"))
				JOptionPane.showMessageDialog(this,"Select the ID of the Criminal to clip the Photo", "clipping Photograph", JOptionPane.ERROR_MESSAGE);
			else
			{
		   imgstatus=true;
		   try
		   {
		   ResultSet rs=stmt.executeQuery("select photo from face where CID="+Integer.parseInt(selcrimid));
		   if(rs.next())
		   {
			imgfile=rs.getString(1);
			JOptionPane.showMessageDialog(this,"Criminal Selected", "clipping Photograph", JOptionPane.INFORMATION_MESSAGE);
			repaint();
		   }
		   }catch(Exception e){}
		   }
        }
		
		if(evt.getSource()==btnHair)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htHair=h;
			 lblHair.setIcon(new ImageIcon(clipimg));
			 try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\hair\\hair"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
		 }	  
			
		if(evt.getSource()==btnEyes)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htEyes=h;
			 lblEyes.setIcon(new ImageIcon(clipimg));
			  try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\eyes\\eyes"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
         }
		 if(evt.getSource()==btnForeHead)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htForeHead=h;
			 lblForeHead.setIcon(new ImageIcon(clipimg));
			  try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\forehead\\forehead"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
         }
		 if(evt.getSource()==btnNose)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htNose=h;
			 lblNose.setIcon(new ImageIcon(clipimg));
			  try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\nose\\nose"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
         }
		 if(evt.getSource()==btnLips)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htLips=h;
			 lblLips.setIcon(new ImageIcon(clipimg));
			  try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\lips\\lips"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
         }
		 if(evt.getSource()==btnChin)
		{
			 setTitle(x1+":"+y1+":"+w+":"+h+":"+x2+":"+y2+":"+"Criminal Id"+":"+selcrimid);
			 clipimg=createImage(new MemoryImageSource(w,h,pixels,0,w));
			 htChin=h;
			 lblChin.setIcon(new ImageIcon(clipimg));
          try
			 {
				FileOutputStream fout=new FileOutputStream("Clips\\chin\\chin"+selcrimid+".gif");
				DataOutputStream dout=new DataOutputStream(fout);
				for(int i=0;i<pixels.length;i++)
				{
					dout.writeInt(pixels[i]);
				}
				dout.close();
				fout.close();
			 }catch(Exception e)
			 {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Criminal Details", JOptionPane.ERROR_MESSAGE);
			 }
		 }
		if(evt.getSource()==btnSave)
		{
			try
			{
			String str="insert into face_height(cid, hair, forehead, eyes, nose, lips, chin) values(" + selcrimid + "," + htHair + "," + htForeHead + "," + htEyes + "," + htNose + "," + htLips + "," + htChin + ")";
			stmt.executeUpdate(str);
			JOptionPane.showMessageDialog(this,"Clips Stored Successfully", "Clipping Images", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e){}
		}
	    if(evt.getSource()==btnCancel)
		{
		  setVisible(false);
		  dispose();
        }

		if(evt.getSource()==clearHair)
		{
			lblHair.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==clearForeHead)
		{
			lblForeHead.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==clearEyes)
		{
			lblEyes.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==clearNose)
		{
			lblNose.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==clearLips)
		{
			lblLips.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==clearChin)
		{
			lblChin.setIcon(new ImageIcon("Icons\\anand.bmp"));
		}
		if(evt.getSource()==btnClear)
		{
			lblHair.setIcon(new ImageIcon("Icons\\anand.bmp"));
			lblForeHead.setIcon(new ImageIcon("Icons\\anand.bmp"));
			lblEyes.setIcon(new ImageIcon("Icons\\anand.bmp"));
			lblNose.setIcon(new ImageIcon("Icons\\anand.bmp"));
			lblLips.setIcon(new ImageIcon("Icons\\anand.bmp"));
			lblChin.setIcon(new ImageIcon("Icons\\anand.bmp"));
			jcbCrimId.setSelectedItem("Select Criminal");
			setTitle("");
		}
	}
	class SourceImageCanvas extends Canvas implements MouseListener
	{
		Toolkit tkt;
		SourceImageCanvas()
		{
			tkt=Toolkit.getDefaultToolkit();
			addMouseListener(this);
        }
			public void paint(Graphics g)
		{
				if(imgstatus)
			{
					orgimg=tkt.getImage("Images\\"+imgfile);
					g.drawImage(orgimg,0,0,this);
            }
		}
		public void mousePressed(MouseEvent evt)
        {
			x1=evt.getX();
			y1=evt.getY();
		}
		public void mouseReleased(MouseEvent evt)
        {
			try
			{
				x2=evt.getX();
				y2=evt.getY();
				
				w=200;
				h=y2-y1;
				
				pixels=new int[w*h];
				PixelGrabber pg=new PixelGrabber(orgimg,x1,y1,w,h,pixels,0,w);
				pg.grabPixels();
				JOptionPane.showMessageDialog(this,"ClickedImage");
				clipstatus=true;
            }
			catch(Exception e){   }
		}
		public void mouseClicked(MouseEvent evt)
		{

		}
		public void mouseEntered(MouseEvent evt)
		{

		}
		public void mouseExited(MouseEvent evt)
		{

		}
	}
	class ClipImagePanel extends Canvas
    {
		public void paint(Graphics g)
		{
			if(clipstatus)
			{
				 g.drawImage(clipimg,0,0,this);
            }
		}
	}
}
class ClipImage
{
	public static void main(String args[])
	{
		ClipImageAnand ob=new ClipImageAnand();
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