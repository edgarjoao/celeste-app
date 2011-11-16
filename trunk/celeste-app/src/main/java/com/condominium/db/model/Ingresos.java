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
@Table(name = "ingresos")
@NamedQueries({@NamedQuery(name = "Ingresos.findAll", query = "SELECT i FROM Ingresos i"), @NamedQuery(name = "Ingresos.findByIngId", query = "SELECT i FROM Ingresos i WHERE i.ingId = :ingId"), @NamedQuery(name = "Ingresos.findByIngFechaIngreso", query = "SELECT i FROM Ingresos i WHERE i.ingFechaIngreso = :ingFechaIngreso"), @NamedQuery(name = "Ingresos.findByIngImporte", query = "SELECT i FROM Ingresos i WHERE i.ingImporte = :ingImporte"), @NamedQuery(name = "Ingresos.findByIngDescuento", query = "SELECT i FROM Ingresos i WHERE i.ingDescuento = :ingDescuento"), @NamedQuery(name = "Ingresos.findByIngComentarios", query = "SELECT i FROM Ingresos i WHERE i.ingComentarios = :ingComentarios")})
public class Ingresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ING_ID")
    private Integer ingId;
    @Column(name = "ING_FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ingFechaIngreso;
    @Column(name = "ING_IMPORTE")
    private BigDecimal ingImporte;
    @Column(name = "ING_DESCUENTO")
    private BigDecimal ingDescuento;
    @Column(name = "ING_COMENTARIOS")
    private String ingComentarios;
    @JoinColumn(name = "CATI_ID", referencedColumnName = "CATI_ID")
    @ManyToOne(optional = false)
    private CatalogoIngresos catiId;
    @JoinColumn(name = "COND_ID", referencedColumnName = "COND_ID")
    @ManyToOne(optional = false)
    private Condominos condId;

    public Ingresos() {
    }

    public Ingresos(Integer ingId) {
        this.ingId = ingId;
    }

    public Integer getIngId() {
        return ingId;
    }

    public void setIngId(Integer ingId) {
        this.ingId = ingId;
    }

    public Date getIngFechaIngreso() {
        return ingFechaIngreso;
    }

    public void setIngFechaIngreso(Date ingFechaIngreso) {
        this.ingFechaIngreso = ingFechaIngreso;
    }

    public BigDecimal getIngImporte() {
        return ingImporte;
    }

    public void setIngImporte(BigDecimal ingImporte) {
        this.ingImporte = ingImporte;
    }

    public BigDecimal getIngDescuento() {
        return ingDescuento;
    }

    public void setIngDescuento(BigDecimal ingDescuento) {
        this.ingDescuento = ingDescuento;
    }

    public String getIngComentarios() {
        return ingComentarios;
    }

    public void setIngComentarios(String ingComentarios) {
        this.ingComentarios = ingComentarios;
    }

    public CatalogoIngresos getCatiId() {
        return catiId;
    }

    public void setCatiId(CatalogoIngresos catiId) {
        this.catiId = catiId;
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
        hash += (ingId != null ? ingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingresos)) {
            return false;
        }
        Ingresos other = (Ingresos) object;
        if ((this.ingId == null && other.ingId != null) || (this.ingId != null && !this.ingId.equals(other.ingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Ingresos[ingId=" + ingId + "]";
    }

}
