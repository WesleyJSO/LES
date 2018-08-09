package br.com.les.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Login;

public interface LoginRepository extends JpaRepository< Login, Long > {

    List<EntidadeDominio> findByNomeLoginAndSenha( String nomeLogin, String senha ) ;
}
