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
package com.wl4g.devops.dts.codegen.bean;

import com.wl4g.components.core.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;

import static com.wl4g.devops.dts.codegen.engine.generator.AbstractGeneratorProvider.RenderingProperty;

/**
 * {@link GenDataSource}
 *
 * @author Wangl.sir <wanglsir@gmail.com, 983708408@qq.com>
 * @version v1.0 2020-09-10
 * @since
 */
@Getter
@Setter
public class GenDataSource extends BaseBean {
    private static final long serialVersionUID = 6815608076300843748L;

    @RenderingProperty
    private String name;

    @RenderingProperty
    private String type;

    @RenderingProperty
    private String host;

    @RenderingProperty
    private String port;

    @RenderingProperty
    private String database;

    private String username;

    private String password;

    private String url;

    public GenDataSource() {
        super();
    }

    public GenDataSource withName(String name) {
        this.name = name;
        return this;
    }

    public GenDataSource withType(String type) {
        this.type = type;
        return this;
    }

    public GenDataSource withHost(String host) {
        this.host = host;
        return this;
    }

    public GenDataSource withPort(String port) {
        this.port = port;
        return this;
    }

    public GenDataSource withDatabase(String database) {
        this.database = database;
        return this;
    }

    public GenDataSource withUsername(String username) {
        this.username = username;
        return this;
    }

    public GenDataSource withPassword(String password) {
        this.password = password;
        return this;
    }

    public GenDataSource withUrl(String url) {
        this.url = url;
        return this;
    }
}