package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.TipoPessoa;
import br.com.playtomate.usuario.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    private ServiceUsuario serviceUsuario;
    public Mapper(ServiceUsuario serviceUsuario){
        this.serviceUsuario = serviceUsuario;
    }

    public Usuario toPessoa(PessoaDTO dto){
        Usuario usuario = buildPessoa();
        usuario.buildPessoa(dto.getLogin(),
                                dto.getEmail(),
                                dto.getSenha(),
                                dto.getNome(),
                                dto.getNumeroTelefone(),
                                dto.getTipoPessoa());

        usuario.buildLocalidade(dto.getEndereco(),
                                    dto.getCidade(),
                                    dto.getEstado(),
                                    dto.getCodigoPostal());
        return usuario;
    }

    public void toJuridica(Usuario usuario, PessoaDTO dto){
        usuario.buildPessoaJuridica(dto.getNomeEmpresa());
    }

    public Usuario buildPessoa(){
        Usuario usuario = new Usuario();
        usuario.setServiceUsuario(serviceUsuario);
        return usuario;
    }

    public PessoaDTO toDto(Usuario usuario) {
        PessoaDTO dto = new PessoaDTO();
        dto.setTipoPessoa(TipoPessoa.converterCodigoTipoPessoa(usuario.getTipoPessoa()));
        dto.setLogin(usuario.getLogin());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setNome(usuario.getNome());
        dto.setNumeroTelefone(usuario.getNumeroTelefone());
        dto.setEndereco(usuario.getLocalidade().getEndereco());
        dto.setCidade(usuario.getLocalidade().getCidade());
        dto.setEstado(usuario.getLocalidade().getEstado());
        dto.setCodigoPostal(usuario.getLocalidade().getCodigoPostal());
        if(usuario.getTipoPessoa() == TipoPessoa.JURIDICA)
            dto.setNomeEmpresa(usuario.getPessoaJuridica().getNomeEmpresa());
        return dto;
    }
}