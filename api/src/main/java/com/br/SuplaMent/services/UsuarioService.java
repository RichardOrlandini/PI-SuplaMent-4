package com.br.SuplaMent.services;
import com.br.SuplaMent.model.Usuario;
import com.br.SuplaMent.model.dto.usuario.UsuarioDTO;
import com.br.SuplaMent.model.repository.UsuarioRepository;
import com.br.SuplaMent.model.dto.usuario.CadastroUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(CadastroUsuarioDTO cadastroUsuarioDTO) {
        if (usuarioRepository.findByEmail(cadastroUsuarioDTO.email()) != null) {
            throw new IllegalArgumentException("O email já existe");
        }
        String passwordEncoder = new BCryptPasswordEncoder().encode(cadastroUsuarioDTO.senha());
        CadastroUsuarioDTO novoDto = new CadastroUsuarioDTO(
                cadastroUsuarioDTO.nome(),
                cadastroUsuarioDTO.email(),
                passwordEncoder,
                cadastroUsuarioDTO.cpf(),
                cadastroUsuarioDTO.role());

        Usuario usuario = new Usuario(novoDto);
        return usuarioRepository.save(usuario);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);
        if(usuario != null){
            return new UsuarioDTO(usuario);
        }
        return null;
    }
}

