<%@page import="java.text.NumberFormat"%>
<%@page import="Modelo.Endereco"%>
<%@page import="ModeloDao.EnderecoDao"%>
<%@page import="Modelo.Cliente"%>
<%@page import="ModeloDao.ClienteDao"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Produto"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>

<!DOCTYPE html>

             
        
        
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Detalhes Produto | E-Shopper </title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
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
        
    <%
        String msg = (String) session.getAttribute("msg");                            
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(msg == null){ msg=" Bem vindo ";}
         Produto produto = (Produto) session.getAttribute("produto");
    %>
    <!--
            Barra Superior Login
    -->
        <header id="header"><!--header-->
		
            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 clearfix">                            
                            
                            <% if(usuario == null || usuario.getUsuario() == null ){ %>
                                <form action="ControleUsuario" class="form-inline"  method="post"  autocomplete="on">                                                   
                                    <div class="shop-menu clearfix pull-right">
                                        <ul class="nav navbar-nav">	
                                            <li><h6><%=msg%></h6></li>                 
                                            <li><a href="cadastro.jsp"><i class="fa fa-lock"></i> Cadastre-se</a></li>
                                            <li><input type="text" name="usuario" placeholder="Usuário" pattern="[a-zA-Z0-9]+" required ></li>
                                            <li><input type="password" name="senha"  placeholder="Senha" pattern="[a-zA-Z0-9]+" required ></li>
                                            <li><input type="submit" name="acao" id="fazerLogin" value="Entrar"></li>                        
                                        </ul>                                    
                                    </div>                                      
                                </form>                   
                            <% }else if(usuario.getAtivo()==false){  %>
                                <form action="ControleUsuario" class="form-inline"  method="post"  autocomplete="on">                                                   
                                    <div class="shop-menu clearfix pull-right">
                                        <ul class="nav navbar-nav">	
                                            <li><h6><%=msg%></h6></li>                                          
                                            <li><input type="submit" class="btn btn-primary"  value="Reenviar E-mail de validação"></li>   
                                            <li><a href="sair.jsp" class="fa fa-power-off"> Sair </a><li>
                                        </ul>                                    
                                    </div>                                      
                                </form> 
                            
                            <% }else{  %>    
                                <div class="form-inline" style="text-align:right ;">  
                                       
                                    <div class="form-group">
                                        <h6><%=msg%> &nbsp &nbsp </h6>
                                    </div>
                                    
                                    

                                    <div class="form-group">
                                        <div class="shop-menu clearfix pull-right">
                                            <a href="ControleCliente?acao=ChecarCliente"  class="fa fa-home">Sua Conta</a>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                      
                                        <div class="shop-menu clearfix pull-right">
                                          
                                            <a href="sair.jsp" class="fa fa-power-off" > Sair </a>
                                        </div>
                                       
                                    </div> 

                                </div>                                                
                            <% } %>                    
                    </div>
                </div>
            </div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-9">                              
                                
                                <div class="mainmenu pull-left">
                                    <ul class="nav navbar-nav collapse navbar-collapse">
                                        <li><a href="index.jsp" class="active">Principal</a></li>

                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <form action="ControleProduto" class="form-inline"  method="post"  autocomplete="on">
                                <div class="form-inline">
                                    <div class="search_box pull-right">
                                        <input type="text" name="consulta" placeholder="Buscar" pattern="[a-zA-Z0-9]+" required >
                                    </div>
                                    <div class="form-group">                                    
                                        <input type="submit" name="acao" value="Buscar" hidden>                                    
                                    </div>     
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
		</div>
	</header>
    <!--        
        Barra Superior Login / FIM       
    --> 
        <!--	
            PRODUTO
        -->
        <section>        
        <div class="container">
            <div class="row">             
                <div class="col-sm-12 padding-right">        
                    <div class="row">
                    <div class="col-12 col-lg-7">
                        <div class="single_product_thumb">                                             
                            <img src="imagens/<%= produto.getImagem() %>" alt="<%= produto.getImagem() %>" height="500px" width="500px"/>
                            </div>
                        </div>                    
                    <div class="col-12 col-lg-5">
                        <div class="single_product_desc">
                            <!-- Dados do Produto-->
                            <div class="product-meta-data">
                                <div class="line"></div>
                                <!-- Valor do Produto-->
                                <h4 style="color: #d9534f;" ><%=NumberFormat.getCurrencyInstance().format(produto.getValorVenda()) %></h4>
                                <!-- Nome do Produto-->
                                <h1><%=produto.getNome()%></h1>                         
                            </div>

                            <div class="short_overview my-5">
                                <p><%= produto.getDescricao() %></p>
                            </div>

                            <!-- Adicionar ao Carrinho  -->
                            
                            <form class="cart clearfix" method="post">                                 
                                <a href="ControleCarrinho?acao=addProduto&idProduto=<%=produto.getIdProduto()%>" class="btn btn-outline-success" style=" font-size: 20pt;  "><i class="fa fa-shopping-cart fa-1x"></i>    Adicionar ao Carrinho</a>
                            </form>

                        </div>
                    </div>
                </div>        	
                </div>
            </div>
        </div>
        </section>
        <!--		
            PRODUTO FIM 
        -->
       
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
