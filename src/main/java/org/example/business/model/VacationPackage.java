package org.example.business.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vacation_package")
public class VacationPackage {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String status;

    @Column
    private String name;

    @Column
    private Float price;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private String details;

    @Column
    private int maxNrPeople;

    @Column
    private int currentNrPeople;

    @ManyToMany(mappedBy = "bookedVacationPackages", fetch = FetchType.EAGER)
    private List<RegularUser> usersThatBooked;

    @ManyToOne
    @JoinColumn(name="travelling_agency_id")
    private TravellingAgency travellingAgency;

    @ManyToOne
    @JoinColumn(name="vacation_destination_id")
    private VacationDestination vacationDestination;

    public VacationPackage(){
        super();
    }

    public boolean addUser(RegularUser regularUser){
        if(this.status.equals("BOOKED"))
            return false;
        this.usersThatBooked.add(regularUser);
        if(this.status.equals("NOT_BOOKED"))
            this.status="IN_PROGRESS";
        if(this.usersThatBooked.size()==maxNrPeople)
            this.status="BOOKED";
        this.currentNrPeople++;
        return true;
    }

    public VacationPackage(String name, Float price, String startDate, String endDate, String details, int maxNrPeople, TravellingAgency travellingAgency, VacationDestination vacationDestination){
        this.status="NOT_BOOKED";
        this.name = name;
        this.price=price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.details=details;
        this.maxNrPeople=maxNrPeople;
        this.currentNrPeople=0;
        this.usersThatBooked=new ArrayList<>();
        this.travellingAgency=travellingAgency;
        this.vacationDestination=vacationDestination;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDetails() {
        return details;
    }

    public int getMaxNrPeople() {
        return maxNrPeople;
    }

    public int getCurrentNrPeople() {
        return currentNrPeople;
    }

    public List<RegularUser> getUsersThatBooked() {
        return usersThatBooked;
    }

    public TravellingAgency getTravellingAgency() {
        return travellingAgency;
    }

    public VacationDestination getVacationDestination() {
        return vacationDestination;
    }
}
