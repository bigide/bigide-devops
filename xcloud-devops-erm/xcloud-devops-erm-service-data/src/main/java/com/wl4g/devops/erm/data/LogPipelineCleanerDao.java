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
package com.wl4g.devops.erm.data;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * {@link LogPipelineCleanerDao}
 * 
 * @author Wangl.sir &lt;wanglsir@gmail.com, 983708408@qq.com&gt;
 * @author vjay
 * @date 2019-12-24
 * @sine v1.0.0
 * @see
 */
public interface LogPipelineCleanerDao {

	int cleanJobStatusTraceLog(@Param("creationTime") Date creationTime);

	int cleanJobExecutionLog(@Param("startTime") Date startTime);

	int cleanUmcAlarmRecordSublist(@Param("createTime") Date createTime);

	int cleanUmcAlarmRecord(@Param("createTime") Date createTime);

	int cleanCiTaskHistorySublist(@Param("createDate") Date createDate);

	int cleanCiTaskHistory(@Param("createDate") Date createDate);

}