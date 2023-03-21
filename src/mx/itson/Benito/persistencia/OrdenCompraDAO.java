/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.Benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.Benito.entidades.Articulo;
import mx.itson.Benito.entidades.OrdenCompra;
import mx.itson.Benito.entidades.Proveedor;
import mx.itson.Benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
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
    
    /**
     * 
     * @param pedido
     * @param direccion
     * @param telefono
     * @param personaCompra
     * @param folio
     * @param total
     * @param articulo
     * @param proveedor
     * @return 
     */
    
     public static boolean guardar(String pedido, String direccion, String telefono, String personaCompra, String folio, double total, Articulo articulo, Proveedor proveedor){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            //se va a generar una intancia de Proveedor
            OrdenCompra O = new OrdenCompra();
            
            session.save(O);
            O.setPedido(pedido);
            O.setDireccion(direccion);
            O.setTelefono(telefono);
            O.setPersonaCompra(personaCompra);
            O.setFolio(folio);
            O.setTotal(total);
            O.setArticulo(articulo);
            O.setProveedor(proveedor);
            
            session.getTransaction().commit();
            
            resultado = O.getId()!=0;
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
     
    public OrdenCompra obtenerPorId(int id){
        OrdenCompra Orden = null;
        
        try{
            
          Session session = HibernateUtil.getSessionFactory().openSession();
          Orden = session.get(OrdenCompra.class, id);
            
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
     return Orden;   
    }
    
    /**
     * 
     * @param id
     * @param pedido
     * @param direccion
     * @param telefono
     * @param personaCompra
     * @param folio
     * @param total
     * @param articulo
     * @param proveedor
     * @return 
     */
    
    public boolean editar(int id, String pedido, String direccion, String telefono, String personaCompra, String folio, double total, Articulo articulo, Proveedor proveedor){
        
        boolean resultado = false;
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            OrdenCompra Orden = obtenerPorId(id);
            
            if(Orden != null){
                Orden.setPedido(pedido);
                Orden.setDireccion(direccion);
                Orden.setTelefono(telefono);
                Orden.setPersonaCompra(personaCompra);
                Orden.setFolio(folio);
                Orden.setTotal(total);
                Orden.setArticulo(articulo);
                Orden.setProveedor(proveedor);
               
                
                session.saveOrUpdate(Orden);
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
            
            OrdenCompra Orden = obtenerPorId(id);
            
            if(Orden != null){
                session.delete(Orden);
                session.getTransaction().commit();
                
                resultado = true;
            }
            }catch(HibernateException ex){
            System.err.println("Ocurrio un error:"+ ex.getMessage());
        }
        return resultado;
    }
}
