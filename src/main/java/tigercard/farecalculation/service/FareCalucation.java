package tigercard.farecalculation.service;

import java.text.ParseException;

import tigercard.farecalculation.model.CommuterJourney;

/**
 * Fare calculation business function calling interface
 *
 */
public interface FareCalucation {

	/**
	 * Function to start fare calculation for single journey between zone
	 * 
	 * @param journey - journey info object contains zone, day of journey, time of
	 *                journey
	 * @throws ParseException
	 */
	void caculateFare(CommuterJourney journey) throws ParseException;

	/**
	 * This Method return total fare for journeys done for commuter
	 * 
	 * @return total fare
	 */
	double getTotalFare();

}
