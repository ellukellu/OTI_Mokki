/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elina
 */
class lasku {

    private int lasku_id, varaus_id, summa, alv;

    public lasku (int lasku_id, int varaus_id, int summa, int alv) {
        this.lasku_id = lasku_id;
        this.varaus_id = summa;
        this.alv = alv;

    }

    public int getLasku_id() {
        return lasku_id;
    }

    public int getVaraus_id() {
        return varaus_id;
    }

    public int getSumma() {
        return summa;
    }

    public int getAlv() {
        return alv;
    }
}
