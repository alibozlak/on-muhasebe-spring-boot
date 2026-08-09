package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import static dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation.TestUtil.*;

public class AdminChangePassword {

    private String newPassword = "Test5678!";

    boolean doOperations() throws Exception {
        printInfoMessageToTerminalWithUncolor("--- Admin Change Password ---");

        return changePassword_correctPreviousPassword();
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
}
