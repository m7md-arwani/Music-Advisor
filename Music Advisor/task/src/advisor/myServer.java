package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class myServer {
    private  HttpServer server;

    public  void creatServer() {
       try {
            server = HttpServer.create();
           server.bind(new InetSocketAddress(8080), 0);
           server.createContext("/",new httpHandler() ); //a handler to be put here.
       } catch(IOException ex ) {ex.printStackTrace();}
    }

    public void start(){
        this.server.start();

    }

    public void stop() {
        this.server.stop(1);
    }


}
