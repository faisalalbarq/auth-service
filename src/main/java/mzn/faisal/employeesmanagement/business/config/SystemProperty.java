package mzn.faisal.employeesmanagement.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemProperty {

    @Value("${jwt.key}")
    private String jwtSecretKey;

    @Value("${jwt.access-token-expiration}")
    private Long jwtExpiration;



    @Value("${backend-domain:http://localhost:8080}")
    private String backendDomain;

    @Value("${frontend-domain:http://localhost:3000}")
    private String frontendDomain;

    @Value("${cookie-domain:localhost}")
    private String cookieDomain;

    @Value("${token-name:Bearer}")
    private String tokenName;






    public String getJwtSecretKey() {
        return jwtSecretKey;
    }

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    public String getBackendDomain() {
        return backendDomain;
    }

    public String getFrontendDomain() {
        return frontendDomain;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public String getTokenName() {
        return tokenName;
    }
}
