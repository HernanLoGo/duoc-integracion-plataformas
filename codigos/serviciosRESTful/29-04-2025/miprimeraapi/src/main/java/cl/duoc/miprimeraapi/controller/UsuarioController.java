package cl.duoc.miprimeraapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Usuario getUsuarios(@PathVariable Long id) {
        return data.get(id);
    }

    @PostMapping("usuarios")
    public Usuario postUsuarios(@RequestBody Usuario usuario) {
        usuario.setId(++contadorId);
        data.put(contadorId, usuario);

        return data.get(contadorId);
    }

    @GetMapping("usuarios")
    public List<Usuario> getTodosUsuarios() {
        return new ArrayList<Usuario>(data.values());
    }

    @PutMapping("usuarios/{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {

        Usuario userData = data.get(id);

        userData.setNombre(usuarioRequest.getNombre());
        userData.setDireccion(usuarioRequest.getDireccion());

        data.put(id, userData);

        return userData;
    }

    @PatchMapping("usuarios/{id}")
    public Usuario patchUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {

        Usuario userData = data.get(id);

        if (usuarioRequest.getNombre() != null && !usuarioRequest.getNombre().isEmpty()
                && !usuarioRequest.getNombre().isBlank()) {
            userData.setNombre(usuarioRequest.getNombre());
        }
        if (usuarioRequest.getDireccion() != null && !usuarioRequest.getDireccion().isEmpty()
                && !usuarioRequest.getDireccion().isBlank()) {
            userData.setDireccion(usuarioRequest.getDireccion());
        }

        data.put(id, userData);

        return userData;
    }

    @DeleteMapping("usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        data.remove(id);
        return;
    }

}
