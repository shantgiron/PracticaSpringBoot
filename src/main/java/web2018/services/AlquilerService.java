package web2018.services;


import web2018.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerService extends JpaRepository<Alquiler, Long> {
}
