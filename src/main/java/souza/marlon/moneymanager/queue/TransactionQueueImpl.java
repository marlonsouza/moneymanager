package souza.marlon.moneymanager.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import souza.marlon.moneymanager.configuration.RabbitMQConfig;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.service.UserBalanceService;

import static souza.marlon.moneymanager.configuration.RabbitMQConfig.ROUNTING_KEY;

@Component
public class TransactionQueueImpl implements RabbitQueue<TransactionDto> {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionQueueImpl.class);

    public static final String TRANSACTION_ENTRY = "transactionEntry";

    private final UserBalanceService userBalanceService;

    @Autowired
    public TransactionQueueImpl(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @Override
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = TRANSACTION_ENTRY, durable = "true" ),
        exchange = @Exchange(value = RabbitMQConfig.MONEYMANAGER_EXCHANGE, type = "topic"),
        key = ROUNTING_KEY
    ))
    public void receiveMessage(TransactionDto transactionDto) {
        LOG.info("Transfer from : {} to : {}", transactionDto.payer(), transactionDto.payee());
        userBalanceService.updateBalance(transactionDto);
    }

    @Override
    public String name() {
        return TRANSACTION_ENTRY;
    }
}
