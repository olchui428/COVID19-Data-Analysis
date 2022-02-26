package comp3111.covid;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DataAnalysisTester{
	String dataset = "COVID_Dataset_v1.0.csv";
	private String dataset_Countries = "CountryList.csv";
	
//	General
	
	@Test
	public void getCountryList() {
		String[] expected = {"Afghanistan", "Africa", "Albania"};
		assertArrayEquals(expected, DataAnalysis.getCountryList(dataset_Countries, 3));
	}
	
	@Test
	public void getNumRecord() {
		assertEquals(2, DataAnalysis.getTotalNumRecord(dataset, "3/4/2021", "3/5/2021"));
	}

	@Test
	public void getNumRecordWithValidInput() {
		assertEquals(2, DataAnalysis.getTotalNumRecord(dataset, "3/4/2021", "3/5/2021"));
	}
	
	@Test
	public void getDataListWithValidInput() {
		assertEquals(-1.0, DataAnalysis.getDataList(dataset, "Africa", LocalDate.parse("2020-03-01"), LocalDate.parse("2020-03-08"), 8, "total_deaths_per_million")[0], 0.1);
	}
	
	@Test
	public void getDateListWithValidInput() {
		assertEquals("3/1/2020", DataAnalysis.getDateList(dataset, "3/1/2020", "3/8/2020", 8)[0]);
	}

//  Confirmed Cases
	
	@Test
	public void getNumberOfCasesWithValidInput() {
		assertEquals(26937, DataAnalysis.getConfirmedCasesA1(dataset, "Canada", "4/13/2020"));
	}
	
	public void getNumberOfCasesWithValidInput2() {
		assertEquals(88415, DataAnalysis.getConfirmedCasesA1(dataset, "Zimbabwe", "7/20/2021"));
	}
	
	@Test
	public void getNumberOfCasesWithInvalidInput1(){
		assertEquals(-1, DataAnalysis.getConfirmedCasesA1(dataset, "Aruba", "3/29/2021"));
	}
	
	@Test
	public void getNumberOfCasesWithInvalidInput2() {
		assertEquals(-1, DataAnalysis.getConfirmedCasesA1(dataset, "Afghanistana", "3/17/2020"));
	}
	
	@Test
	public void getNumberOfCasesPer1MWithValidInput() {
		assertEquals(5948.694, DataAnalysis.getConfirmedCasesPer1MA1(dataset, "Zimbabwe", "7/20/2021"),0.001);
	}
	
	
	@Test
	public void getNumberOfCasesPer1MWithInvalidInput1() {
		assertEquals(-1.0, DataAnalysis.getConfirmedCasesPer1MA1(dataset, "Aruba", "3/29/2021"), 0.1);
	}
	
	@Test
	public void getNumberOfCasesPer1MWithInvalidInput2() {
		assertEquals(-1.0, DataAnalysis.getConfirmedCasesPer1MA1(dataset, "xyzabc", "3/29/2020"), 0.1);
	}
	
	
//	Deaths
	
	@Test
	public void getNumberOfDeathsWithValidInput() {
		assertEquals(2747, DataAnalysis.getConfirmedDeaths(dataset, "Zimbabwe", "7/20/2021"));
	}
	
	@Test
	public void getNumberOfDeathsWithInvalidInput1() {
		assertEquals(-1, DataAnalysis.getConfirmedDeaths(dataset, "Denmark", "3/9/2020"));
	}
	
	@Test
	public void getNumberOfDeathsWithInvalidInput2() {
		assertEquals(-1, DataAnalysis.getConfirmedDeaths(dataset, "Denmarkabc", "3/9/2020"));
	}
	
	@Test
	public void getNumberOfDeathsPer1MWithValidInput() {
		assertEquals(78.453, DataAnalysis.getConfirmedDeathsPer1M(dataset, "Asia", "1/23/2021"), 0.001);
	}
	
	@Test
	public void getNumberOfDeathsPer1MWithInvalidInput1() {
		assertEquals(-1.0, DataAnalysis.getConfirmedDeathsPer1M(dataset, "Aruba", "3/29/2021"), 0.1);
	}
	
	@Test
	public void getNumberOfDeathsPer1MWithInvalidInput2() {
		assertEquals(-1.0, DataAnalysis.getConfirmedDeathsPer1M(dataset, "abc", "3/29/2021"), 0.1);
	}
	
//	Vaccination
	
	@Test
	public void getNumberOfVacWithValidInput() {
		assertEquals(1420625, DataAnalysis.getTotalFullyVac(dataset, "Asia", "1/24/2021"));
	}
	
	@Test
	public void getNumberOfVacWithInvalidInput1() {
		assertEquals(-1, DataAnalysis.getTotalFullyVac(dataset, "Armenia", "5/29/2021"));
	}
	
	@Test
	public void getNumberOfVacWithInvalidInput2() {
		assertEquals(-1, DataAnalysis.getTotalFullyVac(dataset, "Australia", "3/31/2020"));
	}
	
	@Test
	public void getVacRateWithValidInput() {
		assertEquals(5.89, DataAnalysis.getTotalFullyVacPer100(dataset, "Austria", "4/6/2021"), 0.001);
	}
	
	@Test
	public void geVacRateWithInvalidInput1() {
		assertEquals(-1.0, DataAnalysis.getTotalFullyVacPer100(dataset, "Azerbaijan", "5/17/2021"), 0.1);
	}
	
	@Test
	public void getVacRateWithInvalidInput2() {
		assertEquals(-1.0, DataAnalysis.getTotalFullyVacPer100(dataset, "abc", "3/29/2021"), 0.1);
	}

}

