angular.module("main").controller("playlists-ctrl", function ($scope, $http, $servicePlaylists, $serviceMusicas, $serviceClientes){
	
	$scope.musicas = $serviceMusicas.musicas;
	$scope.playlists = $servicePlaylists.playlists;

	$http.get("/playlists")
	.then(function (playlists) {
		$servicePlaylists.playlists = playlists.data;
		$scope.playlists = $servicePlaylists.playlists;
	})
	.catch(function (erro) {
		console.log(erro);
	});

	$http.get("/musicas")
    .then(function (musicas) {
        $serviceMusicas.musicas = musicas.data;
        $scope.musicas = $serviceMusicas.musicas;
        $http.get("/clientes")
	    .then(function (clientes) {
	        $serviceClientes.clientes = clientes.data;
	    })
	    .catch(function (erro) {
	        console.log(erro);
	    });
	})    	 
    .catch(function (erro) {
        console.log(erro);
    });


	$scope.addMusicaPlaylist = function (musicaPlaylist) {
		var musicaPlaylistRequest = {
            musica: musicaPlaylist.musica,
            playlist: musicaPlaylist.nome
        }
        $http.post("/musicaPlaylist", musicaPlaylistRequest)
        .then(function() {
            alert("Personalização de playlist feita com sucesso");
            $http.get("/playlists")
			.then(function (playlists) {
				$servicePlaylists.playlists = playlists.data;
				$scope.playlists = $servicePlaylists.playlists;

 				$http.get("/clientes")
			    .then(function (clientes) {
			        $serviceClientes.clientes = clientes.data;
			        location.reload();
			    })
			    .catch(function (erro) {
			        console.log(erro);
			    });

							
			})
			.catch(function (erro) {
				console.log(erro);
			});
        })
        .catch(function (erro) {
            console.log(erro);
        });

	};

	$scope.removerMusica = function (musicaNome, playlist) {
		if (confirm("Você realmente deseja excluir essa musica ?") == true) {
			var musicaPlaylistRequest = {
            	"musica": "" + musicaNome,
            	"playlist": "" + playlist
        	}
	        $http.delete("/musica", musicaPlaylistRequest)
	        .then(function() {
	            alert("Remoção de música feita com sucesso");
	            $http.get("/playlists")
				.then(function (playlists) {
					$servicePlaylists.playlists = playlists.data;
					$scope.playlists = playlists.data;

					$http.get("/clientes")
				    .then(function (clientes) {
				        $serviceClientes.clientes = clientes.data;
				    })
				    .catch(function (erro) {
				        console.log(erro);
				    });
				})
				.catch(function (erro) {
					console.log(erro);
				});
	        })
	        .catch(function (erro) {
	            console.log(erro);
	        });
	    }     
	};

	$scope.removerPlaylist = function (playlistNome) {
		if (confirm("Você realmente deseja excluir essa playlist ?") == true) {
			var playlistRequest = {
            	playlist: playlistNome
        	}

	        $http.delete("/playlist", playlistRequest)
	        .then(function() {
	            alert("Remoção de playlist feita com sucesso");
	            $http.get("/playlists")
				.then(function (playlists) {
					$servicePlaylists.playlists = playlists.data;
					$scope.playlists = $servicePlaylists.playlists;
		
					$http.get("/clientes")
				    .then(function (clientes) {
				        $serviceClientes.clientes = clientes.data;
				    })
				    .catch(function (erro) {
				        console.log(erro);
				    });
				})
				.catch(function (erro) {
					console.log(erro);
				});

	        })
	        .catch(function (erro) {
	            console.log(erro);
	        });
        }

	};

});