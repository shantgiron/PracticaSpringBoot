package services;


import model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteService extends JpaRepository<Cliente, Long> {

}
