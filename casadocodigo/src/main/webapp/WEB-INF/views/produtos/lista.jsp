<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
</head>
<body>

	<h1>Lista de Produtos</h1>
	
	<div>${sucesso}</div>
	
	<table width="100%">
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
			<td>Data Lançamento</td>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.titulo}</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
				<td>${produto.dataLancamento}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>