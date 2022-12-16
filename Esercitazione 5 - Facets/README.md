# :rocket: Come usare BreakCode

| :exclamation: :exclamation: :exclamation: MOLTO IMPORTANTE, IGNORA A TUO RISCHIO E PERICOLO :exclamation: :exclamation: :exclamation: |
|-|
|<p aign="center">PRIMA DI CONSEGNARE RIMUOVI ```BREAKCODE```, IL ```FILE TXT``` E I METODI ```toString()``` O QUALSIASI ALTRA COSA AGGIUNTIVA CHE TI HO CHIESTO DI CREARE/MODIFICARE/TOGLIERE/DEFINIRE QUI SOTTO! </p>|
|<p aign="center">SE PER UN QUALCHE MOTIVO NON COMPILA E TI BLOCCA TUTTI I TEST DEL CODICE NON VOGLIO SENTIRE CAZZI, LA RESPONSABILITA' E' SOLO TUA :innocent: </p>|
|<p aign="center">Un ottimo modo per ovviare a questo problema é quello di esportare un progetto "di backup" e tenerlo da parte, prima di apporate queste modifiche, cosí alle brutte ricaricate quello e siete sicur* di non avere problemi</p>|

***

Implementare BreakCode è facilissimo, basta scrivere qualche metodo toString (che di sicuro la maggior parte di voi avrà già fatto) e copia incollare il codice del file java che trovate in questa repo, tempo richiesto neanche 60 secondi :ok_woman:

> :bulb: Riscrivi i metodi in modo che coincidano con gli attributi delle classi del tuo programma

## 1. Definisci i metodi toString() per le classi Iscritto e Immagine (se non lo hai già fatto) 

Se lo hai già fatto salta questo passo e vai direttamente a quello sotto.
Se invece non lo hai ancora fatto copia e incolla i codici che trovi qui sotto _riaddattandoli alle tue classi_, eventualmente eliminali dopo.

Per la classe ```Iscritto```

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

> ***Note***
> In realtà non è obbligatorio definire questi metodi toString(), se non li definisci ```BreakCode``` stamperà semplicemente i riferimenti di memoria degli oggetti, tuttavia è caldamente consigliato per avere un output che coincida con quello che ti ho lasciato qui.

## 3. Scarica i file di input per testare la parte 4 (lettura da file di testo)

Il file da scaricare é ```inputBreakCode.txt``` che **da quanto mi ha detto un ragazzo in classe che ha chiesto a Garcea** dovrebbe coprire tutti gli errori che bastano per i test (ovvero la gestione delle eccezioni ```FileNotFoundException```, ```IOException``` (checked exception obbligatiori per utilizzare gli stream di input/output dei file in java) e ```NumberFormatException``` (unchecked exception per gestire le eccezzioni lanciate dai metodi ```Double.parseDouble()``` e ```Integer.parseInt()```)

> ***Note***
> Magari il piú di voi non ha scritto le specifiche eccezioni che ho elencato ma ha usato la generica ```Exception```, va bene lo stesso non preoccupatevi

Per i piú temerari ho preparato anche un file che si spinge oltre (personalmente mi fido fino a un certo punto dei passa parola, quindi nel dubbio io ho usato questo), fornendo righe appositamente studiate per dare altri problemi di conversione, potete trovarlo sempre in questa cartella con il nome ```inputBreakCodeHard.txt```

Se volete usarlo *cambiate il parametro che passate in leggiDatiFacets con questo nuovo file* scorrendo in fondo al codice di BreakCode.java

> ***Warning***
> Notate che l'output di breakCode che fornisco in questa cartella é solo per l'input "normale" (ovvero inputBreakCode.txt), 
>
> Se vuoi assicurarti che anche con ```inputBreakCodeHard.txt``` funzioni aprilo direttamente e seguite il codice che ho scritto (oppure scrivetemi in priv)

## 4. Copia e incolla il codice di BreakCode.java dove vuoi nel tuo progetto

Io personalmente mi faccio un package ```tests``` nel progetto, così ho tutto da parte e non mi dimentico di toglierlo alla consegna, ma puoi metterlo dove ti pare

![Immagine di esempio di dove metto BreakCode](https://i.ibb.co/0qSyrN1/immagine.png)

## 5. Avvialo e confronta i risultati, li trovi su outputBreakCode.txt 

Ti consiglio di utilizzare [DiffChecker](https://www.diffchecker.com/) per confrontare i due file, molto comodo!

> ***Note***
> **E' normale che tutto non coincida alla perfezione**, prima di andare in panic mode pensando di aver sbagliato leggi il codice che ho scritto e controlla se l'output che hai te sia comunque logicamente corretto, quello è l'importante! Non tutti scriviamo il codice nella stessa identica maniera, e io pure posso aver sbagliato! (Nel caso scrivimi pure)
>
> Nota come ogni step sia appositamente commentato con dei print in console, leggili, ragionaci e riscrivili eventualmente! 
