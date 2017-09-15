package job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobTask extends QuartzJobBean {
	private static final Log log = LogFactory.getLog(JobTask.class);
 
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException { 
		
	} 
}
