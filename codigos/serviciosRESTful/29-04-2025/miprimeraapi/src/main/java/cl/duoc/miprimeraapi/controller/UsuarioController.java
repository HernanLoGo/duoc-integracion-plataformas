package cl.duoc.miprimeraapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.miprimeraapi.model.Usuario;

@RestController
public class UsuarioController {

    private Map<Long, Usuario> data = new HashMap<>();
    private Long contadorId = 0L;

    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarios(@PathVariable Long id) {
        Usuario userData = data.get(id);
        if (userData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @PostMapping("usuarios")
    public ResponseEntity<Usuario> postUsuarios(@RequestBody Usuario usuario) {
        usuario.setId(++contadorId);
        data.put(contadorId, usuario);
        return new ResponseEntity<>(data.get(contadorId), HttpStatus.CREATED);
    }

    @GetMapping("usuarios")
    public ResponseEntity<List<Usuario>> getTodosUsuarios() {
        List<Usuario> response = new ArrayList<Usuario>(data.values());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {
        Usuario userData = data.get(id);
        if (userData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userData.setNombre(usuarioRequest.getNombre());
        userData.setDireccion(usuarioRequest.getDireccion());
        data.put(id, userData);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @PatchMapping("usuarios/{id}")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {
        Usuario userData = data.get(id);
        if (userData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (usuarioRequest.getNombre() != null && !usuarioRequest.getNombre().isEmpty()
                && !usuarioRequest.getNombre().isBlank()) {
            userData.setNombre(usuarioRequest.getNombre());
        }
        if (usuarioRequest.getDireccion() != null && !usuarioRequest.getDireccion().isEmpty()
                && !usuarioRequest.getDireccion().isBlank()) {
            userData.setDireccion(usuarioRequest.getDireccion());
        }
        data.put(id, userData);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Boolean eliminado = data.remove(id) != null;
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
