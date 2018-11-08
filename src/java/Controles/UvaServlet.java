/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOUva;
import Entidades.Uva;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaque
 */
@WebServlet(name = "UvaServlet", urlPatterns = {"/uva"})
public class UvaServlet extends HttpServlet {

    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DAOUva daoUva = new DAOUva();
    Uva uva = new Uva();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idUva = 0;
        String nomeUva = "";
        String resultadoUva = "";
        String produtorUva = "";
        String paisDeOrigemUva = "";

        try (PrintWriter out = response.getWriter()) {
            idUva = daoUva.autoIdUva();
//            idUva = Integer.parseInt(request.getParameter("idUva"));
            nomeUva = request.getParameter("nomeUva");
            produtorUva = request.getParameter("produtorUva");
            paisDeOrigemUva = request.getParameter("paisDeOrigemUva");
            uva.setIdUva(daoUva.autoIdUva());
            uva.setNomeUva(nomeUva);
            uva.setProdutorUva(produtorUva);
            uva.setPaisDeOrigemUva(paisDeOrigemUva);
            daoUva.inserir(uva);
            resultadoUva = listaUvasCadastrados();
            request.getSession().setAttribute("resultadoUva", resultadoUva);
            response.sendRedirect(request.getContextPath() + "/paginas/uvaLista.jsp");
        }
    }

    protected String listaUvaNome(String nomeUva) {
        DAOUva uva = new DAOUva();
        String tabela = "";
        List<Uva> lista = uva.listByNome(nomeUva);
        for (Uva l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdUva() + "</td>"
                    + "<td>" + l.getNomeUva() + "</td>"
                    + "<td>" + l.getProdutorUva() + "</td>"
                    + "<td>" + l.getPaisDeOrigemUva() + "</td>"
                    + "</tr>";
        }

        return tabela;
    }

    protected String listaUvasCadastrados() {
        DAOUva uva = new DAOUva();
        String tabela = "";
        List<Uva> lista = uva.listInOrderNome();
        for (Uva l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdUva() + "</td>"
                    + "<td>" + l.getNomeUva() + "</td>"
                    + "<td>" + l.getProdutorUva() + "</td>"
                    + "<td>" + l.getPaisDeOrigemUva() + "</td>"
                    + "</tr>";
        }

        return tabela;
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
        System.out.println("teste doget");
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
        System.out.println("teste dopost");
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
