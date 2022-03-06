package org.example.business.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="travelling_agency")
public class TravellingAgency {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "travellingAgency")
    private List<VacationPackage> vacationPackages;

    @OneToMany(mappedBy = "travellingAgency")
    private List<VacationDestination> vacationDestinations;

    public TravellingAgency(){
        super();
        this.vacationDestinations=new ArrayList<>();
    }

    public TravellingAgency(String name, String email, String password){
        this.email=email;
        this.name=name;
        this.password=password;
        this.vacationDestinations=new ArrayList<>();
        this.vacationPackages= new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public List<VacationDestination> getVacationDestinations() {
        return vacationDestinations;
    }

    public void addVacationDestination(VacationDestination vacationDestination){
        this.vacationDestinations.add(vacationDestination);
    }
}
