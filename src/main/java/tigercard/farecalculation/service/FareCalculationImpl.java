package tigercard.farecalculation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigercard.farecalculation.constant.FareType;
import tigercard.farecalculation.executor.farecap.FareCapFacade;
import tigercard.farecalculation.executor.faretype.service.FareTypeService;
import tigercard.farecalculation.executor.hourlyfare.HourlyFareExecutor;
import tigercard.farecalculation.executor.hourlyfare.OffPeakHoursFareExecutor;
import tigercard.farecalculation.executor.hourlyfare.PeakHourFareExecutor;
import tigercard.farecalculation.model.CommuterJourney;

/**
 * Business logic for calculating total fare for commuter journeys
 *
 */
@Service
public class FareCalculationImpl implements FareCalucation {

	// daily cap zone index for maintaining unique zone traveled for debug purpose
	Set<String> dailycapset = new HashSet<String>();
	// weekly cap zone index for maintaining unique zone traveled for debug purpose
	Set<String> weeklycapset = new HashSet<String>();
	Date currentDate = null;
	Date currentDateHours = null;
	Date oldDate = null;
	int days; // Day of date like MONDAY, TUESDAY
	double weeklyfare = 0;
	double dailyfare = 0;
	double dailycap = 0;
	double weeklycap = 0;
	double totalfare = 0;

	/**
	 * fare type info provider service
	 */
	@Autowired
	private FareTypeService faretypeservice;

	/**
	 * fare cap provider service
	 */
	@Autowired
	private FareCapFacade farecapProvider;

	public FareCalculationImpl() {
		super();
	}

	/**
	 * Function to start fare calculation for single journey between zone
	 * 
	 * @param journey - journey info object contains zone, day of journey, time of
	 *                journey
	 * @throws ParseException
	 */
	@Override
	public void caculateFare(CommuterJourney journey) throws ParseException {

		currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(journey.getDayofjourney());

		String HH = journey.getTimeofjourney().substring(0, journey.getTimeofjourney().indexOf('.'));
		String mm = journey.getTimeofjourney().substring(journey.getTimeofjourney().indexOf('.') + 1,
				journey.getTimeofjourney().length());

		currentDateHours = new SimpleDateFormat("dd/MM/yyyy HH:mm")
				.parse(journey.getDayofjourney() + " " + HH + ":" + mm);

		if (oldDate != null) {
			days = (int) (currentDate.getTime() - oldDate.getTime()) / (1000 * 60 * 60 * 24);

			if (days > 7) {
				// week changed : reset daily and week fare
				// System.out.println("################ week changed : reset daily and week fare
				// ##############");
				dailyfare = 0;
				weeklyfare = 0;
				dailycap = 0;
				weeklycap = 0;
				dailycapset.clear();
				weeklycapset.clear();
				oldDate = null;
				caculateFare(journey);
				totalfare = totalfare + weeklyfare;
			} else if (days >= 1) {

				// Add daily fare calculated to weekly fare
				// System.out.println("################ Add daily fare calculated to weekly fare
				// ##############");
				// next day : reset daily fare
				dailycapset.clear();
				dailycap = 0;
				dailyfare = 0;
				dailycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
				weeklycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
				calculateFareForDay(journey);
				addDailyToWeeklyFare();
				totalfare = weeklyfare;
			} else if (days == 0) {
				// day not changed
				// System.out.println("################ day not changed ##############");
				dailycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
				weeklycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
				// Add first day fare
				calculateFareForDay(journey);
				weeklyfare = dailyfare;
			}
		} else {
			days = 0;
			// Add first day fare
			// System.out.println("################ Add first day fare ##############");
			dailycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
			weeklycapset.add(journey.getZone().getFromzone() + "-" + journey.getZone().getTozone());
			calculateFareForDay(journey);
			weeklyfare = dailyfare;
			oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(journey.getDayofjourney());
		}

		/*
		 * System.out.println("zone:" + journey); System.out.println("currentDateHours:"
		 * + currentDateHours); System.out.println("currentDate:" + currentDate);
		 * System.out.println("oldDate:" + oldDate); System.out.println("dailycapset:" +
		 * dailycapset); System.out.println("weeklycapset:" + weeklycapset);
		 * System.out.println("days:" + days); System.out.println("dailyfare:" +
		 * dailyfare); System.out.println("weeklyfare:" + weeklyfare);
		 * System.out.println("dailycap:" + dailycap); System.out.println("weeklycap:" +
		 * weeklycap); System.out.println("totalfare:" + totalfare);
		 */

	}

	/**
	 * This Method return total fare for journeys done for commuter
	 * 
	 * @return total fare
	 */
	public double getTotalFare() {
		if (totalfare > dailyfare) {
			return totalfare;
		} else {
			return dailyfare;
		}

	}

	/**
	 * fare calculation logic for single journey.
	 * 
	 * @param journey - commuter journey info
	 */
	private void calculateFareForDay(CommuterJourney journey) {
		// fareType
		int faretype = faretypeservice.checkForFareType(currentDateHours, journey.getZone());

		// fare for zone
		HourlyFareExecutor hourlyfareExecutor = null;
		if (FareType.PEAK_HOURS == faretype) {
			hourlyfareExecutor = new PeakHourFareExecutor();

		} else if (FareType.OFF_PEAK_HOURS == faretype) {
			hourlyfareExecutor = new OffPeakHoursFareExecutor();
		}

		double zonefare = hourlyfareExecutor.getHourlyFare(journey.getZone());
		// System.out.println("zonefare" + zonefare);

		// fareCap
		if (dailycap < farecapProvider.dailyFareCap(journey.getZone())) {
			dailycap = farecapProvider.dailyFareCap(journey.getZone());
		}

		if (weeklycap < farecapProvider.weeklyFareCap(journey.getZone())) {
			weeklycap = farecapProvider.weeklyFareCap(journey.getZone());
		}

		if (dailyfare < dailycap) {
			dailyfare = dailyfare + zonefare;
		}

		if (dailyfare >= dailycap) {
			dailyfare = dailycap;
		}

	}

	/**
	 * Method to add Daily fare calculated to Weekly fare
	 */
	private void addDailyToWeeklyFare() {

		if (weeklyfare < weeklycap) {
			weeklyfare = weeklyfare + dailyfare;
		}

		if (weeklyfare >= weeklycap) {
			weeklyfare = weeklycap;
		}

	}

}
