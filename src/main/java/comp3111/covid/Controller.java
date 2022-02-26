package comp3111.covid;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.awt.desktop.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.time.temporal.ChronoUnit;


/**
 * Controller class controls the key functionalities of the User Interface of the project Data Explorer on COVID-19.
 * @author kalpagoels
 *
 */
public class Controller implements Initializable{
	
	/**
	 * Stores the file name of the original data set to be analyzed
	 */
    private String dataset = "COVID_Dataset_v1.0.csv";
    /**
     * Stores the file name of the file that contains the list of countries in our data set
     */
    private String dataset_Countries = "CountryList.csv";
    /**
     * Stores the list of countries as an array.
     */
	private String[] countryList = DataAnalysis.getCountryList(dataset_Countries, 0);
	/**
	 * Stores the number of countries in our dataset.
	 */
	private int numCountries = countryList.length;
	/**
	 * Line Chart Object created for Task A2
	 */
    private LineChart newlinechartA2;
    
	/**
	 * Line Chart Object created for Task B2
	 */
    private LineChart newlinechartB2;
    
	/**
	 * Line Chart Object created for Task C2
	 */
    private LineChart newlinechartC2;

    @FXML
    private AnchorPane anchorA2;    
    
    @FXML
    private AnchorPane anchorB2;

    @FXML
    private AnchorPane anchorC2;
    
    @FXML
    private LineChart<Number, Number> chartB2;

    @FXML
    private Button buttonConfirmedCases;

    @FXML
    private Button buttonConfirmedDeaths;

    @FXML
    private Button buttonRateOfVaccination;

    @FXML
    private Button buttonShowResultA1;
    
    @FXML
    private Button buttonClearA1;
    
    @FXML
    private Button buttonClearA2;

    @FXML
    private Button dowloaddataA1;
    
    @FXML
    private Button buttonClearB1;

    @FXML
    private Button dowloaddataB1;
    
    @FXML
    private Button buttonClearB2;
    
    @FXML
    private Button buttonShowResultA2;

    @FXML
    private Button buttonShowResultB1;

    @FXML
    private Button buttonShowResultB2;
    
    @FXML
    private Button dowloaddataA2;
    
    @FXML
    private Button dowloaddataB2;

    @FXML
    private Button buttonShowResultC1;

    @FXML
    private Button buttonShowResultC2;
    
    @FXML
    private Button dowloaddataC2;
    
    @FXML
    private DatePicker datepickerA1;
    
    @FXML
    private DatePicker startingdatepickerA2;
    
    @FXML
    private DatePicker endingdatepickerA2; 
    
    @FXML
    private LineChart<String, Number> chartA2;
    
    @FXML
    private DatePicker datepickerB1;
    
    @FXML
    private DatePicker datepickerC1;
    
    @FXML
    private DatePicker datepickerStartB2;
    
    @FXML
    private DatePicker datepickerEndB2;
    
    @FXML
    private DatePicker datepickerStartC2;
    
    @FXML
    private DatePicker datepickerEndC2;
    
    @FXML
    private MenuButton menubuttonCountryA1;

    @FXML
    private MenuButton menubuttonCountryA2;

    @FXML
    private MenuButton menubuttonCountryB2;

    @FXML
    private MenuButton menubuttonCountryB1;

    @FXML
    private MenuButton menubuttonCountryC1;

    @FXML
    private MenuButton menubuttonCountryC2;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private Tab tabReport1;

    @FXML
    private Tab tabReport2;

    @FXML
    private Tab tabReport3;

    @FXML
    private Tab tabTaskZero;
    
    @FXML
    private TableView<cases> tableA1;

    @FXML
    private TableView<deaths> tableB1;
    
    @FXML
    private TableView<vaccinations> tableC1;

    @FXML
    private TextArea textAreaConsole;

    @FXML
    private TextField textfieldDataset;

    @FXML
    private TextField textfieldDateA1;

    @FXML
    private TextField textfieldDateB1;

    @FXML
    private TextField textfieldDateC1;

    @FXML
    private TextField textfieldEndDateC2;

    @FXML
    private TextField textfieldEndingDateA2;

    @FXML
    private TextField textfieldISO;

    @FXML
    private TextField textfieldStartingDateA2;

    @FXML
    private TableColumn<cases, String> CountryColA1;
    
    @FXML
    private TableColumn<cases, String> totalCasesColA1;

    @FXML
    private TableColumn<cases, String> totalCasesPer1MColA1;
    
    @FXML
    private TableColumn<deaths, String> CountryColB1;

    @FXML
    private TableColumn<deaths, String> totalDeathsColB1;

    @FXML
    private TableColumn<deaths, String> totalDeathsPer1MColB1;
    
    @FXML
    private TableColumn<vaccinations, String> CountryColC1;

    @FXML
    private TableColumn<vaccinations, String> totalFullyVacColC1;

    @FXML
    private TableColumn<vaccinations, String> totalFullyVacPer100ColC1;
    
    @FXML
    private NumberAxis yaxisB2;
    
    @FXML
    private Text TableDateA1;
    
    @FXML
    private Text TableDateB1;
    
    @FXML
    private Text TableDateC1;
    
    @FXML
    private CheckBox[] CountriesA2 = new CheckBox[numCountries];

    @FXML
    private TableColumn<vaccinations, String> totalFullyVacC1;

    @FXML
    private TableColumn<vaccinations, String> totalFullyVacPer100C1;
    
    @FXML
    private Text DateErrorMsgA1;
    
    @FXML
    private Text CountryErrorMsgA1;

    @FXML
    private Text StartDateErrorMsgA2;
    
    @FXML
    private Text EndDateErrorMsgA2;
    
    @FXML
    private Text CountryErrorMsgA2;
    
    @FXML
    private Text ErrorMsgA2;
    
    @FXML
    private Text DateErrorMsgB1;
    
    @FXML
    private Text CountryErrorMsgB1;
    
    @FXML
    private Text StartDateErrorMsgB2;
    
    @FXML
    private Text EndDateErrorMsgB2;
    
    @FXML
    private Text CountryErrorMsgB2;
    
    @FXML
    private Text ErrorMsgB2;
    
    @FXML
    private Text DateErrorMsgC1;
    
    @FXML
    private Text CountryErrorMsgC1;
    
    @FXML
    private Text StartDateErrorMsgC2;
    
    @FXML
    private Text EndDateErrorMsgC2;
    
    @FXML
    private Text CountryErrorMsgC2;
    
    @FXML
    private Text ErrorMsgC2;
    
    @FXML
    private CheckBox[] CountriesA1 = new CheckBox[numCountries];
    
    @FXML
    private CheckBox[] CountriesB1 = new CheckBox[numCountries];
    
    @FXML
    private CheckBox[] CountriesB2 = new CheckBox[numCountries];
    
    @FXML
    private CheckBox[] CountriesC1 = new CheckBox[numCountries];
    
    @FXML
    private CheckBox[] CountriesC2 = new CheckBox[numCountries];
    
    @FXML
    private VBox vboxTest;  
               

    /**
     * This Class is responsible for Task A1/A2.
     * Contains the following attributes for a specified date: country name, total number of confirmed cases, total number of confirmed cases per 1 Million.
     * @author kalpagoels
     */
    public static class cases {
    	private String Country = "";
    	private String totalCases = "";
    	private String totalCasesPer1M = "";
    	
    	/**
    	 * cases Constructor assigning values to Country, totalCases, totalCasesPer1M
    	 * @param realCountry the country name to be assigned
    	 * @param realTotalCases the number of total confirmed cases to be assigned
    	 * @param realTotalCasesPer1M the number for total confirmed cases per 1 million to be assigned
    	 */
    	private cases(String realCountry, String realTotalCases, String realTotalCasesPer1M) {
    		this.Country = realCountry;
    		this.totalCases = realTotalCases;
    		this.totalCasesPer1M = realTotalCasesPer1M;
    	}
    	
    	/**
    	 * Accessors to output the country name of the class instance 
    	 * @return The name of the country
    	 */
    	public String getCountry() {
    		return Country;
    	}
    	
    	/**
    	 * Mutator to set the country name of the class instance
    	 * @param realCountry the country name to be assigned
    	 */
    	public void setCountry(String realCountry) {
    		this.Country = realCountry;
    	}
    	
    	/**
    	 * Accessors to output the total confirmed cases of the class instance 
    	 * @return The number of total confirmed cases
    	 */
    	public String getTotalCases() {
    		return totalCases;
    	}
    	
    	/**
    	 * Mutator to set the total confirmed cases of the class instance
    	 * @param realTotalCases the number of total confirmed cases to be assigned
    	 */
    	public void setTotalCases(String realTotalCases) {
    		this.totalCases = realTotalCases;
    	}
    	
    	/**
    	 * Accessors to output the total confirmed cases per 1 million of the class instance 
    	 * @return The number for total confirmed cases per 1 million
    	 */
    	public String getTotalCasesPer1M() {
    		return totalCasesPer1M;
    	}
    	
    	/**
    	 * Mutator to set the total confirmed cases per 1 million of the class instance
    	 * @param realTotalCasesPer1M the number for total confirmed cases per 1 million to be assigned
    	 */
    	public void setTotalCasesPer1M(String realTotalCasesPer1M) {
    		this.totalCasesPer1M = realTotalCasesPer1M;
    	}
    }    


    /**
     * This Class is responsible for Task B1/B2.
     * Contains the following attributes for a specified date: country name, total number of deaths, number of deaths per 1 Million.
     * @author kalpagoels
     */
    public static class deaths {
    	private String Country = "";
    	private String totalDeaths = "";
    	private String totalDeathsPer1M = "";


    	/**
    	 * deaths Constructor assigning values to Country, totalDeaths, totalDeathsPer1M
    	 * @param realCountry the country name to be assigned
    	 * @param realTotalDeaths the number of total deaths to be assigned
    	 * @param realTotalDeathsPer1M the number for total deaths per 1 million to be assigned
    	 */
    	private deaths(String realCountry, String realTotalDeaths, String realTotalDeathsPer1M) {
    		this.Country = realCountry;
    		this.totalDeaths = realTotalDeaths;
    		this.totalDeathsPer1M = realTotalDeathsPer1M;
    	}
    	
    	/**
    	 * Accessors to output the country name of the class instance 
    	 * @return The name of the country
    	 */
    	public String getCountry() {
    		return Country;
    	}
    	
    	/**
    	 * Mutator to set the country name of the class instance
    	 * @param realCountry the country name to be assigned
    	 */
    	public void setCountry(String realCountry) {
    		this.Country = realCountry;
    	}
    	
    	/**
    	 * Accessors to output the total deaths of the class instance 
    	 * @return The number of total deaths
    	 */
    	public String getTotalDeaths() {
    		return totalDeaths;
    	}
    	
    	/**
    	 * Mutator to set the total deaths of the class instance
    	 * @param realTotalDeaths the number of total deaths to be assigned
    	 */
    	public void setTotalDeaths(String realTotalDeaths) {
    		this.totalDeaths = realTotalDeaths;
    	}
    	
    	/**
    	 * Accessors to output the total deaths per 1 million of the class instance 
    	 * @return The number for total deaths per 1 million
    	 */
    	public String getTotalDeathsPer1M() {
    		return totalDeathsPer1M;
    	}
    	
    	/**
    	 * Mutator to set the number of deaths per 1 million of the class instance
    	 * @param realTotalDeathsPer1M the number for total deaths per 1 million to be assigned
    	 */
    	public void setTotalDeathsPer1M(String realTotalDeathsPer1M) {
    		this.totalDeathsPer1M = realTotalDeathsPer1M;
    	}
    }
    
	/**
	 * This Class is responsible for Task C1/C2.
     * Contains the following attributes for a specified date: country name, total number of fully vaccinated individuals, number of fully vaccinated individuals per 100 people.
	 * @author kalpagoels
	 */
    public static class vaccinations {
    	private String Country = "";
    	private String totalFullyVac = "0";
    	private String totalFullyVacPer100 = "0";
    	
    	/**
    	 * vaccinations Constructor assigning values to Country, totalFullyVac, totalFullyVacPer100
    	 * @param realCountry the country name to be assigned
    	 * @param realTotalFullyVac the number of total fully vaccinated individuals to be assigned
    	 * @param realTotalFullyVacPer100 the number for total fully vaccinated individuals per 100 people to be assigned
    	 */
    	private vaccinations(String realCountry, long realTotalFullyVac, float realTotalFullyVacPer100) {
    		this.Country = realCountry;
    		this.totalFullyVac = realTotalFullyVac == -1 ? "NA" : Long.toString(realTotalFullyVac);
    		this.totalFullyVacPer100 = realTotalFullyVacPer100 == -1 ? "NA" : Float.toString(realTotalFullyVacPer100);
    	}
    	
    	/**
    	 * Accessors to output the country name of the class instance 
    	 * @return The name of the country
    	 */
    	public String getCountry() {
    		return Country;
    	}
    	
    	/**
    	 * Accessors to output the total fully vaccinated individuals of the class instance 
    	 * @return The number of total fully vaccinated
    	 */
    	public String getTotalFullyVac() {
    		return totalFullyVac;
    	}
    	
    	/**
    	 * Accessors to output the total fully vaccinated individuals per 100 people of the class instance 
    	 * @return The number for total fully vaccinated per 100 people
    	 */
    	public String getTotalFullyVacPer100() {
    		return totalFullyVacPer100;
    	}
    }
    
    /**
     * Generates Table for Task A1 and handles its validation checking.
     * @param event
     */
    @FXML
    void doShowResultA1(ActionEvent event) {
    	tableA1.getItems().clear();
    	DateErrorMsgA1.setText("");
    	CountryErrorMsgA1.setText("");
    	TableDateA1.setText("Number of Confirmed COVID-19 Cases");
    	int numSelected = 0;
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesA1[i].isSelected()) {
    			numSelected++;
    		}
    	}

    	if (datepickerA1.getValue() == null) {
    		DateErrorMsgA1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
        	
        	if (numSelected == 0)
        		CountryErrorMsgA1.setText("Please pick at least one country.");
        		
        	return;
    	}

    	LocalDate inputtedDate = datepickerA1.getValue();
    	
		if (numSelected == 0) {
			CountryErrorMsgA1.setText("Please pick at least one country.");
			return;
		}
    	
		String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		
		for (int i = 0; i < numCountries; i++) {
			if(CountriesA1[i].isSelected()) {
				long confirmedCases = DataAnalysis.getConfirmedCasesA1(dataset, CountriesA1[i].getText(), date);
				float confirmedCasesPer1M = DataAnalysis.getConfirmedCasesPer1MA1(dataset, CountriesA1[i].getText(), date);		
				if (confirmedCases == -1) {
					if (confirmedCasesPer1M == -1) {
						tableA1.getItems().add(new cases(CountriesA1[i].getText(), "NA", "NA"));
					}
					else {
						tableA1.getItems().add(new cases(CountriesA1[i].getText(), "NA", String.valueOf(confirmedCasesPer1M)));
					}
				}
				else if(confirmedCasesPer1M == -1){
					tableA1.getItems().add(new cases(CountriesA1[i].getText(), String.valueOf(confirmedCases), "NA"));
				}
				else {
					tableA1.getItems().add(new cases(CountriesA1[i].getText(), String.valueOf(confirmedCases), String.valueOf(confirmedCasesPer1M)));
				}
			}
		}

		TableDateA1.setText("Number of Confirmed COVID-19 Cases as of: " + inputtedDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")));
    }     
    
    /**
     * Clears all entries as well as the table in Task A1.
     * @param event
     */
    @FXML    
    void clearA1(ActionEvent event) {
    	tableA1.getItems().clear();
    	datepickerA1.setValue(null);
    	DateErrorMsgA1.setText("");
    	CountryErrorMsgA1.setText("");
    	TableDateA1.setText("Number of Confirmed COVID-19 Cases");    	
        for (int i = 0; i < numCountries; i++) {
    		if (CountriesA1[i].isSelected()) {
    			CountriesA1[i].setSelected(false);
    		}
        }
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task A1. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadA1(ActionEvent event) throws Exception
    {
    	tableA1.getItems().clear();
    	DateErrorMsgA1.setText("");
    	CountryErrorMsgA1.setText("");
    	TableDateA1.setText("Number of Confirmed COVID-19 Cases");
    	int numSelected = 0;
    	for(int i = 0; i < numCountries; i++) {
    		if(CountriesA1[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	if (datepickerA1.getValue() == null) {
    		DateErrorMsgA1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
        	
        	if (numSelected == 0)
        		CountryErrorMsgA1.setText("Please pick at least one country.");
        		
        	return;
    	}
    	
    	LocalDate inputtedDate = datepickerA1.getValue();
		
		if (numSelected == 0) {
    		CountryErrorMsgA1.setText("Please pick at least one country.");
    		
    		return;
		}
    	
    	String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	Writer writer = null;   
    	try {
    		File file = new File ("..\\ConfirmedCasesTable.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country, Total Cases, Total Cases (per 1M)\n");
    		for (int i = 0; i < numCountries; i++){
    			if (CountriesA1[i].isSelected()) {
    				String text = CountriesA1[i].getText();
    				long confirmedCases = DataAnalysis.getConfirmedCasesA1(dataset, CountriesA1[i].getText(), date);
    				float confirmedCasesPer1M = DataAnalysis.getConfirmedCasesPer1MA1(dataset, CountriesA1[i].getText(), date);
    				
    				if(confirmedCases == -1) {
    					if(confirmedCasesPer1M == -1) {
    						text = text + ",NA,NA\n";
    					}
    					else {
    						text = text + ",NA," + String.valueOf(confirmedCasesPer1M) + "\n";
    					}
    				}
    				else if (confirmedCasesPer1M == -1) {
    					text = text + "," + String.valueOf(confirmedCases) + ",NA\n";
    				}
    				else {
    					text = text + "," + String.valueOf(confirmedCases) + "," + String.valueOf(confirmedCasesPer1M) + "\n";
    				}
    				
    				writer.write(text);
    			}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * Generates Chart for Task A2 and handles its validation checking.
     * @param event
     */
    @FXML
    void doShowResultA2 (ActionEvent event) {
    	StartDateErrorMsgA2.setText("");
    	EndDateErrorMsgA2.setText("");
    	CountryErrorMsgA2.setText("");
    	ErrorMsgA2.setText("");
    	int numSelected = 0;
    	anchorA2.getChildren().remove(newlinechartA2);
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesA2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (startingdatepickerA2.getValue() == null) {
    		StartDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (endingdatepickerA2.getValue() == null) {
    			EndDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgA2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
		if (endingdatepickerA2.getValue() == null) {
			EndDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgA2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgA2.setText("Please pick at least one country.");
			return;
		}
		
    	String startingDate = startingdatepickerA2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = endingdatepickerA2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		boolean missing = false;
		numRecord = DataAnalysis.getTotalNumRecord("COVID_Dataset_v1.0.csv", startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList("COVID_Dataset_v1.0.csv", startingDate, endingDate, numRecord);
    	CategoryAxis xAxis = new CategoryAxis();
    	xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(dateList))); 
    	xAxis.setAutoRanging(false);
    	NumberAxis yAxis = new NumberAxis();
    	

    	newlinechartA2 = new LineChart<String, Number>(xAxis, yAxis);
		newlinechartA2.setTitle("Cumulative Confirmed COVID-19 Cases (per 1M)");
    	newlinechartA2.setLayoutX(33);
    	newlinechartA2.setLayoutY(254);
    	newlinechartA2.setPrefHeight(316.0);
    	newlinechartA2.setPrefWidth(625.0);
    	newlinechartA2.setCreateSymbols(false);
    	anchorA2.getChildren().add(newlinechartA2);
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesA2[i].isSelected()) {
    			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    			float[] dataList = DataAnalysis.getDataList(dataset, CountriesA2[i].getText(), startingdatepickerA2.getValue(), endingdatepickerA2.getValue(), numRecord, "total_cases_per_million");
    			
    			for (int j = 0; j < numRecord; j++) {
    				
    				if (dataList[j] == -1.0) {
    					missing = true;
    				}
    				else
    					series.getData().add(new XYChart.Data<String, Number>(dateList[j], dataList[j]));
    			}  
    			
    			series.setName(CountriesA2[i].getText());

    	    	newlinechartA2.getData().add(series);
    	    	if (missing == true) {
    	    		ErrorMsgA2.setText("If a country/region line is missing, there is no data for selected range.");
    	    	}
    		}
    	}    	
		
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task A2. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadA2(ActionEvent event) throws Exception
    {
    	StartDateErrorMsgA2.setText("");
    	EndDateErrorMsgA2.setText("");
    	CountryErrorMsgA2.setText("");
    	ErrorMsgA2.setText("");
    	int numSelected = 0;
    	anchorA2.getChildren().remove(newlinechartA2);

    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesA2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (startingdatepickerA2.getValue() == null) {
    		StartDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (endingdatepickerA2.getValue() == null) {
    			EndDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgA2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
		if (endingdatepickerA2.getValue() == null) {
			EndDateErrorMsgA2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgA2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgA2.setText("Please pick at least one country.");
			return;
		}
    	
    	String startingDate = startingdatepickerA2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = endingdatepickerA2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		numRecord = DataAnalysis.getTotalNumRecord("COVID_Dataset_v1.0.csv", startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList("COVID_Dataset_v1.0.csv", startingDate, endingDate, numRecord);
    	Writer writer = null;

    	try {
    		File file = new File ("..\\CumulativeConfirmedCasesPer1M.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country,Date,Cumulative Cases (per 1M)\n");
    		for (int i = 0; i < numCountries; i++){
	    		if (CountriesA2[i].isSelected()) {
	    			float[] dataList = DataAnalysis.getDataList(dataset, CountriesA2[i].getText(), startingdatepickerA2.getValue(), endingdatepickerA2.getValue(), numRecord, "total_cases_per_million");
	    			for (int j = 0; j < numRecord; j++) {
	    				String text = CountriesA2[i].getText();
	    				text = text + "," + dateList[j];
	    				
	    				if (dataList[j] != -1.0) {
	    					text = text + "," + dataList[j] + "\n";
	    				}
	    				else
	    					text = text + ",NA\n";
	    				
	    				writer.write(text);
	    			}  		
	    		}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * Clears all entries as well as the chart in Task A2.
     * @param event
     */
    @FXML    
    void clearA2(ActionEvent event) {
    	startingdatepickerA2.setValue(null);
    	endingdatepickerA2.setValue(null);
    	CountryErrorMsgA2.setText("");
    	StartDateErrorMsgA2.setText("");
    	EndDateErrorMsgA2.setText("");
    	ErrorMsgA2.setText("");
        for (int i = 0; i < numCountries; i++) {
    		  if (CountriesA2[i].isSelected()) {
    			  CountriesA2[i].setSelected(false);
          }
        }
        anchorA2.getChildren().remove(newlinechartA2);
    	if (newlinechartA2 != null)
    		newlinechartA2.getData().clear();
    }
    
    /**
     * Generates Table for Task B1 and handles its validation checking.
     * @param event
     */ 
    @FXML
    void doShowResultB1(ActionEvent event) {
    	tableB1.getItems().clear();
    	DateErrorMsgB1.setText("");
    	CountryErrorMsgB1.setText("");
    	TableDateB1.setText("Number of Confirmed COVID-19 Deaths");
    	int numSelected = 0;
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB1[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	if (datepickerB1.getValue() == null) {
    		DateErrorMsgB1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgB1.setText("Please pick at least one country.");
        		
        	return;
    	}
    	
    	LocalDate inputtedDate = datepickerB1.getValue();
		
		if (numSelected == 0) {
			CountryErrorMsgB1.setText("Please pick at least one country.");
			return;
		}

    	
    	String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB1[i].isSelected()) {
    			long confirmedDeaths = DataAnalysis.getConfirmedDeaths(dataset, CountriesB1[i].getText(), date);
    			float confirmedDeathsPer1M = DataAnalysis.getConfirmedDeathsPer1M(dataset, CountriesB1[i].getText(), date);
    			if (confirmedDeaths == -1) {
    				if (confirmedDeathsPer1M == -1) {
    					tableB1.getItems().add(new deaths(CountriesB1[i].getText(), "NA", "NA"));
    				}
    				else
    					tableB1.getItems().add(new deaths(CountriesB1[i].getText(), "NA", String.valueOf(confirmedDeathsPer1M)));
    			}
    			else if (confirmedDeathsPer1M == -1)
    				tableB1.getItems().add(new deaths(CountriesB1[i].getText(), String.valueOf(confirmedDeaths), "NA"));
    			else
    				tableB1.getItems().add(new deaths(CountriesB1[i].getText(), String.valueOf(confirmedDeaths), String.valueOf(confirmedDeathsPer1M)));
    		}
    	}
    	
    	TableDateB1.setText("Number of Confirmed COVID-19 Deaths as of " + inputtedDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")));
    } 
    
    /**
     * Clears all entries as well as the table in Task B1.
     * @param event
     */
    @FXML    
    void clearB1(ActionEvent event) {
    	tableB1.getItems().clear();
    	datepickerB1.setValue(null);
    	DateErrorMsgB1.setText("");
    	CountryErrorMsgB1.setText("");
    	TableDateB1.setText("Number of Confirmed COVID-19 Deaths");
        for (int i = 0; i < numCountries; i++) {
    		if (CountriesB1[i].isSelected()) {
    			CountriesB1[i].setSelected(false);
    		}
        }
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task B1. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadB1(ActionEvent event) throws Exception
    {
    	tableB1.getItems().clear();
    	DateErrorMsgB1.setText("");
    	CountryErrorMsgB1.setText("");
    	TableDateB1.setText("Number of Confirmed COVID-19 Deaths");
    	int numSelected = 0;
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB1[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	if (datepickerB1.getValue() == null) {
    		DateErrorMsgB1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
        	
        	if (numSelected == 0)
        		CountryErrorMsgB1.setText("Please pick at least one country.");
        		
        	return;
    	}
    	
    	LocalDate inputtedDate = datepickerB1.getValue();
		
		if (numSelected == 0) {
    		CountryErrorMsgB1.setText("Please pick at least one country.");
    		
    		return;
		}
    	
    	String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	Writer writer = null;

    	try {
    		File file = new File ("..\\ConfirmedDeathsTable.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country,Total Deaths,Total Deaths (per 1M)\n");
    		for (int i = 0; i < numCountries; i++){
    			if (CountriesB1[i].isSelected()) {
    				String text = CountriesB1[i].getText();
        			long confirmedDeaths = DataAnalysis.getConfirmedDeaths(dataset, CountriesB1[i].getText(), date);
        			float confirmedDeathsPer1M = DataAnalysis.getConfirmedDeathsPer1M(dataset, CountriesB1[i].getText(), date);
        			if (confirmedDeaths == -1) {
        				if (confirmedDeathsPer1M == -1) {
        					text = text + ",NA,NA\n";
        				}
        				else
        					text = text + ",NA," + String.valueOf(confirmedDeathsPer1M) + "\n";
        			}
        			else if (confirmedDeathsPer1M == -1)
        				text = text + "," + String.valueOf(confirmedDeaths) + ",NA\n";
        			else
        				text = text + "," + String.valueOf(confirmedDeaths) + "," + String.valueOf(confirmedDeathsPer1M) + "\n";
				
    				writer.write(text);
    			}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * Generates Table for Task C1 and handles its validation checking.
     * @param event
     */ 
    @FXML
    void doShowResultC1(ActionEvent event) {
    	tableC1.getItems().clear();
    	int numSelected = 0;
    	boolean proceed = true;
    	LocalDate inputtedDate = datepickerC1.getValue();
    	
//    	Check Invalid Date
    	if (datepickerC1.getValue() == null) {
    		DateErrorMsgC1.setText("Please pick a date.");
    		proceed = false;
    	} else {
    		boolean isBefore = inputtedDate.isBefore(LocalDate.parse("2020-03-01"));
        	boolean isAfter = inputtedDate.isAfter(LocalDate.parse("2021-07-20"));
    		if (isBefore == true || isAfter == true) {
    			DateErrorMsgC1.setText("Please pick a date between 2020-03-01 and 2021-07-20.");
    			proceed = false;
    		} else {
    			DateErrorMsgC1.setText("");
    		}
    	}
    	
//    	Check Empty Countries
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesC1[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	if (numSelected == 0) {
    		CountryErrorMsgC1.setText("Please pick at least one country.");
    		proceed = false;
    	} else {
    		CountryErrorMsgC1.setText("");
    	}
    	
//    	No invalid entries --> output table
    	if (proceed) {
	    	String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
	    	for (int i = 0; i < numCountries; i++) {
	    		if (CountriesC1[i].isSelected()) {
	    			String country = CountriesC1[i].getText();
	    			long vac = DataAnalysis.getTotalFullyVac(dataset, country, date);
	    			float vacRate = DataAnalysis.getTotalFullyVacPer100(dataset, country, date);
	    			tableC1.getItems().add(new vaccinations(country, vac, vacRate));
	    		}
	    	}
    	}
    	
    	TableDateC1.setText("Rate of Vaccination against COVID-19 " + inputtedDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")));
    } 

    /**
     * Clears all entries as well as the table in Task C1.
     * @param event
     */
    @FXML
    void clearC1(ActionEvent event) {
    	tableC1.getItems().clear();
    	datepickerC1.setValue(null);
    	DateErrorMsgC1.setText("");
    	CountryErrorMsgC1.setText("");
        for (int i = 0; i < numCountries; i++) {
    		  if (CountriesC1[i].isSelected()) {
    			  CountriesC1[i].setSelected(false);
    		  }
        }
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task C1. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadC1(ActionEvent event) throws Exception
    {
    	DateErrorMsgC1.setText("");
    	CountryErrorMsgC1.setText("");
    	int numSelected = 0;
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesC1[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (datepickerC1.getValue() == null) {
    		DateErrorMsgC1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
        	if (numSelected == 0) {
        		CountryErrorMsgC1.setText("Please pick at least one country.");
        	}
        	
        	return;
    	}
    	
    	
    	LocalDate inputtedDate = datepickerC1.getValue();
    	
		boolean isBefore = inputtedDate.isBefore(LocalDate.parse("2020-03-01"));
		boolean isAfter = inputtedDate.isAfter(LocalDate.parse("2021-07-20"));
    	
		if (isBefore == true || isAfter == true) {
			DateErrorMsgC1.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
        	
        	if (numSelected == 0) {
        		CountryErrorMsgC1.setText("Please pick at least one country.");
        	}
        	
        	return;
		}

    	if (numSelected == 0) {
    		CountryErrorMsgC1.setText("Please pick at least one country.");
    		return;
    	}
    	
    	String date = inputtedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	Writer writer = null;   
    	
    	try {
    		File file = new File ("..\\VaccinationRateTable.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country,Fully Vaccinated,Rate of Vaccination (%)\n");
    		for (int i = 0; i < numCountries; i++){
    			if (CountriesC1[i].isSelected()) {
    				String text = CountriesC1[i].getText();
        			long vac = DataAnalysis.getTotalFullyVac(dataset, CountriesC1[i].getText(), date);
        			float vacRate = DataAnalysis.getTotalFullyVacPer100(dataset, CountriesC1[i].getText(), date);
        			if (vac == -1) {
        				if (vacRate == -1) {
        					text = text + ",NA,NA\n";
        				}
        				else
        					text = text + ",NA," + String.valueOf(vacRate) + "\n";
        			}
        			else if (vacRate == -1)
        				text = text + "," + String.valueOf(vac) + ",NA\n";
        			else
        				text = text + "," + String.valueOf(vac) + "," + String.valueOf(vacRate) + "\n";
				
    				writer.write(text);
    			}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }

    /**
     * Generates Chart for Task B2 and handles its validation checking.
     * @param event
     */
    @FXML
    void doShowResultB2(ActionEvent event) {
    	StartDateErrorMsgB2.setText("");
    	EndDateErrorMsgB2.setText("");
    	CountryErrorMsgB2.setText("");
    	ErrorMsgB2.setText("");
    	int numSelected = 0;
    	anchorB2.getChildren().remove(newlinechartB2);

    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (datepickerStartB2.getValue() == null) {
    		StartDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (datepickerEndB2.getValue() == null) {
    			EndDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgB2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
		if (datepickerEndB2.getValue() == null) {
			EndDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgB2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgB2.setText("Please pick at least one country.");
			return;
		}
		
    	String startingDate = datepickerStartB2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = datepickerEndB2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		boolean missing = false;
		numRecord = DataAnalysis.getTotalNumRecord("COVID_Dataset_v1.0.csv", startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList("COVID_Dataset_v1.0.csv", startingDate, endingDate, numRecord);
    	CategoryAxis xAxis = new CategoryAxis();
    	xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(dateList))); 
    	xAxis.setAutoRanging(false);
    	NumberAxis yAxis = new NumberAxis();
    	

    	newlinechartB2 = new LineChart<String, Number>(xAxis, yAxis);
		newlinechartB2.setTitle("Cumulative Confirmed COVID-19 Deaths (per 1M)");
    	newlinechartB2.setLayoutX(33);
    	newlinechartB2.setLayoutY(254);
    	newlinechartB2.setPrefHeight(316.0);
    	newlinechartB2.setPrefWidth(625.0);
    	newlinechartB2.setCreateSymbols(false);
    	anchorB2.getChildren().add(newlinechartB2);
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB2[i].isSelected()) {
    			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    			float[] dataList = DataAnalysis.getDataList(dataset, CountriesB2[i].getText(), datepickerStartB2.getValue(), datepickerEndB2.getValue(), numRecord, "total_deaths_per_million");
    			
    			for (int j = 0; j < numRecord; j++) {
    				
    				if (dataList[j] == -1.0) {
    					missing = true;
    				}
    				else
    					series.getData().add(new XYChart.Data<String, Number>(dateList[j], dataList[j]));
    			}  
    			
    			series.setName(CountriesB2[i].getText());

    	    	newlinechartB2.getData().add(series);
    	    	if (missing == true) {
    	    		ErrorMsgB2.setText("If a country/region line is missing, there is no data for selected range.");
    	    	}
    		}
    	}
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task B2. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadB2(ActionEvent event) throws Exception
    {
    	StartDateErrorMsgB2.setText("");
    	EndDateErrorMsgB2.setText("");
    	CountryErrorMsgB2.setText("");
    	ErrorMsgB2.setText("");
    	int numSelected = 0;
    	anchorB2.getChildren().remove(newlinechartB2);

    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesB2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (datepickerStartB2.getValue() == null) {
    		StartDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (datepickerEndB2.getValue() == null) {
    			EndDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgB2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
		if (datepickerEndB2.getValue() == null) {
			EndDateErrorMsgB2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgB2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgB2.setText("Please pick at least one country.");
			return;
		}
    	
    	String startingDate = datepickerStartB2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = datepickerEndB2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		numRecord = DataAnalysis.getTotalNumRecord("COVID_Dataset_v1.0.csv", startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList("COVID_Dataset_v1.0.csv", startingDate, endingDate, numRecord);
    	Writer writer = null;

    	try {
    		File file = new File ("..\\CumulativeConfirmedDeathsPer1M.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country,Date,Cumulative Deaths (per 1M)\n");
    		for (int i = 0; i < numCountries; i++){
	    		if (CountriesB2[i].isSelected()) {
	    			float[] dataList = DataAnalysis.getDataList(dataset, CountriesB2[i].getText(), datepickerStartB2.getValue(), datepickerEndB2.getValue(), numRecord, "total_deaths_per_million");
	    			for (int j = 0; j < numRecord; j++) {
	    				String text = CountriesB2[i].getText();
	    				text = text + "," + dateList[j];
	    				
	    				if (dataList[j] != -1.0) {
	    					text = text + "," + dataList[j] + "\n";
	    				}
	    				else
	    					text = text + ",NA\n";
	    				
	    				writer.write(text);
	    			}  		
	    		}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * Clears all entries as well as the chart in Task B2.
     * @param event
     */
    @FXML    
    void clearB2(ActionEvent event) {
    	datepickerStartB2.setValue(null);
    	datepickerEndB2.setValue(null);
    	CountryErrorMsgB2.setText("");
    	StartDateErrorMsgB2.setText("");
    	EndDateErrorMsgB2.setText("");
    	ErrorMsgB2.setText("");
        for (int i = 0; i < numCountries; i++) {
    		  if (CountriesB2[i].isSelected()) {
    			  CountriesB2[i].setSelected(false);
          }
        }
        anchorB2.getChildren().remove(newlinechartB2);
    	if (newlinechartB2 != null)
    		newlinechartB2.getData().clear();
    }

    /**
     * Generates Chart for Task C2 and handles its validation checking.
     * @param event
     */
    @FXML
    void doShowResultC2(ActionEvent event) {
    	StartDateErrorMsgC2.setText("");
    	EndDateErrorMsgC2.setText("");
    	CountryErrorMsgC2.setText("");
    	ErrorMsgC2.setText("");    	
    	anchorC2.getChildren().remove(newlinechartC2);
    	
    	int numSelected = 0;
		LocalDate start = datepickerStartC2.getValue();
		LocalDate end = datepickerEndC2.getValue();

    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesC2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (start == null) {
    		StartDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (end == null || end.isBefore(LocalDate.parse("2020-03-01")) || end.isAfter(LocalDate.parse("2021-07-20"))) {
    			EndDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgC2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
    	if (start.isBefore(LocalDate.parse("2020-03-01")) || start.isAfter(LocalDate.parse("2021-07-20"))) {
    		StartDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
       		
    		if (end == null || end.isBefore(LocalDate.parse("2020-03-01")) || end.isAfter(LocalDate.parse("2021-07-20"))) {
    			EndDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		else if (end.isBefore(start)) {
    			EndDateErrorMsgC2.setText("Ending date must come after starting date.");
    		}

        	if (numSelected == 0)
        		CountryErrorMsgC2.setText("Please pick at least one country.");
        		
        	return;
    	}
    	
		if (end == null || end.isBefore(LocalDate.parse("2020-03-01")) || end.isAfter(LocalDate.parse("2021-07-20"))) {
			EndDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgC2.setText("Please pick at least one country.");
        		
        	return;
		}
		else if (end.isBefore(start)) {
			EndDateErrorMsgC2.setText("Ending date must come after starting date");
        	
        	if (numSelected == 0)
        		CountryErrorMsgC2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgC2.setText("Please pick at least one country.");
			return;
		}
		
		numSelected = 0;
    	String startingDate = datepickerStartC2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = datepickerEndC2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		boolean missing = false;
		numRecord = DataAnalysis.getTotalNumRecord(dataset, startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList(dataset, startingDate, endingDate, numRecord);
    	CategoryAxis xAxis = new CategoryAxis();
    	xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(dateList))); 
    	xAxis.setAutoRanging(false);
    	NumberAxis yAxis = new NumberAxis();

    	newlinechartC2 = new LineChart<String, Number>(xAxis, yAxis);
		newlinechartC2.setTitle("Cumulative Rate of Vaccination against COVID-19 (%)");
    	newlinechartC2.setLayoutX(33);
    	newlinechartC2.setLayoutY(254);
    	newlinechartC2.setPrefHeight(316.0);
    	newlinechartC2.setPrefWidth(625.0);
    	newlinechartC2.setCreateSymbols(false);
    	anchorC2.getChildren().add(newlinechartC2);
    	
    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesC2[i].isSelected()) {
    			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    			String country = CountriesC2[i].getText();
    			String colName = "people_fully_vaccinated_per_hundred";
    			
    			float[] dataList = DataAnalysis.getDataList(dataset, country, start, end, numRecord, colName);
    			
    			for (int j = 0; j < numRecord; j++) {
    				
    				if (dataList[j] == -1.0) {
    					missing = true;
    				} else {
    					series.getData().add(new XYChart.Data<String, Number>(dateList[j], dataList[j]));
    				}
    			}  
    			
    			series.setName(CountriesC2[i].getText());

    	    	newlinechartC2.getData().add(series);
    	    	if (missing == true) {
    	    		ErrorMsgC2.setText("If a country/region line is missing, there is no data for selected range.");
    	    	}
    		}
    	}
    }
    
    /**
     * Enables you to download the selected data into a CSV file for Task C2. Also handles Validation checking.
     * @param event
     * @throws Exception
     */
    @FXML
    void startdownloadC2(ActionEvent event) throws Exception
    {
    	StartDateErrorMsgC2.setText("");
    	EndDateErrorMsgC2.setText("");
    	CountryErrorMsgC2.setText("");
    	ErrorMsgC2.setText("");
    	int numSelected = 0;
    	anchorC2.getChildren().remove(newlinechartC2);

    	for (int i = 0; i < numCountries; i++) {
    		if (CountriesC2[i].isSelected()) {
    			numSelected++;
    		}
    	}
    	
    	if (datepickerStartC2.getValue() == null) {
    		StartDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		
    		if (datepickerEndC2.getValue() == null) {
    			EndDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");
    		}
    		
    		if (numSelected == 0) {
    			CountryErrorMsgC2.setText("Please pick at least one country.");
    		}
    		
    		return;
    	}
    	
		if (datepickerEndC2.getValue() == null) {
			EndDateErrorMsgC2.setText("Please pick a date between 1/3/2020 and 20/7/2021.");

        	if (numSelected == 0)
        		CountryErrorMsgC2.setText("Please pick at least one country.");
        		
        	return;
		}
		
		if (numSelected == 0) {
			CountryErrorMsgC2.setText("Please pick at least one country.");
			return;
		}
    	
    	String startingDate = datepickerStartC2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	String endingDate = datepickerEndC2.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    	int numRecord = 0;
		numRecord = DataAnalysis.getTotalNumRecord("COVID_Dataset_v1.0.csv", startingDate, endingDate);
		String[] dateList = DataAnalysis.getDateList("COVID_Dataset_v1.0.csv", startingDate, endingDate, numRecord);
    	Writer writer = null;

    	try {
    		File file = new File ("..\\CumulativeRateOfVaccination.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		writer.write("Country,Date,Cumulative Rate of Vaccination\n");
    		for (int i = 0; i < numCountries; i++){
	    		if (CountriesC2[i].isSelected()) {
	    			float[] dataList = DataAnalysis.getDataList(dataset, CountriesC2[i].getText(), datepickerStartC2.getValue(), datepickerEndC2.getValue(), numRecord, "people_fully_vaccinated_per_hundred");
	    			for (int j = 0; j < numRecord; j++) {
	    				String text = CountriesC2[i].getText();
	    				text = text + "," + dateList[j];
	    				
	    				if (dataList[j] != -1.0) {
	    					text = text + "," + dataList[j] + "\n";
	    				}
	    				else
	    					text = text + ",NA\n";
	    				
	    				writer.write(text);
	    			}  		
	    		}
    		}
    		if(Desktop.isDesktopSupported()) {
        		Desktop d = Desktop.getDesktop();
        		d.open(file);   			
    		}
    	}
    	
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
        finally 
        {
            writer.flush();
            writer.close();
        }
    }
    
    /**
     * Clears all entries as well as the chart in Task C2.
     * @param event
     */
    @FXML    
    void clearC2(ActionEvent event) {
    	datepickerStartC2.setValue(null);
    	datepickerEndC2.setValue(null);
    	CountryErrorMsgC2.setText("");
    	StartDateErrorMsgC2.setText("");
    	EndDateErrorMsgC2.setText("");
    	ErrorMsgC2.setText("");
        for (int i = 0; i < numCountries; i++) {
    		  if (CountriesC2[i].isSelected()) {
    			  CountriesC2[i].setSelected(false);
          }
        }
        anchorC2.getChildren().remove(newlinechartC2);
    	if (newlinechartC2 != null) {
    		newlinechartC2.getData().clear();
    	}
    }

    /**
     * Intializes the application features such as date pickers and chart columns, and headings.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Initialize A1 country lists
		for (int i = 0; i < countryList.length; i++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
			CountriesA1[i] = d;
            menubuttonCountryA1.getItems().add(c);
        }
		
		// Initialize A2 country list
		for (int i = 0; i < countryList.length; i++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
			CountriesA2[i] = d;
            menubuttonCountryA2.getItems().add(c);
        }
		
		// Initialize B1 country list
		for (int i = 0; i < countryList.length; i++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
			CountriesB1[i] = d;
            menubuttonCountryB1.getItems().add(c);
        }
		
		// Initialize B2 country lists
		for (int i = 0; i < numCountries; i ++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
			CountriesB2[i] = d;
			menubuttonCountryB2.getItems().add(c);
		}

		// Initialize C1 country lists
		for (int i = 0; i < countryList.length; i++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
            CountriesC1[i] = d;
            menubuttonCountryC1.getItems().add(c);
        }
		
		// Initialize C2 country lists
		for (int i = 0; i < countryList.length; i++) {
			CustomMenuItem c = new CustomMenuItem();
			CheckBox d = new CheckBox(countryList[i]);
			c.setContent(d);
            CountriesC2[i] = d;
            menubuttonCountryC2.getItems().add(c);
        }

		
		CountryColA1.setCellValueFactory(new PropertyValueFactory<cases, String>("Country"));
		totalCasesColA1.setCellValueFactory(new PropertyValueFactory<cases, String>("totalCases"));
		totalCasesPer1MColA1.setCellValueFactory(new PropertyValueFactory<cases, String>("totalCasesPer1M"));	
		
		CountryColB1.setCellValueFactory(new PropertyValueFactory<deaths, String>("Country"));
		totalDeathsColB1.setCellValueFactory(new PropertyValueFactory<deaths, String>("totalDeaths"));
		totalDeathsPer1MColB1.setCellValueFactory(new PropertyValueFactory<deaths, String>("totalDeathsPer1M"));

		CountryColC1.setCellValueFactory(new PropertyValueFactory<vaccinations, String>("Country"));
		totalFullyVacColC1.setCellValueFactory(new PropertyValueFactory<vaccinations, String>("totalFullyVac"));
		totalFullyVacPer100ColC1.setCellValueFactory(new PropertyValueFactory<vaccinations, String>("totalFullyVacPer100"));

    	DateErrorMsgA1.setText("");
    	CountryErrorMsgA1.setText("");
    	TableDateA1.setText("Number of Confirmed COVID-19 Cases");
		
    	DateErrorMsgB1.setText("");
    	CountryErrorMsgB1.setText("");
    	TableDateB1.setText("Number of Confirmed COVID-19 Deaths");
    	
    	datepickerA1.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	startingdatepickerA2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (endingdatepickerA2.getValue() != null)
    				upperBound = endingdatepickerA2.getValue();
    			
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	endingdatepickerA2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (startingdatepickerA2.getValue() != null)
    				lowerBound = startingdatepickerA2.getValue();
    			
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerB1.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerStartB2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (datepickerEndB2.getValue() != null)
    				upperBound = datepickerEndB2.getValue();
    			
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerEndB2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (datepickerStartB2.getValue() != null)
    				lowerBound = datepickerStartB2.getValue();
    			
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerC1.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerStartC2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (datepickerStartC2.getValue() != null)
    				upperBound = datepickerEndC2.getValue();
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	datepickerEndC2.setDayCellFactory(picker->new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date,  empty);
    			LocalDate lowerBound = LocalDate.parse("2020-03-01");
    			LocalDate upperBound = LocalDate.parse("2021-07-20");
    			if (datepickerEndC2.getValue() != null)
    				lowerBound = datepickerStartC2.getValue();
    			
    			setDisable(empty || date.compareTo(lowerBound) < 0 || date.compareTo(upperBound) > 0);
    		}
    	});
    	
    	
	}
	
   
}