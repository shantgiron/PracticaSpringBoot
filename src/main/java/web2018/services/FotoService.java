package services;


import model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoService extends JpaRepository<Foto, Long> {
}
