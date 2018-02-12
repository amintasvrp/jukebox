angular.module("main").factory("$servicePlaylists", function ($http) {
	var playlists = [];
	var atualizaPlaylists = function () {
		$http.get("/playlists")
		.then(function (playlists) {
			playlists = playlists.data;
		})
		.catch(function (erro) {
			console.log(erro);
		});
	}
	return {
		playlists: playlists,
		atualizaPlaylists: atualizaPlaylists
	};
});