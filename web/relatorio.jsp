<%@page import="Modelo.Promocao"%>
<%@page import="Bll.BllPromocao"%>
<%@page import="Bll.BllProduto"%>
<%@page import="ModeloDao.ProdutoDao"%>
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
    BllPromocao bllPromo = new BllPromocao();
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
    if(msg == null){ msg=" Bem vindo ";}        
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Relatório | E-Shopper </title>
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

    
<!--			
    Relatórios
-->
   
    <div class="col-sm-9 padding-right">    
    <div > 
        <form method="post" action="ControleProduto" >
            <h2> Gerar Relatório Por Data</h2>
            <div class="row" >                
               <div class="form-group col-md-6">
                    <label >Data Inicial</label>
                    <input type="date" class="form-control"   name="datainicial" required="required">                    
                </div>  
                <div class="form-group col-md-6">                   
                    <label >Data Final</label>
                    <input type="date" class="form-control"   name="datafinal" required="required">
                </div> 
            </div>
            <div class="form-group  col-md-6">                 
                <input type="submit" name="acao"  class="btn btn-primary"  value="Emitir" >
            </div>
        </form>  
        
        <div>
         </br></br></br></br>
         <h2> Gerar Relatório Mensal</h2></be>
         <a class="btn btn-primary" href="ControleProduto?acao=relatorioMensal" >Relatório Mensal</a>   
        </div>
        
    </div> 
        
        <div >   
            <%
               Promocao promo = bllPromo.buscar();
               if(promo.getAtivo()== false){
            %>
            <form method="post" action="ControleProduto" >
                </br></br></br></br>
                <h2> Gerar Nova Promoção</h2>
                <div class="row">                
                    <div class="form-group col-md-3">
                        <label >Porcentagem</label>
                        <input type="number" class="form-control"   name="porcentagem" required="required" pattern="[0-9]+">                    
                    </div>            
                </div>
            <div class="form-group  col-md-6">                 
                <input type="submit" name="acao"  class="btn btn-primary" id="CadastroCliente" value="Atualizar" >
            </div>
            <%
                }else{
            %>            
            </form>
            <div>
                </br></br></br></br>
                <h2> Remover Promoção</h2></be>
                <a class="btn btn-primary" href="ControleProduto?acao=Remover" >Remover Promoção</a>   
           </div>
           <%
             }   
            %> 
        <div>
         
        
    </div>
    </div>  
<!--							
    Relatórios 	FIM
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

