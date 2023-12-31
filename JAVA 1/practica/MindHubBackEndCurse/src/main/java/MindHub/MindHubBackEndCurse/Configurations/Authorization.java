package MindHub.MindHubBackEndCurse.Configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class Authorization {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/api/admins","/api/teachers","/api/students","/api/courses").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/create/admin", "/api/create/teacher", "api/create/course").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/edit/admin").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/remove/admin","/api/remove/teacher","/api/remove/student", "/api/remove/course").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.GET,"/api/course/students").hasAnyAuthority("ADMIN","TEACHER")
                .requestMatchers(HttpMethod.PUT,"/api/edit/teacher", "/api/edit/course", "/api/notes").hasAnyAuthority("ADMIN", "TEACHER")

                .requestMatchers(HttpMethod.POST, "/api/create/curse").hasAuthority("TEACHER")

                .requestMatchers(HttpMethod.POST, "/api/create/student").authenticated()
                .requestMatchers(HttpMethod.POST, "api/inscription").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/edit/student").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/found/students", "api/find/students").authenticated()

                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()

                .anyRequest().authenticated());
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable()));


        http.formLogin(formLogin -> formLogin
                .loginPage("/index.html")
                .loginProcessingUrl("/api/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                .failureHandler((request, response, exception) -> response.sendError(401))
                .permitAll());
        http.exceptionHandling( exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));
        http.logout(httpSecurityLogoutConfigurer ->
                httpSecurityLogoutConfigurer
                        .logoutUrl("/api/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                        .deleteCookies("JSESSIONID"));
        http.rememberMe(Customizer.withDefaults());



        return http.build();
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}
