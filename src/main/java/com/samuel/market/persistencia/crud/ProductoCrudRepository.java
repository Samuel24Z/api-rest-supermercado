package com.samuel.market.persistencia.crud;

import com.samuel.market.persistencia.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/*
Esta interface va a hererdar de CrudRepository, esta interface CrudRepository va a recibir dos genéricos:
El primer genérico será el tipo de la tabla que va a mapear la tabla respectiva, en este caso es la tabla
productos, por tanto el tipo es de tipo Producto, el segundo genérico será el tipo de la clave primaria de
la clase Porducto, en este caso es la clase contendora Integer, estos datos los colocamos usando el operador
diamante (<>).
Al realizar lo anterior podemos ahorrarnos mucho código debido a que CrudRepository contiene muchos métodos
que nos permiten realizar las diferentes operaciones CRUD.
*/
public interface ProductoCrudRepository extends CrudRepository <Producto, Integer> {
    /*
    En esta sección vamos a colocar los Query methods.

    Es importante recordar que debemos respetar el Camel case al colocar el nombre de las variables
    por las cuales vamos a realizar la consulta, por ejemplo, el atributo que representa el id de la categoria
    en la clase Producto se llama idCategoria, sin embargo, en el encabezado del siguiente método abstracto
    debemos colocar ...IdCategoria...
    */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional <List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
