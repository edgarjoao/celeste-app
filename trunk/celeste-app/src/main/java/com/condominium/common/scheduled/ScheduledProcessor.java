package com.condominium.common.scheduled;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.service.CumulativeService;
import com.condominium.mail.MailService;
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
	@Scheduled(cron="* * * 1 * ?")
	//@Scheduled(fixedDelay = 20 )
    public void cumulativeMonth(){
        log.info("El generador del acumulado se ha iniciado.");
        try {
			cumulativeService.generateCumulativeMonth();
			mailService.sendAlertMail("El acumulado del mes, se ha generado con Exito, favor de verificar en los logs del sistema.");
		} catch (CumulativeException cumulativeException) {
			log.error(cumulativeException);
			mailService.sendErrorAlertMail(cumulativeException, "Ha ocurrido un error al generar el acumulado.");			
		}        
    }
}
