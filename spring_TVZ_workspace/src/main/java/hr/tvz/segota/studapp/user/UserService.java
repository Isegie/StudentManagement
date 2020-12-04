package hr.tvz.segota.studapp.user;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByUsername(String username);
}
