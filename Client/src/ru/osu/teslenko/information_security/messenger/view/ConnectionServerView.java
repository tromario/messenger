package ru.osu.teslenko.information_security.messenger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ru.osu.teslenko.information_security.messenger.controller.Controller;

public class ConnectionServerView extends JFrame {
    private Controller mController;
    
    private String mIPaddress;
    private int mPort;
    private String mName;
    
    //<editor-fold desc="Описание компонентов GUI">
    private JPanel mPanelSettings;
    
    private JLabel mLabelIPaddress;
    private JLabel mLabelPort;
    private JLabel mLabelYourName;
    
    private JTextField mTextFieldIPaddress;
    private JTextField mTextFieldPort;
    private JTextField mTextFieldYourName;
    
    private JButton mButtonConnectServer;
    private JButton mButtonExit;
    //</editor-fold>
    
    public ConnectionServerView( Controller controller ) {
        super( "Подключение к серверу" );
        mController = controller;
        
        //<editor-fold defaultstate="collapsed" desc="Look and feel setting">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ( "GTK+".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( ConnectionServerView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( ConnectionServerView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( ConnectionServerView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( ConnectionServerView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        
        //<editor-fold desc="Создание компонентов GUI">
        mPanelSettings = new JPanel();
        mPanelSettings.setBorder( javax.swing.BorderFactory.createTitledBorder( "Настройки подключения с сервером" ) );
        
        mLabelIPaddress = new JLabel( "IP-адрес:" );
        
        mTextFieldIPaddress = new JTextField( "127.0.0.1" );
        mTextFieldIPaddress.addKeyListener( new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                
                if ( !Character.isDigit( keyChar ) && ( keyChar != '.' ) && ( e.getModifiers() != KeyEvent.CTRL_MASK ) ) {
                    e.consume();
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if ( ( e.getModifiers() == KeyEvent.CTRL_MASK ) || ( e.getModifiers() == KeyEvent.SHIFT_MASK ) ) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        } );
        
        mLabelPort = new JLabel( "Порт:" );
        
        mTextFieldPort = new JTextField( "4040" );
        mTextFieldPort.addKeyListener( new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                
                if ( !Character.isDigit( keyChar ) && ( e.getModifiers() != KeyEvent.CTRL_MASK ) ) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ( ( e.getModifiers() == KeyEvent.CTRL_MASK ) || ( e.getModifiers() == KeyEvent.SHIFT_MASK ) ) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        } );
        
        mLabelYourName = new JLabel( "Ваше имя:" );
        mTextFieldYourName = new JTextField( "Аноним" );
        
        mButtonConnectServer = new JButton( "Подключиться" );
        mButtonConnectServer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectServerAction();
            }
        } );
        
        mButtonExit = new JButton( "Выход" );
        mButtonExit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        } );
        //</editor-fold>
        
        //<editor-fold desc="Выравнивание компонентов GUI">        
        javax.swing.GroupLayout mPanelSettingsLayout = new javax.swing.GroupLayout(mPanelSettings);
        mPanelSettings.setLayout(mPanelSettingsLayout);
        mPanelSettingsLayout.setHorizontalGroup(
            mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mPanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mLabelIPaddress)
                    .addComponent(mLabelYourName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mPanelSettingsLayout.createSequentialGroup()
                        .addComponent(mTextFieldIPaddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mLabelPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mTextFieldPort))
                    .addComponent(mTextFieldYourName))
                .addContainerGap())
        );
        mPanelSettingsLayout.setVerticalGroup(
            mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mPanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLabelIPaddress)
                    .addComponent(mTextFieldIPaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mLabelPort)
                    .addComponent(mTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLabelYourName)
                    .addComponent(mTextFieldYourName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(mButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mButtonConnectServer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {mButtonConnectServer, mButtonExit});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mPanelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mButtonConnectServer)
                    .addComponent(mButtonExit))
                .addContainerGap())
        );
        //</editor-fold>
        
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        this.pack();
        this.setLocationRelativeTo( null );
    }
    
    /** Событие на кнопку "Подключение" */
    protected void connectServerAction() {
        mIPaddress = mTextFieldIPaddress.getText();
        mPort = Integer.parseInt( mTextFieldPort.getText() );
        mName = mTextFieldYourName.getText();
        
        if ( mPort > 65535 ) {
            JOptionPane.showMessageDialog(
                this, 
                "Номер порта превышает допустимый диапазон.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE
            );            
            return;
        }
        
        if ( mName.trim().length() == 0 ) {
            JOptionPane.showMessageDialog(
                this, 
                "Задано некорректное имя.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE
            );            
            return;
        }
        
        if ( mController.connectServer( mIPaddress, mPort ) ) {
            //System.out.println( "Клиент подключен к серверу." );
        }
        else {
            //System.out.println( "Ошибка подключения к серверу." );
            JOptionPane.showMessageDialog(
                this, 
                "Произошла ошибка при подключении к серверу.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if ( mController.authenticationClient( mName ) ) {
            //System.out.println( "Имя отправлено на сервер." );
        }
        else {
            //System.out.println( "Ошибка отправки имени на сервер." );
            JOptionPane.showMessageDialog(
                this, 
                "Произошла ошибка при аутентификации.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
                
        mController.setVisibleConnectionServerView( false );
        mController.setVisibleMainView( true );
        mController.hello();
    }
    
    /** Закрытие приложения */
    protected void exitApplication() {
        System.exit( EXIT_ON_CLOSE );
    }
}
