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
@Table(name = "rol")
@NamedQueries({@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"), @NamedQuery(name = "Rol.findByRolId", query = "SELECT r FROM Rol r WHERE r.rolId = :rolId"), @NamedQuery(name = "Rol.findByRolDescripcion", query = "SELECT r FROM Rol r WHERE r.rolDescripcion = :rolDescripcion")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROL_ID")
    private Integer rolId;
    @Column(name = "ROL_DESCRIPCION")
    private String rolDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private List<Usuario> usuarioCollection;

    public Rol() {
    }

    public Rol(Integer rolId) {
        this.rolId = rolId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    public List<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(List<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Rol[rolId=" + rolId + "]";
    }

}
