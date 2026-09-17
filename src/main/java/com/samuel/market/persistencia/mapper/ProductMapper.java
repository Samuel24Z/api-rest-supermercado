package com.samuel.market.persistencia.mapper;

import com.samuel.market.domain.Product;
import com.samuel.market.persistencia.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            /*
            Debido a que category ya tiene su propio mapper, dentro de la anotación @Mapper vamos a incluir
            un nuevo parametro, será el parametro uses, en este vamos a colocar entre llaves el valor
            CategoryMapper.class, con esto, MapStruct sabrá que cuando se convierta al atributo categoria
            en category, tiene que usar CategoryMapper que ya está implementado.
            */
            @Mapping(source = "categoria", target = "category")
    })
    Product toProduct(Producto producto);
    List <Product> toProducts(List <Producto> productos);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
