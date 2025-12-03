package cl.duoc.levelup.contacto.service.service;

import cl.duoc.levelup.contacto.service.model.Contacto;
import cl.duoc.levelup.contacto.service.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Contiene la lógica de negocio del microservicio de contacto.
 */
@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    public Contacto guardarContacto(Contacto contacto) throws ExecutionException, InterruptedException {
        // Aquí podrías agregar validaciones adicionales si las necesitas
        return contactoRepository.save(contacto);
    }

    public List<Contacto> obtenerTodos() throws ExecutionException, InterruptedException {
        return contactoRepository.findAll();
    }

    public Contacto obtenerPorId(String id) throws ExecutionException, InterruptedException {
        return contactoRepository.findById(id);
    }
}
