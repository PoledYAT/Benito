/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Benito.entidades.Articulo;
import mx.itson.Benito.entidades.Proveedor;
import mx.itson.Benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author pyatq
 */
public class ArticuloDAO {
    
    public static List <Articulo> obtenerTodos(){
        List<Articulo> articulos = new ArrayList<>();
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
                
            criteriaQuery.from(Articulo.class);
            
            articulos = session.createQuery(criteriaQuery).getResultList();

            
        }catch(Exception ex){
          System.err.println("Ocurrio un error:"+ ex.getMessage());
       }
        return articulos;
    }
    
    /**
     * 
     * @param clave
     * @param nombre
     * @param precio
     * @param proveedor
     * @return 
     */
     public static boolean guardar(String clave, String nombre, double precio, Proveedor proveedor){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de Proveedor
            Articulo a = new Articulo();
            a.setClave(clave);
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setProveedor(proveedor);
            
            session.save(a);
            
            session.getTransaction().commit();
            
            resultado = a.getId()!=0;
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
     
    public Articulo obtenerPorId(int id){
        Articulo articulos = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          articulos = session.get(Articulo.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return articulos;   
    }
    
    /**
     * 
     * @param id
     * @param clave
     * @param nombre
     * @param precio
     * @param proveedor
     * @return 
     */
    public boolean editar(int id, String clave, String nombre, double precio, Proveedor proveedor){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo articulos = obtenerPorId(id);
            
            if(articulos != null){
                articulos.setClave(clave);
                articulos.setNombre(nombre);
                articulos.setPrecio(precio);
                articulos.setProveedor(proveedor);
               
                
                session.saveOrUpdate(articulos);
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
            
            Articulo articulos = obtenerPorId(id);
            
            if(articulos != null){
                session.delete(articulos);
                session.getTransaction().commit();
                
                resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}

