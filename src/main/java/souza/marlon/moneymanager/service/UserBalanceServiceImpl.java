package souza.marlon.moneymanager.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.marlon.moneymanager.converter.UserBalanceConverterImpl;
import souza.marlon.moneymanager.documents.UserBalanceDocument;
import souza.marlon.moneymanager.domain.TransactionType;
import souza.marlon.moneymanager.domain.UserEntity;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.repository.UserBalanceRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    private final UserBalanceRepository userBalanceRepository;

    private final UserService userService;

    private final UserBalanceConverterImpl userBalanceConverter;

    @Autowired
    public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository, UserService userService, UserBalanceConverterImpl userBalanceConverter) {
        this.userBalanceRepository = userBalanceRepository;
        this.userService = userService;
        this.userBalanceConverter = userBalanceConverter;
    }

    @Override
    public void updateBalance(TransactionDto transaction){
        var payer = userService.getUser(transaction.payer());
        var payee = userService.getUser(transaction.payee());

        userBalanceRepository.findById(payee.getId())
            .ifPresent(userBalanceDocument -> updateUserBalance(userBalanceDocument, transaction.value(), TransactionType.DEBIT));

        userBalanceRepository.findById(payer.getId())
            .ifPresent(p -> updateUserBalance(p, transaction.value(), TransactionType.CREDIT));

    }

    @Override
    public boolean haveBalance(@NotNull UUID payer, BigDecimal value) {
        var byId = userBalanceRepository.findById(payer).orElseThrow(() -> new EntityNotFoundException("User balance not found"));
        return byId.getValue().compareTo(value) >= 0;
    }

    @Override
    public UserBalanceDto createBalance(UserBalanceDto userBalanceDto) {
        var entity = userBalanceConverter.toEntity(userBalanceDto);
        var saved = userBalanceRepository.save(entity);
        return userBalanceConverter.toDto(saved);
    }

    @Override
    public List<UserBalanceDto> getBalance() {
        return userBalanceRepository.findAll()
                .stream()
                .map(userBalanceConverter::toDto)
                .toList();
    }

    private void updateUserBalance(UserBalanceDocument userBalanceDocument, BigDecimal value, TransactionType transactionType) {
        userBalanceDocument.setValue(transactionType.getFormula().apply(userBalanceDocument.getValue(), value));
        userBalanceRepository.save(userBalanceDocument);
    }

}
