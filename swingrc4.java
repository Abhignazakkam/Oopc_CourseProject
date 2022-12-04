import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class swingrc4 extends JFrame
{
  JTextField jt1,jt2,jt3;
  JButton jb1;
  JLabel jl1,jl2,jl3;
  
  swingrc4()
  {
   super("RC4 Alogorithm");
   jl1=new JLabel("RC4 ENCRYPTION ALGORITHM");
   jl1.setBounds(150,30,800,100); 
   jl1.setFont(new Font("Times New Roman",Font.PLAIN,30));
   jl1.setForeground(Color.yellow);
   this.add(jl1);
   
   jl2=new JLabel("ENTER PALIN TEXT : ");
   jl2.setBounds(50,100,350,100); 
   jl2.setFont(new Font("Times New Roman",Font.PLAIN,20));
   jl2.setForeground(Color.pink);
   this.add(jl2); 
    
   
   jt1=new JTextField(20); 
   jt1.setBounds(300,120,350,50); 
   this.add(jt1);

   jl3=new JLabel("ENTER KEY : ");
   jl3.setBounds(50,220,350,100); 
   jl3.setFont(new Font("Times New Roman",Font.PLAIN,20));
   jl3.setForeground(Color.pink);
   this.add(jl3); 
    
   
   jt2=new JTextField(20); 
   jt2.setBounds(300,250,350,50); 
   this.add(jt2);
   
   jt3=new JTextField(""); 
   jt3.setBounds(150,500,400,100); 
   this.add(jt3);
   
   
   jb1=new JButton("Encrypt"); 
   jb1.setBounds(250,400,200,50);
   jb1.setBackground(Color.green); 
   this.add(jb1);

    

   this.getContentPane().setBackground(Color.gray);
   this.setSize(800,800); 
   this.setLayout(null);
   this.setVisible(true); 
   
   jb1.addActionListener(new ActionListener(){   
   public void actionPerformed(ActionEvent ae)
   {
   try{
   String pltext= jt1.getText();
   String key=jt2.getText();
   if(pltext.equals("")||key.equals(""))
   {
   	System.out.println("enter plain text and key");
   	jl2.setText("Please enter plain text and key");
   }
   
   String tempt="";
    int temp=0; 
	int s[]=new int[256]; 
	int T[]=new int[256];
    char pltextch[]=pltext.toCharArray(); 
	char keych[]=key.toCharArray(); 
	int cipher[]=new int[pltext.length()]; 
	int decrypt[]=new int[pltext.length()]; 
	int pltexti[]=new int[pltext.length()]; 
	int keyi[]=new int[key.length()]; 
	for(int i=0;i<pltext.length();i++) 
	{ 
		pltexti[i]=(int)pltextch[i]; 
	} 
	for(int i=0;i<key.length();i++) 
	{ 
		keyi[i]=(int)keych[i]; 
	} 
	for(int i=0;i<255;i++) 
	{ 
		s[i]=i; T[i]=keyi[i%key.length()]; 
	} 
	int j=0; 
	for(int i=0;i<255;i++) 
	{ 
		j=(j+s[i]+T[i])%256; 
		temp=s[i]; 
		s[i]=s[j]; 
		s[j]=temp; 
	} 
	int i=0; 
	j=0; 
	int keystream=0; 
	for(int l=0;l<pltext.length();l++) 
	{ 
		i=(l+1)%256; 
		j=(j+s[i])%256; 
		temp=s[i]; 
		s[i]=s[j]; 
		s[j]=temp; 
		keystream=s[(s[i]+s[j])%256]; 
		cipher[l]=keystream^pltexti[l]; 
		decrypt[l]=keystream^cipher[l]; 
	} 
	for(i=0;i<pltext.length();i++){
	tempt=tempt+Character.toString((char)cipher[i]);
     }
 
    jt3.setText(tempt);
   }


   catch(Exception e)
   {
   System.out.println(e.getMessage());
   }
     
   }
   }); 
  }
	public static void main(String args[])throws Exception
	{
		swingrc4 obj=new swingrc4();
		
	}
}