<%@page import="Bll.BllCategoria"%>
<%@page import="Modelo.Categoria"%>
<%@page import="Bll.BllProduto"%>
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
    <title>Busca | E-Shopper</title>
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
        BllProduto bll = new BllProduto();
        BllCategoria bllC = new BllCategoria();
        ArrayList<Categoria> listaCat = bllC.listar();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(msg == null){ msg=" Bem vindo ";}
        ArrayList<Produto> produtosConsulta = (ArrayList<Produto>) session.getAttribute("produtosConsulta");
        ArrayList<Produto> top6 = bll.buscarTop6();
        if(produtosConsulta == null || produtosConsulta.isEmpty()) {
            msg = "Nenhum produto foi localizado";
            session.setAttribute("msg", msg);
            response.sendRedirect("index.jsp");
        }
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
                                            <a href="lobby.jsp"  class="fa fa-home">Sua Conta</a>
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
                    TOP 6
    -->
    <div class="col-md-8 " style=" margin-left: 350px;" >    
        <div class="recommended_items"><!--recommended_items-->        
            <h2 class="title text-center">recommended items</h2>            
            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                            <div class="item active">
                                <%                                     
                                    int i = 0;
                                    for(Produto p : top6){
                                        if(i <= 2 ){ 
                                %>
                                    <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                    <div class="single-products">
                                                            <div class="productinfo text-center">
                                                                <img src="imagens/<%= p.getImagem()%>" width="200px" height="300px" alt="<%= p.getImagem()%>" />
                                                                    <h2>R$ <%=p.getValorVenda()%></h2>
                                                                    <p><%= p.getNome() %></p>
                                                                    <a href="ControleCarrinho?acao=detalhesProduto&idProduto=<%=p.getIdProduto()%>"  class="btn btn-default add-to-cart " <i class="fa-shopping-bag"> Detalhes do Produto </a>
                                                            </div>
                                                    </div>
                                            </div>
                                    </div> 
                                    <%  }i++;} %>
                            </div>
                            <div class="item ">	
                                <%
                                    i=0;
                                    for(Produto p : top6){
                                        if(i > 2 && i <=5){                                      
                                %>                                
                                     <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="imagens/<%= p.getImagem()%>" width="200px" height="300px" alt="<%= p.getImagem()%>" />
                                                    <h2>R$ <%=p.getValorVenda()%></h2>
                                                    <p><%= p.getNome() %></p>
                                                    <a href="ControleCarrinho?acao=detalhesProduto&idProduto=<%=p.getIdProduto()%>"  class="btn btn-default add-to-cart " <i class="fa-shopping-bag"> Detalhes do Produto </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                                           
                            <%  }i++;} %>                                  
                            </div> 
                    </div>
                     <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                            <i class="fa fa-angle-left"></i>
                      </a>
                      <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                            <i class="fa fa-angle-right"></i>
                      </a>			
            </div>
        </div>           
    </div>

    <!--
          TOP 6 FIM
    -->    
        <section>
        <div class="container">
            <div class="row">
        <!--
            CATEGORIA
        -->
            <div class="col-sm-3">
                <div class="left-sidebar">        
                    <h2>Categorias</h2>
                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                        <%
                            for (Categoria c : listaCat){
                        %>
                        <div class="panel panel-warning" >                        
                            <div class="panel-heading" id="painelMaroto">
                                <h3 ><a href="ControleProduto?acao=FiltrarPorCategoria&idCategoria=<%=c.getIdCategoria()%>"><%=c.getNome() %></a></h3>
                            </div>                        
                        </div> 
                        <% } %>
                    </div>                 
                </div>
            </div>
        <!--
            CATEGORIA FIM
        -->
                <div class="col-sm-9 padding-right">
        <!--	
                PRODUTOS
        -->
                        
                        <div class="features_items"><!--features_items-->
                                <h2 class="title text-center"></h2>
                                <%                                      
                                    for(Produto produto : produtosConsulta){                                            
                                %>
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                    <img   src="imagens/<%=produto.getImagem()%>" alt="<%=produto.getImagem()%>" />
                                                    <h2>Valor : R$  <%=produto.getValorVenda()%></h2>
                                                    <a href="#"  class="btn btn-default add-to-cart " <i class="fa-shopping-bag"></i><%=produto.getNome()%></a>                                                   
                                                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao Carrinho</a>
                                            </div>
                                            <div class="product-overlay">
                                                    <div class="overlay-content">
                                                            <h2>Valor : R$  <%=produto.getValorVenda()%></h2>                                                            
                                                            <a href="ControleCarrinho?acao=detalhesProduto&idProduto=<%=produto.getIdProduto()%>"  class="btn btn-default add-to-cart " <i class="fa-shopping-bag"></i><%=produto.getNome()%></a>                                                   
                                                            <a href="ControleCarrinho?acao=addProduto&idProduto=<%=produto.getIdProduto()%>" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao Carrinho</a>
                                                    </div>
                                            </div>
                                            
                                        </div>								
                                    </div>
                                </div>
                                <%                                   
                                   }//fim do for
                                %>
                        </div>
        <!--		
            PRODUTOS FIM 
        -->	
                </div>
            </div>
        </div>
	</section>
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
