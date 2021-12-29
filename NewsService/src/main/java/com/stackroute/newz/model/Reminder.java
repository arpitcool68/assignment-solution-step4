package com.stackroute.newz.model;

import java.time.LocalDateTime;

public class Reminder {

	private String reminderId;
	private LocalDateTime schedule;

	public Reminder(String reminderId, LocalDateTime schedule) {
		super();
		this.reminderId = reminderId;
		this.schedule = schedule;
	}

	public Reminder() {
		super();
	}

	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	public LocalDateTime getSchedule() {
		return schedule;
	}

	public void setSchedule() {
		this.schedule = LocalDateTime.now();
	}

}
