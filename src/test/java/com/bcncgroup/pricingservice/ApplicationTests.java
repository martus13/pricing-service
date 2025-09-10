package com.bcncgroup.pricingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void mainMethod_shouldRunWithoutExceptions() {
		// Ejecuta el método main
		Application.main(new String[]{});
	}

}
