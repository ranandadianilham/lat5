package com.idstar.lat5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("all")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.oauth2ResourceServer().jwt()
                .oauth2Login(Customizer.withDefaults());
                //.formLogin(Customizer.withDefaults());


        /*normal jwt token login*/
        /*

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/auth/**")
                //.permitAll()
                //.anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);*/
        return http.build();
    }

    /*@Bean
    public OAuth2AuthorizationRequestRedirectFilter oAuth2Filter() {
        return new OAuth2AuthorizationRequestRedirectFilter(
                new BaseUriRequestMatcher("/oauth2/authorization", null),
                authorizationRequestResolver());
    }

    @Bean
    public OAuth2AuthorizationRequestResolver authorizationRequestResolver() {
        return new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository(),
                "/oauth2/authorization");
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // Configure your OAuth2 client registration details here
        return new InMemoryClientRegistrationRepository(
                ClientRegistration
                        .withRegistrationId("your-registration-id")
                        .clientId("your-client-id")
                        .clientSecret("your-client-secret")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                        .scope("openid", "profile", "email")
                        .authorizationUri("your-authorization-uri")
                        .tokenUri("your-token-uri")
                        .userInfoUri("your-user-info-uri")
                        .userNameAttributeName(IdTokenClaimNames.SUB)
                        .jwkSetUri("your-jwk-set-uri")
                        .clientName("your-client-name")
                        .build());
    }*/
}
