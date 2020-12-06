package Main;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/ws")
public class HandleMessage {
    private Map<String, String> usernames = new HashMap<String, String>();
    static int i = 0;

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
      session.getBasicRemote().sendText("(Server): Welcome to the chat room. Please state your username to begin.");
    }

    @OnClose
    public void close(Session session) throws IOException, EncodeException {

    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
      PrintWriter pWriter = null;
      try {
        pWriter = new PrintWriter(new BufferedWriter(new FileWriter(i+"auftrag.txt")));
        pWriter.println(message);
      } catch (IOException ioe) {
        ioe.printStackTrace();
      } finally {
        if (pWriter != null){
          pWriter.flush();
          pWriter.close();
        }
      }
    }
}
