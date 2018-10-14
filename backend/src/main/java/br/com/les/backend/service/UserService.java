package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.User;

@Service
@Transactional
public class UserService extends AbstractService<User> {

}
