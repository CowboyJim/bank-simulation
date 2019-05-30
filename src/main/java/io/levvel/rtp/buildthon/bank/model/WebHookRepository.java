package io.levvel.rtp.buildthon.bank.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface WebHookRepository extends JpaRepository<WebHook, Long> {

	List<WebHook> findByAccountName(String accountNumer);

	List<WebHook> findByAccountNumber(String accountNumer);
}
