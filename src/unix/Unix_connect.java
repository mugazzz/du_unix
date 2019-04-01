package unix;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Unix_connect {
	
	 /**
	 * @param arg
	 */
	public static void main(String[] arg){
		    
		    try{
		      JSch jsch=new JSch();

		      //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

		      Session session=jsch.getSession("root", "10.4.3.127", 22);
		      
		      session.setPassword("Oj,om34");
		      
		      session.setConfig("StrictHostKeyChecking", "no");
		      session.setConfig("PreferredAuthentications", 
	                  "publickey,keyboard-interactive,password");
		      session.connect(3000);   // making a connection with timeout.
		      
		      Channel channel=session.openChannel("shell");
		      
		      // Enable agent-forwarding.
		      //((ChannelShell)channel).setAgentForwarding(true);
		      //channel.setInputStream(System.in);
		      //channel.setOutputStream(System.out);
		    
		      OutputStream ops = channel.getOutputStream();
		      PrintStream ps = new PrintStream(ops, true);
		      channel.connect();
		      InputStream input = channel.getInputStream();
		      
		      //shell commands using println statement
		      
		      ps.println("ls -lr");
		      Thread.sleep(3000);
		      ps.println("pwd");
		      Thread.sleep(3000);
		      ps.println("scp root@10.4.3.127:/root/testing.txt 172.16.155.121:\\C:\\Users\\mugazp\\Desktop");
		      Thread.sleep(3000);
//		      ps.println("ls -lr");
//		      Thread.sleep(3000);
//		      ps.println("touch testing.txt");
//		      Thread.sleep(3000);
//		      ps.println("ls -lr");
//		      Thread.sleep(3000);
//		      ps.println("cp testing.txt /root");
//		      Thread.sleep(3000);
//		      ps.println("cd ..");
//		      Thread.sleep(3000);
//		      ps.println("pwd");
//		      Thread.sleep(3000);
//		      ps.println("ls -lr");
		      
		      
		      printResult(input, channel);
		    }
		    catch(Exception e){
		      System.out.println(e);
		    }
		  }

		  private static void printResult(InputStream input, Channel channel) throws IOException {
		// TODO Auto-generated method stub
			  int SIZE = 1024;
		        byte[] tmp = new byte[SIZE];
		        while (true) {
		            while (input.available() > 0) {
		                int i = input.read(tmp, 0, SIZE);
		                if (i < 0)
		                    break;
		                System.out.print(new String(tmp, 0, i));
		            }
		            if (channel.isClosed()) {
		                System.out.println("exit-status: " + channel.getExitStatus());
		                break;
		            }
		            try {
		                Thread.sleep(300);
		            } catch (Exception ee) {
		            }
		        }
		  }
		
  }