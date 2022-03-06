package org.example.business.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="regular_user")
public class RegularUser {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="regular_user_vacation_package",
            joinColumns = @JoinColumn(name="regular_user_id"),
            inverseJoinColumns = @JoinColumn(name="vacation_package_id")
    )
    private List<VacationPackage> bookedVacationPackages;

    public void addVacationPackage(VacationPackage vacationPackage){
        this.bookedVacationPackages.add(vacationPackage);
    }
    public RegularUser(){
        super();
    }

    public RegularUser(String name, String email, String password){
        this.email=email;
        this.name=name;
        this.password=password;
        this.bookedVacationPackages=new ArrayList<>();
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

    public List<VacationPackage> getBookedVacationPackages() {
        return bookedVacationPackages;
    }
}
