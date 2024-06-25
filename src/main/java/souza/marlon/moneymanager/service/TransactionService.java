package souza.marlon.moneymanager.service;

import souza.marlon.moneymanager.dto.TransactionDto;

public interface TransactionService {
    void transfer(TransactionDto dto);
}
