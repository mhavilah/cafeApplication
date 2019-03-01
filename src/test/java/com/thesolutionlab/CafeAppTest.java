package com.thesolutionlab;

import com.thesolutionlab.exception.UnavailableProductException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A Simple test to exercise the CLI.
 * <p>
 * Injects the stdin/stdout as a UTF-8 byte stream.
 * Note:
 * Cannot use System.exit() in the main class under test.
 *
 * Based on: https://stackoverflow.com/questions/46173987/how-to-test-a-simple-command-line-application-in-java-using-junit
 * </p>
 */
public class CafeAppTest {

    private static final String MAIN_CLASS_NAME = "com.thesolutionlab.CafeApp";

    @Test
    public void shouldCalculatePrice() throws Throwable {

        String cliOutput = runTest(MAIN_CLASS_NAME, "1", "Latte");

        System.out.printf("CLI Output:\n%s\n", cliOutput );

        assertThat(cliOutput, containsString("4.0000"));
    }

    /**
     * A demo of throwing an exception from the main CLI.
     *
     *
     * @throws UnavailableProductException
     */
    @Test
    public void tooMuchCoffeeShouldBlowUp() throws Exception {

        assertThrows( UnavailableProductException.class,
                () -> {
                String cliOutput = runTest(MAIN_CLASS_NAME,
                    Integer.toString(CafeApp.INITIAL_INVENTORY_LEVELS + 1),
                    "Latte");

                System.out.printf("CLI Output:\n%s\n", cliOutput);
            } );
    }

    @Test
    public void canShowCLIUsageNoArgs() throws Throwable {

        String cliOutput = runTest(MAIN_CLASS_NAME, new String[] {} );

        System.out.printf("CLI Output:\n%s\n", cliOutput );

        assertThat(cliOutput, containsString("Usage"));
    }

    @Test
    public void canShowCLIUsageWithOneArg() throws Throwable {

        String cliOutput = runTest(MAIN_CLASS_NAME, new String[] { "1" } );

        System.out.printf("CLI Output:\n%s\n", cliOutput );

        assertThat(cliOutput, containsString("Usage"));
    }

    @Test
    public void canShowCLIUsageWithIllegalCupArg() throws Throwable {

        String cliOutput = runTest(MAIN_CLASS_NAME, new String[] { "A", "Latte" } );

        System.out.printf("CLI Output:\n%s\n", cliOutput );

        assertThat(cliOutput, containsString("Usage"));
    }

    @Test
    public void canShowCLIUsageWithIllegalBeverageArg() throws Throwable {

        String cliOutput = runTest(MAIN_CLASS_NAME, new String[] { "1", "Coke" } );

        System.out.printf("CLI Output:\n%s\n", cliOutput );

        assertThat(cliOutput, containsString("Usage"));
    }

    /**
     * Helper method to invoke a Main Class/CLI.
     *
     * Uses a byte array output stream buffer to capture the CLI String output.
     *
     * @param className
     * @param args
     * @return          String output intercepted to stdout
     * @throws Throwable
     */
    private static String runTest(final String className, final String... args) throws Throwable {
        PrintStream console = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // final InputStream input = new ByteArrayInputStream(data.getBytes("UTF-8"));
        final InputStream old = System.in;

        try {
            System.setOut(new PrintStream(byteArrayOutputStream));
            //System.setIn(input);

            final Class<?> cls = Class.forName(className);
            final Method method = cls.getDeclaredMethod("main", String[].class);

            try {
                method.invoke(null, (Object) args);
            }
            catch( InvocationTargetException ite )
            {
                // Throw the real cause of the Main CLI problems so that
                // we can use Assertions.assertThrows() in the unit test.
                throw ite.getCause();
            }

        } finally {
            // Reset all the streams
            System.setOut(console);
            System.setIn(old);
        }

        return byteArrayOutputStream.toString();
    }
}
