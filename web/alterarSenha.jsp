<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Endereco"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Produto"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>

<!DOCTYPE html>

        <%
            String msg = (String) session.getAttribute("msg");                            
            if(msg == null){ msg=" Bem vindo ";}                                        
            Usuario usuario = (Usuario) session.getAttribute("usuario");    
        %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title> Alterar Senha | E-Shopper</title>
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
                            
                            <% if(usuario == null || usuario.getUsuario() == null ){ %>
                                <form action="ControleUsuario" class="form-inline"  method="post"  autocomplete="on">                                                   
                                    <div class="shop-menu clearfix pull-right">
                                        <ul class="nav navbar-nav">	
                                            <li><h6><%=msg%></h6></li>                 
                                            <li><a href="cadastro.jsp"><i class="fa fa-lock"></i> Cadastre-se</a></li>
                                            <li><input type="text" name="usuario" placeholder="Usuário" pattern="[a-zA-Z0-9]+" required></li>
                                            <li><input type="password" name="senha"  placeholder="Senha" pattern="[a-zA-Z0-9]+" required></li>
                                            <li><input type="submit" name="acao" id="fazerLogin" value="Entrar"></li> 
                                            <li><a href="ControleCarrinho?acao=verificaCarrinho" class="btn btn-outline-success" ><i class="fa fa-shopping-cart fa-1x"></i>  Carrinho</a></li>                          
                                        </ul>                                    
                                    </div>                                      
                                </form>                   
                            <% }else if(usuario.getAtivo()==false){  %>
                                                                               
                                    <div class="shop-menu clearfix pull-right">
                                        <ul class="nav navbar-nav">	
                                            <li><h6><%=msg%></h6></li>                                          
                                            <li><a href="ControleUsuario?acao=Reenviar E-mail" class="btn btn-primary" >Reenviar E-mail</a>   
                                            <li><a href="ControleUsuario?acao=sair" class="fa fa-power-off"> Sair </a><li>
                                            <li><a href="ControleCarrinho?acao=verificaCarrinho" class="btn btn-outline-success" ><i class="fa fa-shopping-cart fa-1x"></i>  Carrinho</a></li> 
                                        </ul>                                    
                                    </div>      
                                                            
                            <% }else{  %>    
                                <div class="form-inline" style="text-align:right ;">  
                                       
                                    <div class="form-group">
                                        <h6><%=msg%> &nbsp &nbsp </h6>
                                    </div>                      
                                     <div class="form-group">
                                        <div class="shop-menu clearfix pull-right">
                                            <a href="ControleCompra?acao=buscarCompras"  class="fa fa-home">Sua Conta</a>
                                        </div>
                                    </div>
                                    <div class="form-group">                                      
                                        <div class="shop-menu clearfix pull-right">                                          
                                            <a href="ControleUsuario?acao=sair" class="fa fa-power-off" > Sair </a>
                                        </div>                                       
                                    </div>                                     
                                    <div class="form-group">                                      
                                        <div class="shop-menu clearfix pull-right">                                          
                                            <a href="ControleCarrinho?acao=verificaCarrinho" class="btn btn-outline-success" ><i class="fa fa-shopping-cart fa-1x"></i>  Carrinho</a>
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
                                        <input type="text" name="consulta" placeholder="Buscar" pattern="[a-zA-Z0-9]+" required>
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
                            <h4 class="panel-title"><a href="alterarSenha.jsp">Alterar Senha</a></h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="ControleCliente?acao=ChecarCliente"> Dados Pessoais </a></h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                            <h4 class="panel-title"><a href="index.jsp">Continuar as Compras</a></h4>
                    </div>
                </div>					
          </div>
    </div>
</div>

<!--
    Painel FIM
-->                

<!--
    Alteração de Senha
-->

<div class="col-sm-9 padding-right">    
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>Alterar Senha</h2>
                    <form action="ControleUsuario"   method="post"  autocomplete="on">
                        <input type="text" placeholder="Usuario" name="usuario" pattern="[a-zA-Z0-9]+" required >
                        <input type="password" placeholder="Senha Atual" name="senhaAtual" pattern="[a-zA-Z0-9]+" required >
                        <input type="password" placeholder="Nova Senha" name="novaSenha" pattern="[a-zA-Z0-9]+" required >
                        <input type="submit" class="btn btn-primary" name="acao" value="Alterar Senha" >
                    </form>
                </div><!--/sign up form-->
            </div>
        </div>
    </div>

</div>   

<!--
    Alteração de Senha
-->
                             
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


