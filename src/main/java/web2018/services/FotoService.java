package web2018.services;


import web2018.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoService extends JpaRepository<Foto, Long> {
}
