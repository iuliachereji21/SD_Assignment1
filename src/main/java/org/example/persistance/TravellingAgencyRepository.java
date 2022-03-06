package org.example.persistance;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class TravellingAgencyRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sd_assignment_1");

    public TravellingAgency findAgencyByEmailAndPassword(String email, String password){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            TravellingAgency agency = em.createQuery(
                            "SELECT t from TravellingAgency t WHERE t.email= :email", TravellingAgency.class)
                    .setParameter("email",email)
                    .getSingleResult();

            if(agency==null || !agency.getPassword().equals(password))
                return null;
            return agency;
        }
        catch(NoResultException e){
            System.out.println("err");
            return null;
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }

    public ArrayList<VacationDestination> getVacationDestinations(long travellingAgencyId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            return (ArrayList<VacationDestination>) em.createQuery(
                            "SELECT d from VacationDestination d WHERE d.travellingAgency.id= :id", VacationDestination.class)
                    .setParameter("id",travellingAgencyId)
                    .getResultList();
        }
        catch(NoResultException e){
            System.out.println("err");
            return null;
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }

    public ArrayList<VacationPackage> getVacationPackages(long travellingAgencyId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            ArrayList<VacationPackage> vp =  (ArrayList<VacationPackage>) em.createQuery(
                            "SELECT p from VacationPackage p WHERE p.travellingAgency.id= :id", VacationPackage.class)
                    .setParameter("id",travellingAgencyId)
                    .getResultList();
            vp.size();
            return vp;
        }
        catch(NoResultException e){
            System.out.println("err");
            return null;
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }

    public void addVacationDestination(VacationDestination vacationDestination){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationDestination);
        em.getTransaction().commit();
        em.close();
    }
}
