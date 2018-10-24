package br.com.les.backend.strategy.parameter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveParameterStrategy extends AbstractStrategy<Parameter> {

	@Override
	public Result<Parameter> execute(Parameter parameter) {

		Result<Parameter> result = new Result<>();
			if (parameter.getOvertimePercentage() == null || parameter.getOvertimePercentage() < 50) {
				result.setError("O adicional da hora extra deve ser no minimo 50%");
			}
			if (parameter.getNightOvertimePercentage() == null || parameter.getNightOvertimePercentage() < 20) {
				result.setError("O adicional noturno deve ser no minimo 20%");
			}
			if (parameter.getWeekEndOvertimePercentage() == null || parameter.getWeekEndOvertimePercentage() < 100) {
				result.setError("O adicional de domingos e feriados deve ser no minimo 100%");
			}
			if (parameter.getRetroactiveAppointmentLimitTime() == null || parameter.getOvertimePercentage() == 0) {
				result.setError("Informe o prazo de apontamento retroativo!");
			}
			if (parameter.getRelocationRequestLimitTime() == null || parameter.getRelocationRequestLimitTime() == 0) {
				result.setError("Informe o prazo de solicitação de remanejamento!");
			}
			if (parameter.getBankCompensationLimitTime() == null || parameter.getBankCompensationLimitTime() < 6) {
				result.setError("O prazo para compensação do banco deve ser de no mínimo 6 meses");
			}
			parameter.setId(null);
			parameter.setCreationDate();
			
		result.setSuccess( Util.SAVE_SUCESSFUL_PARAMETER );
		return result;
	}

}
