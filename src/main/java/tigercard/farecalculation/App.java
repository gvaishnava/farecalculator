package tigercard.farecalculation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tigercard.farecalculation.model.CommuterJourney;
import tigercard.farecalculation.model.Zone;
import tigercard.farecalculation.service.FareCalucation;

/**
 *  Start your application from here,to read commuter journeys from journeys.txt file
 *  located in resource folder and calculate total fare of journeys
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
		// initialize sprint boot application
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		
		// Read fare calculation bean
		FareCalucation farecalculation = ctx.getBean(FareCalucation.class);

		try {
			// Read journeys file and loop through it
			InputStream journeys = App.class.getClassLoader().getResourceAsStream("journeys.txt");
			BufferedReader journeysbr = new BufferedReader(new InputStreamReader(journeys));
			String line;
			while ((line = journeysbr.readLine()) != null) {
				System.out.println("Inpput Journey : " + line);
				StringTokenizer token = new StringTokenizer(line);
				while (token.hasMoreElements()) {
					String cdate = token.nextToken();
					String time = token.nextToken();
					int fromzone = Integer.parseInt(token.nextToken());
					int tozone = Integer.parseInt(token.nextToken());
					
					// will process fare calculation of journey at a time.
					farecalculation.caculateFare(new CommuterJourney(cdate, time, new Zone(fromzone, tozone)));
				}
			}

		} catch (IOException exp) {
			System.out.println("Error while processing in reading file"+exp.getMessage());
		} catch (Exception exp) {
			System.out.println("Error while processing in fare calcutaion see below stacktrace");
			exp.printStackTrace();
		} 

		System.out.println("TOTAL FARE:" + farecalculation.getTotalFare());
	}

}
