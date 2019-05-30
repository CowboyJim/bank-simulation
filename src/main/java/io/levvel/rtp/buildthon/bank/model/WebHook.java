package io.levvel.rtp.buildthon.bank.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "webhooks")
@Data
public class WebHook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url")
	private String url;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_name")
	private String accountName;

	public WebHook() {
	}

	public WebHook(Long id, String url, String accountNumber, String accountName) {
		this.id = id;
		this.url = url;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}
}

