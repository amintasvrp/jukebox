package br.com.amintasvrp.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.amintasvrp.ws.request.ClienteRequest;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteRequest,String> {

}
