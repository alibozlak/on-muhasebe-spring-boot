package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

import java.lang.ProcessBuilder;

/**
 * ------------------------------------------------------------------------------
 * ---------- This Test Pipeline ASSUMES project is running !! ------------------
 * ------------------------------------------------------------------------------
 */
public class Test {

    static final String SUCCESS_COLOR="\\033[0;32m";
    static final String ERROR_COLOR="\\033[1;31m";
    static final String INFO_COLOR="\\033[0;34m";
    static final String NO_COLOR="\\033[0m";
    static final String INFO_MESSAGE = "[" + INFO_COLOR + "INFO" + NO_COLOR + "]";
    static final String ERROR_MESSAGE = "[" + ERROR_COLOR + "ERROR" + NO_COLOR + "]";;

    static final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "");

    public static void main(String[] args) throws Exception {

        // Create tables and insert first user and admin to Postgres docker container :
        String startTestShOutput = bash("bash start_test.sh");
        System.out.println(startTestShOutput);

        if (!new AdminLoginOperations().doOperations())
            return;

        // Pipeline finish message :
        String finishMessage = String.format(
                "echo -e \"%s %s TEST PIPELINE SUCCESS %s\"", INFO_MESSAGE, SUCCESS_COLOR, NO_COLOR
        );
        String finishOutput = bash(finishMessage);
        System.out.println(finishOutput);
    }

    static String bash(String bashCommand) throws Exception {
        processBuilder.command("bash", "-c", bashCommand);
        return new String(processBuilder.start().getInputStream().readAllBytes());
    }
}