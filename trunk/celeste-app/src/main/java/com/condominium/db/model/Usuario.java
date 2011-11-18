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
@Table(name = "usuario")
@NamedQueries({@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"), @NamedQuery(name = "Usuario.findByUsrId", query = "SELECT u FROM Usuario u WHERE u.usrId = :usrId"), @NamedQuery(name = "Usuario.findByUsrUsername", query = "SELECT u FROM Usuario u WHERE u.usrUsername = :usrUsername"), @NamedQuery(name = "Usuario.findByUsrPassword", query = "SELECT u FROM Usuario u WHERE u.usrPassword = :usrPassword"), @NamedQuery(name = "Usuario.findByUsrNombre", query = "SELECT u FROM Usuario u WHERE u.usrNombre = :usrNombre"), @NamedQuery(name = "Usuario.findByUsrApaterno", query = "SELECT u FROM Usuario u WHERE u.usrApaterno = :usrApaterno"), @NamedQuery(name = "Usuario.findByUsrAmaterno", query = "SELECT u FROM Usuario u WHERE u.usrAmaterno = :usrAmaterno"), @NamedQuery(name = "Usuario.findByUsrTelCasa", query = "SELECT u FROM Usuario u WHERE u.usrTelCasa = :usrTelCasa"), @NamedQuery(name = "Usuario.findByUsrTelCelular", query = "SELECT u FROM Usuario u WHERE u.usrTelCelular = :usrTelCelular"), @NamedQuery(name = "Usuario.findByUsrEmail", query = "SELECT u FROM Usuario u WHERE u.usrEmail = :usrEmail")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USR_ID")
    private Integer usrId;
    @Column(name = "USR_USERNAME")
    private String usrUsername;
    @Column(name = "USR_PASSWORD")
    private String usrPassword;
    @Column(name = "USR_NOMBRE")
    private String usrNombre;
    @Column(name = "USR_APATERNO")
    private String usrApaterno;
    @Column(name = "USR_AMATERNO")
    private String usrAmaterno;
    @Column(name = "USR_TEL_CASA")
    private Integer usrTelCasa;
    @Column(name = "USR_TEL_CELULAR")
    private Integer usrTelCelular;
    @Column(name = "USR_EMAIL")
    private String usrEmail;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne(optional = false)
    private Rol rolId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId")
    private List<Condominos> condominosCollection;

    public Usuario() {
    }

    public Usuario(Integer usrId) {
        this.usrId = usrId;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrUsername() {
        return usrUsername;
    }

    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrApaterno() {
        return usrApaterno;
    }

    public void setUsrApaterno(String usrApaterno) {
        this.usrApaterno = usrApaterno;
    }

    public String getUsrAmaterno() {
        return usrAmaterno;
    }

    public void setUsrAmaterno(String usrAmaterno) {
        this.usrAmaterno = usrAmaterno;
    }

    public Integer getUsrTelCasa() {
        return usrTelCasa;
    }

    public void setUsrTelCasa(Integer usrTelCasa) {
        this.usrTelCasa = usrTelCasa;
    }

    public Integer getUsrTelCelular() {
        return usrTelCelular;
    }

    public void setUsrTelCelular(Integer usrTelCelular) {
        this.usrTelCelular = usrTelCelular;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
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
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.condominium.model.Usuario[usrId=" + usrId + "]";
    }

}
