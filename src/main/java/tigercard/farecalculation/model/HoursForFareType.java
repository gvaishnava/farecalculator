package tigercard.farecalculation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * Entity class represent table in database when mater data of fare type info
 * saved.
 */
@Entity
public class HoursForFareType {

	@javax.persistence.Id
	@GeneratedValue
	private Long id; // unique id of column auto generated.
	private String days; // days of date like MONDAY, TUESDAY
	private double starttime; // start time of journey in HH.mm
	private double endtime; // end time of journey in HH.mm
	private int fromzone; // journey start zone
	private int tozone; // journey end zone
	private int peakhourtype; // fare type peak hours or off-peak hours

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public double getStarttime() {
		return starttime;
	}

	public void setStarttime(double starttime) {
		this.starttime = starttime;
	}

	public double getEndtime() {
		return endtime;
	}

	public void setEndtime(double endtime) {
		this.endtime = endtime;
	}

	public int getFromzone() {
		return fromzone;
	}

	public void setFromzone(int fromzone) {
		this.fromzone = fromzone;
	}

	public int getTozone() {
		return tozone;
	}

	public void setTozone(int tozone) {
		this.tozone = tozone;
	}

	public int getPeakhourtype() {
		return peakhourtype;
	}

	public void setPeakhourtype(int peakhourtype) {
		this.peakhourtype = peakhourtype;
	}

}
