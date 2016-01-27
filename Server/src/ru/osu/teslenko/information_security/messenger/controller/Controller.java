package ru.osu.teslenko.information_security.messenger.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.osu.teslenko.information_security.messenger.model.ClientModel;
import ru.osu.teslenko.information_security.messenger.model.ClientThreadModel;
import ru.osu.teslenko.information_security.messenger.model.Model;
import ru.osu.teslenko.information_security.messenger.view.MainView;

public class Controller {
    private MainView mMainView;
    
    private int mPort;
    private ServerSocket mServerSocket;
    
    private boolean mWorkingServer;
    
    public Controller() {
        mMainView = new MainView( this );
    }
    
    /** Открываем порт на сервере */
    public boolean startServer( int port ) {
        mWorkingServer = true;
        
        try {
            mServerSocket = new ServerSocket( port );
        } catch (IOException ex) {
            Logger.getLogger( Model.class.getName() ).log( Level.SEVERE, null, ex );
            return false;
        }
        
        Thread threadListening = new Thread( new Runnable() {
            @Override
            public void run() {
                while( true ) {
                    try {
                        if ( !mWorkingServer ) {
                            break;
                        }
                        
                        // ожидаем клиента
                        Socket clientSocket = mServerSocket.accept();
                        
                        // запускаем клиента в отдельном потоке
                        ClientThreadModel clientThread = new ClientThreadModel( clientSocket );
                        clientThread.setMainView( mMainView );
                        clientThread.start();
                        
                    } catch ( IOException ex ) {
                        Logger.getLogger( Model.class.getName() ).log( Level.SEVERE, null, ex );
                        return;
                    }
                }
            }
        } );
        threadListening.start();
        
        return true;
    }
    
    /** Закрываем порт на сервере */
    public boolean stopServer() {
        if ( mWorkingServer ) {
            mWorkingServer = false;

            try {
                mServerSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        else {
            return false;
        }
        
        return true;
    }
}
