package pe.com.nttdata.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(ConfigServiceApplicationTest.class)
class ConfigServiceApplicationTest {

    @Test
    void main() throws Exception {
        ConfigServiceApplication.main(new String[] {});
    }
}