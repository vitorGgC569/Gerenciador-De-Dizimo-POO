package br.edu.ifgoiano.jogo.entidades;

import java.util.Date;

public class Fiel extends Usuario{
    private Date dataBatismo;
    private boolean isAdmin;

    public Date getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}