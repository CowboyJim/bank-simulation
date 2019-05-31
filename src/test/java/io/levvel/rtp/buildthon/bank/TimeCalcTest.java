package io.levvel.rtp.buildthon.bank;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

;

public class TimeCalcTest {

	int expiresIn = 500;
	LocalDateTime lastUpdated;

	@Test
	public void timeUpdateCalculationTest() {

		Assert.assertTrue("Should be true", shouldUpdate());

		LocalDateTime now = LocalDateTime.now();
		lastUpdated = now.minusSeconds(300);

		Assert.assertFalse("Should be false", shouldUpdate());

		lastUpdated = now.minusSeconds(10000);

		Assert.assertTrue("Should be true", shouldUpdate());
	}

	private boolean shouldUpdate() {
		if (lastUpdated == null) {
			return true;
		}
		LocalDateTime now = LocalDateTime.now();
		Duration durationSinceUpdated = Duration.between(lastUpdated, now);
		long difference = expiresIn - durationSinceUpdated.toMillis() / 1000;
		return difference < 0;
	}
}
