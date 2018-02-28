<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Traducteur de sous titres</title>
	</head>
	<body>
		<p> <a href="/SubTranslate/index">Retour accueil</a></p>
		<form method="post" action="EditSubtitle">    
	        <input type="submit" value = "enregistrer les modifications" style="position:fixed; top: 10px; right: 10px;" />
		    <table>
		        <c:forEach items="${ original }" var="tab" varStatus="status">
			        	<tr>
			        		<td style="text-align:right;"><c:out value="${ tab[0] }" /></td>
			        		<td style="text-align:right;"><c:out value="${ destination[status.index][0] }" /></td>
			        	</tr>
			        	<tr>
			        		<td style="text-align:right;"><c:out value="${ tab[1] }" /></td>
			        		<td style="text-align:right;"><c:out value="${ destination[status.index][1] }" /></td>
			        	</tr>
			        	<tr>
			        		<td style="text-align:right;"><c:out value="${ tab[2] }" /></td>
			        		<td><input type="text" name="line1-${ status.index }" value="${ destination[status.index][2] }" size="35" /></td>
			        	</tr>
			        	<tr>
			        		<td style="text-align:right;"><c:out value="${ tab[3] }" /></td>
			        		<td><input type="text" name="line2-${ status.index }" value="${ destination[status.index][3] }" size="35" /></td>
			        	</tr>
		    	</c:forEach>
		    </table>
	    </form>
	</body>
</html>