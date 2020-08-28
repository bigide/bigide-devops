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
package com.wl4g.devops.scm.common.config.resolve;

import com.wl4g.devops.scm.common.config.ScmPropertySource;
import com.wl4g.devops.scm.common.model.AbstractConfigInfo.ConfigProfile;

/**
 * {@link PropertySourceResolver}
 * 
 * @author Wangl.sir &lt;wanglsir@gmail.com, 983708408@qq.com&gt;
 * @version 2020-08-20
 * @sine v1.0.0
 * @see
 */
public interface PropertySourceResolver {

	/**
	 * Resolving {@link ScmPropertySource} from release configuration content.
	 * 
	 * @param profile
	 * @param sourceContent
	 * @return
	 */
	ScmPropertySource resolve(ConfigProfile profile, String sourceContent);

}
