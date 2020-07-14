# JSF項目練習
使用maven快速搭建一個JSF Web App

## 環境
 - JavaSE1.7
 - JSF 2.2
 - Maven 3.5.3
 - Tomcat 7.0.81

### pom文件
```
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.tszyi</groupId>
	<artifactId>jsf.practice</artifactId>
	<version>0.0.1-SNAPSHOT</version>
    <!-- 將專案打包設成為war，這樣mvn package後自動打包成war放置target目錄裡 -->
	<packaging>war</packaging>

	<name>jsf.practice Maven Webapp</name>
	<!-- FIXME change it to the project's website -->
	<url>http://www.example.com</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.7</maven.compiler.source>
		<maven.compiler.target>1.7</maven.compiler.target>
	</properties>

	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-impl -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.6</version>
		</dependency>
	</dependencies>

	<build>
		<finalName>jsf.practice</finalName>
		<pluginManagement><!-- lock down plugins versions to avoid using Maven 
				defaults (may be moved to parent pom) -->
			<plugins>
				<plugin>
					<artifactId>maven-clean-plugin</artifactId>
					<version>3.0.0</version>
				</plugin>
				<!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
				<plugin>
					<artifactId>maven-resources-plugin</artifactId>
					<version>3.0.2</version>
				</plugin>
				<plugin>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.7.0</version>
				</plugin>
				<plugin>
					<artifactId>maven-surefire-plugin</artifactId>
					<version>2.20.1</version>
				</plugin>
				<plugin>
					<artifactId>maven-war-plugin</artifactId>
					<version>3.2.0</version>
				</plugin>
				<plugin>
					<artifactId>maven-install-plugin</artifactId>
					<version>2.5.2</version>
				</plugin>
				<plugin>
					<artifactId>maven-deploy-plugin</artifactId>
					<version>2.8.2</version>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
</project>
```
上面的pom文件中主要要添加兩個Jsf的兩個必要依賴：一個jsf的api及另一個impl
```
        <!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-impl -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.6</version>
		</dependency>
```

### web.xml文件
選用mvn搭建一個web app的目錄架構下，將其至於`src/main/webapp/WEB-INF`下
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  <display-name>JsfPractice</display-name>
  <welcome-file-list>
    <welcome-file>index.xhtml</welcome-file>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>index.htm</welcome-file>
  </welcome-file-list>
  <servlet>
    <servlet-name>Faces Servlet</servlet-name>
    <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>*.xhtml</url-pattern>
  </servlet-mapping>
  <context-param>
    <description>State saving method: 'client' or 'server' (=default). See JSF Specification 2.5.2</description>
    <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
    <param-value>client</param-value>
  </context-param>
  <context-param>
    <param-name>javax.servlet.jsp.jstl.fmt.localizationContext</param-name>
    <param-value>resources.application</param-value>
  </context-param>
  <listener>
    <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
  </listener>
</web-app>
```

## beans coding
透過下面Scoped Annotation來標註bean，去觀察bean的成員變數`num`在Web App運行間不同Scope對num變數的影響
```
public class SomeBean {
  private int num = 1;

  public int getNum() {
    return this.num;
  }

  public void incNum() {
    this.num++;
  }
}
```

### Appliaction Scoped
註有Application Scope的Bean的壽命與application能活長就有多長，不管是開新分頁、新視窗，甚至把瀏覽器關掉重啟、換另一個瀏覽器，bean依然存在。
```
@ManagedBean
@ApplicationScoped
public class SomeBean {
    ...
```

### None Scoped
所有視野中bean存活最短，壽命僅止於view的EL運算式"運算期間"，運算完後就不復存在了。
```
@ManagedBean
@NoneScoped
public class SomeBean {
    ...
```

### Request Scoped
若不使用任何Scope Annotation，則預設為Request Scope.視野中次短的(最短的是NoneScoped，只活在EL運算式結束後就被銷毀)，bean存活在HTTP的request至response間，response完後就不復存在。
```
@ManagedBean
@RequestScoped
public class SomeBean {
    ...
```

### View Scoped
註有View Scope的bean存活在一個瀏覽器的同一個"頁籤(tab)"，只要開新視窗、新頁籤，bean就不復存在。
```
@ManagedBean
@ViewScoped
public class SomeBean {
    ...
```

### Session Scoped
Session Scope的bean存活在同一個瀏覽器(HTTP裡的Session)，在瀏覽器不關閉的情況下，不管開(關)多少新頁籤、新視窗，或者**應用程式被重載**也依然存在.
```
@ManagedBean
@SessionScoped
public class SomeBean {
    ...
```

## view page coding
撰寫`index.xhtml`置於`src/main/webapp`下
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>JSF Scope Annotation Demo</title>
</h:head>
<h:body>
  <h:outputText value="#{showBean.info}" />
  <br />
  <h:outputText value="#{showBean.nonbean}" />
  <br />
  <br />
  AppScopeBean: <h:outputText value="#{appScopeBean.num}" />
	<br />
  ReqScopeBean: <h:outputText value="#{reqScopeBean.num}" />
	<br />
  SessScopeBean: <h:outputText value="#{sessScopeBean.num}" />
	<br />
	ViewScopeBean: <h:outputText value="#{viewScopeBean.num}" />
  <br />
  NoneScopeBean: <h:outputText value="#{noneScopeBean.num}" />
  <br />
	<h:form>
		<h:commandButton value="提交">
			<f:actionListener binding="#{appScopeBean.incNum()}" />
			<f:actionListener binding="#{reqScopeBean.incNum()}" />
			<f:actionListener binding="#{sessScopeBean.incNum()}" />
			<f:actionListener binding="#{viewScopeBean.incNum()}" />
			<f:actionListener binding="#{noneScopeBean.incNum()}" />
		</h:commandButton>
	</h:form>
</h:body>
</html>
```

## 觀看成果

### 編譯並打包
`mvn package`，編譯成功後且打包完成後，專案war檔會在target目錄下，以本專案為例是`target/jsf.practice.war`

### 部署
複製`jsf.practice.war`到tomcat的`webapps`下完成部署。

### 運行
啟動tomcat，在瀏覽器鍵入`http://localhost:8080/jsf.practice`

![result](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/result.png)

## 一些測試，觀察num在不同Scope的變化
承運行輸入完網址後，按下提交按鈕
**NoneScopeBean的num不再遞增。**

![first-click](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/first-click.png)


再按一次提交按鈕
**ReqScopeBean的num不在遞增**

![second-click](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/second-click.png)


開啟新的瀏覽器分頁(tab)
**ViewScopeBean跟ReqScopeBean的num都變成1**

![new-tab](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/new-tab.png)


關閉瀏覽器，但不關閉tomcat，在重啟瀏覽器
**AppScopeBean的num依然存在，其他都初始成1了**

![close-browser](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/close-browser.png)


現在按下提交鈕三次：
AppScopeBean: 6 
ReqScopeBean: 2 
SessScopeBean: 4 
ViewScopeBean: 4 
NoneScopeBean: 1 


接著關閉tomcat，但不關閉瀏覽器，再重啟tomcat
**SessScopeBean的num依然存在，其他都初始成1了**

![close-tomcat](https://github.com/tszyi/JSF-practice/blob/master/READMEpicture/close-tomcat.png)


最後可試試開啟不同家的瀏覽器Firefox、Chrome、Opera，配合提交按鈕，來觀察num變數在Application Scope的影響。
