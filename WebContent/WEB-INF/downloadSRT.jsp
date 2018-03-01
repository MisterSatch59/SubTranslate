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
		</style>
	</head>
	<body class = "margin">
		<div class="container">
			<div class="row">
				<c:if test="${ !empty error }">
					<p class = "error col-sm-offset-4 col-sm-4"><span class="glyphicon glyphicon-warning-sign"></span> ${ error }</p>
				</c:if>
			</div>
			<div class="row">
				<a href="/SubTranslate/index" class="btn btn-primary col-sm-offset-3 col-xs-2"><span class="glyphicon glyphicon-arrow-left"></span> Retour accueil</a>
				<a href="${ adresse }${ fileName }" class="btn btn-success col-sm-offset-2 col-xs-2 "><span class="glyphicon glyphicon-download-alt"></span> Télécharger le fichier</a>
			</div>
		</div>
		
	</body>
</html>