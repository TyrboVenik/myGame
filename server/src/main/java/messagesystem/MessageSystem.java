package messagesystem;

import main.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by venik on 06.01.17.
 */
public class MessageSystem {
    private final static Logger log = LogManager.getLogger(MessageSystem.class);

    private final Map<Class, Service> services = new ConcurrentHashMap<>();

    private final Map<Adress,Queue<Message>> messages = new ConcurrentHashMap<>();

    public void registerService(Service service){
        services.putIfAbsent(service.getServiceClass(),service);
        messages.putIfAbsent(service.getAdress(),new ConcurrentLinkedDeque<>());
        log.info("Service {} registered", service.getAdress());
    }

    public <T>T getService(@NotNull Class<T> clazz){
        return (T)services.get(clazz);
    }

    public void sendMessage(Message message){
        messages.get(message.getTo()).add(message);
    }
}
