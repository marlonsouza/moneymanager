package souza.marlon.moneymanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import souza.marlon.moneymanager.dto.AuthorizationResponseDto;
import souza.marlon.moneymanager.dto.NotificationRequestDto;
import souza.marlon.moneymanager.exception.BadRequestException;

import java.util.Optional;

@Service
public class ExternalServiceImpl implements ExternalService {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalServiceImpl.class);

  private final RestTemplate restTemplate;

  private final RetryTemplate retryTemplate;

  @Autowired
  public ExternalServiceImpl(RestTemplate restTemplate, RetryTemplate retryTemplate) {
    this.restTemplate = restTemplate;
    this.retryTemplate = retryTemplate;
  }

  @Override
  public boolean authorizeTransaction() {
    var responseEntity = retryTemplate.execute(a -> restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", AuthorizationResponseDto.class));

    var body = Optional.ofNullable(responseEntity.getBody()).orElseThrow();
    var data = Optional.ofNullable(body.data()).orElseThrow();

    return responseEntity.getStatusCode().value() == HttpStatus.OK.value() &&
        !body.status().equals("fail") && data.authorization();
  }

  @Override
  public void sendNotification() {
    var response = retryTemplate.execute(a -> restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", new NotificationRequestDto(), String.class));

    if (!response.getStatusCode().is2xxSuccessful()) {
      LOG.error("Notification is not working! Body - {}", response.getBody());
      throw new BadRequestException("Notification is not working!");
    }
  }
}
