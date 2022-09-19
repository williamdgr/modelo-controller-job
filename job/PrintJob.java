package br.com.modelo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintJob {

	@Scheduled(cron="0 0/1 * 1/1 * ?")
    public void validateSomething(){
        System.out.println("executou schedule.");
    }

}
