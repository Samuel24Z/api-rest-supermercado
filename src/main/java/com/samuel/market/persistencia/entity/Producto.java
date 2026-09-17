package com.samuel.market.persistencia.entity;

import javax.persistence.*;

/*
@Entity: Esta anotación le hará entender a Java que esta clase se comportará como una clase que mapea una
tabla de la base de datos, colocar esta anotación es muy importante porque define el resto de la arquitectura
de nuestro modelo.

Debido a que colocamos un nombre para esta clase y que es diferente al nombre de la tabla, debemos añadir la
anotación @Table y colocarle el elemento de anotación name, dentro de este elemento tenemos que poner el nombre
de la tabla, es decir, productos, de esta manera Java entenderá que cuando estemos hablando de la clase Producto,
hacemos referencia a la tabla productos.

Un elemento de anotación luce como un método de una clase, en estos podemos definir valores predeterminados
que son opcionales. Para saber más sobre este tema ver:
https://docs.oracle.com/javase/tutorial/java/annotations/declaring.html
*/
@Entity
@Table(name = "productos")
public class Producto {
    /*
    Para los atributos de una clase Entity, es importante usar la clase contenedora para cada uno de los tipos
    primitivos que vamos a ocupar: Integer, Character, Float, Double, etc.

    También, es buena práctica no usar separadores como por ejemplo Integer id_producto; en su lugar, es mejor
    usar Camel case: Integer idProducto.
    Debido a que es muy recomendable usar Camel case, no siempre estaremos usando el mismo nombre de la columna
    en la tabla de la base de datos y en el atributo de la clase, entonces debemos usar la anotación @Column
    y al interior de esta anotación vamos a definir el nombre que verdaderamente tiene esa columna
    (por ejemplo: id_producto). Siempre que una columna de una tabla se llame diferente al atributo de una
    clase, debemos usar la anotación @Column

    Cuando definimos el atributo de clase que representa la llave primaria debemos usar la anotación @Id.

    Debido a que la llave primaria se va a generar automaticmente cuando se inserte un nuevo producto en la
    base de datos, entonces debemos colocar la anotación @GeneratedValue, al interior de esta anotación
    colocamos el elemento de anotación strategy y su valor será GenerationType.IDENTITY. Elegimos este valor
    para strategy debido a que esta anotación será asociada al atributo de la llave primaria (idProducto) y
    con este atributo de clase representamos la identidad de los registros al interior de la tabla.
    A causa de que los id de una tabla se generan automáticamente, la anotación GeneratedValue también nos
    permitirá que Java genere esos valores de manera automática.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    /*
    En esta sección de la clase vamos a agregar otro atributo, este será de tipo Categoria.

    Además, debemos colocar la anotación @ManyToOne (muchos a uno, varios productos pertenecen a una categoría)
    y la anotación @JoinColumn, esta última anotación es usada debido a que la tabla productos contiene la
    columna id_categoria y por medio de ella relacionamos esta tabla con la tabla categorías, por tanto, dentro
    de JoinColumn establecemos el elemento de anotación name con un valor de 'id_categoria'. También debemos
    agregar más elementos de anotación a @JoinColumn, estos son insertable = false y updatable = false,
    estos atributos significan que a través de esta relación que estamos creando, no vamos a insertar ni
    actualizar una nueva categoría, para que podamos hacer estas operaciones, debemos realizarlo directamente
    en el Entity Categoria, por tanto, esta relación que estamos estableciendo solo nos servirá para recuperar
    la categoría a la que pertenece un producto.
    */
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
