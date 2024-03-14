package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.config.ReCaptchaConfig;
import bg.softuni.mobilele.model.dto.ReCaptchaResponseDTO;
import bg.softuni.mobilele.service.ReCaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Optional;

public class ReCaptchaServiceImpl implements ReCaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaServiceImpl.class);

    private final WebClient webClient;
    private final ReCaptchaConfig reCaptchaConfig;

    public ReCaptchaServiceImpl(WebClient webClient, ReCaptchaConfig reCaptchaConfig) {
        this.webClient = webClient;
        this.reCaptchaConfig = reCaptchaConfig;
    }


    @Override
    public Optional<ReCaptchaResponseDTO> verify(String token) {

        return Optional.ofNullable(webClient
                .post()
                .uri(this::biuldReCaptchaURI)
                .body(BodyInserters.fromFormData("secret", reCaptchaConfig.getSecret())
                        .with("response", token))
                .retrieve()
                .bodyToMono(ReCaptchaResponseDTO.class)
                .doOnError(t -> LOGGER.error("Error fetching google response...", t))
                .onErrorComplete()
                .block());
    }

    // https://www.google.com/recaptcha/api/siteverify
    private URI biuldReCaptchaURI(UriBuilder uriBuilder) {
        return uriBuilder
                .scheme("https")
                .host("www.google.com")
                .path("/recaptcha/api/siteverify")
                .build();
    }
}
