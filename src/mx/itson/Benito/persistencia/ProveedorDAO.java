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
 * DATA ACCESS OBJECT, que acede a los datos de la tabla Proveedor
 * @author pyatq
 */
public class ProveedorDAO {
    
    /**
     * Obtine todas las entidades de la lista de Proveedor
     * @return a Articulo
     */
    
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
     * Es para guardar los datos de Proveedor
     * @param nombre se va a guardar el nombre
     * @param clave se va guardar la clave
     * @param email se va guardar email
     * @param telefono se va guardar el telefono
     * @param domicilio se va guardar el domicilio
     * @param contacto se va guardar el cotacto
     * @return Indica si el registro fue guardado correcta mente
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
     * Es para obtener los datos de Proveedor desde su ID
     * @param id obtendremos los datos desde el ID
     * @return retorna conductor de lo contrario retarna a null
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
     * Es para editar los datos de la tabla de Proveedor
     * @param id el ID se quedara igual por el int
     * @param nombre se podra editar nombre
     * @param clave se podra editar clave
     * @param email se podra editar email
     * @param telefono se podra editar telefono
     * @param domicilio se podra editar domicilio
     * @param contacto se podra editar coctacto
     * @return es para indicar si el registro fue editado de Proveedor retorna false
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
     * Es para eliminar una una fila de un dato de Proveedor desde el ID
     * @param id se eliminara el dato desde el ID
     * @return si el registro se elimina correcta mente entrara; pero si no, retornara flase 
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
