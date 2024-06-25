package souza.marlon.moneymanager.mapper;

public interface Converter<T, U> {
    T toDto(U entity);
    U toEntity(T dto);
}
