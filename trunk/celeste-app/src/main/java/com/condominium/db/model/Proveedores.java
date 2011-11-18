/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.condominium.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "proveedores")
@NamedQueries({@NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"), @NamedQuery(name = "Proveedores.findByProvId", query = "SELECT p FROM Proveedores p WHERE p.provId = :provId"), @NamedQuery(name = "Proveedores.findByProvNombre", query = "SELECT p FROM Proveedores p WHERE p.provNombre = :provNombre"), @NamedQuery(name = "Proveedores.findByProvDomicilio", query = "SELECT p FROM Proveedores p WHERE p.provDomicilio = :provDomicilio"), @NamedQuery(name = "Proveedores.findByProvTelCasa", query = "SELECT p FROM Proveedores p WHERE p.provTelCasa = :provTelCasa"), @NamedQuery(name = "Proveedores.findByProvTelCelular", query = "SELECT p FROM Proveedores p WHERE p.provTelCelular = :provTelCelular")})
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROV_ID")
    private Integer provId;
    @Column(name = "PROV_NOMBRE")
    private String provNombre;
    @Column(name = "PROV_DOMICILIO")
    private String provDomicilio;
    @Column(name = "PROV_TEL_CASA")
    private Integer provTelCasa;
    @Column(name = "PROV_TEL_CELULAR")
    private Integer provTelCelular;
    @OneToMany(mappedBy = "provId")
    private List<Egresos> egresosCollection;

    public Proveedores() {
    }

    public Proveedores(Integer provId) {
        this.provId = provId;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvDomicilio() {
        return provDomicilio;
    }

    public void setProvDomicilio(String provDomicilio) {
        this.provDomicilio = provDomicilio;
    }

    public Integer getProvTelCasa() {
        return provTelCasa;
    }

    public void setProvTelCasa(Integer provTelCasa) {
        this.provTelCasa = provTelCasa;
    }

    public Integer getProvTelCelular() {
        return provTelCelular;
    }

    public void setProvTelCelular(Integer provTelCelular) {
        this.provTelCelular = provTelCelular;
    }

    public List<Egresos> getEgresosCollection() {
        return egresosCollection;
    }

    public void setEgresosCollection(List<Egresos> egresosCollection) {
        this.egresosCollection = egresosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Proveedores[provId=" + provId + "]";
    }

}
