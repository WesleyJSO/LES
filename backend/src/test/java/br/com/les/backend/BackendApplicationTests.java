package br.com.les.backend;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Transactional
@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BackendApplicationTests {

	@Test
	public void doNothing() {
	}

}
