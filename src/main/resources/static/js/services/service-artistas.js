angular.module("main").factory("$serviceArtistas", function ($http) {
	var artistas = [];
	var atualizaArtistas = function () {
		$http.get("http://localhost:8080/artistas")
		.then(function (artistas) {
			artistas = artistas.data;
		})
		.catch(function (erro) {
			console.log(erro);
		});
	}
	return {
		artistas: artistas,
		atualizaArtistas: atualizaArtistas
	};
});