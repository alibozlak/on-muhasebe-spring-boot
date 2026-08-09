package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

public class TestUtil {

    static final String SUCCESS_COLOR = "\\033[0;32m";
    static final String ERROR_COLOR = "\\033[1;31m";
    static final String INFO_COLOR = "\\033[0;34m";
    static final String NO_COLOR = "\\033[0m";
    static final String INFO_MESSAGE = "[" + INFO_COLOR + "INFO" + NO_COLOR + "]";
    static final String ERROR_MESSAGE = "[" + ERROR_COLOR + "ERROR" + NO_COLOR + "]";

    static final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "");

    static String adminBozlakJwtToken = "";
    static String adminBozlakPassword = "bozlak";

    static String bash(String bashCommand) throws Exception {
        processBuilder.command("bash", "-c", bashCommand);
        return new String(processBuilder.start().getInputStream().readAllBytes());
    }

    static String jwtTokenResolverFromJson(String jsonHasJwtToken) {
        int tokenInitIndex = jsonHasJwtToken.indexOf("eyJ");
        return jsonHasJwtToken.substring(tokenInitIndex, tokenInitIndex + 184);
    }

    static void printInfoMessageToTerminalWithUncolor(String message) throws Exception {
        String logMessage = bash(String.format("echo -e \"%s %s\"", INFO_MESSAGE, message));
        System.out.println(logMessage);
    }

    static void printSuccessMessageToTerminal(String message) throws Exception {
        String logMessage = bash(String.format(
                "echo -e \"%s%s %s%s\"", INFO_MESSAGE, SUCCESS_COLOR, message, NO_COLOR
        ));
        System.out.println(logMessage);
    }

    static boolean printSuccessOrErrorMessageToTerminal(
            Condition condition, String successMessage, RunnableCanThrowException runnable, String errorMessage
    ) throws Exception
    {
        if (condition.doOperation()){
            runnable.run();

            printSuccessMessageToTerminal(successMessage);
            return true;
        }

        String logMessage = bash(String.format(
                "echo -e \"%s%s %s%s\"", ERROR_MESSAGE, ERROR_COLOR, errorMessage, NO_COLOR
        ));
        System.out.println(logMessage);
        return false;
    }

    static boolean printSuccessOrErrorMessageToTerminal(
            Condition condition, String successMessage, String errorMessage
    ) throws Exception
    {
        return printSuccessOrErrorMessageToTerminal(condition, successMessage, () -> {}, errorMessage);
    }
}