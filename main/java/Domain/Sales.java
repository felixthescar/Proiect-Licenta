package Domain;

public class Sales {
    int id;
    int idPrd;
    String user;
    String adresa;
    String dataC;
    String dataL;
    int status;
    int cant;
    int pret;

    public Sales() {

    }

    public Sales(int id, int idPrd, String user, String adresa, String dataC, String dataL, int status, int cant, int pret) {
        this.id = id;
        this.idPrd = idPrd;
        this.user = user;
        this.adresa = adresa;
        this.dataC = dataC;
        this.dataL = dataL;
        this.status = status;
        this.cant = cant;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProd() {
        return idPrd;
    }

    public void setIdProd(int idPrd) {
        this.idPrd = idPrd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adr) {
        this.adresa = adr;
    }

    public String getDataC() {
        return dataC;
    }

    public void setDataC(String dataC) {
        this.dataC = dataC;
    }

    public String getDataL() {
        return dataL;
    }

    public void setDataL(String dataL) {
        this.dataL = dataL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCantitate() {
        return cant;
    }

    public void setCantitate(int cant) {
        this.cant = cant;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", id_produs=" + idPrd + ","
        		+ " user=" + user + ", adresa=" + adresa +  " data_comandare="
                 + dataC + " data_livrare" + dataL + " status_comanda" + status
                 + " cantitate" + cant + " pret" + pret +'}';
    }
}
