package org.example.persistance;

import org.example.business.model.RegularUser;
import org.example.business.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class VacationPackageRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sd_assignment_1");

    public ArrayList<VacationPackage> getAvailableVacationPackages(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            return (ArrayList<VacationPackage>) em.createQuery(
                            "SELECT p from VacationPackage p WHERE p.status= 'NOT_BOOKED' or p.status= 'IN_PROGRESS'", VacationPackage.class)
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

    public ArrayList<VacationPackage> getBookedPackagesByUserId(long id){
        return null;
    }

    public VacationPackage getVacationPackageById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            return em.createQuery(
                            "SELECT p from VacationPackage p WHERE p.id= :id", VacationPackage.class)
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

    public ArrayList<VacationPackage> getVacationPackagesByAgencyId(long travellingAgencyId){
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

    public void addVacationPackage(VacationPackage vacationPackage){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vacationPackage);
        em.getTransaction().commit();
        em.close();
    }

    public void updateVacationPackage(String name, String startDate, String endDate, int nrPeople, float price, String details, long packageId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery(
                            "UPDATE VacationPackage p set p.name= :name, p.startDate = :startDate, p.endDate= :endDate, p.maxNrPeople= :nrPeople, p.price= :price, p.details= :details where p.id= :id")
                    .setParameter("name",name)
                    .setParameter("startDate",startDate)
                    .setParameter("endDate",endDate)
                    .setParameter("nrPeople",nrPeople)
                    .setParameter("price",price)
                    .setParameter("details",details)
                    .setParameter("id",packageId)
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

    public void deleteVacationPackageById(long package_id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery(
                            "DELETE FROM VacationPackage p WHERE p.id= :id")
                    .setParameter("id",package_id)
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

    public void deleteVacationPackageByDestinationId(long destination_id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery(
                            "DELETE FROM VacationPackage p WHERE p.vacationDestination.id= :id")
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
