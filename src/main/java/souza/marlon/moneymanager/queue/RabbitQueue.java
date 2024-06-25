package souza.marlon.moneymanager.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface RabbitQueue<T> {

    void receiveMessage(T message);

    String name();
}
