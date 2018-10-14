package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Parameter;

@Service
@Transactional
public class ParameterService extends AbstractService<Parameter> {

}
