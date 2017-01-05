package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.omg.CORBA.Object;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by venik on 06.01.17.
 */
public class ApplicationContext {
    private final static @NotNull Logger log = LogManager.getLogger(ApplicationContext.class);

    private static volatile @NotNull ApplicationContext instance = null;

    public static ApplicationContext getInstanse(){
        if(instance ==null)
            synchronized (ApplicationContext.class) {
                if (instance == null)
                    instance = new ApplicationContext();
            }
        return instance;
    }

    @NotNull
    private final Map<Class, Object> contexMap = new ConcurrentHashMap<>();

    public void put(Class clazz, Object object){
        contexMap.put(clazz,object);
    }

    public <T>T get(Class<T> clazz){
        return (T)contexMap.get(clazz);
    }

    private ApplicationContext() {
        log.info(ApplicationContext.class.getName() + " initialized");
    }
}