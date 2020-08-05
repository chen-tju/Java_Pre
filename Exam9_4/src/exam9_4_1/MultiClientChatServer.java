package exam9_4_1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class MultiClientChatServer {
	public static final int SERVER_PORT=2008;
	public static void main(String[] args)
	{
		System.out.println("===========TCp多客户服务器端==========");
		try
		{
			//绑定到指定端口
			ServerSocket server = new ServerSocket(SERVER_PORT);
			System.out.println("正在监听端口"+server.getLocalPort()+"...");
			boolean listenning = true;
			while (listenning)
			{
				//等待客户连接
				Socket client = server.accept();
				System.out.println("有连接接入...端口"+client.getPort()+"...");
				//为该socket创建一个线程
				new ChatThread(client).start();
			}
			server.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
class ChatThread extends Thread
{
	private Socket client;
	public ChatThread(Socket socket)
	{
		client = socket;
	}
	public void run()
	{
		try
		{
			//socket输入流
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//socket输出流
			PrintWriter os = new PrintWriter(client.getOutputStream());
			//标准键盘输入
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			//一直循环
			while(true)
			{
				//读取来自客户端的聊天内容
				String s = is.readLine();
				if(s!=null)
				{
					//屏幕输出来自客户端的聊天内容
					System.out.println(client.getPort()+":"+s);
				}
				//如果输入的是shutdoown，则关闭服务器端
				if("shutdown".equals(s))
					break;
				//等待键盘输入回复
				System.out.print("server>");
				String line = stdin.readLine();
				//向客户端输出回复内容
				os.println(line);
				os.flush();
			}
			//关闭
			os.close();
			is.close();
			client.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}