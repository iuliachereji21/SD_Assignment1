package org.example;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.presentation.controller.LogInController;
import org.example.presentation.controller.RegularUserController;
import org.example.presentation.controller.TravellingAgencyController;
import org.example.presentation.view.MainFrame;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        MainFrame mainFrame= new MainFrame("Booking");
        RegularUserController regularUserController = new RegularUserController();
        TravellingAgencyController travellingAgencyController = new TravellingAgencyController(mainFrame);
        LogInController logInController= new LogInController(mainFrame, regularUserController, travellingAgencyController);

//        RegularUser regularUser = new RegularUser("Ana Iepure","ana@yahoo.com","0000");
//        TravellingAgency travellingAgency = new TravellingAgency("veltravel", "veltravel@agency.com","0000");
//        VacationDestination vacationDestination= new VacationDestination("costinesti", travellingAgency);
//        VacationPackage vacationPackage = new VacationPackage("costinesti 2022", (float) 499.9,"22.08.2022", "29.08.2022", "one week vacation",10, travellingAgency, vacationDestination);
//        vacationPackage.addUser(regularUser);
//        regularUser.addVacationPackage(vacationPackage);
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//
//        em.persist(regularUser);
//        em.persist(travellingAgency);
//        em.persist(vacationDestination);
//        em.persist(vacationPackage);
//
//        em.getTransaction().commit();
//        em.close();
    }
}
