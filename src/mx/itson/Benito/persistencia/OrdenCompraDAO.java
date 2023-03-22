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
 * DATA ACCESS OBJECT, que acede a los datos de la tabla Orden de Compra
 * @author pyatq
 */
public class OrdenCompraDAO {
    
    /**
     * Obtine todas las entidades de la lista de Orden de compra
     * @return a Articulo
     */
    
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
     * Es para guardar los datos de Orden de Compra
     * @param pedido se va guarda el numero de pedido
     * @param direccion se va guardar la direccion
     * @param telefono se va guargar el telefono
     * @param personaCompra se va gaudar el nombre de la persona de compra
     * @param folio se va guardar el numero de folio
     * @param total se va guardar el total de compra
     * @param articulo se va guardar el Articulo
     * @param proveedor se va guardar el Proveedor
     * @return Indica si el registro fue guardado correcta mente
     */
    
     public static boolean guardar(String pedido, String direccion, String telefono, String personaCompra, String folio, double total, Articulo articulo, Proveedor proveedor){
        boolean resultado = false;
        
        try{
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            OrdenCompra O = new OrdenCompra();
            O.setPedido(pedido);
            O.setDireccion(direccion);
            O.setTelefono(telefono);
            O.setPersonaCompra(personaCompra);
            O.setFolio(folio);
            O.setTotal(total);
            
            O.setArticulo(articulo);
            O.setProveedor(proveedor);
            
            session.save(O);
            
            session.getTransaction().commit();
            
            resultado = O.getId()!=0;
        }catch(Exception ex){
            
            System.err.println("Ocurrio un error:"+ ex.getMessage());
            
        }
        return resultado;
    }
    
    /**
     * Es para obtener los datos de Orden de Compra desde su ID
     * @param id obtendremos los datos desde el ID
     * @return retorna conductor de lo contrario retarna a null
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
     * Es para editar los datos de la tabla de Orden de Compra
     * @param id el ID se quedara igual por el int
     * @param pedido se podra editar pedido
     * @param direccion se podra editar direccion
     * @param telefono se podra editar telefono
     * @param personaCompra se podra editar la persona de compra
     * @param folio se podra editar folio
     * @param total se podra editar el total 
     * @param articulo se podra editar articulo
     * @param proveedor se podra editar proveedor
     * @return es para indicar si el registro fue editado de Orden de Compra retorna false
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
     * Es para eliminar una una fila de un dato de Orden de Compra desde el ID
     * @param id se eliminara el dato desde el ID
     * @return si el registro se elimina correcta mente entrara; pero si no, retornara flase 
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
