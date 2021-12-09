package il.ac.haifa.ClinicSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddMovieController {

   /* @FXML // fx:id="nameText"
    private TextField nameText; // Value injected by FXMLLoader

    @FXML // fx:id="producerText"
    private TextField producerText; // Value injected by FXMLLoader

    @FXML // fx:id="actorsText"
    private TextField actorsText; // Value injected by FXMLLoader

    @FXML // fx:id="summaryText"
    private TextArea summaryText; // Value injected by FXMLLoader

    @FXML // fx:id="vbox"
    private VBox vbox; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl1"
    private Label dateLbl1; // Value injected by FXMLLoader

    @FXML // fx:id="dateText1"
    private TextField dateText1; // Value injected by FXMLLoader

    @FXML // fx:id="vbox1"
    private VBox vbox1; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl2"
    private Label dateLbl2; // Value injected by FXMLLoader

    @FXML // fx:id="dateText2"
    private TextField dateText2; // Value injected by FXMLLoader

    @FXML // fx:id="changeDateBtn"
    private Button changeDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addDateBtn"
    private Button addDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteDateBtn"
    private Button deleteDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl"
    private Label dateLbl; // Value injected by FXMLLoader

    @FXML // fx:id="dateList"
    private ListView<ScreeningDay> dateList; // Value injected by FXMLLoader

    @FXML // fx:id="timeLbl"
    private Label timeLbl; // Value injected by FXMLLoader

    @FXML // fx:id="timeList"
    private ListView<ScreeningTime> timeList; // Value injected by FXMLLoader

    @FXML // fx:id="vbox2"
    private VBox vbox2; // Value injected by FXMLLoader

    @FXML // fx:id="timeLbl1"
    private Label timeLbl1; // Value injected by FXMLLoader

    @FXML // fx:id="timeText1"
    private TextField timeText1; // Value injected by FXMLLoader

    @FXML // fx:id="vbox11"
    private VBox vbox11; // Value injected by FXMLLoader

    @FXML // fx:id="timeLbl2"
    private Label timeLbl2; // Value injected by FXMLLoader

    @FXML // fx:id="timeText2"
    private TextField timeText2; // Value injected by FXMLLoader

    @FXML // fx:id="addTimeBtn"
    private Button addTimeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="changeTimeBtn"
    private Button changeTimeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteTimeBtn"
    private Button deleteTimeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="browseImg"
    private Button browseImg; // Value injected by FXMLLoader

    @FXML // fx:id="commitBtn"
    private Button commitBtn; // Value injected by FXMLLoader
    
    @FXML // fx:id="imageText"
    private TextField imageText; // Value injected by FXMLLoader
    
    @FXML // fx:id="exitBtn"
    private Button exitBtn; // Value injected by FXMLLoader
    
    @FXML // fx:id="allowRadioBtn"
    private RadioButton allowRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dontAllowRadioBtn"
    private RadioButton dontAllowRadioBtn; // Value injected by FXMLLoader
    
    private SimpleClient chatClient;
    private byte[] imageData;
    private FileChooser f = new FileChooser();
    private ObservableList<ScreeningDay> dList = FXCollections.observableArrayList();
    private ObservableList<ScreeningTime> tList = FXCollections.observableArrayList();
    private List<ScreeningDay> dates = new ArrayList<>();
    private ToggleGroup radioGroup;
    private List<String> cinemas = null;
    
    private Movie newMovie;
    private ScreeningDay selectedDate;
    private ScreeningTime selectedTime;
    private boolean isValid = false;
    private LocalDate nDate = null;
    private LocalTime nTime = null;
    private Alert alert = new Alert(AlertType.ERROR);
    private Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

    
    @FXML
    void handleDateMouseClick(MouseEvent event) {
    	ScreeningDay temp = dateList.getSelectionModel().getSelectedItem(); //getting clicked screening day
    	if(temp == null) return;
    	selectedDate = temp;
    	tList.removeAll(tList);
		tList.addAll(selectedDate.getScreeningTimes()); 
		timeList.setItems(tList);
    }
    
    @FXML
    void handleTimeMouseClick(MouseEvent event) {
    	ScreeningTime temp = timeList.getSelectionModel().getSelectedItem(); //getting clicked screening day
    	if(temp == null) return;
    	selectedTime = temp; //getting clicked localtime

    }
    
    @FXML
    void deleteDate(ActionEvent event) {
    	if(selectedDate == null) return;
    	dates.remove(selectedDate);
    	selectedDate=null;
		selectedTime=null;
    	dList.removeAll(dList);
		dList.addAll(dates); 
		dateList.setItems(dList);
		timeList.getItems().clear();
    }

    @FXML
    void deleteTime(ActionEvent event) {
    	if(selectedTime==null) return;
    	selectedDate.getScreeningTimes().remove(selectedTime);
    	selectedTime=null;
    	tList.removeAll(tList);
		tList.addAll(selectedDate.getScreeningTimes()); 
		timeList.setItems(tList);
    }
    
    @FXML
    void addDate(ActionEvent event) {
    	ScreeningDay tempDay;
    	String text = dateText2.getText();
    	try {
    		nDate=LocalDate.parse(text);
    		isValid = true;
        } catch (DateTimeParseException e) {
        	//timeLbl1.setText("Invalid input");
        	//timeLbl1.setTextFill(Color.RED);
        	//timeText1.clear();
        	alert.setContentText("Invalid input, please re-enter date");
        	alert.show();
            isValid = false;
        }
    	dateText2.clear();
		if(!isValid) return;
    	//selectedMovie.getDates().add(new ScreeningDay(LocalDate.parse(dateText2.getText())));
    	boolean isAdded = false;
    	for(ScreeningDay sd: dates) {
    		if(nDate.compareTo(sd.getDate()) == 0) {
    			alert.setContentText("Date entered was already added");
	        	alert.show();
	        	isAdded=true;
	        	break;
    		}
    		else if(nDate.compareTo(sd.getDate()) < 0) {
    			int index = dates.indexOf(sd);
    			tempDay = new ScreeningDay(nDate);
    			dates.add(index, tempDay);
    	    	dList.removeAll(dList);
    			dList.addAll(dates); 
    			dateList.setItems(dList);
	        	isAdded=true;
	        	break;
    		}
    		
    	}
    	if(!isAdded) {
    		tempDay = new ScreeningDay(nDate);
    		dates.add(tempDay);
	    	dList.removeAll(dList);
			dList.addAll(dates); 
			dateList.setItems(dList);
    	}
    }

    @FXML
    void addTime(ActionEvent event) {		
    	if(selectedDate==null) return;
		List<ScreeningTime> temp=selectedDate.getScreeningTimes();
    	String text = timeText2.getText();
    	try {
    		nTime=LocalTime.parse(text);
    		isValid = true;
        } catch (DateTimeParseException e) {
        	//timeLbl1.setText("Invalid input");
        	//timeLbl1.setTextFill(Color.RED);
        	//timeText1.clear();
        	alert.setContentText("Invalid input, please re-enter time");
        	alert.show();
            isValid = false;
        }
		timeText2.clear();
		if(!isValid) return;
		boolean isAdded = false;
    	for(ScreeningTime t: temp) {
    		if(nTime.compareTo(t.getTime()) == 0) {
	        	alert.setContentText("Time entered was already added");
	        	alert.show();
	        	isAdded=true;
	        	break;
    		}
    		else if(nTime.compareTo(t.getTime()) < 0) {
    			int index = temp.indexOf(t);
    			temp.add(index, new ScreeningTime(nTime, selectedDate));
    	    	tList.removeAll(tList);
    	    	tList.addAll(selectedDate.getScreeningTimes()); 
    			timeList.setItems(tList);
    	    	timeText2.clear();
	        	isAdded=true;
	        	break;
    		}
    		
    	}
    	if(!isAdded) {
    		temp.add(new ScreeningTime(nTime, selectedDate));
    		tList.removeAll(tList);
	    	tList.addAll(selectedDate.getScreeningTimes()); 
			timeList.setItems(tList);
	    	timeText2.clear();
    	}
    }

    @FXML
    void changeDate(ActionEvent event) {
    	if(selectedDate==null) {
        	dateText1.clear();
    		return;
    	}
		String text = dateText1.getText();
		    	
		    	try {
		    		nDate=LocalDate.parse(text);
		    		isValid = true;
		        } catch (DateTimeParseException e) {
		        	//timeLbl1.setText("Invalid input");
		        	//timeLbl1.setTextFill(Color.RED);
		        	//timeText1.clear();
		        	alert.setContentText("Invalid input, please re-enter date");
		        	alert.show();
		            isValid = false;
		        }
    	dateText1.clear();
	    if(!isValid) return;
    	selectedDate.setDate(nDate);
    	dList.removeAll(dList);
		dList.addAll(dates); 
		dateList.setItems(dList);
    	dateText1.clear();
    }

    @FXML
    void changeTime(ActionEvent event) {
    	if(selectedTime==null) {
        	timeText1.clear();
    		return;
    	}
    	String text = timeText1.getText();
    	
    	try {
    		nTime=LocalTime.parse(text);
    		isValid = true;
        } catch (DateTimeParseException e) {
        	//timeLbl1.setText("Invalid input");
        	//timeLbl1.setTextFill(Color.RED);
        	//timeText1.clear();
        	alert.setContentText("Invalid input, please re-enter time");
        	alert.show();
            isValid = false;
        }
    	timeText1.clear();
	    if(!isValid) return;
    	int index = selectedDate.getScreeningTimes().indexOf(selectedTime);
    	selectedDate.getScreeningTimes().set(index, new ScreeningTime(nTime, selectedDate));
		selectedTime=null;
    	tList.removeAll(tList);
		tList.addAll(selectedDate.getScreeningTimes()); 
		timeList.setItems(tList);
		
		//timeLbl1.setText("Change Screening Time");
	   	//timeLbl1.setTextFill(Color.BLACK);
    }

    @FXML
    void commitChanges(ActionEvent event) throws IOException {
    	    	confirmAlert.setContentText("Are you sure you wish to add this movie entity?");
    	    	confirmAlert.showAndWait().ifPresent(response->{
    	    		if(response == ButtonType.CANCEL) {
    	    			return;
    	    		}
    	    	});
    	    	List<ScreeningCinema> temp = new ArrayList<ScreeningCinema>();
    	    	for(String s: cinemas) {
    	    		List<ScreeningDay> datesTemp = new ArrayList<ScreeningDay>();
    	    		ScreeningCinema c = new ScreeningCinema(s, datesTemp);
    	    		for(ScreeningDay sd: dates) {
    	    			ScreeningDay tempDay = new ScreeningDay(sd);
    	    			List<ScreeningTime> timesTemp = new ArrayList<ScreeningTime>();
    	    			for(ScreeningTime st: sd.getScreeningTimes()) {
    	    				ScreeningTime tempTime = new ScreeningTime(st);
    	    				tempTime.setDay(tempDay);
    	    				timesTemp.add(tempTime);
    	    			}
    	    			tempDay.setScreeningTimes(timesTemp);
    	    			tempDay.setCinema(c);
    	    			datesTemp.add(tempDay);

    	    		}
    	    		temp.add(c);
    	    	}
    	    	
    	    	
    	    	newMovie = new Movie(nameText.getText(), producerText.getText(), actorsText.getText(), summaryText.getText(), temp, imageData);
    	    	if(((RadioButton)radioGroup.getSelectedToggle()).getText() == "allowRadioBtn") newMovie.setWatchAtHome(true);
    	    	else newMovie.setWatchAtHome(false);
    	    	
    	    	
    	    	chatClient.setGotList(false);
    	    	chatClient.sendToServer(newMovie);
    	    	//dateLbl.setText("Change Date");
    	   	    //dateLbl.setTextFill(Color.BLACK);
    	   	    //textField.clear();
    	 		//System.out.println("at init2");

    			 dateList.getItems().clear();
    			 timeList.getItems().clear();
    			 imageText.clear();
   			 selectedDate=null;
   			 selectedTime=null;
     	 		//System.out.println(movies);
    }

    @FXML
    void doImageBrowse(ActionEvent event) {
    	Node source = (Node) event.getSource();
    	Window theStage = source.getScene().getWindow();
    	File file = f.showOpenDialog(theStage);
    	if(file == null) return;
		imageData = new byte[(int) file.length()];
		 try {
		     FileInputStream fileInputStream = new FileInputStream(file);
		     fileInputStream.read(imageData);
		     fileInputStream.close();
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		 imageText.setText(file.getName());
    }
    
    @FXML
    void exitFromAddMovie(ActionEvent event) throws IOException {
    	Node n = (Node)event.getSource();
    	((Stage)n.getScene().getWindow()).close();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws InterruptedException {
    	f.setInitialDirectory(new File("/Users/adelin/Pictures"));
    	f.getExtensionFilters().addAll(
    		     new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg", "*.bmp", "*.dib", "*.gif")
    		);
    	radioGroup = new ToggleGroup();

        allowRadioBtn.setToggleGroup(radioGroup);
        dontAllowRadioBtn.setToggleGroup(radioGroup);
        
        chatClient.setGotList(false);

		 try {
			 chatClient.sendToServer("#Cinemas");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }

		 synchronized(chatClient.getLock()) {
			 while(!chatClient.getGotList()) {
				
				 chatClient.getLock().wait();

			 }
		 }	
		 cinemas = chatClient.getCinemas();
    }
    
    public void setClient(SimpleClient c) {
   	   this.chatClient = c;
    } 
*/
}
