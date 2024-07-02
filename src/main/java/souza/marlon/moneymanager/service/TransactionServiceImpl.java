package souza.marlon.moneymanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.exception.BadRequestException;
import souza.marlon.moneymanager.converter.TransactionConverterImpl;
import souza.marlon.moneymanager.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionConverterImpl transactionConverter;

    private final TransactionRepository transactionRepository;

    private final UserBalanceService userBalanceService;

    private final ExternalService externalService;

    @Autowired
    public TransactionServiceImpl(TransactionConverterImpl transactionConverter, TransactionRepository transactionRepository, UserBalanceService userBalanceService, ExternalService externalService) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
        this.userBalanceService = userBalanceService;
        this.externalService = externalService;
    }

    @Override
    @Transactional
    public void transfer(TransactionDto dto) {
        validateTransfer(dto);
        processTransfer(dto);
        notifyTransfer();
    }

    private void notifyTransfer() {
        externalService.sendNotification();
    }

    private void processTransfer(TransactionDto dto) {
        var transactionEntity = transactionConverter.toEntity(dto);
        transactionRepository.save(transactionEntity);
    }

    private void validateTransfer(TransactionDto dto) {
        if (!userBalanceService.haveBalance(dto.payer(), dto.value())) {
            throw new BadRequestException("Payer doesn`t have balance enough.");
        }

        if (!externalService.authorizeTransaction()) {
            throw new BadRequestException("Tranfer not authorized.");
        }
    }

}
