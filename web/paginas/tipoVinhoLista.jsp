<%-- 
    scriptlet
    Document   : testeUva
    Created on : 22/05/2018, 16:34:21
    Author     : Angelo
--%>
<%@page import="Entidades.Uva"%>
<%@page import="DAOs.DAOUva"%>
<%@page import="java.util.List"%>
<%@page import="DAOs.DAOTipoVinho"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Entidades.TipoVinho"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);
    DAOTipoVinho dao = new DAOTipoVinho();
    List<TipoVinho> tipos = dao.listInOrderNome();
    DAOUva daoUva = new DAOUva();
    List<String> uvas = daoUva.listInOrderNomeStrings("id");
    
%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" type="image/png" href="vinho.png"/>
        <title>Lista de produtos</title>

        <!-- Bootstrap Core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        
        <link href="../Estilos/Menus.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->


    </head>

    <body>
        <nav id="menu">
                <ul>
                    <li><a href="cadastroUva.jsp">Cadastro uva</a></li>
                    <li><a href="cadastroCliente.jsp">Cadastro cliente</a></li>
                    <li><a href="cadastroTipoVinho.jsp">Cadastro tipo vinho</a></li>
                    <li><a href="uvaLista.jsp">Lista uva</a></li>
                    <li><a href="clienteLista.jsp">Lista cliente</a></li>
                    <li><a href="tipoVinhoLista.jsp">Lista tipo Vinho</a></li>
                    
                    
                    
                </ul>
        </nav>
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 align="center">Tipos Cadastrados
                    <img src="wine-glass.png" alt="" width="100px" height="100px" float="left"/>
                    </h1>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row"><div class="col-sm-12">
                                <table width="100%" class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info" style="width: 100%;">
                                    <thead>
                                        <tr role="row">
                                            <th class="sorting_asc" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Id do Tipo" style="width: 170px;">id</th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Nome" style="width: 189px;">nome</th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Id Uva" style="width: 147px;">Id Uva</th>
                                            
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                                        <%
                                            for (TipoVinho c : tipos) {
                                            System.out.println(c.getUvaiduva());
                                            int uvaIde = c.getUvaiduva().getIdUva();
                                        %>
                                        
                                        <tr>
                                            
                                            <td><%=c.getIdTipo()%></td>
                                            <td><%=c.getNomeTipo()%></td>
                                            <td><%=c.getUvaiduva().getNomeUva() %></td>
                                            
                                            
                                        </tr>
                                        <%}%>
                                        
                                    </tbody>
                                </table></div></div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>

            <!-- jQuery -->
            <script src="../vendor/jquery/jquery.min.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

            <!-- Metis Menu Plugin JavaScript -->
            <script src="../vendor/metisMenu/metisMenu.min.js"></script>

            <!-- DataTables JavaScript -->
            <script src="../vendor/datatables/js/jquery.dataTables.min.js"></script>
            <script src="../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
            <script src="../vendor/datatables-responsive/dataTables.responsive.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="../dist/js/sb-admin-2.js"></script>

            <!-- Page-Level Demo Scripts - Tables - Use for reference -->
            <script>
                $(document).ready(function () {
                    $('#dataTables-example').DataTable({
                        responsive: true
                    });
                });
            </script>
    </body>

</html>
