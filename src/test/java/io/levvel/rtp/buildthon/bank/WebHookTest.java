package io.levvel.rtp.buildthon.bank;

import io.levvel.rtp.buildthon.bank.model.WebHookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"username=JasonD", "password=Welcome@1"})
public class WebHookTest {

	@Autowired
	WebHookRepository repository;

	@Test
	public void findWebHookTest(){
			System.out.println("------- /n" + repository.findByAccountNumber("CIBTEAM120001"));
	}

	@Test
	public void intWebHookTest(){
		System.out.println("------- /n" + repository.findAll());
	}
}
