package chatApplication;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class User1 extends Frame implements Runnable,ActionListener
{
    TextField textField;
    TextArea textArea;
    Button send;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    Thread chat;
    
    User1() 
    {
    	textField = new TextField();
    	textArea = new TextArea();
   		send = new Button("Send");
   		
   		send.addActionListener(this);
   		
   		try 
   		{
   			serverSocket = new ServerSocket(12000);
   			socket = serverSocket.accept();
   			input = new DataInputStream(socket.getInputStream());
   			output = new DataOutputStream(socket.getOutputStream());
   		}
   		catch(Exception e)
   		{
   			
   		}
   		
   		add(textField);
   		add(textArea);
   		add(send);
   		
   		chat = new Thread(this);
   		chat.setDaemon(true);
   		chat.start();
   		
   		setSize(500,500);
   		setTitle("User1");
   		setLayout(new FlowLayout());
   		setVisible(true);
   }
    
   public void actionPerformed(ActionEvent e)
   {
	   String msg = textField.getText();
	   textArea.append("User1:"+msg+"\n");
	   textField.setText("");
	   
	   try
	   {
		   output.writeUTF(msg);
		   output.flush();
	   }
	   catch(IOException ex)
	   {
		   
	   }
   }
   
   public static void main(String[] args)
   {
	   new User1();
   }
   
   public void run()
   {
	   while(true)
  		{
  			try
  			{
  				String msg = input.readUTF();
  				textArea.append("User1"+msg+"\n");
  			}
  			catch(Exception e)
  			{
  				
  			}
  		}
   }
}

