package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Integer, BigDecimal> priceConvert =
                mappingContext -> new BigDecimal(mappingContext.getSource());

        modelMapper.typeMap(CreateOfferDto.class, OfferEntity.class)
                .addMappings(map -> map.using(priceConvert)
                        .map(CreateOfferDto::getPrice, OfferEntity::setPrice));

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
