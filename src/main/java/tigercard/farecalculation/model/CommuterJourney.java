package tigercard.farecalculation.model;

/**
 * Model object contains commuter journey includes 
 * day of journey : Monday,Tuesday etc. 
 * time of journey: HH:mm 
 * zone : info of journey start zone to end zone
 */
public class CommuterJourney {

	String dayofjourney;
	String timeofjourney;
	Zone zone;

	public CommuterJourney(String dayofjourney, String timeofjourney, Zone zone) {
		super();
		this.dayofjourney = dayofjourney;
		this.zone = zone;
		this.timeofjourney = timeofjourney;
	}

	public String getDayofjourney() {
		return dayofjourney;
	}

	public void setDayofjourney(String dayofjourney) {
		this.dayofjourney = dayofjourney;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getTimeofjourney() {
		return timeofjourney;
	}

	public void setTimeofjourney(String timeofjourney) {
		this.timeofjourney = timeofjourney;
	}

	@Override
	public String toString() {
		return "CommuterJourney [dayofjourney=" + dayofjourney + ", timeofjourney=" + timeofjourney + ", zone=" + zone
				+ "]";
	}

}
