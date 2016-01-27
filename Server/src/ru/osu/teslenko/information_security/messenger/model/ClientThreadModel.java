package ru.osu.teslenko.information_security.messenger.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.osu.teslenko.information_security.messenger.service.Service;
import ru.osu.teslenko.information_security.messenger.view.MainView;

public class ClientThreadModel extends Thread {
    private MainView mMainView;
    
    private ClientModel mClientModel;
    
    private Socket mClientSocket;
    
    private BufferedReader mInputStream;
    private PrintWriter mOutputStream;
    
    // имя клиента в чате
    private String mName;
    
    public ClientThreadModel( Socket clientSocket ) {
        mClientSocket = clientSocket;
        
        try {
            mInputStream = new BufferedReader( new InputStreamReader( mClientSocket.getInputStream(), "UTF-8" ) );
        } catch ( IOException ex ) {
            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
        }
        try {
            mOutputStream = new PrintWriter( new BufferedWriter( new OutputStreamWriter( mClientSocket.getOutputStream(), "UTF-8" ) ), true );
        } catch ( IOException ex ) {
            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    public void setMainView( MainView mainView ) {
        mMainView = mainView;
    }
        
    @Override
    public void run() {
        boolean waitingRequest = true;
        while ( waitingRequest ) {
            int query = 0;
            
            try {
                query = Integer.parseInt( mInputStream.readLine() );
            } catch (IOException ex) {
                Logger.getLogger(ClientThreadModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            switch ( query ) {
                case Service.CONNECT_CLIENT:
                    connectClient();
                    break;
                case Service.DISCONNECT_CLIENT:
                    waitingRequest = false;
                    disconnectClient();
                    break;
//                case Service.GET_PUBLIC_KEY:
//                    getPublicKey();
//                    break;
                case Service.SEND_MESSAGE:
                    getAndSendMessage();
                    break;
            }
        }
    }
    
    /** Подключение клиента к серверу */
    public void connectClient() {
        // получаем имя клиента
        try {
            mName = mInputStream.readLine();
        } catch ( IOException ex ) {
            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        // создаем модель клиента и добавляем его в общий список
        mClientModel = new ClientModel( mClientSocket, mInputStream, mOutputStream, mName );
        ListClientModel.addClientModel( mClientModel );
        
        mMainView.updateListModelHistory( mName + " подключен." );
        //System.out.println( mName + " подключен." );
        
        // уведомляем клиентов о подключении нового клиента
        for ( int indexClient = 0; indexClient < ListClientModel.getListClientModel().size(); indexClient++ ) {
            PrintWriter outputStream = ListClientModel.getListClientModel().get( indexClient ).getOutputStream();
            outputStream.println( Service.UPDATE_ALL_CLIENT );
            sendOnlineClients( indexClient );
        }
    }
    
    /** Отключение клиента от сервера */
    public void disconnectClient() {
        mOutputStream.println( Service.DISCONNECT_CLIENT );
        
        try {
            mClientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ListClientModel.removeClientModel( mClientModel );
        mMainView.updateListModelHistory( mName + " отключен." );
        //System.out.println( "Клиент отключен." );
        
        // уведомляем клиентов об отключении клиента
        for ( int indexClient = 0; indexClient < ListClientModel.getListClientModel().size(); indexClient++ ) {
            PrintWriter outputStream = ListClientModel.getListClientModel().get( indexClient ).getOutputStream();
            outputStream.println( Service.UPDATE_ALL_CLIENT );
            sendOnlineClients( indexClient );
        }
    }
    
    /** Отправляем клиенту список имен всех клиентов */
    public void sendOnlineClients( int activeIndexClient ) {
        String onlineClients = "";
        for ( int indexClient = 0; indexClient < ListClientModel.getListClientModel().size(); indexClient++ ) {
            onlineClients += ListClientModel.getListClientModel().get( indexClient ).getName() + " ";
        }
        
        PrintWriter outputStream = ListClientModel.getListClientModel().get( activeIndexClient ).getOutputStream();
        outputStream.println( onlineClients );
    }
    
//    public void getPublicKey() {
//        int recipient = 0; // кому
//        try {
//            recipient = Integer.parseInt( mInputStream.readLine() );
//        } catch ( IOException ex ) {
//            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
//        }
//        
//        PrintWriter outputStream = ListClientModel.getListClientModel().get( recipient ).getOutputStream();
//        outputStream.println( Service.GET_PUBLIC_KEY );
//    }
    
    /** Прием сообщения от отправителя и отправка получателю */
    public void getAndSendMessage() {        
        int recipient = 0; // кому
        try {
            recipient = Integer.parseInt( mInputStream.readLine() );
        } catch ( IOException ex ) {
            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        String secretKey = null;
        try {
            secretKey = mInputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ClientThreadModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String message = null;
        try {
            message = mInputStream.readLine();
        } catch ( IOException ex ) {
            Logger.getLogger( ClientThreadModel.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        PrintWriter outputStream = ListClientModel.getListClientModel().get( recipient ).getOutputStream();
        outputStream.println( Service.SEND_MESSAGE );
        outputStream.println( mName );
        outputStream.println( secretKey );
        outputStream.println( message );
    }
}
