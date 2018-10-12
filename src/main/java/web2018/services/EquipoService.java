package web2018.services;

import web2018.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoService extends JpaRepository<Equipo, Long> {
    @Query("select e from Equipo e where e.codigo = :codigo")
    Equipo findByCodigo(@Param("codigo") String codigo);
}
