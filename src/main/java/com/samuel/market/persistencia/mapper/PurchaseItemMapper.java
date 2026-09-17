package com.samuel.market.persistencia.mapper;

import com.samuel.market.domain.PurchaseItem;
import com.samuel.market.persistencia.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/*
Usamos la anotación @Mapper con el parametro componentModel = "spring" con el fin de poder inyectar esta interface
desde otros lugares.
Debido a que en esta interface hacemos uso del mapeador
*/
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            // Usamos la notación id.nombreId para acceder a las diferentes llaves que componen una llave primaria compuesta
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            // Cuando source y target tienen el mismo nombre podemos evitar definir el mapeo, este mapeo se hará automáticamente
            // @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            /*
            En esta sección vamos a incluir todos los atributos de las clase ComprasProducto, incluso aquellos que no
            queremos mapear; a continuación establecemos el parametro ignore con un valor de true para cada atributo que
            no vamos a mapear, por tanto, estos atributos se ignoran para el mapeo.
            */
            @Mapping(target = "compra", ignore = true),
            /*
            El atributo producto que estamos ignorando es de tipo Producto y debido a que el recurso Producto ya tiene su
            propio mapper, dentro de la anotación @Mapper de esta interface vamos a incluir el parametro uses con un valor
            de {ProductMapper.class}, esto lo realizamos incluso cuando estamos ignorando un atributo para el mapeo.
            */
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
