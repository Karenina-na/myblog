 <!--ssr证书-->

        <Connector port="443" protocol="HTTP/1.1" SSLEnabled="true"
         maxThreads="150" scheme="https" secure="true"

         keystoreFile="jks绝对路径"

         keystorePass="密码"
         clientAuth="false"/>

 <Engine name="Catalina" defaultHost="域名">
  <Host name="域名"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
 <Context path="" docBase="项目war解压名" debug="0" reloadable="true"/>
