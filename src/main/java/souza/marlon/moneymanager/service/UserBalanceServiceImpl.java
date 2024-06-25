package souza.marlon.moneymanager.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.marlon.moneymanager.documents.UserBalanceDocument;
import souza.marlon.moneymanager.domain.TransactionType;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.dto.UserDto;
import souza.marlon.moneymanager.repository.UserBalanceRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    private final UserBalanceRepository userBalanceRepository;

    private final UserService userService;

    @Autowired
    public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository, UserService userService) {
        this.userBalanceRepository = userBalanceRepository;
        this.userService = userService;
    }

    @Override
    public void updateBalance(TransactionDto transaction){
        var payer = userService.getUser(transaction.payer().id());
        var payee = userService.getUser(transaction.payee().id());

        userBalanceRepository.findById(payee.getId())
            .ifPresent(userBalanceDocument -> updateUserBalance(userBalanceDocument, transaction.payee(), transaction.value(), TransactionType.DEBIT));

        userBalanceRepository.findById(payer.getId())
            .ifPresent(p -> updateUserBalance(p, transaction.payer(), transaction.value(), TransactionType.CREDIT));

    }

    @Override
    public boolean haveBalance(UserDto payer, BigDecimal value) {
        var byId = userBalanceRepository.findById(payer.id()).orElseThrow(() -> new EntityNotFoundException("User balance not found"));
        return byId.getValue().compareTo(value) >= 0;
    }

    @Override
    public UserBalanceDto createBalance(UserBalanceDto userBalanceDto) {
        return null;
    }

    private void updateUserBalance(UserBalanceDocument userBalanceDocument, UserDto userDto, BigDecimal value, TransactionType transactionType) {
        userBalanceDocument.setValue(transactionType.getFormula().apply(userBalanceDocument.getValue(), value));
        userBalanceRepository.save(userBalanceDocument);
    }

}
