# Come usare BeakCode ⚠️ IGNORA A TUO RISCHIO E PERICOLO ⚠️

| :exclamation: :exclamation: :exclamation: MOLTO IMPORTANTE :exclamation: :exclamation: :exclamation: |
|-----------------------------------------|
| <p style="text-align: center;"> **PRIMA DI CONSEGNARE RIMUOVI ```BREAKCODE```, IL ```FILE TXT``` E I METODI ```toString()``` O QUALSIASI ALTRA COSA AGGIUNTIVA CHE TI HO CHIESTO DI CREARE/MODIFICARE/TOGLIERE/DEFINIRE QUI SOTTO** |	
|SE PER UN QUALCHE MOTIVO NON COMPILA E TI BLOCCA TUTTI I TEST DEL CODICE NON VOGLIO SENTIRE CAZZI, LA RESPONSABILITA' E' SOLO TUA :) </p> |

## 1. Definisci un metodo toString() per le classi Iscritto e Immagine (se non lo hai già fatto) 
Probabilmente lo hai già fatto per rispondere ai requisiti del problema.

Se lo hai già fatto salta questo passo e vai direttamente a quello sotto.

Se invece non lo hai ancora fatto copia e incolla tranquillamente i codici che trovi qui sotto, eventualmente eliminali dopo.

| :zap: Riscrivi i metodi in modo che coincidano con gli attributi delle classi del tuo programma |
|-----------------------------------------|

Per la classe Iscritto
```java

@Override
public String toString() {
	return email + " " + nome + " " + cognome + " " + genere + " " + eta;
}
```

Per la classe ```Immagine```
```java
@Override
public String toString() {
	return codiceImmagine + " " + iscritto.getEmail() + " " + nomeImmagine + " " + tipoImmagine;
}
```
Per la classe ```ImmagineFacebook```
```java
@Override
public String toString() {
	return super.toString() + " " + nomeAlbum;
}
```
Per la classe ```ImmagineInstagram```
```java
@Override
public String toString() {
	return super.toString() + " " + numeroLike;
}
```
Per la classe ```ImmagineAltroSocial```
```java
@Override
public String toString() {
	return super.toString() + " " + nomeSocial + " " + dimensioneImmagine;
}
```

## 2. Definisci un metodo toString() per la classe Report
Questo invece non era richiesto nel pdf, ma ti tornerá utile per verificare i report quando eseguirai ```BreakCode```
```java

@Override
public String toString() {
	return "codice=" + codice + ", immagine=" + immagine.getCodiceImmagine() + ", descrizione=" + descrizione + ", timestamp=" + timestamp;
}
```

| In realtà non è obbligatorio definire questi metodi toString(), se non li definisci il programma stamperà semplicemente i riferimenti di memoria degli oggetti, tuttavia è caldamente consigliato per avere un output che coincida con quello che ti ho lasciato qui!|
|-----------------------------------------|

## 3. Copia e incolla il codice di BreakCode.java dove vuoi nel tuo progetto
Io personalmente mi faccio un package ```tests``` nel progetto, ma puoi metterlo dove ti pare


![Immagine di esempio di dove metto BreakCode](https://i.ibb.co/0qSyrN1/immagine.png)


## 4. Avvialo e confronta i risultati, li trovi su outputBreakCode.txt 
Ti consiglio di utilizzare [DiffChecker](https://www.diffchecker.com/) per confrontare i due file, molto comodo!

| :zap:        NOTA BENE   |
|--------------------------|
| **E' normale che tutto non coincida alla perfezione**, prima di andare in panic mode pensando di aver sbagliato leggi il codice che ho scritto e controlla se l'output che hai te sia comunque logicamente corretto, quello è l'importante! E' normale che ci siano delle differenze, non tutti scriviamo il codice nella stessa identica maniera, e io pure posso aver sbagliato! (Nel caso scrivimi pure)|
| Nota come ogni step sia appositamente commentato con dei print in console, leggili, ragionaci e riscrivili eventualmente! |


<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>. The original Creator do not assume any responsabilty of your use of this code. 
