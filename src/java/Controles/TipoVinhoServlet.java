/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOTipoVinho;
import DAOs.DAOTipoVinho;
import DAOs.DAOUva;
import Entidades.TipoVinho;
import Entidades.TipoVinho;
import Entidades.Uva;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaque
 */
@WebServlet(name = "TipoVinhoServlet", urlPatterns = {"/tipoVinho"})

public class TipoVinhoServlet extends HttpServlet {

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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            DAOTipoVinho daoTipoVinho = new DAOTipoVinho();
            List<TipoVinho> resultadoTipoVinho = new ArrayList<>();

            //parametros do form
            //aqui pq se passar do if não serão nulos
            //tudo que vem do formulario é string, por isso aqui alguns precisam de conversão
            
            int idTipoVinho = daoTipoVinho.autoIdTipoVinho();
//            Integer idTipoVinho = Integer.valueOf(request.getParameter("idTipoVinho"));
            String nomeTipoVinho = request.getParameter("nomeTipoVinho");
            
            
            Integer uvaId = Integer.valueOf(request.getParameter("uvaTipoVinho"));
            
            TipoVinho tipoVinho = new TipoVinho();
            
            DAOUva daoUva = new DAOUva();
            
            

            //busca a categoria do id selecionado no select do form
            //busca com o listById para criar um objeto de entidade completo, 
            //que é o parâmetro que o set de categoria pede
            Uva uva = daoUva.listById(uvaId).get(0);
            

            //seta informacoes do tipoVinho na entidade
            //essa tabela nao tem id automatico no banco, então precisa setar
            //para nao pedir p/ usuario no formulario e correr o risco de repetição
            //use a função do dao p/ calcular o id
            tipoVinho.setIdTipo(idTipoVinho);
            tipoVinho.setNomeTipo(nomeTipoVinho);
            
            //seta a categoria do tipoVinho, que vai gravar apenas o id como fk no tipoVinho  no banco
            //porém, aqui é orientado a objeto, então o categoria é um objeto da entidade categoria
            tipoVinho.setUvaiduva(daoUva.obter(uvaId));
            

            //insere o tipoVinho no banco
            daoTipoVinho.inserir(tipoVinho);
            //faz a busca p/ direcionar p/ uma lista atualizada
            //só se sua lista usa servlet, se for com JSTL ou scriplet é só redirecionar
            resultadoTipoVinho = daoTipoVinho.listInOrderId(); 
            String tabela = listaTipoVinhosCadastrados();
            request.getSession().setAttribute("tabela", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/tipoVinhoLista.jsp");
        }
    }

    protected String listaTipoVinhoNome(String nomeTipoVinho) {
        DAOTipoVinho tipo_vinho = new DAOTipoVinho();
        String tabela = "";
        List<TipoVinho> lista = tipo_vinho.listByNome(nomeTipoVinho);
        for (TipoVinho l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdTipo() + "</td>"
                    + "<td>" + l.getNomeTipo() + "</td>"
                    + "<td>" + l.getUvaiduva()+ "</td>"
                    + "</tr>";
        }

        return tabela;
    }

    protected String listaTipoVinhosCadastrados() {
        DAOTipoVinho tipo_vinho = new DAOTipoVinho();
        String tabela = "";
        List<TipoVinho> lista = tipo_vinho.listInOrderNome();
        for (TipoVinho l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdTipo() + "</td>"
                    + "<td>" + l.getNomeTipo() + "</td>"
                    + "<td>" + l.getUvaiduva()+ "</td>"
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }
// </editor-fold>

}
