package souza.marlon.moneymanager.domain;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public enum TransactionType {
    DEBIT(BigDecimal::subtract),
    CREDIT(BigDecimal::add);

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> formula;

    TransactionType(BiFunction<BigDecimal, BigDecimal, BigDecimal> formula) {
        this.formula = formula;
    }

    public BiFunction<BigDecimal, BigDecimal, BigDecimal> getFormula() {
        return formula;
    }
}
