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
package com.wl4g.devops.erm.service;

import com.wl4g.component.core.bean.model.PageHolder;
import com.wl4g.component.rpc.feign.core.annotation.FeignConsumer;
import com.wl4g.devops.common.bean.erm.DockerRepository;
import com.wl4g.devops.common.bean.erm.model.RepositoryProject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author vjay
 */
@FeignConsumer(name = "${provider.serviceId.erm-facade:dockerRepository-service}")
@RequestMapping("/dockerRepository")
public interface DockerRepositoryService {

	@RequestMapping(value = "/page", method = POST)
	PageHolder<DockerRepository> page(@RequestBody PageHolder<DockerRepository> pm,
			@RequestParam(name = "name", required = false) String name);

	@RequestMapping(value = "/getForSelect", method = POST)
	List<DockerRepository> getForSelect();

	@RequestMapping(value = "/save", method = POST)
	void save(@RequestBody DockerRepository dockerRepository);

	@RequestMapping(value = "/detail", method = POST)
	DockerRepository detail(@RequestParam(name = "id", required = false) Long id);

	@RequestMapping(value = "/del", method = POST)
	void del(@RequestParam(name = "id", required = false) Long id);

	@RequestMapping(value = "/getRepositoryProjects", method = POST)
	List<RepositoryProject> getRepositoryProjects(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "address", required = false) String address,
			@RequestParam(name = "name", required = false) String name)
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

}