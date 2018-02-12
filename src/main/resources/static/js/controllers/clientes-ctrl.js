angular.module("main").controller("clientes-ctrl", function ($scope, $http, $serviceClientes){

	$scope.clientes = $serviceClientes.clientes;

	$http.get("http://localhost:8080/clientes")
    .then(function (clientes) {
        $serviceClientes.clientes = clientes.data;
    })
    .catch(function (erro) {
        console.log(erro);
    });	

	$scope.clienteCampoNaoVazio = function (campo) {
        return campo;
    };

    $scope.emailRepetido = function (email) {
    	
    	var resultado = false;

    	$serviceClientes.clientes.forEach(function (cliente) {
    		if (cliente.email == email) {
    			resultado = true;
    		}
    	});    		
		
		return resultado;
    };

    $scope.habilitaBotaoClienteForm = function (email, nome, senha) {
    	return $scope.clienteCampoNaoVazio(email) && !$scope.emailRepetido(email) && $scope.clienteCampoNaoVazio(nome) && $scope.clienteCampoNaoVazio(senha);
    };

    $scope.addCliente = function (clienteForm) {
    	var clienteRequest = {
    		email: clienteForm.email,
    		senha: clienteForm.senha,
    		nome: clienteForm.nome
    	}
    	$http.post("http://localhost:8080/cliente", clienteRequest)
	    .then(function() {
			alert("Usuário cadastrado com sucesso");
            $http.get("http://localhost:8080/clientes")
            .then(function (clientes) {
                $serviceClientes.clientes = clientes.data;
                $scope.clientes = $serviceClientes.clientes
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

    $scope.habilitaBotaoLoginForm = function (email, senha) {
    	return $scope.clienteCampoNaoVazio(email) && $scope.clienteCampoNaoVazio(senha);
    };

    $scope.logarCliente = function (loginForm) {
    	var clienteRequest = {
    		email: loginForm.email,
    		senha: loginForm.senha,
    	}
    	$http.put("http://localhost:8080/cliente", clienteRequest)
	    .then(function(cliente) {
			alert("Logado como " + cliente.data.nome);
            location.reload();
		})
		.catch(function (erro) {
			console.log("Usuário e/ou senha inválido(s)");
		});
    };


});	