package ru.osu.teslenko.information_security.messenger.controller;

import ru.osu.teslenko.information_security.messenger.service.Service;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.osu.teslenko.information_security.messenger.des_logic.CipherHelper;
import ru.osu.teslenko.information_security.messenger.view.ConnectionServerView;
import ru.osu.teslenko.information_security.messenger.view.MainView;

public class Controller {
    private ConnectionServerView mConnectionServerView;
    private MainView mMainView;
    
    private Socket mClientSocket;    
    private String mIPaddress;
    private int mPort;
    private String mName;
    
    private SocketInputThread mSocketInputThread;
    private SocketOutputThread mSocketOutputThread;
    
    public Controller() {
        mConnectionServerView = new ConnectionServerView( this );
        mConnectionServerView.setVisible( true );
        
        mMainView = new MainView( this );
        mMainView.setVisible( false );
    }
    
    public void setVisibleConnectionServerView( boolean value ) {
        mConnectionServerView.setVisible( value );
    }
    
    public void setVisibleMainView( boolean value ) {
        mMainView.setVisible( value );
    }

    /** Подключение клиента к серверу */
    public boolean connectServer( String ipAddress, int port ) {
        mIPaddress = ipAddress;
        mPort = port;
        
        try {
            mClientSocket = new Socket( mIPaddress, mPort );            
        } catch ( IOException ex ) {
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, null, ex );
            return false;
        }
        
        mSocketInputThread = new SocketInputThread( mClientSocket );
        mSocketInputThread.setMainView( mMainView );
        mSocketInputThread.start();
        
        mSocketOutputThread = new SocketOutputThread( mClientSocket );
        
        return true;
    }
    
    public void disconnectServer() {
        // отправляем имя клиента на сервер
        mSocketOutputThread.send( Service.DISCONNECT_CLIENT );
    }
    
    /** Аутентификация клиента на сервере */
    public boolean authenticationClient( String name ) {
        mName = name;
        
        mSocketOutputThread.send( Service.SEND_NAME );
        mSocketOutputThread.send( mName );
        
        // сделать проверку аутентификации
        
        return true;
    }
    
    /**  */
    public void hello() {
        mMainView.updateListModelHistory( mName + ", приятного общения!" );
    }
    
    /** Отправка сообщения */
    public void sendMessage( int recipient, String message ) {
//        mSocketOutputThread.send( Service.GET_PUBLIC_KEY );
//        mSocketOutputThread.send( recipient ); // адресат
        
        
        mSocketOutputThread.send( Service.GET_MESSAGE );
        mSocketOutputThread.send( recipient ); // адресат
        
        // генерация секретного ключа
        Random random = new Random();
        String secretKey = String.valueOf( random.nextInt( 99999999 ) + 10000000 );
        
        String encryptedMessage = null;
        try {
            encryptedMessage = CipherHelper.cipher( secretKey, message );
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mSocketOutputThread.send( secretKey ); // ключ
        
        mSocketOutputThread.send( encryptedMessage ); // сообщение
    }
}
