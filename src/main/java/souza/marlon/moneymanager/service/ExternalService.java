package souza.marlon.moneymanager.service;

public interface ExternalService {
    boolean authorizeTransaction();

    void sendNotification();
}
