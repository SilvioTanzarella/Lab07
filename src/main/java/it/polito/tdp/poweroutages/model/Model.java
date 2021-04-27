package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Blackout> result;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> getAllBlackoutsByNerc(Nerc nerc){
		return this.podao.getAllBlackoutsByNerc(nerc);
	}

	public String worstCase(Nerc nerc, int year, int hour) {
		String s = "";
		List<Blackout> blackout = new ArrayList<Blackout>(this.getAllBlackoutsByNerc(nerc));
		List<Blackout> parziale = new ArrayList<Blackout>();
		result = new ArrayList<Blackout>();
		trova(parziale, 0, year, hour, blackout);
		

		s = "Tot people affected: "+this.calcolaTotalePersone(result)+"\n";
		s += "Tot hours of outage: "+this.calcolaTotaleOre(result)+"\n";
		for(Blackout b: result) {
			s += b.toString()+"\n";
		}
		
		return s;
	}
	
	private void trova(List<Blackout> parziale,int livello, int year, int hour, List<Blackout> blackout) {
		System.out.println(result);
		if(this.calcolaTotaleOre(parziale) > hour) {
			return;
		}
		if(!this.differenzaAnni(parziale, year)) {
			return;
		}
		if(this.calcolaTotalePersone(parziale) > this.calcolaTotalePersone(result)) {
			result = new ArrayList<Blackout>(parziale);
		}
		
		if(livello == blackout.size()) {
			if(this.calcolaTotalePersone(parziale) > this.calcolaTotalePersone(result)) {
				result = new ArrayList<Blackout>(parziale);
			}
			else
				return;
		}
		
		parziale.add(blackout.get(livello));
		this.trova(parziale, livello+1, year, hour, blackout);
		
		parziale.remove(parziale.size()-1); 
		this.trova(parziale, livello+1, year, hour, blackout);
	
	}
	
	private long calcolaTotaleOre(List<Blackout> blackout) {
		long tot=0;
		
		for(Blackout b: blackout) {
			tot += b.differenceBetweenDaysInHours();
		}
		return tot;
	}
	
	private boolean differenzaAnni(List<Blackout> blackout, int year) {
		if(blackout.size()==0)
			return true;
		LocalDateTime min = blackout.get(0).getDate_event_began();
		LocalDateTime max = blackout.get(0).getDate_event_finished();
		
		for(Blackout b: blackout) {
			if(b.getDate_event_began().isBefore(min))
				min = b.getDate_event_began();
			if(b.getDate_event_finished().isAfter(max))
				max = b.getDate_event_finished();
		}
		long difference = Period.between(min.toLocalDate(), max.toLocalDate()).getYears();
		if(difference <= year)
			return true;
		else
			return false;
	}
	
	private int calcolaTotalePersone(List<Blackout> blackout) {
		if(blackout.size()==0)
			return 0;
		int tot = 0;
		
		for(Blackout b: blackout) {
			tot += b.getCustomers_affected();
		}
		return tot;
	}

}
