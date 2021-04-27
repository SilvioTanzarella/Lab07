package it.polito.tdp.poweroutages.model;

import java.time.*;

public class Blackout {
	
	private int nerc_id;
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private int customers_affected;
	
	public Blackout(int nerc_id, LocalDateTime time, LocalDateTime time2, int customers_affected) {
		super();
		this.nerc_id = nerc_id;
		this.date_event_began = time;
		this.date_event_finished = time2;
		this.customers_affected = customers_affected;
	}
	
	

	public int getCustomers_affected() {
		return customers_affected;
	}



	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}



	public int getNerc_id() {
		return nerc_id;
	}

	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}

	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}

	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	
	public long differenceBetweenDaysInHours() {
		Duration p = Duration.between(this.date_event_began, this.date_event_finished);
		return p.toHours();
	}
	

	@Override
	public String toString() {
		return this.nerc_id+" "+this.date_event_began+" "+this.date_event_finished+" "+this.customers_affected+"\n";
	}
	
	
	

}
