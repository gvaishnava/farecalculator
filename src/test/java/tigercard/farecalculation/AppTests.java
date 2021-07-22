package tigercard.farecalculation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.model.CommuterJourney;
import tigercard.farecalculation.model.Zone;
import tigercard.farecalculation.service.FareCalucation;

/**
 * Integration test for Application to calculate total fare of journeys
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {


	@Autowired
	private FareCalucation farecalculation;

	// fare type of PEAK HOURS or OFF PEAK HOURS
	int faretypeSunday_Hours_19_00_int;
	int faretypeMonday_Hours_19_00_Zone2To1_int;

	// type of fare cap of DAILY or WEEKLY
	int typeoffarecap = 1;

	// fare cap charges
	double farecap;

	Zone zone1to1 = new Zone(ZoneType.ZONE_1, ZoneType.ZONE_1);

	@Test
	void contextLoads() {
	}

	/**
	 * read commuter journeys from journeys.txt file located in resource folder and
	 * calculate total fare of journeys
	 */
	@Test
	public void testInputJourneys() {
		try {
			InputStream journeys = getClass().getClassLoader().getResourceAsStream("journeys.txt");
			BufferedReader journeysbr = new BufferedReader(new InputStreamReader(journeys));
			String line;
			while ((line = journeysbr.readLine()) != null) {
				System.out.println("Input Journey : " + line);
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreElements()) {
					String cdate = token.nextToken();
					String time = token.nextToken();
					int fromzone = Integer.parseInt(token.nextToken());
					int tozone = Integer.parseInt(token.nextToken());
					farecalculation.caculateFare(new CommuterJourney(cdate, time, new Zone(fromzone, tozone)));
				}
			}

			System.out.println("TOTAL FARE:" + farecalculation.getTotalFare());

		} catch (Exception exp) {
			exp.printStackTrace();
		}

		Assert.assertEquals(farecalculation.getTotalFare(), 120.0, 0.001);
	}

}
