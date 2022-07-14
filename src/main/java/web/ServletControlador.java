package web;

import data.VinilosDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String accion = req.getParameter("accion");
        
        if(accion!=null){
            switch(accion){
                case "eliminar":
                    eliminarVinilo(req,res);
                    break;
                case "editar":
                    editarVinilo(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String queryParam = req.getParameter("accion");
        if(queryParam!=null){
            switch(queryParam){
                case "insertar":
                    insertarVinilo(req,res);
                    break;
                case "modificar":
                    modificarVinilo(req,res);
                    break;
                default:
                    accionDefault(req,res);
                    break;
            }
        }
    }
    
    private void accionDefault(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        List <Vinilos> vinilos = new VinilosDAO().findAll();
        vinilos.forEach(System.out::println);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("vinilos", vinilos);
        sesion.setAttribute("cantidadVinilos",calcularCopias(vinilos));
        sesion.setAttribute("precioTotal", calcularPrecio(vinilos));
//        req.getRequestDispatcher("libros.jsp").forward(req, res);
        res.sendRedirect("vinilos.jsp");
    }
    
    private void insertarVinilo(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String autor = req.getParameter("autor");
        int cantCanciones = Integer.parseInt(req.getParameter("cantCanciones"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int copias = Integer.parseInt(req.getParameter("copias"));
        
        Vinilos vinilo = new Vinilos(nombre, autor, cantCanciones, precio, copias);
        
        int registrosMod = new VinilosDAO().insert(vinilo);
        
        System.out.println("insertados = " + registrosMod);
        
        accionDefault(req, res);
    }
    
    private void eliminarVinilo(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idvinilo = Integer.parseInt(req.getParameter("idVinilo"));
        
        int regMod = new VinilosDAO().deleteVinilo(idvinilo);
        
        System.out.println("SE ELIMINARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private void editarVinilo(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idVinilo = Integer.parseInt(req.getParameter("idvinilo"));
        Vinilos vinilo = new VinilosDAO().findById(idVinilo);
        req.setAttribute("vinilo", vinilo);
        String jspEditar = "/WEB-INF/paginas/operaciones/editar.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, res);
    }
    
    private void modificarVinilo(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String autor = req.getParameter("autor");
        int cantCanciones = Integer.parseInt(req.getParameter("cantCanciones"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int copias = Integer.parseInt(req.getParameter("copias"));
        
        int idVinilo = Integer.parseInt(req.getParameter("idVinilo"));
        
        Vinilos vin = new Vinilos(idVinilo,nombre,autor,cantCanciones,precio,copias);
        
        int regMod = new VinilosDAO().update(vin);
        
        System.out.println("SE ACTUALIZARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private int calcularCopias(List<Vinilos> vin){
        int cant=0;
        for (int i = 0; i < vin.size(); i++) {
            cant += vin.get(i).getCopias();
        }
        return cant;
    }
    
    private double calcularPrecio(List<Vinilos> vin){
        double precio = 0;
        for (int i = 0; i < vin.size(); i++) {
            precio += (vin.get(i).getPrecio() * vin.get(i).getCopias());
        }
        return precio;
    }
}
