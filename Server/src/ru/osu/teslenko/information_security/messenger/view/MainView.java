package ru.osu.teslenko.information_security.messenger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import ru.osu.teslenko.information_security.messenger.controller.Controller;

public class MainView extends JFrame {
    private Controller mController;    
    private int mPort;    
    private DefaultListModel mListModelHistory;
    
    //<editor-fold desc="Описание компонентов GUI">
    private JPanel mPanelSettings;
    
    private JLabel mLabelPort;
    private JTextField mTextFieldPort;
    
    private JLabel mLabelHistory;
    private JList mListHistory;
    private JScrollPane mScrollPaneHistory;
    
    private JButton mButtonOpenPort;
    private JButton mButtonClosePort;
    //</editor-fold>
    
    public MainView( Controller controller ) {
        super( "Сервер" );
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
            java.util.logging.Logger.getLogger( MainView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( MainView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( MainView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( MainView.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }
        //</editor-fold>
        
        //<editor-fold desc="Создание компонентов GUI">
        mPanelSettings = new JPanel();
        mPanelSettings.setBorder( javax.swing.BorderFactory.createTitledBorder( "Параметры сервера" ) );
        
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
        
        mButtonOpenPort = new JButton( "Открыть" );
        mButtonOpenPort.setEnabled( true );
        mButtonOpenPort.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPortAction();
            }
        } );
        
        mButtonClosePort = new JButton( "Закрыть" );
        mButtonClosePort.setEnabled( false );
        mButtonClosePort.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closePortAction();
            }
        } );
        
        mLabelHistory = new JLabel( "История:" );
        mListHistory = new JList();
        mScrollPaneHistory = new JScrollPane();
        
        mListModelHistory = new DefaultListModel();
        mListHistory.setModel( mListModelHistory );
        //</editor-fold>
        
        //<editor-fold desc="Строка меню">
        JMenuBar menuBar = new JMenuBar();

        JMenu referenceMenuItem = new JMenu( "Справка" );
        JMenuItem aboutProgramMenuItem = new JMenuItem( "О программе" );
        aboutProgramMenuItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutProgramMenuItemAction();
            }
        } );
        referenceMenuItem.add( aboutProgramMenuItem );
        menuBar.add( referenceMenuItem );
        
        JMenuItem exitApplicationMenuItem = new JMenuItem( "Завершить приложение" );
        exitApplicationMenuItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplicationMenuItemAction();
            }
        } );
        menuBar.add( exitApplicationMenuItem );
        
        this.setJMenuBar( menuBar );
        //</editor-fold>
        
        //<editor-fold desc="Выравнивание компонентов GUI">
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mPanelSettings);
        mPanelSettings.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mLabelPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mButtonOpenPort, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mButtonClosePort, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLabelPort)
                    .addComponent(mTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mButtonOpenPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mButtonClosePort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        mScrollPaneHistory.setViewportView(mListHistory);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mLabelHistory)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(mScrollPaneHistory)
                    .addComponent(mPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mPanelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mLabelHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mScrollPaneHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        //</editor-fold>
        
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }
    
    /** Событие на кнопку "Открыть" */
    protected void openPortAction() {
        mPort = Integer.parseInt( mTextFieldPort.getText() );
        
        if ( mPort > 65535 ) {
            JOptionPane.showMessageDialog(
                this, 
                "Номер порта превышает допустимый диапазон.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE
            );
            
            return;
        }
        
        if ( mController.startServer( mPort ) ) {
            mListModelHistory.addElement( "Порт " + mPort + " открыт." );
            
            mButtonOpenPort.setEnabled( false );
            mButtonClosePort.setEnabled( true );
        }
        else {
            mListModelHistory.addElement( "Порт " + mPort + " открыть не удалось." );
        }
    }
    
    /** Событие на кнопку "Закрыть" */
    protected void closePortAction() {
        if ( mController.stopServer() ) {
            mListModelHistory.addElement( "Порт " + mPort + " закрыт." );
            
            mButtonOpenPort.setEnabled( true );
            mButtonClosePort.setEnabled( false );
        }
        else {
            mListModelHistory.addElement( "Порт " + mPort + " не открыт." );
        }
    }
    
    public void updateListModelHistory( String message ) {
        mListModelHistory.addElement( message );
    }
    
    /** Окно о программе */
    protected void aboutProgramMenuItemAction() {
        AboutProgramView aboutProgramView = new AboutProgramView( this, true );
    }
    
    /** Завершение приложения */
    protected void exitApplicationMenuItemAction() {
        closePortAction();
        
        System.exit( EXIT_ON_CLOSE );
    }
}
