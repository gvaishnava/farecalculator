package tigercard.farecalculation.executor.faretype.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tigercard.farecalculation.model.HoursForFareType;

/**
 * Repository call to fetch data base database
 *
 */
public interface HoursForFareTypeRepo extends JpaRepository<HoursForFareType, Long> {

	/**
	 * find fare type based on day of date, between start time and end time, from
	 * zone and to zone
	 * 
	 * @param days      - Day of date like MONDAY,TUESDAY etc..
	 * @param starttime - journey time in hours and minutes like HH:mm
	 * @param endtime   - journey time in hours and minutes like HH:mm
	 * @param fromzone  - journey start zone
	 * @param tozone    - journey end zone
	 * @return
	 */
	public List<HoursForFareType> findByDaysAndStarttimeLessThanEqualAndEndtimeGreaterThanEqualAndFromzoneAndTozone(
			String days, double starttime, double endtime, int fromzone, int tozone);
}
