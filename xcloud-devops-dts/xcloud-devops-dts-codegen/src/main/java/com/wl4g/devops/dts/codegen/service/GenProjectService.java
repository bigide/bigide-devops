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
package com.wl4g.devops.dts.codegen.service;

import com.wl4g.component.core.bean.model.PageHolder;
import com.wl4g.devops.dts.codegen.bean.GenProject;

/**
 * GenProjectService
 *
 * @author heweijie
 * @Date 2020-09-11
 */
public interface GenProjectService {

	PageHolder<GenProject> page(PageHolder<GenProject> pm, String name);

	void save(GenProject project);

	GenProject detail(Long id);

	void del(Long id);

}