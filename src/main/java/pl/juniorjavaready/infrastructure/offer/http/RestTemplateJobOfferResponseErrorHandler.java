package pl.juniorjavaready.infrastructure.offer.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class RestTemplateJobOfferResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();

        switch (statusCode) {
            case NOT_FOUND:
                throw new JobOfferNotFoundException("Job offer not found");
            case BAD_REQUEST:
                throw new InvalidJobOfferRequestException("Invalid job offer request");
            case INTERNAL_SERVER_ERROR:
                throw new JobOfferServiceException("Internal server error in job offer service");
            case UNAUTHORIZED:
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            default:
                super.handleError(response);
        }
    }
}

class JobOfferNotFoundException extends RuntimeException {
    public JobOfferNotFoundException(String message) {
        super(message);
    }
}

class InvalidJobOfferRequestException extends RuntimeException {
    public InvalidJobOfferRequestException(String message) {
        super(message);
    }
}

class JobOfferServiceException extends RuntimeException {
    public JobOfferServiceException(String message) {
        super(message);
    }

}
