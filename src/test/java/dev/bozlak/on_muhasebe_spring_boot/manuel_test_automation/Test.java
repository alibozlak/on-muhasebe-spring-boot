package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import static dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation.TestUtil.*;

/**
 * ------------------------------------------------------------------------------
 * ---------- This Test Pipeline ASSUMES project is running !! ------------------
 * ------------------------------------------------------------------------------
 */
public class Test {

    public static void main(String[] args) throws Exception {

        // Create tables and insert first user and admin to Postgres docker container :
        String startTestShOutput = bash("bash start_test.sh");
        System.out.println(startTestShOutput);

        printSuccessOrErrorMessageToTerminal(
                Test::doOperations,
                "TEST PIPELINE SUCCESS :)",
                "TEST PIPELINE FAILED !!"
        );
    }

    static boolean doOperations() throws Exception {
        return new AdminLoginOperations().doOperations() &&
                new AdminChangePassword().doOperations();
    }
}