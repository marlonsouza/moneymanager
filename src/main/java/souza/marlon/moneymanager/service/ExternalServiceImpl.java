package souza.marlon.moneymanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import souza.marlon.moneymanager.dto.AuthorizationResponseDto;
import souza.marlon.moneymanager.dto.NotificationRequestDto;
import souza.marlon.moneymanager.exception.BadRequestException;

import java.util.Optional;

@Service
public class ExternalServiceImpl implements ExternalService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean authorizeTransaction() {
        var responseEntity = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", AuthorizationResponseDto.class);

        var body = Optional.ofNullable(responseEntity.getBody()).orElseThrow();
        var data = Optional.ofNullable(body.data()).orElseThrow();

        return responseEntity.getStatusCode().value() == HttpStatus.OK.value() &&
                !body.status().equals("fail") && data.authorization();
    }

    @Override
    public void sendNotification() {
        var response = restTemplate.postForEntity("https://util.devi.tools/api/v2/authorize", new NotificationRequestDto(), String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            throw new BadRequestException("Notification is not working!");
        }
    }
}
