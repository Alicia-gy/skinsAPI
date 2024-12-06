package skinsAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skinsAPI.domain.entities.Skin;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long> {
}
