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
@Table(name = "catalogo_egresos")
@NamedQueries({@NamedQuery(name = "CatalogoEgresos.findAll", query = "SELECT c FROM CatalogoEgresos c"), @NamedQuery(name = "CatalogoEgresos.findByCateId", query = "SELECT c FROM CatalogoEgresos c WHERE c.cateId = :cateId"), @NamedQuery(name = "CatalogoEgresos.findByCateDescripcion", query = "SELECT c FROM CatalogoEgresos c WHERE c.cateDescripcion = :cateDescripcion")})
public class CatalogoEgresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CATE_ID")
    private Integer cateId;
    @Column(name = "CATE_DESCRIPCION")
    private String cateDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cateId")
    private List<Egresos> egresosCollection;

    public CatalogoEgresos() {
    }

    public CatalogoEgresos(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateDescripcion() {
        return cateDescripcion;
    }

    public void setCateDescripcion(String cateDescripcion) {
        this.cateDescripcion = cateDescripcion;
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
        hash += (cateId != null ? cateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoEgresos)) {
            return false;
        }
        CatalogoEgresos other = (CatalogoEgresos) object;
        if ((this.cateId == null && other.cateId != null) || (this.cateId != null && !this.cateId.equals(other.cateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.CatalogoEgresos[cateId=" + cateId + "]";
    }

}
