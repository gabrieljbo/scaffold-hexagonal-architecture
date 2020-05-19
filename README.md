# scaffold-hexagonal-architecture


## Installation

1. Clone this project in any directory:
```
git clone https://github.com/gabrieljbo/scaffold-hexagonal-architecture.git
```

2. In the directory, execute the following command:
```
mvn archetype:create-from-project -Darchetype.properties=archetype.properties
```

3. Go to the subdirectory **target/generated-sources/archetype/**:
```  
cd target/generated-sources/archetype/
```

4. In the subdirectory, execute the following command:
``` 
mvn install
```

5. Go to the working directory where the Java project will be generated and execute:
```  
mvn archetype:generate -DarchetypeCatalog=local
```

6. Choose the archetype number corresponding to **scaffold-hexagonal-architecture-archetype**, for example:
```
1: local -> dev.gabrieljbo:scaffold-hexagonal-architecture-archetype (Parent pom providing ...
``` 
