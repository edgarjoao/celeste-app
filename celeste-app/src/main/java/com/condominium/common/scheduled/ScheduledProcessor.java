package com.condominium.common.scheduled;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.service.CumulativeService;
import com.condominium.mail.MailService;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
/**
 * 
 * @author rioslore
 *
 */
@Component("scheduleProcessor")
public class ScheduledProcessor  {

    private static final Logger log = Logger.getLogger(ScheduledProcessor.class);
	
	@Autowired
	CumulativeService cumulativeService;
	@Autowired
	MailService mailService;
	
	/**
	    1 Seconds (0-59)
		2 Minutes (0-59)
		3 Hours (0-23)
		4 Day of month (1-31)
		5 Month (1-12 or JAN-DEC)
		6 Day of week (1-7 or SUN-SAT)
		7 Year (1970-2099)
		
		Este Job se ejecuta una vez al mes para
		generar el acumulado del mes.
		
	 */
	@Scheduled(cron="0 0 0 1 * ?")
	//@Scheduled(fixedDelay = 20 )
    public void cumulativeMonth(){
        log.info("El generador del acumulado se ha iniciado.");
        try {
			cumulativeService.generateCumulativeMonth();
			mailService.sendAlertMail("El acumulado del mes, se ha generado con Exito, favor de verificar en los logs del sistema.");
			AsyncHttpClient client = null;
			try {
				client = new AsyncHttpClient();
				Response responseAuthentication = client.prepareGet("http://api.clickatell.com/http/auth?api_id=3361860&user=condominio&password=Sms@1234").execute().get();
				String sessionId = responseAuthentication.getResponseBody("UTF-8");
				String sId = sessionId.substring(3, sessionId.length() -1);
				log.info("Before Send SMS Message");
				log.info("Authentication Response - " + sessionId);
				Response responsePing = client.prepareGet("http://api.clickatell.com/http/ping?session_id=" + sId.trim()).execute().get();
				log.info("Ping Response - " + responsePing.getResponseBody("UTF-8"));
				Response sendMessage = client.prepareGet("http://api.clickatell.com/http/sendmsg?session_id="+sId.trim()+"&to=3333688045&text=Listo_acumulado_generado_exitosamente").execute().get();
				log.info("SendMsg Response - " + sendMessage.getResponseBody("UTF-8"));
			} catch (InterruptedException e) {
				log.error(e);
			} catch (ExecutionException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
			}
		} catch (CumulativeException cumulativeException) {
			log.error(cumulativeException);
			mailService.sendErrorAlertMail(cumulativeException, "Ha ocurrido un error al generar el acumulado.");			
		}        
    }
}
