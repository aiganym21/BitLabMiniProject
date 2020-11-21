import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server{
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(1998);
			int id = 0;
			while (true){
				Socket socket = serverSocket.accept();
				id++;
				System.out.println("Client with id: " + id + " connected");
				ClientHandler clientHandler = new ClientHandler(socket, id);
				clientHandler.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}