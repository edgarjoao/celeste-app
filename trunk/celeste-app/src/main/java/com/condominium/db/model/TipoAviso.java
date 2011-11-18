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
@Table(name = "tipo_aviso")
@NamedQueries({@NamedQuery(name = "TipoAviso.findAll", query = "SELECT t FROM TipoAviso t"), @NamedQuery(name = "TipoAviso.findByTaId", query = "SELECT t FROM TipoAviso t WHERE t.taId = :taId"), @NamedQuery(name = "TipoAviso.findByTaTipoAviso", query = "SELECT t FROM TipoAviso t WHERE t.taTipoAviso = :taTipoAviso")})
public class TipoAviso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TA_ID")
    private Integer taId;
    @Column(name = "TA_TIPO_AVISO")
    private String taTipoAviso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taId")
    private List<Avisos> avisosCollection;

    public TipoAviso() {
    }

    public TipoAviso(Integer taId) {
        this.taId = taId;
    }

    public Integer getTaId() {
        return taId;
    }

    public void setTaId(Integer taId) {
        this.taId = taId;
    }

    public String getTaTipoAviso() {
        return taTipoAviso;
    }

    public void setTaTipoAviso(String taTipoAviso) {
        this.taTipoAviso = taTipoAviso;
    }

    public List<Avisos> getAvisosCollection() {
        return avisosCollection;
    }

    public void setAvisosCollection(List<Avisos> avisosCollection) {
        this.avisosCollection = avisosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taId != null ? taId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAviso)) {
            return false;
        }
        TipoAviso other = (TipoAviso) object;
        if ((this.taId == null && other.taId != null) || (this.taId != null && !this.taId.equals(other.taId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.TipoAviso[taId=" + taId + "]";
    }

}
