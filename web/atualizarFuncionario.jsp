<%@page import="Bll.BllFuncionario"%>
<%@page import="Modelo.Funcionario"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Endereco"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Produto"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>

<!DOCTYPE html>

        <%  
            String msg = (String) session.getAttribute("msg");
            Usuario u = (Usuario) session.getAttribute("usuario");
            if(u==null){         
                response.sendRedirect("index.jsp");
            }else{
                if(u.getCredencial().equals("Cliente")){
                    response.sendRedirect("index.jsp");}
                if(u.getCredencial().equals("Funcionario")){                    
                    msg = " Apenas o Administrador tem acesso a essa área";
                    session.setAttribute("msgAdm", msg);
                    response.sendRedirect("administrador.jsp");                   
                }
            }
            BllFuncionario bll = new BllFuncionario();                     
            if(msg == null){ msg=" Bem vindo ";}    
            Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");
            ArrayList<Funcionario> listaDeFuncionarios = bll.listarFuncionarios();
        %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Atualizar Funcionário | E-Shopper</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head><!--/head-->

<body>
    
<!--
        Barra Superior Login
-->
    <header id="header"><!--header-->		
        <div class="header-middle"><!--header-middle-->
            <div class="container">
                <div class="row">
                    <div class="col-md-12 clearfix">            
                        <div class="form-inline" style="text-align:right ;">  

                            <div class="form-group">
                                <h6><%=msg%> &nbsp &nbsp </h6>
                            </div>                               
                            <div class="form-group">                                      
                                <div class="shop-menu clearfix pull-right">                                          
                                    <a href="ControleUsuario?acao=sair" class="fa fa-power-off" > Sair </a>
                                </div>                                       
                            </div>                        
                        </div>                                        
                </div>
            </div>
        </div><!--/header-middle-->	
            <div class="header-bottom"><!--header-bottom-->                    
            </div>
    </header>
<!--       
    Barra Superior Login / FIM
-->        
       
    <section>
        <div class="container">
            <div class="row">
<!--
    Painel
-->                
    <div class="col-sm-3">
        <div class="left-sidebar">
            <h2>Acesso</h2>
            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="relatorio.jsp">Relatório & Promoções</a></h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="cadastroFuncionario.jsp">Cadastrar Funcionário</a></h4>
                    </div>
                </div>  
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="atualizarFuncionario.jsp">Atualizar Funcionário</a></h4>
                    </div>
                </div> 
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="cadastroProduto.jsp">Cadastrar Produto</a></h4>
                    </div>
                </div>  
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="atualizarProduto.jsp">Alterar Produto</a></h4>
                    </div>
                </div> 
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="cadastroCategoria.jsp">Cadastrar Categoria</a></h4>
                    </div>
                </div>   
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="senhaFuncionario.jsp">Trocar Senha</a></h4>
                    </div>
                </div> 
            </div>
        </div>
    </div>                 
<!--
    Painel FIM
-->     

<div class="col-sm-9 padding-right" id="DadosPessoaisEEndereco"> 

<!--
    Atualizar Funcionario	
-->
<%
    if(funcionario != null ){
%>
<div class="col-sm-9 padding-right">    
    <div id="dadosClienteVazio">                            
        <form  >
            <h2> Dados Pessoais</h2>                               
        </form>      
        <form method="post" action="ControleFuncionario" >
            <input type="text" class="form-control"  value="<%= funcionario.getIdFuncionario() %>" name="idFuncionario" hidden>
            <input type="text" class="form-control"  value="<%= funcionario.getUsuario().getIdUsuario() %>" name="idUsuario" hidden>
            <input type="text" class="form-control"  value="<%= funcionario.getEndereco().getIdEndereco() %>" name="idEndereco" hidden>
            <div class="form-group col-md-12">
              <label >Nome</label>
              <input type="text" class="form-control"  value="<%= funcionario.getNome() %>" name="nome"  required >
            </div>                                 
            <div class="form-row">
                <div class="form-group  col-md-6">
                  <label >RG</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getRg() %>" name="rg" pattern="[0-9]+" required >
                </div>
                <div class="form-group  col-md-6">
                  <label >CPF</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getCpf() %>" name="cpf" pattern="[0-9]+" required >
                </div>
            </div>
            <div class="form-row">
                <div class="form-group  col-md-6">
                  <label >Telefone</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getTelefone() %>" name="telefone" pattern="[0-9]+" required >
                </div>
                <div class="form-group  col-md-6">
                  <label >Celular</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getCelular() %>" name="celular" pattern="[0-9]+" required >
                </div>
            </div>
            <div class="form-row">
                <div class="form-group  col-md-12">
                  <label >Email</label>
                  <input type="email" class="form-control"  value="<%= funcionario.getUsuario().getEmail() %>" name="email" required >
                  </br></br>
                </div>                
            </div>
            
            <div class="form-row">
                <h2> Dados de Endereço</h2>
            </div>
            <div class="form-group col-md-12">
              <label >Rua/Avenida/Viela </label>
              <input type="text" class="form-control"  value="<%= funcionario.getEndereco().getLogradouro() %>" name="rua"  required >
            </div>                                                            
            <div class="form-row">
                <div class="form-group  col-md-6">
                  <label >Número</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getEndereco().getNumero() %>" name="numero" pattern="[0-9]+" required >
                </div>
                <div class="form-group  col-md-6">
                  <label >CEP</label>
                  <input type="number" class="form-control"  value="<%= funcionario.getEndereco().getCep() %>" name="cep" pattern="[0-9]+" required >
                </div>                                    
            </div>                                
            <div class="form-row">
                <div class="form-group  col-md-6">
                  <label >Bairro</label>
                  <input type="text" class="form-control"  value="<%= funcionario.getEndereco().getBairro() %>"  name="bairro"  required >
                </div>
                <div class="form-group  col-md-6">
                  <label >Cidade</label>
                  <input type="text" class="form-control"  value="<%= funcionario.getEndereco().getCidade() %>" name="cidade"  required >
                </div>                                    
            </div>     
            <div class="form-group  col-md-6">                 
                <input type="submit" name="acao"  class="btn btn-primary" id="CadastroCliente" value="Salvar" >
                
            </div>           
        </form>
    </div>  
</div>
<%
    }
    if(!listaDeFuncionarios.isEmpty()){
        
%>
<div>
        <section id="cart_items">
        <div >
            <table class="table">        <!-- SE REPETE A CADA CARRINHO -->                        
                <thead class="thead-dark">
                    <tr class="cart_menu">  
                        <td class="description"><label> ID </label></td>
                        <td class="description"><label> Nome </label></td>                        
                        <td class="description"><label> CPF </label></td>
                        <td class="description"><label> E-mail </label></td>                     
                    </tr>
                </thead> 
                <tbody>  
                    <%                        
                            for( Funcionario f : listaDeFuncionarios){
                    %>
                    <tr>                  
                        <td class="cart_description">
                            <h6><%=f.getIdFuncionario() %></h6> <!-- ID -->               
                        </td>
                        <td class="cart_description">
                             <h6> <%=f.getNome() %> </h6>     <!-- Nome -->
                        </td>
                        <td class="cart_description">
                             <h6>  <%=f.getCpf() %> </h6>      <!-- CPF -->
                        </td>
                        <td class="cart_description">
                            <h6> <%= f.getUsuario().getEmail()%> </h6> <!-- EMAIL -->
                        </td> 
                        <td class="cart_description">
                            <a class="fa fa-edit" href="ControleFuncionario?acao=LocalizarFuncionario&idFuncionario=<%=f.getIdFuncionario()%>" >Editar</a>                        <!-- Imagem -->
                        </td>                        
                    </tr>
                    <%
                        }
                    %>
                </tbody>                     
            </table>
        </div>
        </section>
    </div>
<%
   }
%>

<!--							
    Atualizar Funcionario 	FIM
-->            
                    
           
                                
    </div>             
    </div>
    </div>
    </section>
                            
    </br></br></br></br></br></br></br>
<!--
 RODAPÉ
-->	
    <footer id="footer">
        <div class="footer-widget">
            <div class="container">
                <div class="col-md12">
                    <div class="single-widget">
                        <h2>Service</h2>
                        <ul class="nav nav-pills nav-stacked">
                        <li><h6>Online Help</h6></li>
                        <li><h6>Contact Us</h6></li>							
                    </div>
                </div>
            </div>
        </div>		
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
                    <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                </div>
            </div>
        </div>		
    </footer>

<!--	
 RODAPÉ FIM	
-->
	

  
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>

