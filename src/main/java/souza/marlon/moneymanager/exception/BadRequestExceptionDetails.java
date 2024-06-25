package souza.marlon.moneymanager.exception;

import java.time.LocalDateTime;

public class BadRequestExceptionDetails extends ExceptionDetails{
    public BadRequestExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime time) {
        super(title, status, details, developerMessage, time);
    }

    public BadRequestExceptionDetails() {
    }
}
