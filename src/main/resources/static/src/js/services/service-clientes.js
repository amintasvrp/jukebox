angular.module("main").factory("$serviceClientes", function ($http) {
	var clientes = [];
	var atualizaClientes = function () {
		$http.get("/clientes")
		.then(function (clientes) {
			clientes = clientes.data;
		})
		.catch(function (erro) {
			console.log(erro);
		});
	}
	return {
		clientes: clientes,
		atualizaClientes: atualizaClientes
	};
});