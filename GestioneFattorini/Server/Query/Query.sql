/*inserisce all'interno della tabella dei pacchi i valori che si vogliono che inserisco nel VALUE*/
INSERT INTO Pacchi (codicePacco, destinatario, edificio, indirizzo, CAP, paese, provincia, dataConsegna, consegnato, noteFattoruno)
VALUES ('014525897548', 'Gino Gino', 'Ed. 1 Int.2 Piano 4', 'Via Cuneo 10', '12016', 'Peveragno', 'Cueno', '', '0','');

/*creo una tabella che selezioni il campo CAP, il campo Paese e in campo codicePacco ordinandoli in maniera ascendente seguendo il CAP del paese*/
SELECT CAP, paese, codicePacco FROM Pacchi 
ORDER BY CAP ASC;

/*Query che salva tutti i dati che sono stati gia consegnati(tutti quelli che hanno valore 1 a consegnato)*/
SELECT dataConsegna, codicePacco FROM Pacchi
WHERE consegnato = 1
ORDER BY dataConsegna

/*NON ANCORA FUNZIONANTE, DA RICONTROLLARE*/
/*Query dinamica che chiede quale paese si vuole cercare e che controlla se in quel'paese è presente un pacco da spedire*/
SELECT codicePacco, indirizzo, destinatario FROM Pacchi
WHERE (paese=[Quale paese?])
ORDER BY codicePacco

/*Cancellazione della tabella*/
DELETE *
FROM Pacchi

/*Query che identifica e salva tutti i pacchi da consegnare in un condominio e non in un singolo edificio*/
SELECT codicePacco from Pacchi
WHERE edificio <> ''
ORDER BY codicePacco

/*Quary che conta quanti pacchi devono essere spediti in un determinato luogo avente lo stesso CAP*/
SELECT CAP, Count(*) AS numeroPacchi from Pacchi
GROUP BY CAP

/*Query completa ordinata però per Cap e Indirizzo*/
SELECT * FROM Pacchi
ORDER BY CAP, indirizzo

/*NON ANCORA FUNZIONANTE, DA RICONTROLLARE*/
/*query che controlla se nel database una sola persona ha eseguito più ordini*/
SELECT destinatario, Count(*) AS numeroVolte
GROUP BY destinatario
ORDER BY destinatario

/*query che controlla se il pacco che è stato consegnato o meno ha avuto dei problemi di consegna o dei cambi di programma, questi devono però essere segnalati nella colonna noteFattorino*/
SELECT codicePacco, consegnato, noteFattorino FROM Pacchi
WHERE noteFattorino <> ''
ORDER BY codicePacco

/**/