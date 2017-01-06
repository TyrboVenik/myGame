package main;

import messagesystem.Abonent;
import messagesystem.Adress;

/**
 * Created by venik on 06.01.17.
 */
public abstract class Service extends Thread implements Abonent{
    private final Adress adress;

    public Service(String name){
        super(name);
        this.adress= new Adress(name);
    }

    @Override
    public Adress getAdress(){
        return adress;
    }

    @Override
    public String toString(){
        return adress.toString();
    }

    public Class<? extends Service> getServiceClass(){
        return this.getClass();
    }
}
