import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类的总体介绍
 *
 * @author gaodong
 * @program okhttp
 * @description 同步阻塞的多线程池server
 * @create 2021-01-22 20:35
 * @since 1.8
 **/
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        //创建ServerSocket,j
        final ServerSocket serverSocket = new ServerSocket(8801);
        while(true){
            try{
                //这里其实也是阻塞的
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void  service(Socket socket){
        try{
            Thread.sleep(5);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println();
            printWriter.println(body);
            printWriter.close();
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
