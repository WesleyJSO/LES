package br.com.les.backend.strategy.chartFilter;

import org.springframework.stereotype.Component;

import br.com.les.backend.filter.ChartFilter;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.Result;

@Component
public class FindChartFilter extends AbstractStrategy<ChartFilter> {
	public Result<ChartFilter> execute(ChartFilter chartFilter) {
		
		Result<ChartFilter> result = new Result<>();
		
		return result;
	}
}