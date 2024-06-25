package souza.marlon.moneymanager.controller;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.TransactionResponse;
import souza.marlon.moneymanager.service.TransactionService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransactionService transactionService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TransferController(TransactionService transactionService, RabbitTemplate rabbitTemplate) {
        this.transactionService = transactionService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public TransactionResponse send(@RequestBody @Valid TransactionDto dto){
        transactionService.transfer(dto);
        rabbitTemplate.convertAndSend("transactionEntry", dto);
        return new TransactionResponse("Transaction completed successfully.");
    }

}
