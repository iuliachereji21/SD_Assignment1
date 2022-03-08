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
}
