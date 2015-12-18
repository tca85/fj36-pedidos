package br.com.caelum.aggregation;
import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;


/**
 * Ao chamar os serviços externos HTTP vamos receber uma resposta HTTP.
 * Ambas respostas são recolhidas pelo componente Scatter-Gather, no entanto
 * ele não sabe o que fazer com elas.
 * 
 * Essa classe serve para criar uma estragégia de agregação
 * 
 * obs: essa estratégia tem que ser associada ao Scatter-Gather posteriormente,
 * e também vai precisar remover o componente Set Payload
 * 
 * @author tca85
 *
 */
public class SimpleResponseStrategy implements AggregationStrategy{

	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		
		for (MuleEvent event : context.collectEventsWithExceptions()) {
			throw new RuntimeException(event.toString());
		}
		
		// criado um payload
		DefaultMuleEvent evento = new DefaultMuleEvent(context.getOriginalEvent(), 
				                                       context.getOriginalEvent().getFlowConstruct());		
		evento.getMessage().setPayload("<resposta>ok</resposta>");
		
		return evento;
	}
}