package com.pujanov.controller;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;

import com.pujanov.config.properties.RestApiProperties;
import com.pujanov.dto.Business;
import com.pujanov.service.RestService;
import com.pujanov.util.ConnectionUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Slf4j
@Component
@FxmlView("MainView.fxml")
public class MainViewController {
	private final ConnectionUtil connectionUtil;
	private final FxWeaver fxWeaver;
	private final RestService restService;
	
	private int DELAY = 2000;
	private Timer timer;
	
	private static final String CSV_FILE_PATH = "businesses.csv";
	
	
	@Autowired
	RestApiProperties restApiProperties;
	
	public MainViewController(ConnectionUtil connectionUtil, FxWeaver fxWeaver,
			RestService restService) {
		this.connectionUtil = connectionUtil;
		this.fxWeaver = fxWeaver;
		this.restService = restService;
	}
	
	@FXML
	private Button searchBtn;
	
	@FXML
	private Button exportCSVBtn;

	  @FXML
	    private Label label;
	    @FXML private TextField filterField;
	    @FXML private TableView<Business> tableview;
	    @FXML private TableColumn<Business, String> businessName;
	    @FXML private TableColumn<Business, String> businessAddress;
	    @FXML private TableColumn<Business, String> city;
	    @FXML private TableColumn<Business, String> state;
	    @FXML private TableColumn<Business, String> zipCode;

	    
	@FXML
	public void initialize() {
		
		
		 businessName.setCellValueFactory(new PropertyValueFactory<>("businessName"));       
	        businessAddress.setCellValueFactory(new PropertyValueFactory<>("businessAddress"));        
	        city.setCellValueFactory(new PropertyValueFactory<>("city"));        
	        state.setCellValueFactory(new PropertyValueFactory<>("state"));        
	        zipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));       
	                            
	        ObservableList<Business> dataList = FXCollections.observableArrayList();
			
			searchBtn.setOnAction(event ->{
				dataList.clear();
				String searchQuery = filterField.getText();
				
				if(searchQuery.equals("")) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Search Text Empty");
					alert.show();
				}
				
				List<Business> bznesses = restService.searchPlacesApiCall(UriEncoder.encode(searchQuery));
				dataList.addAll(bznesses);			
				tableview.setItems(dataList);
			});
			
			timer = new Timer();
			
			filterField.textProperty().addListener((observable, oldValue, newValue) -> {
					
					//System.out.println("text chaged--->"+ filterField.getText());
				
				
				timer.cancel();
				timer = new Timer();
				
			        timer.schedule(new TimerTask() {

			            @Override
			            public void run() {
			               System.out.println("timer task run");
			               //Added for textfiled changed
			               
			               dataList.clear();
							String searchQuery = filterField.getText();
							List<Business> bznesses = restService.searchPlacesApiCall(UriEncoder.encode(searchQuery));
							dataList.addAll(bznesses);			
							tableview.setItems(dataList);
			               //added ends
			               
			            }
			        }, DELAY);
			        
			       
			        
			        
			        
					
			});
			
			
			exportCSVBtn.setOnAction(event -> {
				System.out.println("Exporting file");
				
				
				try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(restApiProperties.getExportDirectory() + CSV_FILE_PATH));
		    	CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
		    			.withHeader("Business Name", "Business Address", "City", "State", "Zip Code"));
		    	
		    	for(Business bzns: dataList) {
		    		csvPrinter.printRecord(bzns.getBusinessName(), bzns.getBusinessAddress(), bzns.getCity(), bzns.getState(), bzns.getZipCode());
		    	}
		    	
		    	csvPrinter.flush();
		    	
		    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("CSV Exported successfully!" + "\nPlease check " + restApiProperties.getExportDirectory());
				alert.show();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			});
	}
}
