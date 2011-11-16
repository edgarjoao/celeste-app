/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.condominium.db.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edgar Joao
 */
@Entity
@Table(name = "vehiculos")
@NamedQueries({@NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v"), @NamedQuery(name = "Vehiculos.findByVeId", query = "SELECT v FROM Vehiculos v WHERE v.veId = :veId"), @NamedQuery(name = "Vehiculos.findByVeDescripcion", query = "SELECT v FROM Vehiculos v WHERE v.veDescripcion = :veDescripcion"), @NamedQuery(name = "Vehiculos.findByVeModelo", query = "SELECT v FROM Vehiculos v WHERE v.veModelo = :veModelo"), @NamedQuery(name = "Vehiculos.findByVePlacas", query = "SELECT v FROM Vehiculos v WHERE v.vePlacas = :vePlacas")})
public class Vehiculos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VE_ID")
    private Integer veId;
    @Column(name = "VE_DESCRIPCION")
    private String veDescripcion;
    @Column(name = "VE_MODELO")
    private String veModelo;
    @Column(name = "VE_PLACAS")
    private String vePlacas;
    @JoinColumn(name = "COND_ID", referencedColumnName = "COND_ID")
    @ManyToOne
    private Condominos condId;

    public Vehiculos() {
    }

    public Vehiculos(Integer veId) {
        this.veId = veId;
    }

    public Integer getVeId() {
        return veId;
    }

    public void setVeId(Integer veId) {
        this.veId = veId;
    }

    public String getVeDescripcion() {
        return veDescripcion;
    }

    public void setVeDescripcion(String veDescripcion) {
        this.veDescripcion = veDescripcion;
    }

    public String getVeModelo() {
        return veModelo;
    }

    public void setVeModelo(String veModelo) {
        this.veModelo = veModelo;
    }

    public String getVePlacas() {
        return vePlacas;
    }

    public void setVePlacas(String vePlacas) {
        this.vePlacas = vePlacas;
    }

    public Condominos getCondId() {
        return condId;
    }

    public void setCondId(Condominos condId) {
        this.condId = condId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veId != null ? veId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.veId == null && other.veId != null) || (this.veId != null && !this.veId.equals(other.veId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Vehiculos[veId=" + veId + "]";
    }

}
