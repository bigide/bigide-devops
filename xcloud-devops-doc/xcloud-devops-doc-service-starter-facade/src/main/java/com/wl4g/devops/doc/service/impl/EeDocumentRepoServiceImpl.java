// Generated by XCloud DevOps for Codegen, refer: http://dts.devops.wl4g.com

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

package com.wl4g.devops.doc.service.impl;

import com.wl4g.component.core.bean.BaseBean;
import com.wl4g.component.core.bean.model.PageHolder;
import com.wl4g.component.rpc.feign.core.context.RpcContextHolder;
import com.wl4g.devops.common.bean.doc.EeDocumentRepo;
import com.wl4g.devops.doc.data.EeDocumentRepoDao;
import com.wl4g.devops.doc.service.EeDocumentRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wl4g.component.common.lang.Assert2.notNullOf;
import static java.util.Objects.isNull;

/**
 *  service implements of {@link EeDocumentRepo}
 *
 * @author root
 * @version 0.0.1-SNAPSHOT
 * @Date 
 * @since v1.0
 */
@Service
public class EeDocumentRepoServiceImpl implements EeDocumentRepoService {

    @Autowired
    private EeDocumentRepoDao eeDocumentRepoDao;

    @Override
    public PageHolder<EeDocumentRepo> page(EeDocumentRepo eeDocumentRepo) {
        PageHolder pm = RpcContextHolder.get().get("pm", PageHolder.class);
        pm.count().startPage();
        pm.setRecords(eeDocumentRepoDao.list(eeDocumentRepo));
        return pm;
    }

    @Override
    public int save(EeDocumentRepo eeDocumentRepo) {
        if (isNull(eeDocumentRepo.getId())) {
        	eeDocumentRepo.preInsert();
            return eeDocumentRepoDao.insertSelective(eeDocumentRepo);
        } else {
        	eeDocumentRepo.preUpdate();
            return eeDocumentRepoDao.updateByPrimaryKeySelective(eeDocumentRepo);
        }
    }

    @Override
    public EeDocumentRepo detail(Long id) {
        notNullOf(id, "id");
        return eeDocumentRepoDao.selectByPrimaryKey(id);
    }

    @Override
    public int del(Long id) {
        notNullOf(id, "id");
        EeDocumentRepo eeDocumentRepo = new EeDocumentRepo();
        eeDocumentRepo.setId(id);
        eeDocumentRepo.setDelFlag(BaseBean.DEL_FLAG_DELETE);
        return eeDocumentRepoDao.updateByPrimaryKeySelective(eeDocumentRepo);
    }

}
