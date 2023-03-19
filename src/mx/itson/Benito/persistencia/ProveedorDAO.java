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
import org.hibernate.HibernateException;
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
    
    /**
     * 
     * @param nombre
     * @param clave
     * @param email
     * @param telefono
     * @param domicilio
     * @param contacto
     * @return 
     */
    
     public static boolean guardar(String nombre, String clave, String email, String telefono, String domicilio, String contacto ){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de Proveedor
            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setClave(clave);
            p.setEmail(email);
            p.setTelefono(telefono);
            p.setDomicilio(domicilio);
            p.setContacto(contacto);
            
            
            session.save(p);
            
            session.getTransaction().commit();
            
            resultado = p.getId()!=0;
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error:"+ ex.getMessage());
            
        }
        return resultado;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
     
    public Proveedor obtenerPorId(int id){
        Proveedor proveedores = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          proveedores = session.get(Proveedor.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return proveedores;   
    }
    
    /**
     * 
     * @param id
     * @param nombre
     * @param clave
     * @param email
     * @param telefono
     * @param domicilio
     * @param contacto
     * @return 
     */
    
    public boolean editar(int id, String nombre, String clave, String email, String telefono, String domicilio, String contacto){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedores = obtenerPorId(id);
            
            if(proveedores != null){
                proveedores.setNombre(nombre);
                proveedores.setClave(clave);
                proveedores.setEmail(email);
                proveedores.setTelefono(telefono);
                proveedores.setDomicilio(domicilio);
                proveedores.setContacto(contacto);
                
                session.saveOrUpdate(proveedores);
                session.getTransaction().commit();
                
                resultado = true;
            }
            
        }catch(Exception ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    
    public boolean eliminar(int id){
        boolean resultado = false;
        
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedores = obtenerPorId(id);
            
            if(proveedores != null){
                session.delete(proveedores);
                session.getTransaction().commit();
                
                resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}
