package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

@FunctionalInterface
public interface Condition {

    boolean doOperation() throws Exception;
}
