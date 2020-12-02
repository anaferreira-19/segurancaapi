package com.etec.tcc.apiseguranca.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AlertaFilter {
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private LocalDate alertade;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private LocalDate alertaate;

	public LocalDate getAlertade() {
		return alertade;
	}

	public void setAlertade(LocalDate alertade) {
		this.alertade = alertade;
	}

	public LocalDate getAlertaate() {
		return alertaate;
	}

	public void setAlertaate(LocalDate alertaate) {
		this.alertaate = alertaate;
	}
	
}