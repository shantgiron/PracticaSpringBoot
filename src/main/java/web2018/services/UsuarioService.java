package services;


import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioService extends JpaRepository<Usuario, Long>{
    Usuario findUsuarioBy(String nombreUsuario);


    Usuario save(Usuario entity);
}
