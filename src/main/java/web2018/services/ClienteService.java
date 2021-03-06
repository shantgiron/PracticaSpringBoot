package web2018.services;


import web2018.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteService extends JpaRepository<Cliente, Long> {

}
