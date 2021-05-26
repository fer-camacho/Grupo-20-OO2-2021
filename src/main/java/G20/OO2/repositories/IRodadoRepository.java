package G20.OO2.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import G20.OO2.entities.Rodado;

 @Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Serializable> {
	
	@Query(nativeQuery=true,value="Select count(*) from rodado r where r.dominio=(:dominio)")
	public int repetido(String dominio);
}
