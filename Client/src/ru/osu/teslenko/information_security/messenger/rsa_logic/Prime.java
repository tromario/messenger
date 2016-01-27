package ru.osu.teslenko.information_security.messenger.rsa_logic;

import java.util.Random;

public class Prime {
    private static final Prime mInstance = new Prime();
    
    private Prime() {
        
    }
    
    public static Prime getInstance() {
        return mInstance;
    }
    
    public long generation() {
        long number;
        Random random = new Random( );
        
        while ( true ) {
            number = (long) ( random.nextInt( 1000 ) + 100 );
            if ( testBruteForce( number ) ) {
                break;
            }
        }
        
        return number;
    }
    
    /** Алгоритм полного перебора */
    public boolean testBruteForce( long number ) {
        for ( long i = 2; i < number; i++ ) {
            if ( number % i == 0 ) {
                return Number.COMPOSITE;
            }
        }
        
        return Number.PRIME;
    }
}
