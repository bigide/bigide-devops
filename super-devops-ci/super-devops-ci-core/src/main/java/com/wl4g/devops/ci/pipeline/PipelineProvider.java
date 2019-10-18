/*
 * Copyright 2017 ~ 2025 the original author or authors. <wanglsir@gmail.com, 983708408@qq.com>
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
package com.wl4g.devops.ci.pipeline;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

import java.lang.reflect.Field;
import java.util.Objects;

import com.wl4g.devops.ci.pipeline.model.PipelineInfo;
import com.wl4g.devops.common.bean.ci.dto.TaskResult;
import com.wl4g.devops.support.beans.DelegateAliasPrototypeBean;
import com.wl4g.devops.support.beans.DelegateAliasPrototypeBeanFactory;

/**
 * Based pipeline provider.
 * 
 * @author vjay
 * @author Wangl.sir <983708408@qq.com>
 * @date 2019-05-05 17:17:00
 */
public abstract interface PipelineProvider extends DelegateAliasPrototypeBean {

	void execute() throws Exception;

	default void rollback() throws Exception {
		throw new UnsupportedOperationException();
	}

	TaskResult getTaskResult();

	PipelineInfo getPipelineInfo();

	String getShaLocal();

	String getShaGit();

	/**
	 * Integrate pipeline type definition.
	 *
	 * @author Wangl.sir
	 * @version v1.0 2019年8月29日
	 * @since
	 */
	public static abstract class PipelineType {

		/**
		 * MAVEN assemble tar provider alias.
		 */
		final public static String MVN_ASSEMBLE_TAR = "PipeWithMvnAssTar";

		/**
		 * Spring boot executable jar provider alias.
		 */
		final public static String SPRING_EXECUTABLE_JAR = "PipeWithSpringExecJar";

		/**
		 * Docker native provider alias.
		 */
		final public static String DOCKER_NATIVE = "PipeWithDockerNative";

		/**
		 * DJANGO standard provider alias.
		 */
		final public static String DJANGO_STANDARD = "PipeWithDjangoStandard";

		/**
		 * NPM provider alias.
		 */
		final public static String NPM_VIEW = "PipeWithNpm";

		/**
		 * Get prototype bean provider.
		 * 
		 * @param beanFactory
		 * @param provider
		 * @return
		 */
		final public static PipelineProvider getPrototypePipelineProvider(DelegateAliasPrototypeBeanFactory beanFactory,
				String provider, PipelineInfo info) {
			Field f = findField(PipelineType.class, provider, String.class);
			if (Objects.nonNull(f)) {
				return beanFactory.getPrototypeBean((String) getField(f, null), info);
			}
			throw new IllegalArgumentException("Unknown provider for: " + provider);
		}

	}

}