angular.module("main").factory("$serviceArtistas", function ($http) {
	var artistas = [];
	var atualizaArtistas = function () {
		$http.get("/artistas")
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