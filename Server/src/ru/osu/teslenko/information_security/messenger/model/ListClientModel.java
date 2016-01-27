package ru.osu.teslenko.information_security.messenger.model;

import java.util.ArrayList;

public class ListClientModel {
    private static ArrayList< ClientModel > listClientModel = new ArrayList< ClientModel >();
    
    public synchronized static ArrayList< ClientModel > getListClientModel() {
        return listClientModel;
    }
    
    public synchronized static void addClientModel( ClientModel clientModel ) {
        listClientModel.add( clientModel );
    }
    
    public synchronized static void removeClientModel( ClientModel clientModel ) {
        listClientModel.remove( clientModel );
    }
}
