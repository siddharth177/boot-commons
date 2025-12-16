package io.github.siddharth177;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the boot-commons application.
 */
@SpringBootApplication
public class BootCommonsApplication {

    /**
     * Default constructor for {@code BootCommonsApplication}.
     */
    public BootCommonsApplication() {
        // Default constructor
    }

    /**
     * The main method, which serves as the entry point for the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(BootCommonsApplication.class, args);
    }
}
