package ru.osu.teslenko.information_security.messenger.des_logic;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CipherHelper {
    private final static String ALGORITHM = "DES";
    //private String secretKey = "01234567";
    
    public static String cipher( String secretKey, String data ) throws Exception {
        if ( secretKey == null || secretKey.length() != 8 )
            throw new Exception( "Invalid key length - 8 bytes key needed!" );
        
        SecretKey key = new SecretKeySpec( secretKey.getBytes(), ALGORITHM );
        Cipher cipher = Cipher.getInstance( ALGORITHM );
        cipher.init( Cipher.ENCRYPT_MODE, key );
        
        return toHex( cipher.doFinal( data.getBytes() ) );
    }
    
    public static String decipher( String secretKey, String data ) throws Exception {
        if ( secretKey == null || secretKey.length() != 8 )
            throw new Exception( "Invalid key length - 8 bytes key needed!" );
        
        SecretKey key = new SecretKeySpec( secretKey.getBytes(), ALGORITHM );
        Cipher cipher = Cipher.getInstance( ALGORITHM );
        cipher.init( Cipher.DECRYPT_MODE, key );
        
        return new String( cipher.doFinal( toByte( data ) ) );
    }
    
    private static byte[] toByte( String hexString ) {
        int len = hexString.length() / 2;

        byte[] result = new byte[ len ];

        for ( int i = 0; i < len; i++ )
            result[ i ] = Integer.valueOf( hexString.substring( 2 * i, 2 * i + 2 ), 16 ).byteValue();
        return result;
    }
    
    public static String toHex( byte[] stringBytes ) {
        StringBuffer result = new StringBuffer( 2 * stringBytes.length );

        for ( int i = 0; i < stringBytes.length; i++ ) {
            result.append( HEX.charAt( ( stringBytes[ i ] >> 4 ) & 0x0f ) ).append( HEX.charAt( stringBytes[ i ] & 0x0f ) );
        }

        return result.toString();
    }
    
    private final static String HEX = "0123456789ABCDEF";
    
    public static void main(String[] args) {
        try {
            String secretKey = "01234567";
            String data = "Привет мир!";
            
            String encryptedData = cipher( secretKey, data );
            System.out.println( "Зашифрованное сообщение: " + encryptedData );
            
            String decryptedData = decipher( secretKey, encryptedData );
            System.out.println( "Расшифрованное сообщение: " + decryptedData );
            
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}