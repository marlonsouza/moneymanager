package souza.marlon.moneymanager.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @ManyToOne
    private UserEntity payer;

    @ManyToOne
    private UserEntity payee;

    private TransactionCategory category;

    @NotNull
    private BigDecimal value;

    public TransactionEntity(String description, UUID id, UserEntity payee, UserEntity payer, TransactionCategory transactionCategory, BigDecimal value) {
        this.description = description;
        this.id = id;
        this.payee = payee;
        this.payer = payer;
        this.category = transactionCategory;
        this.value = value;
    }

    public TransactionEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(payer, that.payer) && Objects.equals(payee, that.payee) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, payer, payee, value);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getPayee() {
        return payee;
    }

    public void setPayee(UserEntity payee) {
        this.payee = payee;
    }

    public UserEntity getPayer() {
        return payer;
    }

    public void setPayer(UserEntity payer) {
        this.payer = payer;
    }

    public @NotNull BigDecimal getValue() {
        return value;
    }

    public void setValue(@NotNull BigDecimal value) {
        this.value = value;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }
}
