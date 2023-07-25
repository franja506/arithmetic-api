package com.pachico.arithmetic.shared.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class OpenAPIConfigTest {

    @Autowired
    private OpenAPIConfig openAPIConfig;

    @Value("${franjagonca.openapi.local-url}")
    private String localUrl;

    @Value("${franjagonca.openapi.prod-url}")
    private String prodUrl;

    @Test
    public void testMyOpenAPI() {
        // Given (set up properties via TestPropertySource)

        // When
        OpenAPI openAPI = openAPIConfig.myOpenAPI();

        // Then
        assertNotNull(openAPI, "OpenAPI object should not be null");

        // Test the Info object
        Info info = openAPI.getInfo();
        assertNotNull(info, "Info object should not be null");
        assertEquals("Arithmetic API", info.getTitle(), "Incorrect title");
        assertEquals("1.0", info.getVersion(), "Incorrect version");
        assertEquals("This API retrieve multiple operations  ", info.getDescription(), "Incorrect description");

        // Test the servers
        assertEquals(2, openAPI.getServers().size(), "Incorrect number of servers");

        Server localServer = openAPI.getServers().get(0);
        assertNotNull(localServer, "Local server should not be null");
        assertEquals(localUrl, localServer.getUrl(), "Incorrect local server URL");
        assertEquals("Server URL in local environment", localServer.getDescription(), "Incorrect local server description");

        Server prodServer = openAPI.getServers().get(1);
        assertNotNull(prodServer, "Production server should not be null");
        assertEquals(prodUrl, prodServer.getUrl(), "Incorrect production server URL");
        assertEquals("Server URL in Production environment", prodServer.getDescription(), "Incorrect production server description");
    }
}