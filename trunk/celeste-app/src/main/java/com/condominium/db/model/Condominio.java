/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.condominium.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar Joao
 */
@Entity
@Table(name = "condominio")
@NamedQueries({@NamedQuery(name = "Condominio.findAll", query = "SELECT c FROM Condominio c"), @NamedQuery(name = "Condominio.findByConId", query = "SELECT c FROM Condominio c WHERE c.conId = :conId"), @NamedQuery(name = "Condominio.findByConNombre", query = "SELECT c FROM Condominio c WHERE c.conNombre = :conNombre"), @NamedQuery(name = "Condominio.findByConDomicilio", query = "SELECT c FROM Condominio c WHERE c.conDomicilio = :conDomicilio"), @NamedQuery(name = "Condominio.findByConTelCasa", query = "SELECT c FROM Condominio c WHERE c.conTelCasa = :conTelCasa"), @NamedQuery(name = "Condominio.findByConTelCelular", query = "SELECT c FROM Condominio c WHERE c.conTelCelular = :conTelCelular"), @NamedQuery(name = "Condominio.findByConEmail", query = "SELECT c FROM Condominio c WHERE c.conEmail = :conEmail")})
public class Condominio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CON_ID")
    private Integer conId;
    @Column(name = "CON_NOMBRE")
    private String conNombre;
    @Column(name = "CON_DOMICILIO")
    private String conDomicilio;
    @Column(name = "CON_TEL_CASA")
    private Integer conTelCasa;
    @Column(name = "CON_TEL_CELULAR")
    private Integer conTelCelular;
    @Column(name = "CON_EMAIL")
    private String conEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conId")
    private List<Condominos> condominosCollection;

    public Condominio() {
    }

    public Condominio(Integer conId) {
        this.conId = conId;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getConNombre() {
        return conNombre;
    }

    public void setConNombre(String conNombre) {
        this.conNombre = conNombre;
    }

    public String getConDomicilio() {
        return conDomicilio;
    }

    public void setConDomicilio(String conDomicilio) {
        this.conDomicilio = conDomicilio;
    }

    public Integer getConTelCasa() {
        return conTelCasa;
    }

    public void setConTelCasa(Integer conTelCasa) {
        this.conTelCasa = conTelCasa;
    }

    public Integer getConTelCelular() {
        return conTelCelular;
    }

    public void setConTelCelular(Integer conTelCelular) {
        this.conTelCelular = conTelCelular;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public List<Condominos> getCondominosCollection() {
        return condominosCollection;
    }

    public void setCondominosCollection(List<Condominos> condominosCollection) {
        this.condominosCollection = condominosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conId != null ? conId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condominio)) {
            return false;
        }
        Condominio other = (Condominio) object;
        if ((this.conId == null && other.conId != null) || (this.conId != null && !this.conId.equals(other.conId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Condominio[conId=" + conId + "]";
    }

}
