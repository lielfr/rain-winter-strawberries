package il.ac.haifa.ClinicSystem.entities;

import java.io.Serializable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "clinics")
public class Clinic implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="clinic_id")
	 private int id;

	@Column(name = "clinic_name")
	private String name;

	@Column(name = "location")
	 private String location;
	 
	 @Column(name = "openHours")
	 @LazyCollection(LazyCollectionOption.FALSE)
	 @ElementCollection(targetClass=LocalTime.class)
	 private List<LocalTime> openHours;

	@Column(name = "closeHours")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection(targetClass=LocalTime.class)
	private List<LocalTime> closeHours;


	private transient StringProperty curOpenHour;
	private transient StringProperty curCloseHour;
	//private transient LocalTime curOpenHour;
	//private transient LocalTime curCloseHour;
	private transient ChoiceBox<String> dayOfWeek;

	public Clinic(String name, String location, List<LocalTime> openHours, List<LocalTime> closeHours) {
		this.name = name;
		this.location = location;
		this.openHours = openHours;
		this.closeHours = closeHours;
	}

	public Clinic () {
	 }
	 
	 public Clinic(Clinic m) {
		this.name = m.getName();
		this.location = m.getLocation();
		this.openHours = m.getOpenHours();

		 this.openHours=(new ArrayList<LocalTime>());
		 for(LocalTime t: m.getOpenHours()){
			 this.openHours.add(t);
		 }
		 this.closeHours=(new ArrayList<LocalTime>());
		 for(LocalTime t: m.getCloseHours()){
			 this.closeHours.add(t);
		 }
		 this.id = m.getId();
	 }
	 
	 @Override
	    public String toString() {
	        return String.format(name + " | " + location + " | " + openHours + "\n");
	    }

	 public int getId(){
		 return this.id;
	 }

	public StringProperty curOpenHourProperty() { return curOpenHour; }
	public void setCurOpenHourProperty(SimpleStringProperty s){ this.curOpenHour = s; }
	public String getCurOpenHour() { return curOpenHour.get(); }
	public void setCurOpenHour(String name) { curOpenHour.set(name); }

	public StringProperty curCloseHourProperty() { return curCloseHour; }
	public void setCurCloseHourProperty(SimpleStringProperty s){ this.curCloseHour = s; }
	public String getCurCloseHour() { return curCloseHour.get(); }
	public void setCurCloseHour(String name) { curCloseHour.set(name); }

	public List<LocalTime> getCloseHours() {
		return closeHours;
	}

	public void setCloseHours(List<LocalTime> closeHours) {
		this.closeHours = closeHours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<LocalTime> getOpenHours() {
		return openHours;
	}

	public void setOpenHours(List<LocalTime> openHours) {
		this.openHours = openHours;
	}

	public ChoiceBox<String> getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(ChoiceBox<String> dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	 public boolean equals(Object obj) {
		 if(this.name.equals(((Clinic)obj).getName()) && this.location.equals(((Clinic)obj).getLocation())
				 && this.openHours.equals(((Clinic)obj).getOpenHours())) {
			 return true;
		 }
		 return false;
	 }

}
