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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar Joao
 */
@Entity
@Table(name = "condominos")
@NamedQueries({@NamedQuery(name = "Condominos.findAll", query = "SELECT c FROM Condominos c"), @NamedQuery(name = "Condominos.findByCondId", query = "SELECT c FROM Condominos c WHERE c.condId = :condId"), @NamedQuery(name = "Condominos.findByCondNumCasa", query = "SELECT c FROM Condominos c WHERE c.condNumCasa = :condNumCasa")})
public class Condominos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COND_ID")
    private Integer condId;
    @Column(name = "COND_NUM_CASA")
    private Integer condNumCasa;
    @OneToMany(mappedBy = "condId")
    private List<Vehiculos> vehiculosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condId")
    private List<Ingresos> ingresosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condId")
    private List<Terraza> terrazaCollection;
    @JoinColumn(name = "CON_ID", referencedColumnName = "CON_ID")
    @ManyToOne(optional = false)
    private Condominio conId;
    @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
    @ManyToOne(optional = false)
    private Usuario usrId;
    @JoinColumn(name = "TC_ID", referencedColumnName = "TC_ID")
    @ManyToOne(optional = false)
    private TipoCondomino tcId;

    public Condominos() {
    }

    public Condominos(Integer condId) {
        this.condId = condId;
    }

    public Integer getCondId() {
        return condId;
    }

    public void setCondId(Integer condId) {
        this.condId = condId;
    }

    public Integer getCondNumCasa() {
        return condNumCasa;
    }

    public void setCondNumCasa(Integer condNumCasa) {
        this.condNumCasa = condNumCasa;
    }

    public List<Vehiculos> getVehiculosCollection() {
        return vehiculosCollection;
    }

    public void setVehiculosCollection(List<Vehiculos> vehiculosCollection) {
        this.vehiculosCollection = vehiculosCollection;
    }

    public List<Ingresos> getIngresosCollection() {
        return ingresosCollection;
    }

    public void setIngresosCollection(List<Ingresos> ingresosCollection) {
        this.ingresosCollection = ingresosCollection;
    }

    public List<Terraza> getTerrazaCollection() {
        return terrazaCollection;
    }

    public void setTerrazaCollection(List<Terraza> terrazaCollection) {
        this.terrazaCollection = terrazaCollection;
    }

    public Condominio getConId() {
        return conId;
    }

    public void setConId(Condominio conId) {
        this.conId = conId;
    }

    public Usuario getUsrId() {
        return usrId;
    }

    public void setUsrId(Usuario usrId) {
        this.usrId = usrId;
    }

    public TipoCondomino getTcId() {
        return tcId;
    }

    public void setTcId(TipoCondomino tcId) {
        this.tcId = tcId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (condId != null ? condId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condominos)) {
            return false;
        }
        Condominos other = (Condominos) object;
        if ((this.condId == null && other.condId != null) || (this.condId != null && !this.condId.equals(other.condId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Condominos[condId=" + condId + "]";
    }

}
