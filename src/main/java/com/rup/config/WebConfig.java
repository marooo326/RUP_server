package com.rup.config;

import com.rup.annotation.resolver.AuthMemberArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthMemberArgumentResolver authMemberArgumentResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 앞으로 만들 모든 CORS 정보를 적용한다
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:63343",
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "https://port-0-rup-server-cn1vmr2clp9vy29k.sel5.cloudtype.app/"
                )
                // 모든 HTTP Method를 허용한다.
                .allowedMethods("*", "PUT", "POST", "DELETE", "OPTIONS", "PATCH", "GET")
                // HTTP 요청의 Header에 어떤 값이든 들어갈 수 있도록 허용한다.
                .allowedHeaders("*")
                .exposedHeaders("Set-Cookie")
                // 자격증명 사용을 허용한다.
                // 해당 옵션 사용시 allowedOrigins를 * (전체)로 설정할 수 없다.
                .allowCredentials(true);
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList) {
//        resolverList.add(authMemberArgumentResolver);
//    }

//    @Override
//    public void addFormatters(final FormatterRegistry registry) {
//        registry.addConverter(new SocialPathVariableConverter());
//    }

//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration configuration = new CorsConfiguration();
//        // 모든 출처를 허용합니다.
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080"));
//        configuration.setAllowedOrigins(Arrays.asList("https://port-0-rup-server-cn1vmr2clp9vy29k.sel5.cloudtype.app"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//
//        corsConfigSource.registerCorsConfiguration("/**", configuration);
//        return corsConfigSource;
//    }
}

