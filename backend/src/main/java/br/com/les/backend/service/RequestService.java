package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Request;
import br.com.les.backend.service.AbstractService;

@Service
@Transactional
public class RequestService extends AbstractService<Request> {

}
