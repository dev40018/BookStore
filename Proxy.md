

Imagine you have a celebrity agent.  
When someone wants to book the celebrity for an event, they don't contact the celebrity directly  
they go through the agent. The agent acts as a proxy (or stand-in) for the celebrity,  
handling initial negotiations, filtering requests, and potentially adding services like contract verification.  
The celebrity **only gets involved when necessary.**

In software, the Proxy pattern works the same way - it provides a **surrogate object**   
that controls access to another object. Let's see this in action with a practical example:

```java
// The interface that both the real object and proxy will implement
public interface DocumentAccess {
    void viewDocument(String documentId);
    void editDocument(String documentId);
}

// The real object that does the actual work
public class RealDocumentAccess implements DocumentAccess {
    @Override
    public void viewDocument(String documentId) {
        System.out.println("Actually viewing document: " + documentId);
    }

    @Override
    public void editDocument(String documentId) {
        System.out.println("Actually editing document: " + documentId);
    }
}

// The proxy that controls access to the real object
public class DocumentAccessProxy implements DocumentAccess {
    private RealDocumentAccess realAccess;
    private final UserContext userContext;  // Contains user permissions

    public DocumentAccessProxy(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public void viewDocument(String documentId) {
        // Lazy initialization - create real object only when needed
        if (realAccess == null) {
            realAccess = new RealDocumentAccess();
        }

        // Access control
        if (!userContext.hasPermission("VIEW_DOCUMENT")) {
            throw new SecurityException("User doesn't have view permission");
        }

        // Logging before actual operation
        System.out.println("Logging: User " + userContext.getUserId() + 
                         " accessing document " + documentId);

        // Delegating to real object
        realAccess.viewDocument(documentId);
    }

    @Override
    public void editDocument(String documentId) {
        if (realAccess == null) {
            realAccess = new RealDocumentAccess();
        }

        if (!userContext.hasPermission("EDIT_DOCUMENT")) {
            throw new SecurityException("User doesn't have edit permission");
        }

        System.out.println("Logging: User " + userContext.getUserId() + 
                         " editing document " + documentId);

        realAccess.editDocument(documentId);
    }
}
```

The Proxy pattern is particularly useful in several scenarios:

1. Lazy Loading (Virtual Proxy):
```java
public class LazyLoadingImageProxy implements Image {
    private RealImage realImage;
    private final String filename;

    public LazyLoadingImageProxy(String filename) {
        this.filename = filename;
    }

    public void display() {
        // Create the real image only when it's first needed
        if (realImage == null) {
            realImage = new RealImage(filename);  // Expensive operation
        }
        realImage.display();
    }
}
```

2. Access Control (Protection Proxy):
```java
public class SecurePaymentProxy implements PaymentService {
    private final RealPaymentService realPaymentService;
    private final AuthenticationService authService;

    public void processPayment(Payment payment) {
        if (!authService.isUserAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }
        realPaymentService.processPayment(payment);
    }
}
```

3. Remote Proxy (for accessing remote resources):
```java
public class RemoteServiceProxy implements Service {
    private final String serviceUrl;
    private final RestTemplate restTemplate;

    public Object executeRequest(String request) {
        // Handle all the complex remote communication details
        return restTemplate.postForObject(serviceUrl, request, Object.class);
    }
}
```

Spring Framework extensively uses the Proxy pattern, especially in:

1. Transaction Management (@Transactional):
```java
@Service
public class UserService {
    @Transactional
    public void createUser(User user) {
        // Spring creates a proxy that wraps this method
        // to handle transaction begin/commit/rollback
    }
}
```

2. Aspect-Oriented Programming (AOP):
```java
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* com.example.service.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) 
            throws Throwable {
        // Spring creates a proxy to inject this logging behavior
        System.out.println("Before method execution");
        Object result = joinPoint.proceed();
        System.out.println("After method execution");
        return result;
    }
}
```

The key benefits of using the Proxy pattern are:
- Separation of concerns: The proxy can handle cross-cutting concerns like security or logging
- Control over object access: You can add validation, lazy loading, or caching
- Transparency: Clients don't need to know they're working with a proxy

## Proxy Pattern in Spring Framework


The Proxy pattern is fundamental to many of Spring's core features,  
particularly AOP (Aspect-Oriented Programming).  
Think of a proxy as a stand-in that controls access to the real object,  
similar to how a personal assistant might manage access to a busy executive.  

Let's explore this with a concrete example:

```java
// The interface both real object and proxy will implement
public interface UserService {
    void createUser(String username);
    User findUser(String username);
}

// The real implementation
@Service
public class UserServiceImpl implements UserService {
    public void createUser(String username) {
        // Actual user creation logic
        System.out.println("Creating user: " + username);
    }
    
    public User findUser(String username) {
        // Actual user finding logic
        return new User(username);
    }
}
```

When you use this service in Spring, what actually happens is quite interesting. Spring creates a proxy around your UserServiceImpl automatically when you use annotations like @Transactional or when you define AOP aspects:

```java
@Service
@Transactional
public class UserServiceImpl implements UserService {
    // Same methods as above
}
```

Behind the scenes, Spring creates something conceptually similar to this:

```java
// This is a simplified version of what Spring generates
public class UserServiceProxy implements UserService {
    private final UserService target;
    private final TransactionManager transactionManager;
    
    public UserServiceProxy(UserService target, TransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }
    
    public void createUser(String username) {
        TransactionStatus status = null;
        try {
            // Begin transaction before method execution
            status = transactionManager.beginTransaction();
            
            // Call the real service method
            target.createUser(username);
            
            // Commit transaction after successful execution
            transactionManager.commit(status);
        } catch (Exception e) {
            // Rollback transaction on error
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    public User findUser(String username) {
        // Similar transaction handling for findUser
        // ...
    }
}
```

Spring uses two main types of proxies:

1. JDK Dynamic Proxies: These are used when your class implements an interface. Spring creates a proxy that implements the same interface and delegates to your real object.

2. CGLIB Proxies: When your class doesn't implement an interface, Spring uses CGLIB to create a subclass of your actual class. This subclass overrides the methods to add the proxy behavior.

The proxy pattern in Spring enables several powerful features:

- Transaction management (@Transactional)
- Security checks (@Secured, @PreAuthorize)
- Caching (@Cacheable)
- Logging and monitoring
- Method performance timing
- Lazy initialization of beans

For example, if you want to add security to your service:

```java
@Service
@Secured("ROLE_ADMIN")
public class UserServiceImpl implements UserService {
    // Methods as before
}
```

Spring creates a proxy that checks security permissions before allowing access to the methods.  
The proxy intercepts the call, verifies the user's role, and only if authorized, forwards the call to the real service.

This approach is powerful because it separates cross-cutting concerns (like security and transactions)  
from your business logic.  
Your UserServiceImpl remains focused on user management, while the proxy handles these additional responsibilities.

