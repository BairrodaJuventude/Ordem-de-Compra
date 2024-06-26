package com.bairro.ordemCompra.service;

import com.bairro.ordemCompra.model.Usuario;
import com.bairro.ordemCompra.repository.UsuarioRepository;
import com.bairro.ordemCompra.util.GeraTokens;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        // Criptografa a senha
        ValidaCaracteres(usuario);
        String senhaCriptografada = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(senhaCriptografada);

        // Salva os dados do usuario juntamente com o token
        return repository.save(usuario);
    }


    public Usuario buscaPorUsuario(String usuario) {
        Optional<Usuario> usuarioOptional = repository.findByUsuario(usuario);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return usuarioOptional.get();
    }

    public Usuario alterarSenha(String usuario, String senhaAtual, String novaSenha) {
        Optional<Usuario> existingUsuarioOptional = repository.findByUsuario(usuario);
        if (existingUsuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Usuario existingUsuario = existingUsuarioOptional.get();

        // Verifique se a senha atual está correta
        if (!BCrypt.checkpw(senhaAtual, existingUsuario.getPassword())) {
            throw new SenhaInvalidaException("Senha atual incorreta");
        }

        // Criptografe a nova senha
        String senhaCriptografada = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

        // Atualize apenas a senha
        existingUsuario.setPassword(senhaCriptografada);

        return repository.save(existingUsuario);
    }

    public boolean autenticar(String usuarioOuEmail, String password) {
        Optional<Usuario> usuarioOptional;

        // Verifica se o valor fornecido é um email
        if (usuarioOuEmail.contains("@")) {
            usuarioOptional = repository.findByEmail(usuarioOuEmail);
        } else {
            usuarioOptional = repository.findByUsuario(usuarioOuEmail);
        }

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Aqui, você verifica se a senha enviada é igual à senha no banco de dados
            if (BCrypt.checkpw(password, usuario.getPassword())) {
                return true;
            }
        }

        throw new NotFoundException("Credenciais inválidas");
    }

    private void ValidaCaracteres(Usuario usuario) {
        if(usuario.getUsuario().length() < 5) {
            throw new ValidationException("O nome do usuário deve ter ao menos 5 caracteres!");
        }
    }
}
