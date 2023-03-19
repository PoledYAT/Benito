/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Benito.entidades.OrdenCompra;
import mx.itson.Benito.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author pyatq
 */
public class OrdenCompraDAO {
    
    public static List <OrdenCompra> obtenerTodos(){
       
        List<OrdenCompra> ordencompra = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<OrdenCompra> criteriaQuery = session.getCriteriaBuilder().createQuery(OrdenCompra.class);
                
            criteriaQuery.from(OrdenCompra.class);
            
            ordencompra = session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return ordencompra;
    }
}
