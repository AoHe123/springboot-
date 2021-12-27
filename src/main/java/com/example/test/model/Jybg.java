package com.example.test.model;



import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;

import java.io.Serializable;


@Entity(name = "yywd_jljybg")
@Data
public class Jybg{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "drug_id")
    private String drugID;

    private String pihao;

    private String url;

    private String type;

    private String image;

    private String flag;

    @Column(name = "newdrug_id")
    private String nwedrugId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrugID() {
        return drugID;
    }

    public void setDrugID(String drugID) {
        this.drugID = drugID;
    }

    public String getPihao() {
        return pihao;
    }

    public void setPihao(String pihao) {
        this.pihao = pihao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNwedrugId() {
        return nwedrugId;
    }

    public void setNwedrugId(String nwedrugId) {
        this.nwedrugId = nwedrugId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jybg jybg = (Jybg) o;

        if (id != jybg.id) return false;
        if (drugID != null ? !drugID.equals(jybg.drugID) : jybg.drugID != null) return false;
        if (pihao != null ? !pihao.equals(jybg.pihao) : jybg.pihao != null) return false;
        if (url != null ? !url.equals(jybg.url) : jybg.url != null) return false;
        if (type != null ? !type.equals(jybg.type) : jybg.type != null) return false;
        if (image != null ? !image.equals(jybg.image) : jybg.image != null) return false;
        if (flag != null ? !flag.equals(jybg.flag) : jybg.flag != null) return false;
        return nwedrugId != null ? nwedrugId.equals(jybg.nwedrugId) : jybg.nwedrugId == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (drugID != null ? drugID.hashCode() : 0);
        result = 31 * result + (pihao != null ? pihao.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (nwedrugId != null ? nwedrugId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Jybg{" +
                "id=" + id +
                ", drugID='" + drugID + '\'' +
                ", pihao='" + pihao + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", flag='" + flag + '\'' +
                ", nwedrugId='" + nwedrugId + '\'' +
                '}';
    }
}
