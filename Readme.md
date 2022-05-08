# Struts 2
# SetUp & Procedure

## **Eclipse Struts Setup**
 
> **`File -> New -> Dynamic Web Project` -> Enter the project name and hit `next`**
>
> **Build path is `src\main\java`**
> 
> **Output Folder is `build\classes` and hit `next`**
> 
> **Content Directory is `web` and `ENABLE Generate web.xml deployment descriptor` and hit `finish`**
>
> **Right click `Project -> Properties -> Java Build Path -> Library -> Add Library -> User Library -> User Libraries -> struts2 -> Apply and Close`**
>
> **Right click `Project -> Properties -> Deployment Assembly -> Add ->Java Build Path Entries -> struts2 -> Apply and Close`**
>
> **In `Markers tab` click on `Classpath Dependency Validator Message` then right click `.......jar will not be exported or published.` then click `Quick Fix` then select `Mark the associated raw classpath entry as a publish/export dependency` then click `Finish`. Do the same for all messages.**

> **Create `struts.xml` file at the build path (`src\main\java`)**
>
> **Copy `struts-2.3.dtd` from `struts-2.3.37/src/core/src/main/resources` and paste the file at `Project/web/WEB-INF/dtds/struts-2.3.dtd`**

### **`struts.xml` File Contents :**
```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
    "/WEB-INF/dtds/struts-2.3.dtd">
    
    <struts>
    	<package name="default" extends="struts-default">
    		<action name="url" class="package.class">
    			<result name="success">/successpage.jsp</result>
    			<result name="error">/errorpage.jsp</result>
    		</action>
    	</package>
    </struts>
```

> **Create a class inside a package at the build path (`src\main\java`)**

### **`packagename.classname.class` File Contents :**
```java
package packagename;

public class classname {
	public String execute()
	{
		return("success");
	}
}
```

> **Replace `web.xml` file contents with the following (No need to change anything except Display Name)**

### **`web.xml` File Contents :**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <display-name>Display Name</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  <filter>
  	<filter-name>struts2</filter-name>
  	<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>struts2</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>
```








## **Version Management**

> **JAVA SE-8**
> 
> **TOMCAT v9.0**
>
> **Struts 2.3.37**
>
> **mysql  Ver 8.0.29-0ubuntu0.20.04.2 for Linux on x86_64 ((Ubuntu))**
>
> **mysql-connector-java-8.0.29.jar**
> 
> **Eclipse v2019-12 (4.14.0)**


##### 06-05-2022