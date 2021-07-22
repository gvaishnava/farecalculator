package tigercard.farecalculation.executor.farecap;

import org.springframework.stereotype.Service;

import tigercard.farecalculation.model.Zone;

/**
 * Fare cap facade gives centralize access of different types of fare cap
 * executor methods It help avoid unnecessary instance creation.
 */
@Service
public class FareCapFacade {

	private FareCapExecutor dailyFareCapExecutor;
	private FareCapExecutor weeklyFareCapExecutor;

	/**
	 * Preinitialize instance of different type of fare cap executor.
	 */
	public FareCapFacade() {
		super();
		dailyFareCapExecutor = new DailyFareCapExecutor();
		weeklyFareCapExecutor = new WeeklyFareCapExecutor();
	}

	/**
	 * Method for calculating daily fare cap for input zone
	 * 
	 * @param zone journey information between zone
	 * @return daily fare cap
	 */
	public double dailyFareCap(Zone zone) {

		return dailyFareCapExecutor.getFareCap(zone);
	}

	/**
	 * Method for calculating weekly fare cap for input zone
	 * 
	 * @param zone journey information between zone
	 * @return weekly fare cap
	 */
	public double weeklyFareCap(Zone zone) {

		return weeklyFareCapExecutor.getFareCap(zone);
	}

}
