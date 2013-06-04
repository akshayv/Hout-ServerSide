package com.hout.web.api.marshaller.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class MeetupRetrievalResponse {
	
	@XmlElement
	List<Meetup> meetups = new ArrayList<Meetup>();
	
	@XmlJavaTypeAdapter(value=JaxbDateAdapter.class, type=Date.class)
	@XmlElement
	Date fromDate;
	
	@XmlJavaTypeAdapter(value=JaxbDateAdapter.class, type=Date.class)
	@XmlElement
	Date toDate;

	
	public MeetupRetrievalResponse() {
	}

	public MeetupRetrievalResponse(List<Meetup> meetups, Date fromDate,
			Date toDate) {
		super();
		this.meetups = meetups;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


}
