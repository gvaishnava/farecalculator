package tigercard.farecalculation.executor.faretype.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigercard.farecalculation.constant.FareType;
import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.executor.faretype.repo.HoursForFareTypeRepo;
import tigercard.farecalculation.model.HoursForFareType;
import tigercard.farecalculation.model.Zone;
import tigercard.farecalculation.utils.DateUtil;

/**
 * Service class to perform business logic to calculate fate type
 *
 */
@Service
public class FareTypeServiceImpl implements FareTypeService {

	/**
	 * Repository class to interact with fare type table in database
	 */
	private HoursForFareTypeRepo faretyperepo;

	/**
	 * Initialize fare type repository class.
	 * 
	 * @param faretyperepo
	 */
	@Autowired
	public FareTypeServiceImpl(HoursForFareTypeRepo faretyperepo) {
		this.faretyperepo = faretyperepo;
	}

	/**
	 * Business logic for calculating fate type
	 * 
	 * @param datetime - time of journey
	 * @param zone     - journey between zones
	 */
	public int checkForFareType(Date datetime, Zone zone) {

		if (isNonPeekHours(datetime, zone)) {
			return FareType.OFF_PEAK_HOURS;
		} else if (isPeekHours(datetime)) {
			return FareType.PEAK_HOURS;
		} else {
			return FareType.OFF_PEAK_HOURS;
		}

	}

	/**
	 * check if fate type is peak hours
	 * 
	 * @param datetime - time of journey
	 * @return boolean - is peak hours or not
	 */
	private boolean isPeekHours(Date datetime) {

		String datehours = datetime.getHours() + "." + datetime.getMinutes();
		double datehoursd = Double.parseDouble(datehours);

		// zone are any as we do not have any zone specified for peak hours calculation
		List<HoursForFareType> list = faretyperepo
				.findByDaysAndStarttimeLessThanEqualAndEndtimeGreaterThanEqualAndFromzoneAndTozone(
						DateUtil.getDay(datetime.getDay()), datehoursd, datehoursd, ZoneType.ZONE_ANY,
						ZoneType.ZONE_ANY);
		if (list.size() >= 1) {
			return true;
		}

		return false;
	}

	/**
	 * check if fate type is off-peak hours
	 * 
	 * @param datetime - time of journey
	 * @param zone     - journey info of zone like from zone to to zone
	 * @return boolean - is off-peak hours or not
	 */
	private boolean isNonPeekHours(Date datetime, Zone zone) {
		String datehours = datetime.getHours() + "." + datetime.getMinutes();
		double datehoursd = Double.parseDouble(datehours);

		List<HoursForFareType> list = faretyperepo
				.findByDaysAndStarttimeLessThanEqualAndEndtimeGreaterThanEqualAndFromzoneAndTozone(
						DateUtil.getDay(datetime.getDay()), datehoursd, datehoursd, zone.getFromzone(),
						zone.getTozone());
		if (list.size() >= 1) {
			return true;
		}
		return false;
	}

}
