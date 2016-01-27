package ru.osu.teslenko.information_security.messenger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutProgramView extends JDialog {
    
    //<editor-fold desc="Описание компонентов GUI">   
    private JLabel mLabelNameProject;
    private JLabel mLabelVersion;
    private JLabel mLabelAuthor;
    
    private JButton mButtonClose;
    //</editor-fold>
    
    public AboutProgramView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("О программе");
        
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
        mLabelNameProject = new JLabel("<html>Прикладная программа \"Кроссплатформенная<br> программа защищенного обмена сообщениями<br> на основе криптографического алгоритма<br> DES и RSA\"</html>");
        mLabelVersion = new JLabel("Версия: 1.0.0.0, 2016г.");
        mLabelAuthor = new JLabel("<html>Авторы проекта:<br>Тишина Н.А. - руководитель,<br>Тесленко Р.С. - разработчик</html>");
        
        mButtonClose = new JButton("Закрыть");
        mButtonClose.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAction();
            }
        } );
        //</editor-fold>
        
        //<editor-fold desc="Выравнивание компонентов GUI">
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mLabelNameProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mLabelVersion)
                    .addComponent(mLabelAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mButtonClose)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mLabelNameProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mLabelVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mLabelAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mButtonClose)
                .addContainerGap())
        );
        //</editor-fold>
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }
    
    /** Закрытие окна */
    protected void closeAction() {
        this.dispose();
    }
}
