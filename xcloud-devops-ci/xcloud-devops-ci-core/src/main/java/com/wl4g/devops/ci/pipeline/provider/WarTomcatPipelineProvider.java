/*
 * Copyright 2017 ~ 2050 the original author or authors <Wanglsir@gmail.com, 983708408@qq.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wl4g.devops.ci.pipeline.provider;

import com.wl4g.devops.ci.core.context.PipelineContext;
import com.wl4g.devops.ci.pipeline.deploy.WarTomcatPipeDeployer;
import com.wl4g.devops.common.bean.erm.AppInstance;

/**
 * Pipeline provider for deployment war to tomcat.
 *
 * @author Wangl.sir <983708408@qq.com>
 * @version v1.0 2019年5月25日
 * @since
 */
public class WarTomcatPipelineProvider extends BasedMavenPipelineProvider {

	public WarTomcatPipelineProvider(PipelineContext context) {
		super(context);
	}

	@Override
	protected void postBuiltModulesDependencies() throws Exception {
		// TODO
	}

	@Override
	protected Runnable newPipeDeployer(AppInstance instance) {
		Object[] args = { this, instance, getContext().getPipelineHistoryInstances() };
		return beanFactory.getBean(WarTomcatPipeDeployer.class, args);
	}

}