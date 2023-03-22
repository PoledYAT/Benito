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
 * DATA ACCESS OBJECT, que acede a los datos de la tabla Articulo
 * @author pyatq
 */
public class ArticuloDAO {
    
    /**
     * Obtine todas las entidades de la lista de Articulo
     * @return a Articulo
     */
    
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
     * Es para guardar los datos de Articulo
     * @param clave guarda los valores de clave 
     * @param nombre guarda nombre
     * @param precio guarda precio
     * @param proveedor guarda proveedor
     * @return Indica si el registro fue guardado correcta mente
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
     * Es para obtener los datos de Articulo desde su ID
     * @param id obtendremos los datos desde el ID
     * @return retorna conductor de lo contrario retarna a null
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
     * Es para editar los datos de la tabla de Articulo
     * @param id el ID se quedara igual por el int
     * @param clave se podra editar la clave
     * @param nombre se podra editar el nombre
     * @param precio se pobra editar el precio
     * @param proveedor se podra editar al proveedor
     * @return es para indicar si el registro fue editado de Articulo retorna false
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
     * Es para eliminar una una fila de un dato de Articulo desde el ID
     * @param id se eliminara el dato desde el ID
     * @return si el registro se elimina correcta mente entrara; pero si no, retornara flase 
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

