package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Role;

@Service
@Transactional
public class RoleService extends AbstractService<Role> {

}
