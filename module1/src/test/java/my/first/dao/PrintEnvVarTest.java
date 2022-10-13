package my.first.dao;

import org.junit.Test;

public class PrintEnvVarTest {

    @Test
    public void testPrintEnvVars() {
        System.getProperties().forEach(
                (o, o2) -> System.out.println(o + "=" + o2));

        System.getenv().forEach(
                (k, v) -> System.out.println(k + "=" + v));

        System.out.println(System.getenv().get("USERPROFILE"));
    }
}
