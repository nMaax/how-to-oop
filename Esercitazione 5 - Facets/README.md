# Come usare breakCode

 _*PRIMA DI CONSEGNARE RIMUOVI IL FILE*_, SE PER UN QUALCHE MOTIVO NON COMPILA E TI BLOCCA TUTTI I TEST DEL CODICE NON VOGLIO SENTIRE CAZZI

## Definisci un metodo toString() per le classi Iscritto e Immagine (se già non lo hai fatto) 
Probabilmente lo hai già fatto per rispondere ai requisiti del problema, se invece non lo hai ancora fatto copia e incolla tranquillamente i codici che trovi qui sotto

Per la classe Iscritto
```java
class 

@Override
public String toString() {
	return email + " " + nome + " " + cognome + " " + genere + " " + eta;
}
```

Per la classe Immagine e figle
```java
class Immagine

@Override
public String toString() {
	return codiceImmagine + " " + iscritto.getEmail() + " " + nomeImmagine + " " + tipoImmagine;
}

class ImmagineFacebook
@Override
public String toString() {
	return super.toString() + " " + nomeAlbum;
}

class ImmagineInstagram
@Override
public String toString() {
	return super.toString() + " " + numeroLike;
}

class ImmagineAltroSocial
@Override
public String toString() {
	return super.toString() + " " + nomeSocial + " " + dimensioneImmagine;
}
```


## Definisci un metodo toString() per la classe Report
Questo invece non era richiesto nel pdf, ma ti tornerá utile per verificare i report quando eseguirai ```BreakCode```
```java
class Report

@Override
public String toString() {
	return "codice=" + codice + ", immagine=" + immagine.getCodiceImmagine() + ", descrizione=" + descrizione + ", timestamp=" + timestamp;
}
```
## Copia e incolla il codice di BreakCode.java dove vuoi nel tuo progetto

## Avvialo e confronta i risultati, li trovi su outputBreakCode.txt
Ti consiglio di utilizzare [DiffChecker](https://www.diffchecker.com/)
### NOTA BENE: E' normale che tutto non coincida alla perfezione, prima di andare in panic mode pensando di aver sbagliato leggi il codice che ho scritto e controlla se l'output che hai te sia comunque logicamente corretto, quello è l'importante!
