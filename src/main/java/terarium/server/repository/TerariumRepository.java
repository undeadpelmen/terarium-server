package terarium.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import terarium.server.model.Terarium;

@Repository
public interface TerariumRepository extends JpaRepository<Terarium, Integer>{
    
    @Query("select t from Terarium t where t.aftorId = :aftorId")
    List<Terarium> findByAftorId(@Param("aftorId") int aftorId);
    
    @Query("select t from Terarium t where t.mac = :mac")
    Optional<Terarium> findByMac(@Param("mac") String mac);
}
