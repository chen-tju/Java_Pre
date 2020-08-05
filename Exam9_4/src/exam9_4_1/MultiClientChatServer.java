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
		System.out.println("===========TCp��ͻ���������==========");
		try
		{
			//�󶨵�ָ���˿�
			ServerSocket server = new ServerSocket(SERVER_PORT);
			System.out.println("���ڼ����˿�"+server.getLocalPort()+"...");
			boolean listenning = true;
			while (listenning)
			{
				//�ȴ��ͻ�����
				Socket client = server.accept();
				System.out.println("�����ӽ���...�˿�"+client.getPort()+"...");
				//Ϊ��socket����һ���߳�
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
			//socket������
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//socket�����
			PrintWriter os = new PrintWriter(client.getOutputStream());
			//��׼��������
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			//һֱѭ��
			while(true)
			{
				//��ȡ���Կͻ��˵���������
				String s = is.readLine();
				if(s!=null)
				{
					//��Ļ������Կͻ��˵���������
					System.out.println(client.getPort()+":"+s);
				}
				//����������shutdoown����رշ�������
				if("shutdown".equals(s))
					break;
				//�ȴ���������ظ�
				System.out.print("server>");
				String line = stdin.readLine();
				//��ͻ�������ظ�����
				os.println(line);
				os.flush();
			}
			//�ر�
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