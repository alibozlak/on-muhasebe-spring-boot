package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import static dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation.TestUtil.*;

public class AdminLoginOperations {

    boolean doOperations() throws Exception {
        printInfoMessageToTerminalWithUncolor("--- Admin Login Operations ---");

        return postLoginRequest_usernameAndPasswordCorrect();
    }

    private boolean postLoginRequest_usernameAndPasswordCorrect() throws Exception {
        String responseJson = bash(
                "curl -X POST http://localhost:8080/api/login -H \"Content-Type: application/json\" " +
                        "-d \"{\\\"username\\\" : \\\"bozlak\\\", \\\"password\\\" : \\\"bozlak\\\"}\""
        );
        printInfoMessageToTerminalWithUncolor("Response : " +  responseJson);

        return printSuccessOrErrorMessageToTerminal(
                () -> responseJson.contains("true"),
                "Login Succeed :) \nJWT Token : " + jwtTokenResolverFromJson(responseJson),
                () -> adminBozlakJwtToken = jwtTokenResolverFromJson(responseJson),
                "Login failed !!"
        );
    }
}
