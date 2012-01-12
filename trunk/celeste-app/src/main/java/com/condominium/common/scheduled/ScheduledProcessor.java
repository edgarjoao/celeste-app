package com.condominium.common.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.condominium.cumulative.exception.CumulativeException;
import com.condominium.cumulative.service.CumulativeService;
/**
 * 
 * @author rioslore
 *
 */
@Component("scheduleProcessor")
public class ScheduledProcessor  {

	@Autowired
	CumulativeService cumulativeService;
	
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
        System.out.println("Works!");
        try {
			cumulativeService.generateCumulativeMonth();
			//enviar un correo si se genero correctamente
		} catch (CumulativeException e) {
			//enviar un correo si fallo
			e.printStackTrace();
		}        
    }
}
