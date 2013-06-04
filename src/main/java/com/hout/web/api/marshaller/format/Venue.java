package com.hout.web.api.marshaller.format;

public class Venue {
	long id;

    String location;
    
    String address;
    

	public Venue(com.hout.domain.entities.Venue venue) {
		if(venue != null) {
			this.id = venue.getId();
			this.location = venue.getLocation();
			this.address = venue.getAddress();
		}
	}
    
    public Venue(String location) {
		this.location = location;
	}

	public Venue() {
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAddress() {
        return location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
