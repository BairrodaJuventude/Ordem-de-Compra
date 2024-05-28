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

        // Gera um token aleatório
        String token = GeraTokens.gerarToken();
        usuario.setToken(token);

        // Salva os dados do usuario juntamente com o token
        return repository.save(usuario);
    }


    public Usuario buscaPorToken(String token) {
        Optional<Usuario> usuarioOptional = repository.findByToken(token);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return usuarioOptional.get();
    }

    public Usuario alterarSenhaPorToken(String token, String senhaAtual, String novaSenha) {
        Optional<Usuario> existingUsuarioOptional = repository.findByToken(token);
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

    public String autenticar(String usernameOuEmail, String password) {
        Optional<Usuario> usuarioOptional;

        // Verifica se o valor fornecido é um email
        if (usernameOuEmail.contains("@")) {
            usuarioOptional = repository.findByEmail(usernameOuEmail);
        } else {
            usuarioOptional = repository.findByUsername(usernameOuEmail);
        }

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Aqui, você verifica se a senha enviada é igual à senha no banco de dados
            if (BCrypt.checkpw(password, usuario.getPassword())) {
                return usuario.getToken();
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
