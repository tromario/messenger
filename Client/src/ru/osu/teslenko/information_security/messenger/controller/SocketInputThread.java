package ru.osu.teslenko.information_security.messenger.controller;

import ru.osu.teslenko.information_security.messenger.service.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.osu.teslenko.information_security.messenger.des_logic.CipherHelper;
import ru.osu.teslenko.information_security.messenger.rsa_logic.RSA;
import ru.osu.teslenko.information_security.messenger.view.MainView;

public class SocketInputThread extends Thread {
    private Socket mClientSocket;
    private BufferedReader mInputStream;
    private MainView mMainView;
    
    private RSA rsa;
    
    public SocketInputThread( Socket clientSocket ) {
        mClientSocket = clientSocket;
    }
    
    public void setMainView( MainView mainView ) {
        mMainView = mainView;
    }
    
    @Override
    public void run() {
        int query = 0;
        
        try {
            mInputStream = new BufferedReader( new InputStreamReader( mClientSocket.getInputStream(), "UTF-8" ) );
        } catch ( IOException ex ) {
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        boolean waitingRequest = true;
        while ( waitingRequest ) {
            try {
                query = Integer.parseInt( mInputStream.readLine() );
                //System.out.println( "Клиент получил " + query );
            } catch (IOException ex) {
                Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            switch ( query ) {
                case Service.TAKE_ONLINE_CLIENTS: 
                    takeOnlineClients();
                    break;
                case Service.DISCONNECT_CLIENT:
                    waitingRequest = false;
                    disconnectClient();
                    break;
//                case Service.GET_PUBLIC_KEY:
//                    getPublicKey();
//                    break;
                case Service.GET_MESSAGE:
                    getMessage();
                    break;
            }
        }
    }
    
    public void takeOnlineClients() {
        String onlineClients = "";
        
        try {
            onlineClients = mInputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mMainView.showOnlineClients( onlineClients );
    }

    private void disconnectClient() {
        try {
            mInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            mClientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //System.out.println( "Клиент завершил работу." );
    }
    
    private void getMessage() {
        String nameClient = null;        
        try {
            nameClient = mInputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String secretKey = null;
        try {
            secretKey = mInputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String message = null;
        try {
            message = mInputStream.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String decryptedMessage = null;
        try {
            decryptedMessage = CipherHelper.decipher( secretKey, message );
        } catch (Exception ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mMainView.updateListModelHistory( "Сообщение от " + nameClient + ": " /* + message + " | " */ + decryptedMessage );
    }
    
//    private void getPublicKey() {
//        String publicKey = null;
//        try {
//            publicKey = mInputStream.readLine();
//        } catch (IOException ex) {
//            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        RSA.getInstance().setPublicKey( Long.parseLong( publicKey ) );
//    }
}
