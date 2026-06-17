# Projeto-AED
Rodar

Windows - Dê cd na pasta que tem o programa e rode :
javac -d out (Get-ChildItem -Recurse -Filter *.java programa).FullName
java -cp out programa.Main


Linux -  Dê cd na pasta que tem o programa e rode :
javac -d out $(find programa -name "*.java")
java -cp out programa.Main
