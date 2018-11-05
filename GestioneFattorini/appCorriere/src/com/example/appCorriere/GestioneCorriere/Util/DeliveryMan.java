package com.example.appCorriere.GestioneCorriere.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creato da Andrea Delmastro, Alessio Franco. <br><br>
 * Classe rapprensentante un fattorino.
 * Il fattorino corrisponde alla rappresentazione nel DB.
 */
public class DeliveryMan {
    private String codiceFattorino = null;
    private String nome = null;
    private String cognome = null;
    private String paese = null;
    private String CAP = null;

    public DeliveryMan() {}

    public DeliveryMan(String codiceFattorino, String nome, String cognome, String paese, String CAP) {
        this.codiceFattorino = codiceFattorino;
        this.nome = nome;
        this.cognome = cognome;
        this.paese = paese;
        this.CAP = CAP;
    }

    public DeliveryMan(ResultSet deliveryManRecord) throws SQLException {
        this(deliveryManRecord.getString("codiceFattorino"),
             deliveryManRecord.getString("cognome"),
             deliveryManRecord.getString("nome"),
             deliveryManRecord.getString("paese"),
             deliveryManRecord.getString("CAP"));
    }

    public String getCodiceFattorino() {
        return codiceFattorino;
    }

    public void setCodiceFattorino(String codiceFattorino) {
        this.codiceFattorino = codiceFattorino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }
}
