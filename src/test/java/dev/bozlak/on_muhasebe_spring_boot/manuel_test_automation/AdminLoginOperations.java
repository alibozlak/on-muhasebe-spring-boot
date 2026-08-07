package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import static dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation.Test.*;

public class AdminLoginOperations {

    boolean doOperations() throws Exception {
        String infoMassage = bash(String.format("echo -e \"%s --- Admin Login Operations ---\"", INFO_MESSAGE));
        System.out.println(infoMassage);

        if (!postLoginRequest_usernameAndPasswordCorrect())
            return false;

        return true;
    }

    private boolean postLoginRequest_usernameAndPasswordCorrect() throws Exception {
        String infoMassage = bash(String.format("echo -e \"%s Post Login Request : \"", INFO_MESSAGE));
        System.out.println(infoMassage);
        String responseJson = bash(
                "curl -X POST http://localhost:8080/api/login -H \"Content-Type: application/json\" " +
                        "-d \"{\\\"username\\\" : \\\"bozlak\\\", \\\"password\\\" : \\\"bozlak\\\"}\""
        );
        System.out.println(responseJson);

        if (responseJson.contains("true")){
            TestUtil.adminBozlakJwtToken = TestUtil.tokenResolverFromJson(responseJson);
            infoMassage = bash(
                    String.format("echo -e \"%s%s Login Succeed :)%s JWT Token : \"",
                            INFO_MESSAGE, SUCCESS_COLOR, NO_COLOR) +
                    TestUtil.adminBozlakJwtToken
            );
            System.out.println(infoMassage);
            return true;
        }

        String errorMessage = bash(
                String.format("echo -e \"%s %s Login failed !! %s \"", ERROR_MESSAGE, ERROR_COLOR, NO_COLOR)
        );
        System.out.println(errorMessage);
        return false;
    }
}
