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
@Table(name = "tipo_condomino")
@NamedQueries({@NamedQuery(name = "TipoCondomino.findAll", query = "SELECT t FROM TipoCondomino t"), @NamedQuery(name = "TipoCondomino.findByTcId", query = "SELECT t FROM TipoCondomino t WHERE t.tcId = :tcId"), @NamedQuery(name = "TipoCondomino.findByTcDescripcion", query = "SELECT t FROM TipoCondomino t WHERE t.tcDescripcion = :tcDescripcion")})
public class TipoCondomino implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TC_ID")
    private Integer tcId;
    @Column(name = "TC_DESCRIPCION")
    private String tcDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcId")
    private List<Condominos> condominosCollection;

    public TipoCondomino() {
    }

    public TipoCondomino(Integer tcId) {
        this.tcId = tcId;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public String getTcDescripcion() {
        return tcDescripcion;
    }

    public void setTcDescripcion(String tcDescripcion) {
        this.tcDescripcion = tcDescripcion;
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
        hash += (tcId != null ? tcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCondomino)) {
            return false;
        }
        TipoCondomino other = (TipoCondomino) object;
        if ((this.tcId == null && other.tcId != null) || (this.tcId != null && !this.tcId.equals(other.tcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.TipoCondomino[tcId=" + tcId + "]";
    }

}
