/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.condominium.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edgar Joao
 */
@Entity
@Table(name = "terraza")
@NamedQueries({@NamedQuery(name = "Terraza.findAll", query = "SELECT t FROM Terraza t"), @NamedQuery(name = "Terraza.findByTerId", query = "SELECT t FROM Terraza t WHERE t.terId = :terId"), @NamedQuery(name = "Terraza.findByTerDescripcion", query = "SELECT t FROM Terraza t WHERE t.terDescripcion = :terDescripcion"), @NamedQuery(name = "Terraza.findByTerFecha", query = "SELECT t FROM Terraza t WHERE t.terFecha = :terFecha"), @NamedQuery(name = "Terraza.findByTerStatus", query = "SELECT t FROM Terraza t WHERE t.terStatus = :terStatus")})
public class Terraza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TER_ID")
    private Integer terId;
    @Column(name = "TER_DESCRIPCION")
    private String terDescripcion;
    @Column(name = "TER_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date terFecha;
    @Column(name = "TER_STATUS")
    private String terStatus;
    @JoinColumn(name = "COND_ID", referencedColumnName = "COND_ID")
    @ManyToOne(optional = false)
    private Condominos condId;

    public Terraza() {
    }

    public Terraza(Integer terId) {
        this.terId = terId;
    }

    public Integer getTerId() {
        return terId;
    }

    public void setTerId(Integer terId) {
        this.terId = terId;
    }

    public String getTerDescripcion() {
        return terDescripcion;
    }

    public void setTerDescripcion(String terDescripcion) {
        this.terDescripcion = terDescripcion;
    }

    public Date getTerFecha() {
        return terFecha;
    }

    public void setTerFecha(Date terFecha) {
        this.terFecha = terFecha;
    }

    public String getTerStatus() {
        return terStatus;
    }

    public void setTerStatus(String terStatus) {
        this.terStatus = terStatus;
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
        hash += (terId != null ? terId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terraza)) {
            return false;
        }
        Terraza other = (Terraza) object;
        if ((this.terId == null && other.terId != null) || (this.terId != null && !this.terId.equals(other.terId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Terraza[terId=" + terId + "]";
    }

}
