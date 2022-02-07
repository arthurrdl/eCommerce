

<%@page import="java.text.NumberFormat"%>
<%@page import="Modelo.Endereco"%>
<%@page import="Modelo.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.ItemDeCompra"%>
<%@page import="Modelo.CarrinhoDeCompra"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>

<!DOCTYPE html>
<%
    String msg = (String) session.getAttribute("msg");                            
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if(msg == null){ msg=" Bem vindo ";} 
    CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");     
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title> Carrinho | E-Shopper </title>
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
                            
                            <% if(usuario == null ){ %>
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
                                            <li><a href="ControleUsuario?acao=sair" class="fa fa-power-off" > Sair </a></li>
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
                                            <a href="ControleCliente?acao=ChecarCliente"  id="SuaConta" class="fa fa-home" >Sua Conta</a>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                      
                                        <div class="shop-menu clearfix pull-right">
                                          
                                            <a href="ControleUsuario?acao=sair" class="fa fa-power-off" > Sair </a>
                                        </div>
                                       
                                    </div> 

                                </div>                                                
                            <% } %>                    
                    </div>
                </div>
            </div><!--/header-middle-->
	
		
	</header>
    <!--        
            Barra Superior Login / FIM       
    -->
	
    <!--
        CARRINHO
    -->
	          
        <section id="cart_items">
            <div class="container">
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description">Nome</td>
                                <td class="price">Preço</td>
                                <td class="quantity">Quantidade</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>     
                        
                        <%
                            for(ItemDeCompra item : carrinho.getItens()){
                        %>
                        <tbody>
                        <tr>
                            <td class="cart_product">
                                <a href="ControleCarrinho?acao=detalhesProduto&idProduto=<%=item.getProduto().getIdProduto()%>"><img src="imagens/<%=item.getProduto().getImagem()%>" alt="<%=item.getProduto().getImagem()%>" width="100px" height="100px" ></a>
                            </td>
                            <td class="cart_description">
                                <h4><%=item.getProduto().getNome() %></h4>                                
                            </td>
                            <td class="cart_price">
                                <p><%=NumberFormat.getCurrencyInstance().format(item.getProduto().getValorVenda()) %></p>
                            </td>
                            <td class="cart_quantity">
                                <div class="cart_quantity_button">
                                    <a class="cart_quantity_up" href="ControleCarrinho?acao=addProduto&idProduto=<%=item.getProduto().getIdProduto()%>"> + </a>
                                    <input class="cart_quantity_input" type="text" name="quantity" value="<%=item.getQuantidade()%>" readonly autocomplete="off" size="2">
                                    <a class="cart_quantity_down" href="ControleCarrinho?acao=diminuiProduto&idProduto=<%=item.getProduto().getIdProduto()%>"> - </a>
                                </div>
                            </td>
                            <td class="cart_total">
                                <p class="cart_total_price"> <%=NumberFormat.getCurrencyInstance().format(item.getTotal()) %></p>
                            </td>
                            <td class="cart_delete">
                                <a href="ControleCarrinho?acao=removeProduto&idProduto=<%=item.getProduto().getIdProduto()%>" class="cart_quantity_delete"><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        </tbody>
                        <%
                            }
                        %>

                            
                    </table>
                </div>
            </div>
	</section> <!--/#cart_items-->        
        
        
    <!--
        CARRINHO FIM
    -->
	
    <!--
            OPÇÕES
    -->	
            <section id="do_action">
            <div class="container">			
                    <div class="row">
                            <div class="col-sm-6">					
                            </div>
                            <div class="col-sm-6">
                                <div class="total_area">
                                    <ul>							
                                        <li>Total<span> <%=NumberFormat.getCurrencyInstance().format(carrinho.calculaTotal()) %></span></li>
                                    </ul>
                                        <div class="row">
                                            				
                                            <a class="btn btn-default update" href="ControleCarrinho?acao=cancelaCompra">Cancelar Compra</a>
                                            <a class="btn btn-default update" href="index.jsp" id="ContinuarComprando" >Continuar Comprando</a>                                       
                                            <a class="btn btn-default check_out" href="ControleCarrinho?acao=finalizaCompra" id="finalizarCompra" >Finalizar Compra   </a>							
                                            
                                        </div>

                                </div>
                            </div>
                    </div>
            </div>
	</section>
    <!--
            OPÇÕES
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
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
