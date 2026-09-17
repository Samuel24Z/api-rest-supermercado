package com.samuel.market.persistencia.mapper;

import com.samuel.market.domain.Purchase;
import com.samuel.market.persistencia.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            /*
            En este contexto de código es importante recalcar que la clase destino (Purchase en este caso) debe tener
            todos los mapeos, sino tiene algunos mapeos, estos debemos ignorarlos explícitamente por medio del parametro
            ignore de la anotación @Mapping que colocamos más abajo en el código.
            */
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            /*
            El siguiente atributo a mapear es el que usa PurchaseItemMapper para convertir los productos uno a uno,
            por eso es que usamos {PurchaseItemMapper.class} como valor del parametro uses de la anotación @Mapper
            de esta interface.
            */
            @Mapping(source = "productos", target = "items")
    })
    Purchase toPurchase(Compra compra);
    /*
    En la linea de código siguiente deseamos realizar el mapeo de una lista de compras a una lista de purchases, en este
    caso no necesitamos usar nuevamente la anotación @Mappings, este nuevo mapeo que deseamos definir va a adquirir
    automaticamente toda la configuración que establecimos en el mapeo singular (Purchase toPurchase(Compra compra))
    */
    List <Purchase> toPurchases(List <Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
