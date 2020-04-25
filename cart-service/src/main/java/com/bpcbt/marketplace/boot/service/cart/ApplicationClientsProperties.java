package com.bpcbt.marketplace.boot.service.cart;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app-clients")
@Validated
public class ApplicationClientsProperties {
    @Valid
    private List<ApplicationClient> clients = new ArrayList<>();

    @Data
    public static class ApplicationClient {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String[] authorities;
    }

}