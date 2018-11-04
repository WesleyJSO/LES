package br.com.les.backend.validator.parameter;

import java.time.LocalTime;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class InferiorLimits implements IStrategy<Parameter> {

	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {
		
		if ( aEntity != null ) {

			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getOvertimePercentage())) 
					|| aEntity.getOvertimePercentage() < 50) {
				aCase.getResult().setError("O adicional da hora extra deve ser no minimo 50%");
			}
			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getNightOvertimePercentage())) 
					|| aEntity.getNightOvertimePercentage() < 20) {
				aCase.getResult().setError("O adicional noturno deve ser no minimo 20%");
			}
			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getWeekEndOvertimePercentage())) 
					|| aEntity.getWeekEndOvertimePercentage() < 100) {
				aCase.getResult().setError("O adicional de domingos e feriados deve ser no minimo 100%");
			}
			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getRetroactiveAppointmentLimitTime())) 
					|| aEntity.getRetroactiveAppointmentLimitTime() == LocalTime.MIN) {
				aCase.getResult().setError("O prazo para apontamento retroativo deve ser maior que zero!");
			}
			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getRelocationRequestLimitTime())) 
					|| aEntity.getRelocationRequestLimitTime() <= 0) {
				aCase.getResult().setError("O prazo para solicitação de remanejamento deve ser maior que zero!");
			}
			if (Strings.isNullOrEmpty(String.valueOf(aEntity.getBankCompensationLimitTime())) 
					|| aEntity.getBankCompensationLimitTime() < 6) {
				aCase.getResult().setError("O prazo para compensação do banco deve ser de no mínimo 6 meses");
			}
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}
