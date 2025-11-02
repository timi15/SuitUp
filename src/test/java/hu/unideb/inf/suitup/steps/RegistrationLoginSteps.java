package hu.unideb.inf.suitup.steps;

import hu.unideb.inf.suitup.entity.UserEntity;
import hu.unideb.inf.suitup.repository.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class RegistrationLoginSteps {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private MvcResult response;
    private UserEntity testUser;

    @Before
    public void clearDatabase() {
        userRepository.deleteAll();
    }

    // --- SIKERES REGISZTRÁCIÓ ---

    @Given("a felhasználó a regisztrációs oldalon van")
    public void a_felhasznalo_a_regisztracios_oldalo_van() {
        testUser = new UserEntity();
        testUser.setName("Stílusos Stella");
        testUser.setUsername("stella");
        testUser.setPassword(passwordEncoder.encode("teszt"));
    }

    @When("megadja a szükséges adatokat")
    public void megadja_a_szukseges_adatokat() {

    }

    @And("rákattint a {string} gombra")
    public void rakattint_a_gombra(String gomb) throws Exception {
        String url = gomb.equals("Regisztráció") ? "/auth/register" : "/auth/login";

        if (url.equals("/auth/register")) {
            response = mockMvc.perform(post("/auth/register")
                            .with(csrf())
                            .param("name", testUser.getName())
                            .param("username", testUser.getUsername())
                            .param("password", testUser.getPassword()))
                    .andReturn();
        } else {
            response = mockMvc.perform(post("/auth/login")
                            .with(csrf())
                            .param("username", testUser.getUsername())
                            .param("password", testUser.getPassword()))
                    .andReturn();
        }
    }

    @Then("a rendszer létrehozza a felhasználói fiókot")
    public void a_rendszer_letrehozza_a_felhasznaloi_fiokot() {
        assertThat(userRepository.findByUsername("stella")).isPresent();
    }

    @And("a felhasználó visszajelzést kap a sikeres regisztrációról")
    public void visszajelzes() {
        assertThat(response.getResponse().getRedirectedUrl()).contains("/login");
    }

    // --- SIKERES BEJELENTKEZÉS ---

    @Given("a felhasználó már regisztrált")
    public void a_felhasznalo_mar_regisztralt() {
        UserEntity existing = new UserEntity();
        existing.setName("Stílusos Stella");
        existing.setUsername("stella");
        existing.setPassword(passwordEncoder.encode("teszt"));
        userRepository.save(existing);
        testUser = existing;
    }

    @Given("a bejelentkezési oldalon van")
    public void a_bejelentkezesi_oldalon_van() {

    }

    @When("megadja a helyes felhasználónevet és jelszavát")
    public void megadja_a_helyes_felhasznalonevet_es_jelszavat() {
        testUser = new UserEntity();
        testUser.setUsername("stella");
        testUser.setPassword("teszt");
    }

    @Then("a rendszer bejelentkezteti a felhasználót")
    public void a_rendszer_bejelentkezteti_a_felhasznalot() {
        assertThat(response.getResponse().getStatus()).isEqualTo(302);
        assertThat(response.getResponse().getRedirectedUrl()).contains("/home");
    }

    @And("átirányítja a személyes tartalmak oldalára")
    public void atiranyitja_a_szemelyes_oldalara() {
        assertThat(response.getResponse().getRedirectedUrl()).contains("/home");
    }


}
