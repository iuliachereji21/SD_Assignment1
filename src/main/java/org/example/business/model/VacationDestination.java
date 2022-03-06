package org.example.business.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vacation_destination")
public class VacationDestination {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "vacationDestination")
    private List<VacationPackage> vacationPackages=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="travelling_agency_id")
    private TravellingAgency travellingAgency;

    public VacationDestination(){
        super();
    }

    public VacationDestination(String name, TravellingAgency travellingAgency){
        this.name=name;
        this.travellingAgency=travellingAgency;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public TravellingAgency getTravellingAgency() {
        return travellingAgency;
    }
}
