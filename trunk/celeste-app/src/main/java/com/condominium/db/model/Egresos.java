/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.condominium.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "egresos")
@NamedQueries({@NamedQuery(name = "Egresos.findAll", query = "SELECT e FROM Egresos e"), @NamedQuery(name = "Egresos.findByEgrId", query = "SELECT e FROM Egresos e WHERE e.egrId = :egrId"), @NamedQuery(name = "Egresos.findByEgrFechaEgreso", query = "SELECT e FROM Egresos e WHERE e.egrFechaEgreso = :egrFechaEgreso"), @NamedQuery(name = "Egresos.findByEgrImporte", query = "SELECT e FROM Egresos e WHERE e.egrImporte = :egrImporte"), @NamedQuery(name = "Egresos.findByEgrComentarios", query = "SELECT e FROM Egresos e WHERE e.egrComentarios = :egrComentarios")})
public class Egresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EGR_ID")
    private Integer egrId;
    @Column(name = "EGR_FECHA_EGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date egrFechaEgreso;
    @Column(name = "EGR_IMPORTE")
    private BigDecimal egrImporte;
    @Column(name = "EGR_COMENTARIOS")
    private String egrComentarios;
    @JoinColumn(name = "CATE_ID", referencedColumnName = "CATE_ID")
    @ManyToOne(optional = false)
    private CatalogoEgresos cateId;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
    @ManyToOne
    private Proveedores provId;

    public Egresos() {
    }

    public Egresos(Integer egrId) {
        this.egrId = egrId;
    }

    public Integer getEgrId() {
        return egrId;
    }

    public void setEgrId(Integer egrId) {
        this.egrId = egrId;
    }

    public Date getEgrFechaEgreso() {
        return egrFechaEgreso;
    }

    public void setEgrFechaEgreso(Date egrFechaEgreso) {
        this.egrFechaEgreso = egrFechaEgreso;
    }

    public BigDecimal getEgrImporte() {
        return egrImporte;
    }

    public void setEgrImporte(BigDecimal egrImporte) {
        this.egrImporte = egrImporte;
    }

    public String getEgrComentarios() {
        return egrComentarios;
    }

    public void setEgrComentarios(String egrComentarios) {
        this.egrComentarios = egrComentarios;
    }

    public CatalogoEgresos getCateId() {
        return cateId;
    }

    public void setCateId(CatalogoEgresos cateId) {
        this.cateId = cateId;
    }

    public Proveedores getProvId() {
        return provId;
    }

    public void setProvId(Proveedores provId) {
        this.provId = provId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egrId != null ? egrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egresos)) {
            return false;
        }
        Egresos other = (Egresos) object;
        if ((this.egrId == null && other.egrId != null) || (this.egrId != null && !this.egrId.equals(other.egrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Egresos[egrId=" + egrId + "]";
    }

}
