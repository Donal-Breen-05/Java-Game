# Java Game & 2D Game Engine 

A pixel art zombie survival game made with pure java using inbuilt libraries like JFrame and Graphics2D

--- 

## Screenshots

<table>
  <tr>
    <td><img src="ReadMe%20Images/image1.png" width="250"/></td>
    <td><img src="ReadMe%20Images/image2.png" width="250"/></td>
    <td><img src="ReadMe%20Images/image3.png" width="250"/></td>
  </tr>
</table>

### Gameplay 
<img src="ReadMe%20Images/gameplay.gif" width="500"/>

---

# Installation / Usage 

Requires installation of java , JDK , JRE and the JVM 
- all included in installation of the JDK
  
## Windows 
```powershell
javac -d bin src\entity\*.java src\item\*.java src\main\*.java src\tile\*.java && java -cp "bin;resources" main.Main
```

## linux / Mac Os 
```bash
javac -d bin $(find src -name "*.java") && java -cp "bin:resources" main.Main
```
