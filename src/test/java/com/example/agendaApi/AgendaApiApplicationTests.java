package com.example.agendaApi;

import com.example.agendaApi.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class AgendaApiApplicationTests {
	@Autowired
	private ContactRepository contactRepository;

	@Test
	void contextLoads() {
		assertNotNull(contactRepository, "Repositorio");
	}

}
