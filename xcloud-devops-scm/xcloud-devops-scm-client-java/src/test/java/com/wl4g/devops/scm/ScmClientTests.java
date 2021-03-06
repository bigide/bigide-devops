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
package com.wl4g.devops.scm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.wl4g.devops.scm.client.ScmClient;
import com.wl4g.devops.scm.client.ScmClientBuilder;
import com.wl4g.devops.scm.client.event.RefreshConfigEvent.RefreshContext;

/**
 * {@link ScmClientTests}
 *
 * @author Wangl.sir <wanglsir@gmail.com, 983708408@qq.com>
 * @version v1.0 2020-08-19
 * @since
 */
public class ScmClientTests {

	public static void main(String[] args) throws Exception {
		System.out.println("SCM client starting ...");

		ScmClient client = ScmClientBuilder.newBuilder()
			.withBaseUri("http://localhost:14043")
			.withCluster("scmClientApp1")
			.enableManagementConsole()
			.withLongPollTimeout(6000L)
			.withListeners(event-> {
				System.out.println("On refresh configuration...");
				RefreshContext context = event.getSource();
				System.out.println("Refresh property: " + context.toString());

				// Do refreshing
				// ...

				// Commit changed keys
				Set<String> changeds = new HashSet<>();
				changeds.add("myconfig.task.threads");
				context.commitChanged(changeds);

			}).build();

		client.start();

		// Exiting and shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));

	}

}