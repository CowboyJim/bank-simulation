package io.levvel.rtp.buildthon.bank.config;


import io.levvel.rtp.buildthon.bank.services.AuthenticationCredentialsService;
import io.levvel.rtp.buildthon.bank.services.PaymentsReceivedPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

	@Autowired
	AuthenticationCredentialsService credentialsService;

	@Autowired
	PaymentsReceivedPoller paymentsReceivedPoller;

	@Scheduled(fixedDelayString = "${credentials.update.frequency}")
	public void refreshCredentials() {
		logger.info("Updating client credentials");
		credentialsService.updateTokens();
	}

	@Scheduled(fixedDelayString = "${payment.polling.frequency}")
	public void pollForPayments(){
		paymentsReceivedPoller.pollForNewPayments();
	}

}
