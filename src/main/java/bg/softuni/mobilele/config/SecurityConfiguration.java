package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.impl.MobileleUserDetailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${mobilele.remember.me.key}") String rememberMe) {
        this.rememberMeKey = rememberMe;
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                // Define which URLs are visible by which users
                authorizeRequest -> authorizeRequest
                        // ALL static resources which are situated in js, images, css are available for everyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow actuator endpoints
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/users/login", "/users/register", "/users/login-error").anonymous()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/offers/all").permitAll()
                        .requestMatchers("/api/currency/convert").permitAll()
                        .requestMatchers(HttpMethod.GET, "/offer/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())
                        //All other requests are authenticated
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            // redirect here when we access something which is not allowed
                            // also this is the page where we perform login
                            .loginPage("/users/login")
                            // the names of the input fields (in our case in auth-login.html)
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // the URL where we should POST something in order to perform logout
                            .logoutUrl("/users/logout")
                            // where to go when logged out?
                            .logoutSuccessUrl("/")
                            // invalidate the http session
                            .invalidateHttpSession(true);
                }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("rememberme")
                            .rememberMeCookieName("rememberme")
                            // Valid one week
                            .tokenValiditySeconds(604800);
                }
        );

        return httpSecurity.build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the mobilele users and roles
        // to representation witch spring security understands

        return new MobileleUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
