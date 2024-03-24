import java.io.*;
import java.net.Socket;

import java.text.SimpleDateFormat;
import java.util.Date;

class ClientSomthing{

    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputUser; // поток чтения с консоли
    private String addr; // ip адрес клиента
    private int port; // порт соединения
    private String nickname; // имя клиента
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    private File file; //файл для передачи

    public ClientSomthing(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.pressNickname();

            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            ClientSomthing.this.downService();
        }
    }


    private void pressNickname() throws IOException {
        System.out.print("Press your nick: ");
        nickname = inputUser.readLine();
        out.write("Hello " + nickname + "\n");
        out.flush();
    }

    private void downService() {
        if (!socket.isClosed()) {
            try {
                out.write("Goodbye " + nickname + "\n");
                out.flush();
                out.write("/stop");
                out.flush();
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                end:
                while (true) {
                    str = in.readLine();
                    String option = "dflt";

                    if (str.length() > 5) {
                        option = str.substring(0, 5);
                    }

                    switch (option){
                        case ("/stop"):
                            ClientSomthing.this.downService();
                            break end;
                        case ("/file"):
                            System.out.println("you have received the file");
                            str = str.substring(5);
                            File accepted_file = new File("accepted_file.txt");
                            accepted_file.createNewFile();
                            PrintWriter pw = new PrintWriter(accepted_file);
                            pw.print(str);
                            pw.close();
                            break;
                        default:
                            System.out.println(str);
                            break;
                    }
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {
            end:
            while (true) {
                String userWord;
                try {
                    userWord = inputUser.readLine();
                    switch (userWord){
                        case ("/stop"):
                            ClientSomthing.this.downService();
                            break end;
                        case ("/file"):
                            out.write(userWord);
                            System.out.print("enter the path to the file\n");
                            String fileName = inputUser.readLine();

                            file = new File(fileName);
                            byte[] b = new byte[(int) file.length()];
                            FileInputStream fileInputStream = new FileInputStream(file);
                            fileInputStream.read(b);
                            for (int i = 0; i < b.length; i++) {
                                out.write((char)b[i]);
                            }
                            out.write("\n");
                            break;
                        default:
                            time = new Date();
                            dt1 = new SimpleDateFormat("HH:mm:ss");
                            dtime = dt1.format(time);
                            out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n"); // отправляем на сервер
                            break;
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientSomthing.this.downService();
                }
            }
        }
    }
}

public class Client {

    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientSomthing(ipAddr, port);
    }
}