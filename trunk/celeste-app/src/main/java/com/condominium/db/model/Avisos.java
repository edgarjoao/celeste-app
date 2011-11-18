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
@Table(name = "avisos")
@NamedQueries({@NamedQuery(name = "Avisos.findAll", query = "SELECT a FROM Avisos a"), @NamedQuery(name = "Avisos.findByAvId", query = "SELECT a FROM Avisos a WHERE a.avId = :avId"), @NamedQuery(name = "Avisos.findByAvFecha", query = "SELECT a FROM Avisos a WHERE a.avFecha = :avFecha"), @NamedQuery(name = "Avisos.findByAvDetalle", query = "SELECT a FROM Avisos a WHERE a.avDetalle = :avDetalle")})
public class Avisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AV_ID")
    private Integer avId;
    @Column(name = "AV_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date avFecha;
    @Column(name = "AV_DETALLE")
    private String avDetalle;
    @JoinColumn(name = "TA_ID", referencedColumnName = "TA_ID")
    @ManyToOne(optional = false)
    private TipoAviso taId;

    public Avisos() {
    }

    public Avisos(Integer avId) {
        this.avId = avId;
    }

    public Integer getAvId() {
        return avId;
    }

    public void setAvId(Integer avId) {
        this.avId = avId;
    }

    public Date getAvFecha() {
        return avFecha;
    }

    public void setAvFecha(Date avFecha) {
        this.avFecha = avFecha;
    }

    public String getAvDetalle() {
        return avDetalle;
    }

    public void setAvDetalle(String avDetalle) {
        this.avDetalle = avDetalle;
    }

    public TipoAviso getTaId() {
        return taId;
    }

    public void setTaId(TipoAviso taId) {
        this.taId = taId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avId != null ? avId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avisos)) {
            return false;
        }
        Avisos other = (Avisos) object;
        if ((this.avId == null && other.avId != null) || (this.avId != null && !this.avId.equals(other.avId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Avisos[avId=" + avId + "]";
    }

}
