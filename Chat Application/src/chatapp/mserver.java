/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;

/**
 *
 * @author conan
 */
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class mserver extends Thread{
    Socket soc;
    ServerSocket ss;
    DataInputStream din;
    DataOutputStream dout;
    chat_server ser;
    boolean active;
    String gname="";
    mserver(Socket soc,chat_server obj)
    {
        this.soc=soc;
        ser=obj;
        active=true;
    }
    public void run()
    {
        try{
            String msg="";
            din=new DataInputStream(soc.getInputStream());
            dout=new DataOutputStream(soc.getOutputStream());
            gname=din.readUTF();
            ser.cname.add(gname);
            ser.msg_area.setText(ser.msg_area.getText()+"\n"+gname+" has joined the Chatroom");
            while(!msg.equals("exit"))
            {
                    msg =din.readUTF();
                    ser.sms=ser.sms+"\n"+msg;
                    for(mserver ch : chat_server.v)
                    {
                        if(ch.active)
                        {
                            ch.dout.writeUTF(ser.sms) ;
                        }
                    }
            }
            soc.close();
            dout.close();
            din.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
            active=false;
            ser.msg_area.setText(ser.msg_area.getText()+"\n"+gname+" has left the Chatroom");
        }

}
}
