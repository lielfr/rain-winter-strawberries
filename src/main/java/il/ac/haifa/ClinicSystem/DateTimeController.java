/**
 * Sample Skeleton for 'secondary.fxml' Controller Class
 */

package il.ac.haifa.ClinicSystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class DateTimeController {

	/*
    @FXML // fx:id="borderPane"
	private BorderPane borderPane; // Value injected by FXMLLoader

	@FXML // fx:id="anchorPane"
    private AnchorPane anchorPane; // Value injected by FXMLLoader

    @FXML // fx:id="vbox1"
    private VBox vbox; // Value injected by FXMLLoader

    @FXML // fx:id="listLbl"
    private Label listLbl; // Value injected by FXMLLoader

    @FXML // fx:id="movieTable"
    private TableView<Movie> movieTable; // Value injected by FXMLLoader

    @FXML // fx:id="image"
    private TableColumn<Movie, byte[]> image; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<Movie, String> name; // Value injected by FXMLLoader

    @FXML // fx:id="producer"
    private TableColumn<Movie, String> producer; // Value injected by FXMLLoader

    @FXML // fx:id="actors"
    private TableColumn<Movie, String> actors; // Value injected by FXMLLoader

    @FXML // fx:id="cinema"
    private TableColumn<Movie, ChoiceBox<ScreeningCinema>> cinema; // Value injected by FXMLLoader

    @FXML // fx:id="summary"
    private TableColumn<Movie, String> summary; // Value injected by FXMLLoader

    @FXML // fx:id="changeDateBtn"
    private Button changeDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl"
    private Label dateLbl; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl1"
    private Label dateLbl1; // Value injected by FXMLLoader

    @FXML // fx:id="dateText1"
    private TextField dateText1; // Value injected by FXMLLoader

    @FXML // fx:id="deleteDateBtn"
    private Button deleteDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="commitBtn"
    private Button commitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="UndoBtn"
    private Button UndoBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dateList"
    private ListView<ScreeningDay> dateList; // Value injected by FXMLLoader

    @FXML // fx:id="vbox1"
    private VBox vbox1; // Value injected by FXMLLoader

    @FXML // fx:id="dateLbl2"
    private Label dateLbl2; // Value injected by FXMLLoader

    @FXML // fx:id="dateText2"
    private TextField dateText2; // Value injected by FXMLLoader

    @FXML // fx:id="addDateBtn"
    private Button addDateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="timeList"
    private ListView<ScreeningTime> timeList; // Value injected by FXMLLoader

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

    @FXML // fx:id="vbox2"
    private VBox vbox2; // Value injected by FXMLLoader

    @FXML // fx:id="timeLbl1"
    private Label timeLbl1; // Value injected by FXMLLoader

    @FXML // fx:id="timeText1"
    private TextField timeText1; // Value injected by FXMLLoader

    @FXML // fx:id="deleteTimeBtn"
    private Button deleteTimeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="timeLbl"
    private Label timeLbl; // Value injected by FXMLLoader

    @FXML // fx:id="returnBtn"
    private Button returnBtn; // Value injected by FXMLLoader
    
	

    private ObservableList<Movie> mList = FXCollections.observableArrayList();
    private ObservableList<ScreeningDay> dList = FXCollections.observableArrayList();
    private ObservableList<ScreeningTime> tList = FXCollections.observableArrayList();

    
    private SimpleClient chatClient;
    private List<Movie> movies;
    private Movie backup;
    private Movie selectedMovie;
    private ScreeningDay selectedDate;
    private ScreeningTime selectedTime;
    private boolean isValid = false;
    private LocalDate nDate = null;
    private LocalTime nTime = null;
    private Alert alert = new Alert(AlertType.ERROR);
    private Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

    
    @FXML
    void handleTableMouseClick(MouseEvent event) {
    	Movie temp = movieTable.getSelectionModel().getSelectedItem(); //getting clicked movie
    	if(temp == null || temp.getCinema().getSelectionModel().getSelectedItem() == null) return;
    	selectedMovie = temp;
    	backup=new Movie(selectedMovie);
    	selectedDate=null;
		selectedTime=null;
    	dList.removeAll(dList);
		dList.addAll(selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates()); 
		dateList.setItems(dList);
		timeList.getItems().clear();
		

    }
    
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
    void undoChanges(ActionEvent event) {
    	if(selectedMovie == null || backup == null) return;
    	int index = movies.indexOf(selectedMovie);
		movies.set(index, backup);
		mList.removeAll(mList);
		mList.addAll(movies);
	    movieTable.setItems(mList);
	    dList.removeAll(dList);
		dList.addAll(backup.getCinema().getSelectionModel().getSelectedItem().getDates()); 
		dateList.setItems(dList);
		timeList.getItems().clear();;
		selectedDate=null;
		selectedTime=null;
		
		selectedMovie=backup;
		backup=new Movie(selectedMovie);
    }
    
    @FXML
    void deleteDate(ActionEvent event) {
    	if(selectedDate == null) return;
    	selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates().remove(selectedDate);
    	movieTable.refresh();
    	selectedDate=null;
		selectedTime=null;
    	dList.removeAll(dList);
		dList.addAll(selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates()); 
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
    	if(selectedMovie==null) return;
    	ScreeningDay tempDay;
    	List<ScreeningDay> temp=selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates();
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
    	for(ScreeningDay sd: temp) {
    		if(nDate.compareTo(sd.getDate()) == 0) {
    			alert.setContentText("Date entered was already added");
	        	alert.show();
	        	isAdded=true;
	        	break;
    		}
    		else if(nDate.compareTo(sd.getDate()) < 0) {
    			int index = temp.indexOf(sd);
    			tempDay = new ScreeningDay(nDate);
    			tempDay.setCinema(selectedMovie.getCinema().getSelectionModel().getSelectedItem());
    			temp.add(index, tempDay);
    			movieTable.refresh();
    	    	dList.removeAll(dList);
    			dList.addAll(selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates()); 
    			dateList.setItems(dList);
	        	isAdded=true;
	        	break;
    		}
    		
    	}
    	if(!isAdded) {
    		tempDay = new ScreeningDay(nDate);
    		tempDay.setCinema(selectedMovie.getCinema().getSelectionModel().getSelectedItem());
    		temp.add(tempDay);
    		movieTable.refresh();
	    	dList.removeAll(dList);
			dList.addAll(selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates()); 
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
    	movieTable.refresh();
    	dList.removeAll(dList);
		dList.addAll(selectedMovie.getCinema().getSelectionModel().getSelectedItem().getDates()); 
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
	void commitChanges(ActionEvent event) throws IOException, InterruptedException {
    	textDate = textField.getText();
    	
	    	try {
	    		pDate=LocalDate.parse(textDate);
	    		isValid = true;
	        } catch (DateTimeParseException e) {
	        	dateLbl.setText("Invalid Date Input, Please Re-enter Date.");
	        	dateLbl.setTextFill(Color.RED);
	            textField.clear();
	            isValid = false;
	        } 
    	if(!isValid) return;
    	selectedMovie.setDate(pDate);
    	
   	    
   	    if(!selectedMovie.equals(backup)) {
   	    	confirmAlert.setContentText("Are you sure you wish to update the movie entity?");
   	    	confirmAlert.showAndWait().ifPresent(response->{
   	    		if(response == ButtonType.CANCEL) {
   	    			undoChanges(null);
   	    			return;
   	    		}
   	    	});
   	    	chatClient.setGotList(false);
   	    	chatClient.sendToServer(selectedMovie);
   	    	//dateLbl.setText("Change Date");
   	   	    //dateLbl.setTextFill(Color.BLACK);
   	   	    //textField.clear();

   	    	synchronized(chatClient.getLock()) {
   				 while(!chatClient.getGotList()) {
   					chatClient.getLock().wait();

   				 }
   			 }		
	   	     movies = chatClient.getMovieList();       	
	      	 for(Movie m : movies) {
	      		 if(m.getCinema() == null) {
		      	 	 ObservableList<ScreeningCinema> data = FXCollections.observableArrayList();
		   	         data.addAll(m.getCinemas());
		   	         m.setCinema(new ChoiceBox<ScreeningCinema>(data));
	      		 }
	      	 }
   			 mList.removeAll(mList);
   			 mList.addAll(movies);
   			 movieTable.setItems(mList);
   			 dateList.getItems().clear();
   			 timeList.getItems().clear(); 
   			 backup=null;
  			 selectedDate=null;
  			 selectedTime=null;
    	 		//System.out.println(movies);

   		}
	}
    
    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
    	App.setRoot("contentMenu");
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
     void initialize() throws IOException, InterruptedException {
    	chatClient.setGotList(false);

		 try {
			 chatClient.sendToServer("#MovieList");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		 synchronized(chatClient.getLock()) {
			 while(!chatClient.getGotList()) {
				 
				 chatClient.getLock().wait();

			 }
		 }	
		 movies = chatClient.getMovieList();    
    	 for(Movie m : movies) {
       	 	 ObservableList<ScreeningCinema> data = FXCollections.observableArrayList();
    	     data.addAll(m.getCinemas());
    	     m.setCinema(new ChoiceBox<ScreeningCinema>(data));
	    	
       	 }
    	
    		image.setCellFactory(param -> {
    			final ImageView imageview = new ImageView();
    		    imageview.setFitHeight(50);
    		    imageview.setFitWidth(50);
    		    
    		    TableCell<Movie, byte[]> cell = new TableCell<Movie, byte[]>() {
    		           public void updateItem(byte[] item, boolean empty) {
    		        	 super.updateItem(item, empty);
    		             if (item != null) {
    		            	 Image i = new Image(new ByteArrayInputStream(item));
    		                 imageview.setImage(i);
    		             }
    		             if (empty) {
    	    		            this.setGraphic(null); // remove graphic
    	    		     } else {
    	    		            // setItem(t); //already done by super.updateItem
    	    		            this.setGraphic(imageview);
    	    		     }
    		           }
    		        };
    		        // Attach the imageview to the cell
    		        
    		        return cell;
    		});
    		image.setCellValueFactory(new PropertyValueFactory<Movie, byte[]>("image"));
    		name.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
    		producer.setCellValueFactory(new PropertyValueFactory<Movie, String>("producer"));
    		actors.setCellValueFactory(new PropertyValueFactory<Movie, String>("actors"));
    		cinema.setCellValueFactory(new PropertyValueFactory<Movie, ChoiceBox<ScreeningCinema>>("cinema"));
    		summary.setCellValueFactory(new PropertyValueFactory<Movie, String>("summary"));
    		    		

    		mList.removeAll(mList);
    		mList.addAll(movies);
    		movieTable.setItems(mList);
        	
     }
   
     public void loadData() throws InterruptedException {
    	 chatClient.setGotList(false);

		 try {
			 chatClient.sendToServer("#MovieList");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }

		 synchronized(chatClient.getLock()) {
			 while(!chatClient.getGotList()) {
				
				 chatClient.getLock().wait();

			 }
		 }	
		 movies = chatClient.getMovieList();
		 
		 
		 mList.removeAll(mList);
		 mList.addAll(movies);
		 movieTable.setItems(mList);

     }
    
     public void setClient(SimpleClient c) {
    	 this.chatClient = c;
     } 
     */
    

}
