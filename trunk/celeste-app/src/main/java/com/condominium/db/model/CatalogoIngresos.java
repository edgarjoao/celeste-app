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
@Table(name = "catalogo_ingresos")
@NamedQueries({@NamedQuery(name = "CatalogoIngresos.findAll", query = "SELECT c FROM CatalogoIngresos c"), @NamedQuery(name = "CatalogoIngresos.findByCatiId", query = "SELECT c FROM CatalogoIngresos c WHERE c.catiId = :catiId"), @NamedQuery(name = "CatalogoIngresos.findByCatiDescripcion", query = "SELECT c FROM CatalogoIngresos c WHERE c.catiDescripcion = :catiDescripcion")})
public class CatalogoIngresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CATI_ID")
    private Integer catiId;
    @Column(name = "CATI_DESCRIPCION")
    private String catiDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catiId")
    private List<Ingresos> ingresosCollection;

    public CatalogoIngresos() {
    }

    public CatalogoIngresos(Integer catiId) {
        this.catiId = catiId;
    }

    public Integer getCatiId() {
        return catiId;
    }

    public void setCatiId(Integer catiId) {
        this.catiId = catiId;
    }

    public String getCatiDescripcion() {
        return catiDescripcion;
    }

    public void setCatiDescripcion(String catiDescripcion) {
        this.catiDescripcion = catiDescripcion;
    }

    public List<Ingresos> getIngresosCollection() {
        return ingresosCollection;
    }

    public void setIngresosCollection(List<Ingresos> ingresosCollection) {
        this.ingresosCollection = ingresosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catiId != null ? catiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoIngresos)) {
            return false;
        }
        CatalogoIngresos other = (CatalogoIngresos) object;
        if ((this.catiId == null && other.catiId != null) || (this.catiId != null && !this.catiId.equals(other.catiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.CatalogoIngresos[catiId=" + catiId + "]";
    }

}
