/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author conan
 */
package chatapp;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.* ;

class ClientHandler extends Thread {

	private Socket s1,s2;
	private int id ;

	public DataInputStream in1;
	public DataOutputStream out1;

	public boolean isOnline;

	ClientHandler(Socket s1,int id){

		this.s1 = s1;
		this.id = id ;
		
		try{
			this.out1 = new DataOutputStream(s1.getOutputStream());
			this.in1 = new DataInputStream(s1.getInputStream());
			this.isOnline=true ;
		}

		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void run(){

		//System.out.println("Run Method Called");
		try
		{
			out1.writeUTF(Integer.toString(this.id)) ;
		}
		catch(Exception e)
		{

		}

		String message;
		while(true){

			try{

				message = in1.readUTF();
				StringTokenizer st = new StringTokenizer(message, "#"); 
                                String MsgToSend = st.nextToken(); 
                                String recipient = st.nextToken(); 
                                int recId = Integer.parseInt(recipient) ;
                             /*   for(ClientHandler ch : chat_server.v)
                                {
                                if(ch.id == recId && ch.isOnline )
                                {
                                    ch.out1.writeUTF("client "+this.id+": "+MsgToSend) ;
                                }
                }
                            */
				//System.out.println(message);


			}

			catch(Exception e){
				
				System.out.println("Client "+this.id+" Disconnected");
				this.isOnline = false ;
				break;
				//this.stop();
			}
		}
	
	}
}