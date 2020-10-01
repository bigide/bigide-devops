# ${watermark}
# Copyright (c) 2017 ~ 2025, the original author wangl.sir individual Inc,
# All rights reserved. Contact us <Wanglsir@gmail.com, 983708408@qq.com>
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

<#assign topDomain = organName?lower_case + '.'+ organType?lower_case />
<#assign redisHost = 'redis.' + topDomain />

# #### Environment(PRO) configuration. ####
#
spring:
  cloud:
    devops:
<#if javaSpecs.isConf(extraOptions, "gen.iam.security-mode", "cluster")>
      iam:
        cors:
          rules:
            '[/**]':
              allows-origins:
                - https://${r'${'}X_SERVICE_ZONE:${topDomain}}
                - http://${r'${'}X_SERVICE_ZONE:${topDomain}}
                - https://*.${r'${'}X_SERVICE_ZONE:${topDomain}}
                - http://*.${r'${'}X_SERVICE_ZONE:${topDomain}}
        acl:
          secure: false # Turn off protection will trust any same intranet IP.
          allowIpRange: ${r'${'}X_IAM_ACL_ALLOW:127.0.0.1}
          denyIpRange: ${r'${'}X_IAM_ACL_DENY}
        client:
          server-uri: ${r'${'}X_SERVICE_SCHEMA:https}://iam.${r'${'}X_SERVICE_ZONE:${topDomain}}/iam-server
          unauthorized-uri: ${r'${'}spring.cloud.devops.iam.client.server-uri}/view/403.html
          success-uri: ${r'${'}X_SERVICE_SCHEMA:https}://devops.${r'${'}X_SERVICE_ZONE:${topDomain}}/#/share
<#elseif javaSpecs.isConf(extraOptions, "gen.iam.security-mode", "local")>
      iam: # IAM server configuration.
        cors:
          rules:
            '[/**]':
              allows-origins:
                - https://${r'${'}X_SERVICE_ZONE:wl4g.com}
                - http://${r'${'}X_SERVICE_ZONE:wl4g.com}
                - https://*.${r'${'}X_SERVICE_ZONE:wl4g.com}
                - http://*.${r'${'}X_SERVICE_ZONE:wl4g.com}
        login-uri: ${r'${'}X_SERVICE_SCHEMA:https}://devops.${r'${'}X_SERVICE_ZONE:wl4g.com}/#/login
        unauthorized-uri: /view/403.html
        success-endpoint: umc-manager@https://umc.${r'${'}X_SERVICE_ZONE:wl4g.com}/umc-manager
        acl:
          secure: false # Turn off protection will trust any same intranet IP.
          allowIpRange: ${r'${'}X_IAM_ACL_ALLOW:127.0.0.1}
          denyIpRange: ${r'${'}X_IAM_ACL_DENY}
        captcha:
          #jigsaw:
            #source-dir: ${r'${'}server.tomcat.basedir}/data/jigsaw-maternal
        sns: # SNS configuration.
          oauth2-connect-expire-ms: 60_000 # oauth2 connect processing expire time
          wechat-mp:
            app-id: yourappid
            app-secret: yoursecret
            redirect-url: https://iam.${r'${'}X_SERVICE_ZONE:wl4g.com}${r'${'}server.servlet.contextPath}/sns/wechatmp/callback
          wechat:
            app-id: yourappid
            app-secret: yoursecret
            redirect-url: https://iam.${r'${'}X_SERVICE_ZONE:wl4g.com}${r'${'}server.servlet.contextPath}/sns/wechat/callback
            href: https://${r'${'}X_SERVICE_ZONE:wl4g.com}/${r'${'}server.servlet.contextPath}/iam-jssdk/assets/css/iam-jssdk-wx.min.css
          qq:
            app-id: 101542056 # Public testing.
            app-secret: 46b2ba9fa24c2b973abc64ec898db3b4
            redirect-url: https://iam.${r'${'}X_SERVICE_ZONE:wl4g.com}{server.servlet.contextPath}/sns/qq/callback
</#if>

  # Datasource configuration.
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driverClassName: com.mysql.jdbc.Driver
    druid:
      url: jdbc:mysql://${r'${'}X_DB_URL:${datasource.databaseName}.mysql.rds.aliyuncs.com:3306}/${r'${'}X_DB_NAME:${datasource.databaseName}}?useUnicode=true&characterEncoding=utf-8&useSSL=false
      username: ${r'${'}X_DB_USER:${projectName}}
      password: ${r'${'}X_DB_PASSWD:DFDDD7F502E694F3E40D750FEEAE423D}
      initial-size: 10
      max-active: 100
      min-idle: 10
      max-wait: 60000
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 1
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      filters: stat,wall
      log-slow-sql: true

# Redis configuration.
redis:
  passwd: zzx!@#$%
  connect-timeout: 10000
  max-attempts: 10
  # Redis's cluster nodes.
  nodes: ${r'${'}X_REDIS_NODES:${redisHost}:6379,${redisHost}:6380,${redisHost}:6381,${redisHost}:7379,${redisHost}:7380,${redisHost}:7381}