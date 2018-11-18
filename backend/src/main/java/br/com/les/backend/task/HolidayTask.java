package br.com.les.backend.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.les.backend.entity.Holiday;
import br.com.les.backend.repository.HolidayRepository;

@Component
public class HolidayTask extends TimerTask {
	
	@Autowired HolidayRepository holidayRepository;

	// state initials
 	private final static String STATE = "SP";
 	// name of the city
 	private final static String CITY = "MOGI_DAS_CRUZES";
 	// API token
 	private final static String TOKEN = "am9zZXdzbzU1QGdtYWlsLmNvbSZoYXNoPTY4MDQxMzAx";	
	// Period in milliseconds
// 	private final static int PERIOD_IN_YEAR = 1;
// 	// used to indicate init date
// 	private final static int ONE_DAY = 0;
// 	// schedule hour
// 	private final static int HOUR = 4;
// 	// schedule minutes
// 	private final static int MINUTE = 0;
 	
// 	@PostConstruct
// 	public void calcBankTask() {
// 		
// 		Timer timer = new Timer();
// 		timer.scheduleAtFixedRate(this, getSchedule(), getPeriod());
// 	}
//	
//	private static Date getSchedule() {
//		
//		Calendar tomorrow = new GregorianCalendar();
//		tomorrow.add(Calendar.DATE, ONE_DAY);
//		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
//		    tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE), HOUR,
//		    MINUTE);
//		return result.getTime();
//		
//	}
//	
//	private static Long getPeriod() {
//		
//		Calendar date = new GregorianCalendar();
//		date.add(Calendar.YEAR, PERIOD_IN_YEAR);
//		long period =
//				date.getTimeInMillis() - System.currentTimeMillis();
//		return period;
//		
//	}
	
	@Override
    public void run() {
		
	    List< Holiday > holidays = new ArrayList<>();
	    holidays = holidayRepository.findAll();
	    List< Holiday > holidays2 = new ArrayList<>();
	    
	    
		for ( int i = 0; i < 3; i++ ) {
			
			LocalDate localDate = LocalDate.now();
			localDate = localDate.plusYears(i);
			
			if ( !(holidays2 = holidayRepository.findByYear(localDate.withDayOfYear(1), localDate.withDayOfYear(365))).isEmpty() ) {
				holidays.removeAll(holidays2);
				continue;
			}
			
			Integer year = localDate.getYear();
			String jsonString = callURL("https://api.calendario.com.br/?ano=" + year + 
					"&estado=" + STATE + "&cidade=" + CITY + "&json=true&token=" + TOKEN);
			ObjectMapper mapper = new ObjectMapper();
	 
			JSONArray jArray = new JSONArray(jsonString);
		    
			Holiday holiday = null;
			for (int j = 0; j < jArray.length(); j++) {
			    JSONObject explrObject = jArray.getJSONObject(j);
			    try {
					holiday = mapper.readValue(explrObject.toString(), Holiday.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    Stream<Holiday> stream = holidays.stream();
			    LocalDate date = holiday.getDate();
			    if ( null != stream.filter(h -> h.getDate().equals(date))
			    	.findAny().orElse(null)) {
			    	continue;
			    }
			    if ( !holiday.getType().equals("Facultativo") && !holiday.getType().equals("Dia Convencional") ) {
				    holidays.add(holiday);
			    }
			}
		}
		
		holidayRepository.saveAll(holidays);
	}
 
	public static String callURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			System.out.println("Exception while calling URL:"+ myURL);
		} 
 
		return sb.toString();
	}

}
