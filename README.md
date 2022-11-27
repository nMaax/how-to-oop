# How to OOP

Durante la scrittura del codice mantieni una scrittura ordinata, segui il format di coding ufficiale Google:
[Google Styleguide Java](https://google.github.io/styleguide/javaguide.html)

**Importante**: usa la documentazione fino a *Java SE 8* compreso, [qui trovi la documentazione](https://docs.oracle.com/javase/8/docs/)


### 0. Preparazione materiale necessario
Copia lo scheletro e salvalo in un file ```bones.txt``` interno al progetto

Crea un file txt ```todo.txt``` interno al progetto dove annotare ció che ti viene in mente, la todo list e altro

Crea un package ```myTests``` e ```myMethods``` per, rispettivamente:

1. Creare i tuoi main di testing del codice (```myMain.java``` e ```breakCode.java``` in myTest)
2. Riunire i metodi di manipolazione dati (```dataManipulationMethods.java``` in myMethods)

N.B. _myMethods mettilo come sottopackage del package principale fornito dalla consegna_

[![Esempio di struttura del progetto](https://i.postimg.cc/TYHb8Tc4/immagine.png)](https://postimg.cc/BXHbH9kT)
	
### 1. Analisi del problema e dello scheletro
Leggi il pdf diviso per parti

Ogni volta che inizi una parte dei requisiti:

1. Sottolinea/evidenzia i relativi vincoli mentre leggi
2. Copia e incolla le richieste del pdf nella javadoc del metodo a esse relativo (```/***/```)
3. Prepara i commenti multi riga per lo pseudo codice, per ogni metodo (```/**/```)

[![Esempio di analisi del pdf](https://i.postimg.cc/Bnj4y0hN/immagine.png)](https://postimg.cc/w1YCySYs)

### 2. Schema mentale del programma
Fatti un idea generale di programma e abbozza velocemente su un foglio di carta una struttura generale del programma
	
### 3. Getters, Setters e costruttori
Torna all'inizio del pdf e skimma ogni parte per generare i vari package, classi, attributi, getter e setter di base (```Alt + S```)

* Metti gli attributi a private/protected, i metodi a public
* Crea dei costruttori di default per ogni classe con valori static final di default (utili per testare il codice velocemente)
* Crea i metodi ```toString()```, ```equals(Object o)``` e ```compareTo(Object o)``` (nei test non vengono mai stampati gli oggeti direttamente)

```java
package simplePackage;

import java.util.LinkedList;

public class SimpleClass {
	
	public final static int CONSTANT = 42;
	
	private int commonAttribute;
	protected LinkedList<Boolean> attributeForChilds;
	
	public SimpleClass() {
		super();
		commonAttribute = CONSTANT;
		attributeForChilds = new LinkedList<Boolean>();
		attributeForChilds.addFirst(true);
	}

	public SimpleClass(int commonAttribute, LinkedList<Boolean> attributeForChilds) {
		super();
		this.commonAttribute = commonAttribute;
		this.attributeForChilds = attributeForChilds;
	}

	public int getCommonAttribute() {
		return commonAttribute;
	}

	public LinkedList<Boolean> getAttributeForChilds() {
		return attributeForChilds;
	}

	public void setCommonAttribute(int commonAttribute) {
		this.commonAttribute = commonAttribute;
	}

	public void setAttributeForChilds(LinkedList<Boolean> attributeForChilds) {
		this.attributeForChilds = attributeForChilds;
	}
	
	public int compareTo(SimpleClass other) {
		return other.commonAttribute - this.commonAttribute;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleClass other = (SimpleClass) obj;
		return compareTo(other) > CONSTANT;
	}

	@Override
	public String toString() {
		return "SimpleClass [commonAttribute=" + commonAttribute + ", attributeForChilds=" + attributeForChilds + "]";
	}
}

```

### 4. Elementi, metodi e algoritmi avanzati del programma
Per ogni parte del pdf:
* _**Prima si pianifica e dopo si scrive!**_
* Individua quei metodi "simili" che sono riscrivibili come unico metodo ([DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself))
* Inzia a creare il programma, per ogni metodo:
	1. Compila lo pseudocodice relativo (aiutandoti con carta e penna eventualmente)
	2. Crea il codice, eventualmente commentando riga per riga (```//```) per i metodi piú complessi
	3. Testa il loro funzionamento su myMain e con ```Esempio.java``` 
	4. **Debugga!** Non importa quali siano le tue capacitá, se non sai debuggare _imparalo subito!_, all'esame sará _fondamentale_. Se non sai come debuggare su Eclipse puoi guardare [questo video](https://youtu.be/aqcJsKdjjvU) 

```java 

	/**
	 * Riceve come parametro
	 * l’email dell’utente da cercare
	 * e ritorna l’utente desiderato se registrato, 
	 * null altrimenti
	 * */
	public Utente cercaUtente(String email) {
		
		/*
		 * Scorro la lista di utenti
		 * Controllo che l'email in scorrimento coincida con il parametro
		 * 		Se true: utente trovato, me lo salvo e lo restituisco
		 * 		Se false: non faccio nulla e continuo lo scorrimento del for
		 * */
		
		Utente output = null;
		for (Utente utente : utenti) {
			String emailInLoop = utente.getEmail();
			if (emailInLoop.equals(email)) {
				output = utente;
			}
		}
		
		return output;
	}

```

### 5. Buca il codice
Cerca di bucare il codice in BreakCode, anche qui mantieni una scrittua ordinata e dividi i vari test in metodi

```java
package myTests;

import cluster.*;

public class BreakCode {

	public static void main(String[] args) {
		sopln("Creazione Cluster\n");
		Cluster c = new Cluster(5, 10);
		
		test1(c);
		test2(c);
		test3(c);
	}
	
	public static void test1(Cluster c) {
		
		/* TEST PARTE 1 */
		
		makeUsers(c);
		describeUsers(c);
		searchUsers(c);
		
	}
	
	public static void makeUsers(Cluster c) {
		sopln(" * * * " + "Creazione utenti".toUpperCase() + " * * *\n");
		//Creo un primo admin e testo di getter
		UtenteAdmin u1 = c.registraUtente("fabio.garcea@polito.it", "Fabio", "Garcea", "Password1", "19930917", "pub - Che schifo Python (mi dissocio)", "priv - ma poi chi é che fa ingegneria matematica in questo corso? Ragazzi fatevi vedere");
		sopln("Creato utente admin: " + u1.getEmail());
		sopln(u1.getNome());
		sopln(u1.getCognome());
		sopln(u1.getPassword());
		sopln(u1.getDataNascita());
		sopln(u1.getChiavePubblica());
		sopln(u1.getChiavePrivata());
		sopln();
		
		// Creo un secondo admin
		c.registraUtente("grandiraggazzi@forzajjuve.it", "Cristiano", "Ronaldo", "jesusChrist3", "20000101", "SHA-1-PUBKEY", "SHA-1-PRIV_KEY");
		
		// Creo utenti standard
		sopln("Creazione altri utenti (standard)...");
		c.registraUtente("fabrizio.lamberti@polito.it", "Fabrizio", "Lamberti", "passWord2", "19800101");
		c.registraUtente("lia.morra@polito.it", "Lia", "Morra", "3passworD", "19850101");
		c.registraUtente("guido.marchetto@polito.it", "Guido", "Marchetto", "pass4woRd", "19850202");
		
		// Genero un utente e provo a rifarlo ma con un tipo diverso
		c.registraUtente("winston.churchill@gov.uk", "Winston", "Churchill", "nukeBerlinN0W", "18901225");
		c.registraUtente("winston.churchill@gov.uk", "Winston", "Churchill", "nukeBerlinN0W", "18901225", "ThisKeyWillBeIgnored", "ThisOneToo");
		c.registraUtente("elonmusk@twitter.com", "Elon", "Musk", "comicityIsLegalOnTwitterNow", "19850101", "DOGE101-PUB", "DOGE101-PRIV");
		c.registraUtente("elonmusk@twitter.com", "Elon", "Muschio", "Selvaggi0", "19850101");
		
		//Genero degli utenti con formati di email, password o dataNascita incompatibili
		c.registraUtente("passwordErrata@test.it", "Misato", "Katsuragi", "thekidlaroi", "19850101");
		c.registraUtente("emailErrata", "Asuka", "Langley Soryu", "theK1dLaroi", "19850101");
		c.registraUtente("eta@minorenne", "Rei", "Ayanami", "theK1dLaroi", "20200101");
		sopln();
	}
	
	public static void sopln() {
		sopln("");
	}
	
	public static void sopln(Object o) {
		System.out.println(o);
	}
}
```

### 6. Ottimizza il codice
Crea algoritmi più snelli o semplici (DRY, KISS, SOLID)

### 7. Prima di consegnare:
1. Effettua un backup esportando il file zip del progetto
2. Rimetti le varie righe spezzate su una sola riga (per non avere problemi nei test)
3. Rileggi il pdf per vedere se hai rispettato tutte le specifiche
4. Ricontrolla che le firme dei metodi coincidano con lo scheletro (usa bones.txt del passo 1)
5. Fai un ultimo run di BreakCode, myMain e Esempio
6. Togli la roba inutile
	1. Commenti di troppo
	2. Metodi inutilzzati (non togliere i setter e getter inutilizzati, magari ti serviranno nella riconsegna)
	3. I file extra dove magari hai scritto e usato caratteri non riconosciuti
7. Runna per l'ultimissima volta Esempio (unico main rimasto)
8. Consegna

## DRY, KISS and SOLID Principles:

[DRY, KISS & SOLID explained in Godot](https://youtube.com/playlist?list=PLJ690cxlZTgK6j3wpoI9PBopRLNZc5QPv)
	
### DRY

[Spiegazione di Wikipedia](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)	

* Dont write duplicate code
	* If code is repeated, put it inside a function (Code Refactor)
	* It's NOT ok to repeat the code once or twice, it must NEVER be repeated, neather one time
	* Less lines = Clean, Simple, Mantainable
	
* The more you use DRY the more you are gonna see code duplication

_General Rule:_	```200 lines per class, 20-30 per function, 20-50 per method (spaces and {} excluded)```
			
**OBJ**: _Making Code Isolation and Code Reusability_
	
### KISS

[Spiegazione di Wikipedia](https://en.wikipedia.org/wiki/KISS_principle)

* If you are thinking "What is going on? I cant follow this code" then you are breaking the KISS principle
* If KISS is broken, refactor your code by trying alternatives 

	```Note: (NOT by innesting it in functions - that's dry - but by creating new lean algorithms)```
* The more you use KISS the more you are gonna see complex code

**OBJ**: _Untagling Complex Code in something readable_
		
### SOLID (Avanzato)

[Spiegazione di Wikipedia](https://en.wikipedia.org/wiki/SOLID)

**S: 	Single Responsibility**

	Each class should have one responsability in the code
	Eventually you can break a class in smaller classes if it gets bigger (refactoring + class injection)

	Each function should do one thing only in the code
	Eventually you can break your function in other smaller functions (refactoring + function injection)

	Basically every block of code should have one and one porpuse only, aiming to solve it

**O:	Open to extensions, Closed to modifications**

	Each piece of code you develop should let others to implement it or extend it in other cases
	but you have to make a good piece of code that works so well it doesn't need to be modified in order to implement the new extensions

	Basically you have to make a "generic" code that works in a generic way so your code can be re-used easily

**L: 	Liskov Sobstitution**

	If a certain piece of code works for a group of object of type T then
	it should perfectly work even with a group of objects of type S (where S is a child of T)
	If you need to override some parent's functions then make sure the parameters (inputs)
	and the returned values (outputs) are of the same type and respect the original rules
		If you need to broke this last rule then just make a new function (eventually with a similar name)

	Basically just make sure your child classes are 100% compatible with code that uses parent class

**I: Interface Segregation**

	Programmers should not be forced to depend upon interfaces (classes, functions etc) that they don't use
	what it means is that it should never happen that you will have to "work" or "think about" how to resolve a certain
	interface that you wont ever use. 
	
**D: Dependency Inversion**

	High level classes/modules should not depend on low level classes/modules, both shoul depend on abstractions
	Abstractions should not depend on details, details should depend on abstractions.
	
###### Cosa significa...
* _Refactoring_ = Change the code of a program without changing its behaviour
* _Injection_   = Making classes/functions/elements only in order to be used into other classes/functions
