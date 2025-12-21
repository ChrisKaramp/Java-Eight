# Εργασία Προγραμματισμός ΙΙ

## Στοιχεία Μαθήματος
- Τμήμα: Διοικητικής Επιστήμης και Τεχνολογίας (ΔΕΤ)
- Μάθημα: Προγραμματισμός ΙΙ
- Διδάσκων: Διομήδης Σπινέλλης
- Ακαδημαϊκό Έτος: 2025–2026

## Στοιχεία Φοιτητή
- Ονοματεπώνυμο: …
- Αριθμός Μητρώου: …
- Εξάμηνο: …

## Περιγραφή Εργασίας
Η παρούσα εργασία υλοποιεί … (σύντομη περιγραφή της λειτουργικότητας).
Η εφαρμογή είναι ανεπτυγμένη σε Java ακολουθώντας τη δομή Maven.

## Τεχνολογίες
- Γλώσσα προγραμματισμού: Java 25
- Build & dependency management: Maven 3.9.11
- IDE: Visual Studio Code
- UML / Diagrams: Mermaid
- Documentation: Markdown
- Λειτουργικό σύστημα: Windows 11 (PowerShell)
- Version control: Git, GitHub
- Χρησιμοποιούμενες βιβλιοθήκες: (αναφορά όλων των Maven dependencies)

## Απαιτήσεις / Περιβάλλον
- Java: 25 (Oracle)
- Maven: 3.9.11
- Maven home: C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.11
- Λειτουργικό σύστημα: Windows 11 (64-bit)
- Κέλυφος: PowerShell
- Default locale: en_US
- Platform encoding: UTF-8

## Οδηγίες Μεταγλώττισης και Εκτέλεσης (PowerShell)

Από τον ριζικό φάκελο του project:

```powershell
mvn clean
mvn package
java -jar target\pfad_j8-1.0.jar
