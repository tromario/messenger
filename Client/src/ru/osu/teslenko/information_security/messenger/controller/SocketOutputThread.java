package ru.osu.teslenko.information_security.messenger.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketOutputThread {
    private Socket mClientSocket;
    private PrintWriter mOutputStream;
    
    public SocketOutputThread( Socket clientSocket ) {
        mClientSocket = clientSocket;
        
        try {
            mOutputStream = new PrintWriter( new BufferedWriter( new OutputStreamWriter( mClientSocket.getOutputStream(), "UTF-8" ) ), true );
        } catch ( IOException ex ) {
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    public void send( String query ) {
        mOutputStream.println( query );
    }
    
    public void send( int query ) {
        mOutputStream.println( query );
    }
}
