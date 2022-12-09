# Come usare breakCode

## _PRIMA DI CONSEGNARE RIMUOVI IL FILE E I METODI toString AGGIUNTIVI CHE TI HO CHIESTO DI DEFINIRE QUI SOTTO_ SE PER UN QUALCHE MOTIVO NON COMPILA E TI BLOCCA TUTTI I TEST DEL CODICE NON VOGLIO SENTIRE CAZZI :)

## 1. Definisci un metodo toString() per le classi Iscritto e Immagine (se già non lo hai fatto) 
Probabilmente lo hai già fatto per rispondere ai requisiti del problema,

Se già lo hai fatto salta questo passo e vai direttamente a quello sotto

se invece non lo hai ancora fatto  (magari preferisci dei metodi diversi) copia e incolla tranquillamente i codici che trovi qui sotto, eventualmente eliminali dopo.

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

## 2. Definisci un metodo toString() per la classe Report
Questo invece non era richiesto nel pdf, ma ti tornerá utile per verificare i report quando eseguirai ```BreakCode```
```java
class Report

@Override
public String toString() {
	return "codice=" + codice + ", immagine=" + immagine.getCodiceImmagine() + ", descrizione=" + descrizione + ", timestamp=" + timestamp;
}
```

## 3. Copia e incolla il codice di BreakCode.java dove vuoi nel tuo progetto
Io personalmente mi faccio un package ```tests``` nel progetto, ma puoi metterlo dove ti pare
![Immagine di esempio di dove metto BreakCode](https://ibb.co/sjG9qSc)

## 4. Avvialo e confronta i risultati, li trovi su outputBreakCode.txt
Ti consiglio di utilizzare [DiffChecker](https://www.diffchecker.com/)
### NOTA BENE: E' normale che tutto non coincida alla perfezione, prima di andare in panic mode pensando di aver sbagliato leggi il codice che ho scritto e controlla se l'output che hai te sia comunque logicamente corretto, quello è l'importante!