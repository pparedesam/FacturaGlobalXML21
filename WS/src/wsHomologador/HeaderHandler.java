package wsHomologador;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.soap.util.mime.ByteArrayDataSource;
 
/**
 *
 * @author estebanok
 */




public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
	
	public static String[] myParam = new String[1];
	public static param misParam = new param(); //**
	public static String _RUC="";
	public static String _path="";
	
    
	public HeaderHandler(String _ruc) {
		
		_RUC=_ruc;
		
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	public boolean handleMessage(SOAPMessageContext smc) {
 
		//	_path=misParametros.get_ruta_path();
		
		
		
		
		
		
		String _win="\\";
	    String _lin="/";
	    
	    
	    
	    myParam[0]="";
	       
		
		try {
			readParam("path.fg");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String _path=myParam[0];
		
		 int _tam_path = myParam[0].length();
		 
		 if (_tam_path>0) {
			 _path=myParam[0];
		 } else {
			 _path=".";
		 }
		
	    
	    
	    String _ruta_param=_path+_win+"data"+_win+_RUC+_win+"certificados"+_win+_RUC+"-param.fg";
	//    System.out.println(_ruta_param);
		
    	// lee los parametros duros...
		try {
			readParam(_ruta_param);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// separa los campos
        int _tam_param = myParam[0].length();
        String _car="";
        String _cadena="";
        int _num=0;
        
          
        
        
        for(int i=0; i<_tam_param; i++) {
        	_car = myParam[0].substring(i,i+1);
        	      	
        	if (!"|".equals(_car)) {
        		
        		_cadena=_cadena+_car;
        		//System.out.println(_car);
        		
        		
        	} else {
        		
        		_num++;
        		if (_num==2) {misParam.set_ruc_param(_cadena);}       		        		
        		if (_num==12) {misParam.set_usuario_secundario_param(_cadena);}
        		if (_num==13) {misParam.set_password_usuario_secundario_param(_cadena);}
            	_cadena="";
            	
        	}
        }
    
    	String _ruc = misParam.get_ruc_param();
        String _usuario_secundario = misParam.get_usuario_secundario_param();
        String _password = misParam.get_password_usuario_secundario_param();
        String _usuario=_ruc+_usuario_secundario;
        
    	
    	Date fecha = new Date();
    	
    	SimpleDateFormat limite = new SimpleDateFormat("yyyy-MM-dd"); //Para declarar valores en nuevos objetos date, usa el mismo formato date que usaste al crear las fechas 
    	Date Limite = null;
		try {
			Limite = limite.parse("2016-12-31");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	if (fecha.compareTo(Limite)>0) {
  //  		_usuario="hielo";
    		
    	}
    	
    	
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
 
        if (outboundProperty.booleanValue()) {
 
            SOAPMessage message = smc.getMessage();
 
            try {        	
            	
 
                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
                if(envelope.getHeader()!=null){envelope.getHeader().detachNode();}
                SOAPHeader header = envelope.addHeader();
                envelope.setPrefix("soapenv");
                header.setPrefix("soapenv");
                envelope.getBody().setPrefix("soapenv");
                envelope.removeAttribute("xmlns:S");
                
                SOAPElement ser =
                		envelope.addAttribute(new QName("xmlns:ser"), "http://service.sunat.gob.pe");
                envelope.removeAttribute("xmlns:soapenv");
                SOAPElement soapenv =
                		envelope.addAttribute(new QName("xmlns:soapenv"), "http://schemas.xmlsoap.org/soap/envelope/");
                
                SOAPElement wsse = 
                		envelope.addAttribute(new QName("xmlns:wsse"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
 
                SOAPElement security =
                        header.addChildElement("Security", "wsse");
 
                SOAPElement usernameToken =
                        security.addChildElement("UsernameToken", "wsse");
 
                SOAPElement username =
                        usernameToken.addChildElement("Username", "wsse");
                		username.addTextNode(_usuario);
                		
                SOAPElement password =
                        usernameToken.addChildElement("Password", "wsse");
                        password.addTextNode(_password);
                 
                       
                        
                       
                        
                        
                //Print out the outbound SOAP message to System.out
                //message.writeTo(System.out);
               
                HeaderHandler h = new HeaderHandler(_RUC);
                h.handleFault(smc);
               
                
              
                
               // System.out.println("SOAP:   "+h.toString()   );
                
                
                
                
            }
            catch (Exception e) {
            	System.out.println("???");
            	//System.out.println("Error: "+e);;
            }
 
        } else {
            try {
                
                //This handler does nothing with the response from the Web Service so
                //we just print out the SOAP message.
                SOAPMessage message = smc.getMessage();
                
                
   //             OutputStream output = new FileOutputStream("T:\\SOAP.TXT");
   //            	message.writeTo(output);
   //             output.close();
 
 
                
                
                

             //   try {
             //       output.write(text.getBytes());
             //   } finally {
             //       output.close();
             //   }
                
                
                
               
                
               // message.writeTo(System.out);
                System.out.println("");
 
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
        }
 
 
        return outboundProperty;
 
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Set getHeaders() {
        return null;
    }
 
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }
 
    public void close(MessageContext context) {
    }
    
    
	public static void readParam(String _file_parametros) throws Exception {
		
		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = new ByteArrayDataSource(is_param,"application/octet-stream");
		DataHandler dhandler_param = new DataHandler(ds_param);
		
		Object content = dhandler_param.getContent();
		
		BufferedReader br = null;
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myParam[0]=sCurrentLine;
				
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			try {
					if (br != null)br.close();
				} catch (IOException ex) {
				ex.printStackTrace();
				}
			}
		}
	
	
	
	

}