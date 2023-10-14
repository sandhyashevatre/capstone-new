// package com.capstone.telecom.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {
   
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customUserDetailsService);
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//            .authorizeHttpRequests((requests) -> requests
//            .requestMatchers("/register").permitAll()
//            .anyRequest().authenticated())
//            .logout(withDefaults())
//            .formLogin((login) -> login.loginProcessingUrl("/login").defaultSuccessUrl("/cycle/list", true));
       
//        return http.build();
//    }

// }
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.oauth2.client.registration.ClientRegistration;
// // import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
// // import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails;
// // import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
// // import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint.Oidc;
// // import org.springframework.security.oauth2.core.oidc.OidcIdToken;

// // @Configuration
// // public class SecurityConfig {

// //     @Bean
// //     public ClientRegistration clientRegistration() {
// //         ProviderDetails userInfoProvider = ProviderDetails.builder()
// //             .userInfoEndpoint(userInfoEndpoint())
// //             .build();

// //         return ClientRegistration
// //             .withRegistrationId("okta")
// //             .providerDetails(userInfoProvider)
// //             .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
// //             .scope("openid", "profile", "email")
// //             .clientId("RehnBNe7A86Q9ItJwvfFDjnr9M8nf7Ru")
// //             .clientSecret("iNCEj8v_bQyZow8zAIz2zq4PjD5W_P_X57smIb2Q1gJTsq-QL_u3mLYVFLUN-nE1")
// //             .clientName("Okta")
// //             .build();
// //     }

// //     @Bean
// //     public InMemoryClientRegistrationRepository clientRegistrationRepository() {
// //         return new InMemoryClientRegistrationRepository(clientRegistration());
// //     }

// //     private UserInfoEndpoint userInfoEndpoint() {
// //         Oidc oidc = UserInfoEndpoint.Oidc.builder()
// //             .userNameAttributeName(OidcIdToken.SUB)
// //             .build();
// //         return UserInfoEndpoint.builder().oidc(oidc).build();
// //     }
// // }
