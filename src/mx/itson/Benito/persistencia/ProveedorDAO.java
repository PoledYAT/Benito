/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Benito.entidades.Proveedor;
import mx.itson.Benito.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author pyatq
 */
public class ProveedorDAO {
    
    public static List <Proveedor> obtenerTodos(){
        List<Proveedor> proveedores = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<Proveedor> criteriaQuery = session.getCriteriaBuilder().createQuery(Proveedor.class);
                
            criteriaQuery.from(Proveedor.class);
            
            proveedores = session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return proveedores;
    }

}
