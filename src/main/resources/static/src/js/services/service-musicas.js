angular.module("main").factory("$serviceMusicas", function ($http) {
	var musicas = [];
	var atualizaMusicas = function () {
		$http.get("/musicas")
		.then(function (musicas) {
			musicas = musicas.data;
		})
		.catch(function (erro) {
			console.log(erro);
		});
	}
	return {
		musicas: musicas,
		atualizaMusicas: atualizaMusicas
	};
});