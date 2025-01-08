# Hibernate-JPA-Query

In Eclipse,
firstly , we need to create maven project and choose java version that is important.
Secondly, we must made build-property in pom.xml beacause we setup java-version and other requirements such as <project.source.Encoding>.
And then we must download required dependencies in pom.xml.They are
- hibernate-core for hibernate jpa 
- mysql-connector-j for database
- lombok for getter,setter and so on as annotation
- junit-jupiter for testing in eclipse
- hibernate-jpamodelgen for generating a metamodel class for your JPA entities instead of using string-based queries,primarily using to enable type-safe criteria queries in JPA Criteria API
