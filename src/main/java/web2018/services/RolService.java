package web2018.services;


import web2018.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolService extends JpaRepository<Rol, Long> {
}
