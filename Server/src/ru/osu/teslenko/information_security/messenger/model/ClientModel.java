package ru.osu.teslenko.information_security.messenger.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientModel {
    private Socket mClientSocket;
    
    private BufferedReader mInputStream;
    private PrintWriter mOutputStream;
    
    private String mName;
    
    public ClientModel( Socket clientSocket, BufferedReader inputStream, PrintWriter outputStream, String name ) {
        mClientSocket = clientSocket;
        mInputStream = inputStream;
        mOutputStream = outputStream;
        mName = name;
    }
    
    public Socket getClientSocket() {
        return mClientSocket;
    }
    
    public BufferedReader getInputStream() {
        return mInputStream;
    }
    
    public void setInputStream( BufferedReader inputStream ) {
        mInputStream = inputStream;
    }
    
    public PrintWriter getOutputStream() {
        return mOutputStream;
    }
    
    public void setOutputStream( PrintWriter outputStream ) {
        mOutputStream = outputStream;
    }
    
    public String getName() {
        return mName;
    }
    
    public void setName( String name ) {
        mName = name;
    }
}
