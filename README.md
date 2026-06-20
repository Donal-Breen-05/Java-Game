# Java Game & 2D Game Engine 

A pixel art zombie survival game made with pure java using inbuilt libraries like JFrame and Graphics2D

---

# Installation / Usage 

Requires installation of java , JDK , JRE and the JVM 
- all included in installation of the JDK
  
## Windows 
```powershell
javac -d bin src\entity\*.java src\item\*.java src\main\*.java src\tile\*.java && java -cp "bin;resources" main.Main
```

## linux 
```bash
javac -d bin $(find src -name "*.java") && java -cp "bin:resources" main.Main
```
