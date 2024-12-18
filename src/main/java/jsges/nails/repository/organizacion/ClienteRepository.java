package jsges.nails.repository.organizacion;

import jsges.nails.domain.organizacion.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT p FROM Cliente p WHERE p.estado = 0 ORDER BY p.nombre")
    List<Cliente> buscarNoEliminados();

    @Query("SELECT p FROM Cliente p WHERE p.estado = 0 AND p.nombre LIKE %:consulta% ORDER BY p.nombre")
    List<Cliente> buscarNoEliminados(@Param("consulta") String consulta);
}