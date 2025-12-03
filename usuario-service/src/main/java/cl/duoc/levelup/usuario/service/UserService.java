package cl.duoc.levelup.usuario.service;

import cl.duoc.levelup.usuario.model.User;

/**
 * Capa de servicio con la lógica de negocio del usuario.
 */
public interface UserService {

    User createUser(User user) throws Exception;

    User getUserById(String id) throws Exception;
}
