package com.shm.job;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 定时关闭HttpClient的无效连接
 * @author SHM
 *
 */
//当定时任务没有执行完的情况下，不会启动新的任务
//同步执行任务（单线程）
@DisallowConcurrentExecution
public class CloseConnectJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		获取spring容器
		ApplicationContext application  = (ApplicationContext) context.getJobDetail().getJobDataMap().get("context");
//		从spring容器获取httpClient的连接管理器，关闭无效连接
		application.getBean(PoolingHttpClientConnectionManager.class).closeExpiredConnections();;
		
	}
	

}
