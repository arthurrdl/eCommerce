
<%@page import="java.text.NumberFormat"%>
<%@page import="Modelo.ItemDeCompra"%>
<%@page import="Modelo.Endereco"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.CarrinhoDeCompra"%>
<%@page import="Modelo.Usuario"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>

<!DOCTYPE html>
<%
    String msg = (String) session.getAttribute("msg"); 
    String msgCheck = (String) session.getAttribute("msgCheck");  
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    Cliente cliente = (Cliente) session.getAttribute("cliente");
    Endereco endereco = (Endereco) session.getAttribute("endereco");
    if(msg == null){ msg=" Bem vindo ";} 
    if(msgCheck==null){msgCheck="";}
    CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");
    
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Finalizar Compra | E-Shopper</title>
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
                            
                            <% if(usuario== null ){ %>
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
                                        <form action="ControleUsuario" method="post"  class="form-inline" autocomplete="on">
                                            <div class="shop-menu clearfix pull-right">                                                                                                                                       
                                                <a href="carrinho.jsp" class="fa fa-shopping-cart"> Carrinho</a>                                                                            
                                            </div>
                                        </form>
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
        CLIENTE
    -->
        <section id="cart_items">
            
            <div class="container">
                <h4 style="text-align: center;"><%=msgCheck%></h4>
            </div>
            
            <div class="container">				
                <div class="step-one">
                        <h2 class="heading">Informações do Cliente</h2>
                </div>	
            </div>
               
            
            
            
            <div class="container">
                <div class="row">
                    <div class="col-md-6">                         
                    <p><label>Nome     : </label> <%= cliente.getNome() %> </p>
                    <p><label>RG       : </label><%= cliente.getRg() %></p>
                    <p><label>CPF      : </label><%= cliente.getCpf() %></p>
                    <p><label>Telefone : </label><%= cliente.getTelefone() %></p>
                    <p><label>Celular  : </label> <%= cliente.getCelular()%></p>
                    </div>
                    
                    <div class="col-md-6">                    
                    <p><label>Logradouro : </label> <%= endereco.getLogradouro() %> </p>
                    <p><label>Número     : </label> <%= endereco.getNumero() %> </p>
                    <p><label>Bairro     : </label> <%= endereco.getBairro() %> </p>
                    <p><label>Cidade     : </label> <%= endereco.getCidade() %> </p>
                    <p><label>CEP        : </label> <%= endereco.getCep() %> </p>
                    </div>
                </div>    
                
            </div>			
        </section>
    <!--
        CLIENTE OFF
    -->
        
        
    <!--				
        CARRINHO
    -->
				
       <section id="cart_items">
       <div class="container">	    
        <div class="review-payment">
                <h2>Compras & Pagamento</h2>
        </div>

        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                    <tr class="cart_menu">
                        <td class="image">Item</td>
                        <td class="description"></td>
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
                                <a href="ControleCarrinho?acao=detalhesProduto&idProduto=<%=item.getProduto().getIdProduto()%>"><img src="imagens/<%=item.getProduto().getImagem()%>" width="100px" height="100px" alt="<%=item.getProduto().getImagem()%>"></a>
                            </td>
                            <td class="cart_description">
                                <h4><%=item.getProduto().getNome() %></h4>                                
                            </td>
                            <td class="cart_price">
                                <p><%=NumberFormat.getCurrencyInstance().format(item.getProduto().getValorCompra()) %></p>
                            </td>
                            <td class="cart_quantity">
                                <div class="cart_quantity_button">
                                    <a class="cart_quantity_up" href="ControleCarrinho?acao=addProduto&idProduto=<%=item.getProduto().getIdProduto()%>"> + </a>
                                    <input class="cart_quantity_input" type="text" name="quantity" value="<%=item.getQuantidade()%>" autocomplete="off" size="2">
                                    <a class="cart_quantity_down" href="ControleCarrinho?acao=diminuiProduto&idProduto=<%=item.getProduto().getIdProduto()%>"> - </a>
                                </div>
                            </td>
                            <td class="cart_total">
                                <p class="cart_total_price"><%=NumberFormat.getCurrencyInstance().format(item.getTotal())%></p>
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
       </section>	
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
                        <form action="ControleCompra" method="post">
                            <div class="total_area"> 
                                <h2 class="title text-center">Finalizando Compra</h2>
                                    
                                <div class="form-inline"style="padding-top: 20px;  padding-left: 20px;" >                        
                                    <div class="form-group">
                                        Forma de Pagamento  
                                    </div>
                                    <div class="form-group" style="padding-left: 155px;">
                                        <select name="forma" class="custom-select"   style=" width: 175px; ">
                                            <option value="dinheiro">Dinheiro</option>
                                            <option value="cartao">Cartão</option>
                                            <option value="deposito">Depósito</option>

                                        </select> 
                                    </div>
                                    </div>
                                
                                    <div class="form-inline"style="padding-top: 10px; padding-left: 20px;">   
                                        <div class="form-group">
                                            Total
                                        </div>
                                        <div class="form-group" style="padding-left: 260px;">
                                            <input  name="valorTotal" value="<%=NumberFormat.getCurrencyInstance().format(carrinho.calculaTotal()) %>"  readonly  style=" width: 175px; ">
                                        </div>
                                        
                                        <div class="form-group">
                                            <a class="btn btn-default update" href="ControleCarrinho?acao=cancelaCompra" style=" width: 200px; height: 50px; font-size: 15pt;" >Cancelar Compra</a>
                                            <input class="btn btn-default update" type="submit" name="acao" value="Finalizar Pedido"  id="finalizarPedido" style=" width: 200px; height: 50px; font-size: 15pt;">
                                        </div>
                                    </div>                           
                            </div>
                        </form>    
                            
                    </div>
                </div>
            </div>
	</section> 
    <!--
        OPÇÕES FIM
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
