angular.module("main").controller("musicas-ctrl", function ($scope, $http, $serviceMusicas, $serviceArtistas, $serviceClientes){

	$scope.musicas = $serviceMusicas.musicas;
    $scope.artistas = $serviceArtistas.artistas;
    
    $http.get("http://localhost:8080/musicas")
    .then(function (musicas) {
        $serviceMusicas.musicas = musicas.data;
        $scope.musicas = $serviceMusicas.musicas;
        $http.get("http://localhost:8080/artistas")
        .then(function (artistas) {
            $scope.artistas = artistas.data;
            $serviceArtistas.artistas = artistas.data;
            $http.get("http://localhost:8080/clientes")
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

    $scope.addMusica = function (musicaForm) {
        var musicaRequest = {
            nome: musicaForm.nome,
            artista: musicaForm.artista,
            album: musicaForm.album,
            lancamento: musicaForm.lancamento,
            duracao: musicaForm.duracao
        }
		$http.post("http://localhost:8080/musica", musicaRequest)
        .then(function() {
            alert("Música adicionada com sucesso");
            $http.get("http://localhost:8080/musicas")
            .then(function (musicas) {
                $serviceMusicas.musicas = musicas.data;
                $scope.musicas = $serviceMusicas.musicas;
                $http.get("http://localhost:8080/artistas")
                .then(function (artistas) {
                    $serviceArtistas.artistas = artistas.data;
                    $scope.artistas = $serviceArtistas.artistas;
                    $http.get("http://localhost:8080/clientes")
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
        })
        .catch(function (erro) {
            console.log(erro);
        });
    };

	$scope.habilitaBotaoMusicaForm = function (musicaNome, musicaArtista, musicaAlbum, musicaLancamento, musicaDuracao) {
    	return $scope.musicaNomeNaoVazio(musicaNome) && !$scope.musicaNomeRepetido(musicaNome) && 
        $scope.musicaArtistaNaoVazio(musicaArtista) && $scope.musicaAlbumNaoVazio(musicaAlbum) && 
        $scope.musicaLancamentoNaoVazio(musicaLancamento) && $scope.musicaDuracaoNaoVazio(musicaDuracao);
    };

    $scope.musicaNomeNaoVazio = function (musicaNome) {
        return musicaNome;
    };


    $scope.musicaArtistaNaoVazio = function (musicaArtista) {
        return musicaArtista;
    };


    $scope.musicaAlbumNaoVazio = function (musicaAlbum) {
        return musicaAlbum;
    };

    $scope.musicaLancamentoNaoVazio = function (musicaLancamento) {
        return musicaLancamento;
    };

    $scope.musicaDuracaoNaoVazio = function (musicaDuracao) {
        return musicaDuracao;
    };

    $scope.musicaNomeRepetido = function (musicaNome) {
    	var resultado = false;

    	$scope.musicas.forEach(function (musica) {
    		if (musica.nome == musicaNome) {
    			resultado = true;
    		}
    	});    		
		
		return resultado;
    };

});