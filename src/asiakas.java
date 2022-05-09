/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elina
 */
class asiakas {

    private int asiakas_id;
    private String postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro;

    public asiakas(int asiakas_id, String postinro, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro) {
        this.asiakas_id = asiakas_id;
        this.postinro = postinro;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }

    public String getPostinro() {
        return postinro;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public String getEmail() {
        return email;
    }

    public String getPuhelinnro() {
        return puhelinnro;
    }

}

