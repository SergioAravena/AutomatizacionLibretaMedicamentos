
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.MedicamentoDto;
import dao.MedicamentoDao;
import java.sql.Date;


/**
 *
 * @author Sergio
 */
public class AgregarMedicamento extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            String mensaje = "";
            MedicamentoDto dto = new MedicamentoDto();
            dto.setCodigo(Integer.parseInt(request.getParameter("txtCodigo".trim())));
            dto.setNombre(request.getParameter("txtNombre".trim()));
            dto.setTipo(request.getParameter("txtTipo".trim()));
            dto.setFabricante(request.getParameter("txtFabricante".trim()));
            dto.setComponente(request.getParameter("txtComponente".trim()));
            dto.setContenido(request.getParameter("txtContenido".trim()));
            dto.setCantidad(request.getParameter("txtCantidad".trim()));
            dto.setGramaje(request.getParameter("txtGramaje".trim()));
            dto.setFecha_vencimiento(Date.valueOf(request.getParameter("txtFechaVencimiento")));
            dto.setEstado(request.getParameter("txtEstado".trim()));
            dto.setId_seccion(Integer.parseInt(request.getParameter("txtIdSeccion".trim())));
             
            if(new dao.MedicamentoDao().agregar(dto)){
                
                mensaje = "Medicamento agregado";
                
            }else{
                mensaje = "Medicamento no agregado";
            }
            
            request.setAttribute("lista",  new dao.MedicamentoDao().listar());
            request.setAttribute("mensaje", mensaje);
            
           request.getRequestDispatcher("Medicamento/AgregarMedicamento.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}