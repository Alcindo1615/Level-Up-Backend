package cl.duoc.levelup.usuario.service;

import cl.duoc.levelup.usuario.model.User;
import cl.duoc.levelup.usuario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementación de la capa de servicio.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User createUser(User user) throws Exception {
        return repository.save(user);
    }

    @Override
    public User getUserById(String id) throws Exception {
        return repository.findById(id);
    }
}
