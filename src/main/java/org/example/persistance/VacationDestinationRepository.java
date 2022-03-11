package org.example.persistance;

import org.example.business.model.VacationDestination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class VacationDestinationRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sd_assignment_1");

    public ArrayList<VacationDestination> getVacationDestinationsByAgencyId(long travellingAgencyId){
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

    public void addVacationDestination(VacationDestination vacationDestination){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationDestination);
        em.getTransaction().commit();
        em.close();
    }

    public VacationDestination getDestinationById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            return em.createQuery(
                            "SELECT d from VacationDestination d WHERE d.id= :id", VacationDestination.class)
                    .setParameter("id",id)
                    .getSingleResult();
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

    public void deleteVacationDestinationById(long destination_id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery(
                            "DELETE FROM VacationDestination d WHERE d.id= :id")
                    .setParameter("id",destination_id)
                    .executeUpdate();
        }
        catch(NoResultException e){
            System.out.println("err");
        }
        finally{
            em.getTransaction().commit();
            em.close();
        }
    }
}
