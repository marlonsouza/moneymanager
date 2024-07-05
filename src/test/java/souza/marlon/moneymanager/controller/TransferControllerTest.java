package souza.marlon.moneymanager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.TransferDto;
import souza.marlon.moneymanager.service.TransactionService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransferControllerTest {

  @InjectMocks
  private TransferController transferController;

  @Mock
  private TransactionService transactionService;

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Test
  @DisplayName("Should create transaction typo TRANSFER and user balance update should be called")
  void testShouldTransferTransactionBeCalledSuccess() {

    var transferDto = new TransferDto("transfer test", UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(33.33));
    var transactionResponse = transferController.send(transferDto);

    verify(transactionService).transfer(any(TransactionDto.class));
    verify(rabbitTemplate).convertAndSend(anyString(), any(TransferDto.class));
    assertEquals("Transaction completed successfully.", transactionResponse.message());
  }
}