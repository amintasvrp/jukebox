angular.module("main").controller("artistas-ctrl", function ($scope, $http, $serviceArtistas, $serviceClientes){

    $scope.artistas = $serviceArtistas.artistas;

    $http.get("/artistas")
    .then(function (artistas) {
        $serviceArtistas.artistas = artistas.data;
        $scope.artistas = $serviceArtistas.artistas;
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
   

    $scope.filtro = '';

	$scope.addArtista = function (artistaForm) {
        var artistaRequest = {
            nota: artistaForm.nota,
            favoritado: artistaForm.favoritado,
            url: artistaForm.url,
            nome: artistaForm.nome
        }
        $http.post("/artista", artistaRequest)
        .then(function() {
            alert("Artista adicionado com sucesso");
            $http.get("/artistas")
            .then(function (artistas) {
                $serviceArtistas.artistas = artistas.data;
                $scope.artistas = $serviceArtistas.artistas;
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

    $scope.habilitaBotaoArtistaForm = function (artistaNome) {
    	return $scope.artistaNomeNaoVazio(artistaNome) && !$scope.artistaRepetido(artistaNome);
    };

    $scope.artistaNomeNaoVazio = function (artistaNome) {
        return artistaNome;
    };

    $scope.artistaRepetido = function (artistaNome) {
    	
    	var resultado = false;

    	$serviceArtistas.artistas.forEach(function (artista) {
    		if (artista.nome == artistaNome) {
    			resultado = true;
    		}
    	});    		
		
		return resultado;
    };

    $scope.favoritar = function (artistaForm) {
        if(confirm("Tem certeza que deseja executar essa ação?") === true){
            var artistaRequest = {
                nota: artistaForm.nota,
                favoritado: artistaForm.favoritado,
                url: artistaForm.url,
                nome: artistaForm.nome
            }
            $http.put("/artista", artistaRequest)
            .then(function() {
                console.log("Artista (des)favoritado com sucesso");
            })
            .catch(function (erro) {
                console.log(erro);
            });
        }
       
        $http.get("/artistas")
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
        
    };

   
});

