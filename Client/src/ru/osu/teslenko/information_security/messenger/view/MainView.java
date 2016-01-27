package ru.osu.teslenko.information_security.messenger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;
import ru.osu.teslenko.information_security.messenger.controller.Controller;

public class MainView extends JFrame {
    private Controller mController;
    
    private DefaultListModel mListModelOnlineClients;
    private DefaultListModel mListModelHistory;
    
    //<editor-fold desc="Описание компонентов GUI">
    private JLabel mLabelHistory;
    private JScrollPane mScrollPaneHistory;
    private JList mListHistory;    
    
    private JLabel mLabelOnline;
    private JScrollPane mScrollPaneOnline;
    private JList mListOnline;    
    
    private JLabel mLabelMessage;
    private JScrollPane mScrollPaneMessage;
    private JTextArea mTextAreaMessage;    
    
    private JButton mButtonSend;
    //</editor-fold>
    
    public MainView( Controller controller ) {
        super( "Клиентское приложение" );
        mController = controller;
        
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
        mLabelHistory = new JLabel( "История сообщений:" );
        mListHistory = new JList();
        mScrollPaneHistory = new JScrollPane();
        mScrollPaneHistory.setViewportView( mListHistory );
        
        mListModelHistory = new DefaultListModel();
        mListHistory.setModel( mListModelHistory );
        
        mLabelOnline = new JLabel( "Онлайн:" );        
        mListOnline = new JList();
        mScrollPaneOnline = new JScrollPane();
        mScrollPaneOnline.setViewportView( mListOnline );
        
        mLabelMessage = new JLabel( "Сообщение:" );        
        mTextAreaMessage = new JTextArea();
        mTextAreaMessage.setColumns( 20 );
        mTextAreaMessage.setRows( 5 );
        mScrollPaneMessage = new JScrollPane();
        mScrollPaneMessage.setViewportView( mTextAreaMessage );
        mTextAreaMessage.addKeyListener( new KeyListener() {

            @Override
            public void keyTyped( KeyEvent e ) {
                
            }

            @Override
            public void keyPressed( KeyEvent e ) {
                
            }
                
            @Override
            public void keyReleased( KeyEvent e ) {
                if ( e.getKeyCode() == 10 ) {
                    buttonSendAction();
                }
            }
        } );
        
        mButtonSend = new JButton( "Отправить" );
        mButtonSend.setToolTipText( "Enter" );
        mButtonSend.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSendAction();
            }
        } );
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windowClosingAction();
            }
        });
        //</editor-fold>
        
        //<editor-fold desc="Выравнивание компонентов GUI">      
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mScrollPaneHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mLabelHistory)
                                    .addComponent(mLabelMessage))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(mScrollPaneMessage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mLabelOnline)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(mScrollPaneOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mButtonSend)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLabelHistory)
                    .addComponent(mLabelOnline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mScrollPaneHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mLabelMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mScrollPaneMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mScrollPaneOnline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mButtonSend)
                .addContainerGap())
        );
        //</editor-fold>
        
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        this.pack();
        this.setLocationRelativeTo( null );
    }
    
    /** Завершение работы с программой */
    protected void windowClosingAction() {
        mController.disconnectServer();
    }
    
    /** Обновить список онлайн-клиентов */
    public void showOnlineClients( String onlineClients ) {
        mListModelOnlineClients = new DefaultListModel();
        mListOnline.setModel( mListModelOnlineClients );
        
        String[] arrayOnlineClients = onlineClients.split(" ");
        for ( int indexClient = 0; indexClient < arrayOnlineClients.length; indexClient++ ) {
            mListModelOnlineClients.addElement( arrayOnlineClients[ indexClient ] );
        }
    }
    
    /** Добавить новую запись в список истории */
    public void updateListModelHistory( String message ) {
        mListModelHistory.addElement( message );
    }
    
    private void sendMessage() {
        int recipient = mListOnline.getSelectedIndex(); // кому
        
        if ( recipient != -1 ) {
            String recipientName = mListOnline.getSelectedValue().toString(); // имя кому
            
            String message = mTextAreaMessage.getText().replace("\n", ""); // текст сообщения
            if ( message.trim().length() != 0 ) {
                mListModelHistory.addElement( "Сообщение для " + recipientName + ": " + message );
                mController.sendMessage( recipient, message );
                
                mTextAreaMessage.setText( "" );
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Укажите не пустое сообщение.", 
                    "Предупреждение", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                mTextAreaMessage.setText( "" );
            }
        } else {
            JOptionPane.showMessageDialog(
                this, 
                "Укажите получателя сообщения.", 
                "Предупреждение", 
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /** Отправляем сообщение */
    public void buttonSendAction() {
        sendMessage();
    }
        
    /** Окно о программе */
    protected void aboutProgramMenuItemAction() {
        AboutProgramView aboutProgramView = new AboutProgramView( this, true );
    }
    
    /** Завершение приложения */
    protected void exitApplicationMenuItemAction() {
        mController.disconnectServer();
        
        System.exit( EXIT_ON_CLOSE );
    }
}
