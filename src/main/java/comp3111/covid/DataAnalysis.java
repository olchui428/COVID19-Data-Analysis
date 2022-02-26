package comp3111.covid;

import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 
 * Data Explorer on COVID-19
 * @author <a href=mailto:namkiu@ust.hk>Namkiu Chan</a>
 * @version	1.1
 * 
 */
public class DataAnalysis {
 
	/**
	 * The following function gets parser for the CSV file
	 * @param dataset represents the original data set to be analyzed
	 * @return returns parsed
	 */
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	
	/**
	 * The following function returns the country list from the given data
	 * @param dataset represents the original data set to be analyzed
	 * @param num number of countries
	 * @return array of countries
	 */	
	public static String[] getCountryList(String dataset, int num) {
		boolean all = num == 0 ? true : false;
		int adj_num = all ? 231 : num;
		
		String[] countries = new String[adj_num];
		int i = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			if (!all && i >= num) {
				break;
			}
			String s = rec.get("countries");
			countries[i] = s;
			i++;
		}
		return countries;
	}
	
	/**
	 * The following function returns the number of records of data within a given date range
	 * @param dataset represents the original data set to be analyzed
	 * @param startingDate Starting Date of the Range for which the number of records is requested
	 * @param endingDate Ending Date of the Range for which the number of records is requested
	 * @return number of record data within the range
	 */
	public static int getTotalNumRecord(String dataset, String startingDate, String endingDate) {
		int totalNumRecord = 0;
		boolean started = false;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			if (rec.get("date").equals(startingDate)) {
				started = true; 
			}
			
			if (rec.get("date").equals(endingDate)) {
				totalNumRecord++;
				break;
			}
				
			if (started == true)
				totalNumRecord++;
		}
		
		return totalNumRecord;
	}
	
	/**
	 * The following function returns the list of dates as a string array within a specified date range.
	 * @param dataset represents the original data set to be analyzed
	 * @param startingDate starting Date of the Range for which the list of dates is requested
	 * @param endingDate ending Date of the Range for which the list of dates is requested
	 * @param numRecord number records within the specified date range
	 * @return returns the list of dates as a string array 
	 */
	public static String[] getDateList(String dataset, String startingDate, String endingDate, int numRecord) {
		String[] dateList = new String[numRecord];
		boolean started = false;
		int i = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			if (rec.get("date").equals(startingDate)) { 
				started = true; 
			}
			
			if (rec.get("date").equals(endingDate)) {
				dateList[i] = rec.get("date");
				break;
			}
				
			if (started == true) {
				dateList[i] = rec.get("date");
				i++;
			}
		}

		return dateList;
	}
	
	/**
	 * The following function returns the COVID19 related numeric data for a specified column for a given country over a specified period of time as a float array. 
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param startingDate the starting date of the range for which data is requested
	 * @param endingDate the ending date of the range for which data is requested
	 * @param numRecord number records within the specified date range
	 * @param column the requested column from the CSV for which the data is needed
	 * @return returns a float array of the requested data
	 */
	public static float[] getDataList(String dataset, String Country, LocalDate startingDate, LocalDate endingDate, int numRecord, String column) {
		float[] dataList = new float[numRecord];
		for (int i = 0; i < numRecord; i++) {
			dataList[i] = -1;
		}
//		ChronoUnit.DAYS.between(startingDate, endingDate);
		for (CSVRecord rec : getFileParser(dataset)) {
			if (rec.get("location").equals(Country)) {
				LocalDate currentDate = LocalDate.now();
				try {
					currentDate = new SimpleDateFormat("M/d/yyyy").parse(rec.get("date")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (currentDate.isEqual(startingDate) || currentDate.isEqual(endingDate) || currentDate.isAfter(startingDate) && currentDate.isBefore(endingDate)) {
					String s = rec.get(column);
					int index = (int) ChronoUnit.DAYS.between(startingDate, currentDate);
					if (!s.equals("")) {
						dataList[index] = Float.parseFloat(s);
					} else {
						dataList[index] = -1;
					}
				}	
			}
		}
		
		return dataList;
	}
	
	/**
	 * The following function returns the number of confirmed cases for a given country on a specified date from the data set.
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of confirmed cases as a long type
	 */
	 public static long getConfirmedCasesA1(String dataset, String Country, String date) {
			long confirmedCases = -1;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				if(rec.get("location").equals(Country) && rec.get("date").equals(date)) {
					String s = rec.get("total_cases");
					if (!s.equals("")) {
						confirmedCases = Long.parseLong(s);
					}
					break;
				}
			}
			
			return confirmedCases;
	 }
	 
	/**
	 * The following function returns the number of confirmed cases per 1 million for a given country on a specified date from the data set. 
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of confirmed cases per 1 million as a float type
	 */
	public static float getConfirmedCasesPer1MA1(String dataset, String Country, String date) {
			float confirmedCasesPer1M = -1;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				if (rec.get("location").equals(Country) && rec.get("date").equals(date)) {
					String s = rec.get("total_cases_per_million");

					if (!s.equals("")) {
						confirmedCasesPer1M = Float.parseFloat(s);				
					}
					
					break;
				}			
			}
			
			return confirmedCasesPer1M;			
	 }
	
	/**
	 * The following function returns the number of confirmed deaths for a given country on a specified date from the data set.
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of confirmed deaths as a long type
	 */
	 public static long getConfirmedDeaths(String dataset, String Country, String date) {
			long confirmedDeaths = -1;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				if (rec.get("location").equals(Country) && rec.get("date").equals(date)) {
					String s = rec.get("total_deaths");
					if (!s.equals("")) {
						confirmedDeaths = Long.parseLong(s);
					}
					break;
				}	
				
			}

			return confirmedDeaths;
	 }
	 	 
	/**
	 * The following function returns the number of confirmed deaths per 1 million for a given country on a specified date from the data set. 
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of confirmed deaths per 1 million as a float type
	 */
	public static float getConfirmedDeathsPer1M(String dataset, String Country, String date) {
		float confirmedDeathsPer1M = -1;
		for (CSVRecord rec : getFileParser(dataset)) {
			if (rec.get("location").equals(Country) && rec.get("date").equals(date)) {
				String s = rec.get("total_deaths_per_million");

				if (!s.equals("")) {
					confirmedDeathsPer1M = Float.parseFloat(s);				
				}
				
				break;
			}			
		}
		
		return confirmedDeathsPer1M;
	 }

	/**
	 * The following function returns the number of fully vaccinated individuals for a given country on a specified date from the data set.
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of fully vaccinated individuals as a long type
	 */
	public static long getTotalFullyVac(String dataset, String Country, String date) {
			long fullVac = -1;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				if (rec.get("location").equals(Country)) {
					if (rec.get("date").equals(date)) {
						String s = rec.get("people_fully_vaccinated");
						if (!s.equals("")) {
							fullVac = Long.parseLong(s);
							break;
						}
					}
				}		
			}
			
			return fullVac;
	 }
	
	/**
	 * The following function returns the number of fully vaccinated people per 100 individuals for a given country on a specified date from the data set. 
	 * @param dataset represents the original data set to be analyzed
	 * @param Country the country for which the data is requested
	 * @param date the date for which the data is requested
	 * @return returns the number of fully vaccinated people per 100 individuals as a float type
	 */
	public static float getTotalFullyVacPer100(String dataset, String Country, String date) {
			float fullVac = -1;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				if (rec.get("location").equals(Country)) {
					if (rec.get("date").equals(date)) {
						String s = rec.get("people_fully_vaccinated_per_hundred");
						if (!s.equals("")) {
							fullVac = Float.parseFloat(s);
							break;
						}
					}
				}		
			}
			
			return fullVac;
	 }
 
}