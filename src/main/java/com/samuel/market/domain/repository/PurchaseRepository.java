package com.samuel.market.domain.repository;

import com.samuel.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List <Purchase> getAll();
    /*
     En algunas ocasiones es posible que estemos consultando un cliente que no tenga compras, entonces la lista de
     productos de ese cliente va a estar vacia, para manejar esto vamos a envolver la posible lista de productos
     (compra o purchase) de un cliente en un objeto Optional.
    */
    Optional <List <Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
