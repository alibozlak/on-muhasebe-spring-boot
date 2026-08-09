package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import static dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation.TestUtil.*;

public class AdminChangePassword {

    private final String newPassword = "Test5678!";

    boolean doOperations() throws Exception {
        printInfoMessageToTerminalWithUncolor("---------- Admin Change Password ----------");

        if (
                changePassword_correctPreviousPassword() &&
                        loginResponseShouldIncorrectPasswordJson_withPreviousPassword() &&
                        loginResponseHasToken_loginRequestWithNewPassword()
        ){
            printSuccessMessageToTerminal("Admin Change Password Part Passed <--- Wooohuu ");
            return true;
        }
        return false;
    }

    private boolean changePassword_correctPreviousPassword() throws Exception {
        String responseJson = bash(
                "curl -X PUT \"http://localhost:8080/api/v1/users/change-password\" " +
                        "-H \"Authorization: Bearer " + TestUtil.adminBozlakJwtToken + "\" " +
                        "-H \"Content-Type: application/json\" " +
                        "-d \"{\\\"currentPassword\\\":\\\"bozlak\\\",\\\"newPassword\\\":\\\"" + this.newPassword +
                        "\\\"}\""
        );
        printInfoMessageToTerminalWithUncolor("Change Password Response : " + responseJson);

        return printSuccessOrErrorMessageToTerminal(
                () -> responseJson.contains("true"),
                "admin bozlak's password changed :)",
                "admin bozlak's password chancing operation failed !!"
        );
    }

    private boolean loginResponseShouldIncorrectPasswordJson_withPreviousPassword() throws Exception {
        printInfoMessageToTerminalWithUncolor(
                "Login Request Trial with Previous Password.. Expected : Response has \"success\":false"
        );

        String responseJson = bash(
                "curl -X POST http://localhost:8080/api/login -H \"Content-Type: application/json\" " +
                        "-d \"{\\\"username\\\" : \\\"bozlak\\\", \\\"password\\\" : \\\"bozlak\\\"}\""
        );
        printInfoMessageToTerminalWithUncolor("Response JSON : " + responseJson);
        return printSuccessOrErrorMessageToTerminal(
                () -> responseJson.contains("\"success\":false"),
                "Expected did :)",
                "Noooo, something is wrong !!!"
        );
    }

    private boolean loginResponseHasToken_loginRequestWithNewPassword() throws Exception {
        printInfoMessageToTerminalWithUncolor(
                "Login Trial with correct (new) admin password.. Expected : Response has JWT Token"
        );

        String responseJson = bash(
                "curl -X POST http://localhost:8080/api/login -H \"Content-Type: application/json\" " +
                        "-d \"{\\\"username\\\" : \\\"bozlak\\\", \\\"password\\\" : \\\"" + this.newPassword + "\\\"}\""
        );
        printInfoMessageToTerminalWithUncolor("Response JSON : " + responseJson);
        return printSuccessOrErrorMessageToTerminal(
                () -> responseJson.contains("\"success\":true"),
                "Login succeed :)",
                () -> {
                    adminBozlakJwtToken = jwtTokenResolverFromJson(responseJson);
                    adminBozlakPassword = this.newPassword;
                },
                "Noooo, something is wrong !!!"
        );
    }

}
