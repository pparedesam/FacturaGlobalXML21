package wsHomologador;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
 
/**
 *
 * @author estebanok
 */
public class HeaderHandlerResolver implements HandlerResolver {
    
public static String _ruc="";

	
public HeaderHandlerResolver(String $ruc) {
		// TODO Auto-generated constructor stub
	_ruc=$ruc;
	
}

@SuppressWarnings("rawtypes")
public List<Handler> getHandlerChain(PortInfo portInfo) {
      List<Handler> handlerChain = new ArrayList<Handler>();
 
    
      
      HeaderHandler hh = new HeaderHandler(_ruc);
 
      handlerChain.add(hh);
      
    
 
      return handlerChain;
   }
}