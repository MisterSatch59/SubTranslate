<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<title>Traducteur de sous titres</title>
		<style type="text/css">
			.error {
				color : red ;
				margin : 10px 10px 10px 10px;
			}
			.margin{
				margin : 10px 10px 10px 10px;
			}
			td{
				padding: 2px 5px 2px 5px;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<c:if test="${ !empty error }">
					<p class = "error col-sm-offset-4 col-sm-4"><span class="glyphicon glyphicon-warning-sign"></span> ${ error }</p>
				</c:if>
			</div>
		</div>

		<p><a href="/SubTranslate/index" class="btn btn-primary margin"><span class="glyphicon glyphicon-arrow-left"></span> Retour accueil</a></p>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-offset-2 col-sm-8">
					<form method="post" action="EditSubtitle">
						<input type="submit" value = "enregistrer les modifications" class="btn btn-primary" style="position:fixed; top: 20px; right: 20px;" />
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
				</div>
			</div>
		</div>
	</body>
</html>