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
package com.wl4g.devops.umc.service;

import com.wl4g.component.data.page.PageHolder;
import com.wl4g.devops.common.bean.umc.CustomEngine;

/**
 * @author vjay
 * @date 2019-08-05 16:01:00
 */
public interface CustomEngineService {

	PageHolder<CustomEngine> list(PageHolder<CustomEngine> pm, String name);

	CustomEngine detal(Long id);

	void save(CustomEngine customEngine);

	void del(Long id);

}