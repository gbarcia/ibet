/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.dominio.forms;

import com.pd.dominio.Estado;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nath
 */
public class ClienteForm implements Serializable {
    private String rif;
    private String nombre;
    private String estados;
    private Estado estado;
    private String typeImg;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
  

    public String getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }

    public ClienteForm() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }
}
