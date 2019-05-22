package com.wl4g.devops.ci.subject;

import com.wl4g.devops.ci.devtool.ConnectLinuxCommand;
import com.wl4g.devops.ci.devtool.TarThreadTask;
import com.wl4g.devops.ci.service.DependencyService;
import com.wl4g.devops.common.bean.ci.Dependency;
import com.wl4g.devops.common.bean.ci.TaskDetail;
import com.wl4g.devops.common.bean.scm.AppInstance;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author vjay
 * @date 2019-05-05 17:28:00
 */
@Component
public class TarSubject extends BaseSubject {

	public TarSubject(){

	}

	public TarSubject(DependencyService dependencyService,Integer projectId,String path, String url, String branch, String alias, String tarPath, List<AppInstance> instances, List<TaskDetail> taskDetails){

		super.path = path;
		super.url = url;
		super.branch = branch;
		super.alias = alias;
		super.tarPath = tarPath;
		super.instances = instances;
		super.taskDetails = taskDetails;
		String[] a = tarPath.split("/");
		super.tarName = a[a.length-1];
		super.projectId = projectId;

		//service
		super.dependencyService = dependencyService;
	}

	@Override
	public void exec() throws Exception{
		/*//chekcout
		if(checkGitPahtExist()){
			checkOut(path,branch);
		}else{
			clone(path,url,branch);
		}

		//build
		build(path);*/
		Dependency dependency = new Dependency();
		dependency.setProjectId(projectId);
		dependencyService.build(dependency,branch);

		//backup in local
		bakLocal(path+"/"+tarPath);

		//scp to server
		/*for(AppInstance instance : instances){
			//scp to server  and  tar
			//scp(path+"/"+tarPath,instance.getServerAccount()+"@"+instance.getHost(),instance.getWebappsPath());
			scpAndTar(path+"/"+tarPath,instance.getHost(),instance.getServerAccount(),instance.getWebappsPath());
			//stop server
			//stop(instance.getHost(),instance.getServerAccount(),alias);
			reLink(instance.getHost(),instance.getWebappsPath(),instance.getServerAccount(),path+"/"+tarPath);
			//decompression the	tar package
			//tar(instance.getHost(),instance.getServerAccount(),instance.getWebappsPath(),tarName);
			//restart server
			restart(instance.getHost(),instance.getServerAccount());
			//start(instance.getHost(),instance.getServerAccount(),alias,tarName);
		}*/
		//scp to server
		for(AppInstance instance : instances){
			Thread thread = new TarThreadTask(this,path,instance,tarPath,taskDetails);
			thread.start();
			thread.join();
		}
		log.info("Done");
	}

	public String restart(String host,String userName) throws Exception{
		String command = "sudo -u "+alias+" sc "+alias+" restart;";

		ConnectLinuxCommand.execute(host,userName,command);

		return null;

	}

	public String start(String host,String userName,String module,String targetName) throws Exception{
		String command = "nohup java -Djava.ext.dirs=/root/webapps/dataflux-oper-master-bin/libs  -cp /root/webapps/dataflux-oper-master-bin/libs/datafluxOper.jar com.cn7782.devops.DatafluxOper >/dev/null  &   ";
		//String command = "sc "+module+" start";
		return ConnectLinuxCommand.execute(host,userName,command);
	}

}
