# dingtalk-edu-openapi-demo
钉钉教育相关生态开放能力对外透出后，为了方便对接的ISV快速接入，减少沟通成本，产出了针对开放能力的demo示例工程，供外部ISV快速接入使用。

## 运行环境开发工具
java8  
intellij idea

## 项目结构
```
.
├── README.md
├── dingtalk-edu-openapi-demo.iml
├── lib
│   ├── taobao-sdk-java-auto_1479188381469-20200903-source.jar
│   └── taobao-sdk-java-auto_1479188381469-20200903.jar
├── pom.xml
└── src
    ├── main
    │     ├── java
    │     │       └── com
    │     │              └── dingtalk
    │     │                          └── edu
    │     │                                 └── openapi
    │     │                                            └── demo
    │     │                                                   ├── Application.java
    │     │                                                   ├── config
    │     │                                                   │        ├── ApiUrlConstant.java
    │     │                                                   │        └── Constant.java
    │     │                                                   ├── common
    │     │                                                   │        └── BaseService.java
    │     │                                                   ├── controller
    │     │                                                   │        ├── course
    │     │                                                   │        │         └── CourseController.java
    │     │                                                   │        └── subject
    │     │                                                   │                  └── SubjectController.java
    │     │                                                   └── model
    │     │                                                            └── request
    │     │                                                                      ├── BaseRequest.java
    │     │                                                                      ├── CourseParticipantRequest.java
    │     │                                                                      ├── EduCourseCreateRequest.java
    │     │                                                                      └── EduSubjectCreateRequest.java
    │     │                                                     
    │     │                                                    
    │     └── resources
    │            └── application.properties
    └── test
        └── java
            
                
```
                    
                
## 项目配置
1.用intellij idea打开工程，as maven project，然后右键选择工程中lib目录，Add as library， 该目录中的jar包通过下方链接更新最新版本。
https://ding-doc.dingtalk.com/doc#/faquestions/vzbp02/8DMhu

2.更新Constant.java文件的SUITE_KEY，SUITE_SECRET，TOKEN，ENCODING_AES_KEY 四个属性。  
具体数值值请登录[开发者后台套件列表](http://open-dev.dingtalk.com/#/providerSuite?_k=j3e5en)，查看套件详情中获取      

3.更新application.properties文件的服务器启动端口。

4.如使用钉钉音视频能力，请联系钉钉接口人做音视频相关初始化配置。

## 打包命令
mvn clean package  -Dmaven.test.skip=true  
打成的包在工程文件的target目录下。文件为  "工程名"-"版本号".jar。

## 服务部署    
java -jar  target/"工程名"-"版本号".jar