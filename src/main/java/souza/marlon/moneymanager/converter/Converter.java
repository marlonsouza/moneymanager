package souza.marlon.moneymanager.converter;

public interface Converter<T, U> {
    T toDto(U entity);
    U toEntity(T dto);
}
