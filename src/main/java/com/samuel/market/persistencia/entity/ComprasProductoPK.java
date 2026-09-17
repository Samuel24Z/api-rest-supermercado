package com.samuel.market.persistencia.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/*
En esta clase no vamos a colocar la anotación @Entity porque esta clase no va a mapear ninguna tabla
de la base de datos.
La única anotación que vamos a poner es @Embeddable, esto a causa de que vamos a embeber (incorporar) esta
clase dentro de ComprasProducto.
*/
@Embeddable
public class ComprasProductoPK implements Serializable {
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
