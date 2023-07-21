package br.com.escuderodev.api.depoimento;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = DepoimentoApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
class DepoimentoApplicationTests {

	@Test
	void contextLoads() {
	}

}
