package se331.zzz.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Page<User> findByNameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevelIsNotNullOrSurnameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevelIsNotNullOrderById(
            String name, String province, String surname, String province2, Pageable pageRequest);

    Page<User> findByNameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevel_IdOrSurnameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevel_IdOrderById(
            String name, String province, Long levelId, String surname, String province2, Long levelId2, Pageable pageRequest);
}
