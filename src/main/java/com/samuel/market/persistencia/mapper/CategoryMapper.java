package com.samuel.market.persistencia.mapper;

import com.samuel.market.domain.Category;
import com.samuel.market.persistencia.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/*
Usaremos la anotación @Mapper para indicarle a nuestro proyecto que esta interface es un mapeador, además,
MapStruct nos ofrece una integración con Spring, para esto vamos a incluir el parametro componentModel y le
vamos a establecer un valor de spring, es decir, estamos estableciendo un componente de tipo Spring.
*/
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // En esta sección vamos a diseñar nuestros conversores o mappers

    /*
    Para indicar como vamos a mapear o traducir los objetos se va a utilizar la anotación @Mappings, con
    esta anotación podemos realizar la configuración de los mapeos de varios atributos.
    */
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    /*
    La anotación @InheritInverseConfiguration le indica a MapStruct que la conversión que vamos a realizars
    (en este caso, Category hacia Categoria), es la inversa a la que estamos colocando justo arriba de esta
    sección, Categoria hacia Category, entonces no tenemos que definir las configuraciones usando
    @Mappings. Con esto, MapStruct sabe que debe realizar el mapeo inverso al que ya tenemos.

    Una aspecto que debemos tomar en cuenta es que la entidad Categoria tiene un atributo llamado productos
    que es de tipo List <Producto>, y en la clase Category no tenemos productos, entonces estos atributos no
    se van a mapear, por tanto usaremos la anotación @Mapping y daremos la indicación de que el atributo
    productos deberá ser ignorado, por ello, usaremos el parametro ignore con un valor de true.
    */
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
