package hu.unideb.inf.suitup.config;

import hu.unideb.inf.suitup.SuitupApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SuitupApplication.class)
@AutoConfigureMockMvc
public class CucumberConfig {
}
