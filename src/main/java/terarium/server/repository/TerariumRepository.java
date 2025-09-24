package terarium.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import terarium.server.model.Terarium;

@Repository
public interface TerariumRepository extends JpaRepository <Terarium, Integer>{}