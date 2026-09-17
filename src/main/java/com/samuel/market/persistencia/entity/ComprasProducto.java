package com.samuel.market.persistencia.entity;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
    /*
    Usamos la anotación @EmbeddedId cuando la clave primaria es compuesta, como en el caso de esta clase y
    de esta aplicación.
    */
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    @ManyToOne
    @MapsId("idCompra")
    /*
    La anotación @MapsId debe incluir el nombre del atributo dentro de una llave compuesta que corresponde a la relación
    ManyToOne que está colocada justo aqui arriba, en este caso el atributo dentro de la llave compuesta que corresponde
    a esta relación es el que tiene por nombre idCompra.
    Con lo anterior, esta clase puede saber la clave primaria a la que pertenece cada uno de los productos que están en
    una compra y con ello guardar datos (en cascada) en la tabla a la que mapea esta clase.
    */
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
