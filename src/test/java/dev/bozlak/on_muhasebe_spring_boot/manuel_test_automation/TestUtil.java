package dev.bozlak.on_muhasebe_spring_boot.manuel_test_automation;

public class TestUtil {

    static String adminBozlakJwtToken = "";

    static String tokenResolverFromJson(String jsonHasJwtToken){
        int tokenInitIndex = jsonHasJwtToken.indexOf("eyJ");
        return jsonHasJwtToken.substring(tokenInitIndex, tokenInitIndex + 184);
    }
}
