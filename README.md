# Modern API Development with Spring and Spring Boot

<a href="https://www.packtpub.com/web-development/modern-api-development-with-spring-and-spring-boot?utm_source=github&utm_medium=repository&utm_campaign=9781800562479"><img src="https://static.packt-cdn.com/products/9781800562479/cover/smaller" alt="Modern API Development with Spring and Spring Boot" height="256px" align="right"></a>

This is the code repository for [Modern API Development with Spring and Spring Boot](https://www.packtpub.com/web-development/modern-api-development-with-spring-and-spring-boot?utm_source=github&utm_medium=repository&utm_campaign=9781800562479), published by Packt.

**Design highly scalable and maintainable APIs with REST, gRPC, GraphQL, and the reactive paradigm**

## What is this book about?
The philosophy of API development has been evolving over the years to serve the modern needs of enterprise architecture, and developers need to know how to adapt to these modern API design principles. Apps are now developed with APIs which enable ease of integration for the cloud environment and distributed systems. In this Spring book, you will discover various kinds of production-ready API implementation with the REST API, along with exploring async using the reactive paradigm, gRPC, and GraphQL. 

This book covers the following exciting features:
Understand RESTful API development, its design paradigm, and its best practices
Become well versed in Spring's core components for implementing RESTful web services
Implement reactive APIs and explore async API development
Apply Spring Security for authentication using JWT and authorization of requests
Develop a React-based UI to consume APIs
Implement gRPC inter-service communication
Design GraphQL-based APIs by understanding workflows and tooling
Gain insights into how you can secure, test, monitor, and deploy your APIs

If you feel this book is for you, get your [copy](https://www.amazon.com/dp/1800562470) today!

<a href="https://www.packtpub.com/?utm_source=github&utm_medium=banner&utm_campaign=GitHubBanner"><img src="https://raw.githubusercontent.com/PacktPublishing/GitHub/master/GitHub.png" 
alt="https://www.packtpub.com/" border="5" /></a>

## Instructions and Navigations
All of the code is organized into folders. For example, Chapter02.

The code will look like the following:
```
@Autowired
private DgsQueryExecutor dgsQueryExecutor;
@MockBean
private ProductService productService;
@MockBean
```

**Following is what you need for this book:**
0

With the following software and hardware list you can run all code files present in the book (Chapter 1-14).
### Software and Hardware List
| No | Software required | OS required |
| -------- | ------------------------------------ | ----------------------------------- |
| 1  | Java 15 | Windows, Mac OS X, and Linux (Any) |
| 2 | Java IDE such as IntelliJ or Eclipse | Windows, Mac OS X, and Linux (Any) |
| 3 | Docker | Windows, Mac OS X, and Linux (Any) |
| 4 | Kubernetes (Minikube) | Windows, Mac OS X, and Linux (Any) |
| 5 | cURL or Postman | Windows, Mac OS X, and Linux (Any) |
| 6 | Node.js 14.x with NPM 6.x | Windows, Mac OS X, and Linux (Any) |
| 7 | Visual Studio Code | Windows, Mac OS X, and Linux (Any) |
| 8 | React 17 | Windows, Mac OS X, and Linux (Any) |
| 9 | ELK Stack | Windows, Mac OS X, and Linux (Any) |
| 10 | Zipkin | Windows, Mac OS X, and Linux (Any) |

We also provide a PDF file that has color images of the screenshots/diagrams used in this book. [Click here to download it](https://static.packt-cdn.com/downloads/9781800562479_ColorImages.pdf).

### Related products
* Microservices with Spring Boot and Spring Cloud - Second Edition [[Packt]](https://www.packtpub.com/product/microservices-with-spring-boot-and-spring-cloud-second-edition/9781801072977?utm_source=github&utm_medium=repository&utm_campaign=9781801072977) [[Amazon]](https://www.amazon.com/dp/B094DMGZ6T)

* Hands-On Full Stack Development with Spring Boot 2 and React - Second Edition [[Packt]](https://www.packtpub.com/product/hands-on-full-stack-development-with-spring-boot-2-and-react-second-edition/9781838822361?utm_source=github&utm_medium=repository&utm_campaign=9781838822361) [[Amazon]](https://www.amazon.com/dp/B07S6F7YL3)

## Errata 
 * Page 328 (paragraph 3 , line 1):  **The host name is import when you're using the containers. Therefore, we'll use the IP number instead of the local hostname for the registry host** _should be_ **The host name is necessarywhen you're using the containers. Therefore, you'll use the IP number instead of the local hostname for the registry host**
 *  Page 269 (bullet 1 , line 1):  **The login operation sets the access token, refresh token, user ID, and username in the responseToken key of the local storage by using the state arguments passed by the App component.** _should be_ **The login operation sets the access token, refresh token, user ID, and username in the tokenResponse key of the local storage by using the state arguments passed by the App component.**
 


## Get to Know the Author
**Sourabh Sharma** works at Oracle as a lead technical member where he is responsible for developing and designing the key components of the blueprint solutions that are used by various Oracle products. He has over 18 years of experience delivering enterprise products and applications for leading companies. His expertise lies in conceptualizing, modeling, designing, and developing N-tier and cloud-based web applications as well as leading teams. He has vast experience in developing microservice-based solutions and implementing various types of workflow and orchestration engines. Sourabh believes in continuous learning and sharing knowledge through his books and training.

