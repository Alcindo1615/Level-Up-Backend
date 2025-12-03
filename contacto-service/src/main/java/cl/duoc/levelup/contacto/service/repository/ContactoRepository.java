package cl.duoc.levelup.contacto.service.repository;

import cl.duoc.levelup.contacto.service.model.Contacto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ContactoRepository {

    private static final String COLLECTION_NAME = "contacto";

    private final Firestore firestore;

    // ✅ Firestore entra por inyección, NO se crea dentro del método
    public ContactoRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Contacto save(Contacto contacto) throws ExecutionException, InterruptedException {
        CollectionReference collection = firestore.collection(COLLECTION_NAME);

        DocumentReference docRef = collection.document();
        contacto.setId(docRef.getId());

        ApiFuture<WriteResult> result = docRef.set(contacto);
        result.get(); // esperamos a que termine

        return contacto;
    }

    public List<Contacto> findAll() throws ExecutionException, InterruptedException {
        List<Contacto> contactos = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
            Contacto c = doc.toObject(Contacto.class);
            c.setId(doc.getId());
            contactos.add(c);
        }

        return contactos;
    }

    public Contacto findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        DocumentSnapshot document = docRef.get().get();

        if (!document.exists()) {
            return null;
        }

        Contacto contacto = document.toObject(Contacto.class);
        if (contacto != null) {
            contacto.setId(document.getId());
        }
        return contacto;
    }
}
