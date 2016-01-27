package ru.osu.teslenko.information_security.messenger.rsa_logic;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSA {
    private long p;
    private long q;
    private long n;
    private long y; // открытый ключ
    private long x; // закрытый ключ    
    private long fi;
    
    private static final RSA mInstance = new RSA();
    
    private RSA() {
        
    }
    
    public static RSA getInstance() {        
        return mInstance;
    }
    
    /** Шифрование сообщения */
    public byte[] encryption( String message ) throws UnsupportedEncodingException {        
        //p = Prime.getInstance().generation();
        //q = Prime.getInstance().generation();
        p = 7;
        q = 17;
        n = p * q;
        fi = ( p - 1 ) * ( q - 1 );
        y = findPublicKey( fi );
        x = findPrivateKey( y, fi );
        
        byte[] m = message.getBytes( "Unicode" );
        byte[] c = new byte[ m.length ];
        
        for ( int i = 0; i < m.length; i++ ) {
            long a1 = (long) modulPow( m[ i ], y, n );
            //long a2 = a1 % n;
            
            c[ i ] = (byte) ( a1 );
        }
        
        return c;
    }
    
    /** Дешифрация сообщения */
    public String decryption( byte[] c ) throws UnsupportedEncodingException {        
        //byte[] c = message.getBytes( "UTF-8" );
        byte[] m = new byte[ c.length ];
        
        for ( int i = 0; i < c.length; i++ ) {
            long a1 = (long) modulPow( c[ i ], x, n );
            //long a2 = a1 % n;
            
            m[ i ] = (byte) ( a1 );
        }
        
        return ( new String( m, "Unicode" ) );
    }
    
    public static void main( String[] argc ) {
        RSA rsa = new RSA();
        
        byte[] encryptionMessage = null;
        try {
            // А а Б б В в Г г Д д Е е Ё ё Ж ж З з И и Й й К к Л л М м Н н О о П п Р р С с Т т У у Ф ф Х х Ц ц Ч ч Ш ш Щ щ Ъ ъ Ы ы Ь ь Э э Ю ю Я я
            // qwertyuiop[] asdfghjkl;' zxcvbnm,./
            
            encryptionMessage = rsa.encryption( "hi" );
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String decryptionMessage = null;
        try {
            decryptionMessage = rsa.decryption( encryptionMessage );
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println( encryptionMessage );
        System.out.println( decryptionMessage );
        
//        String newMes;
//        try {
//            newMes = test( "Привет!!!" );
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    /** Поиск открытого ключа */
    private long findPublicKey( long fi ) {
        long a;
        long y = 1;
        
        do {
            y++; // т.к. y > 1
            a = gcd( y, fi );
        } while ( ( a != 1 ) && ( y <= fi ) );
        
        return y;
    }
    
    /** Поиск закрытого ключа */
    private long findPrivateKey( long y, long fi ) {
        long x = 0;
        long result;
        
        do {
            x++;
            result = ( x * y ) % fi;            
        } 
        while ( result != 1 );
        
        return x;
    }
    
    /** Нахождение НОД алгоритмом Евклида */
    private long gcd( long a, long b ) {
	if ( b == 0 ) {
            return a;
        }
        
	return gcd( b, a%b );
    }
    
    public void setPublicKey( long y ) {
        this.y = y;
    }
    
    /** Возвращаем открытый ключ */
    public long getPublicKey() {
        return y;
    }
    
    /** Возвращаем закрытый ключ */
    public long getPrivateKey() {
        return x;
    }
    
    
    /** Двоичное умножение чисел по модулю */
    private long mul( long a, long b, long m ) {
	if ( b == 1 ) {
            return a;
        }
	if ( b % 2 == 0 ) {
            long t = mul( a, b/2, m );
            return ( 2 * t ) % m;
	}
        
	return ( mul( a, b-1, m ) + a ) % m;
}

    /** Быстрое возведение в степень по модулю */
    private long pows( long a, long b, long m ) {
	if ( b == 0 ) {
            return 1;
        }
	if ( b % 2 == 0 ) {
            long t = pows( a, b/2, m );
            return mul( t, t, m ) % m;
	}
        
	return ( mul( pows( a, b-1, m ), a, m ) ) % m;
    }
    
    private long modulPow(byte value, long pow, long modul) {
        long result = value;
        
        for (int i = 0; i < pow - 1; i++) {
            result = (result * value) % modul;
        }
        
        return result;
    }
}
