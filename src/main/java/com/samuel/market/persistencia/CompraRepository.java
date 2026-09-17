package com.samuel.market.persistencia;

import com.samuel.market.domain.Purchase;
import com.samuel.market.domain.repository.PurchaseRepository;
import com.samuel.market.persistencia.crud.CompraCrudRepository;
import com.samuel.market.persistencia.entity.Compra;
import com.samuel.market.persistencia.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        /*
        El siguiente par de explicaciones son importantes para saber porque se realizan de cierta forma las cosas
        en este método.

        Ahora que tenemos un objeto de tipo Compra (por medio del mapeador), tenemos que garantizar que la información
        se va a guardar en casacada, para guardar en cascada tenemos que estar seguros de que una compra conoce sus
        productos y los productos conocen a que compra pertenecen, para esto usamos las siguientes instrucciones:
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        El método foreach realiza la acción dada para cada elemento del Iterable hasta que se hayan procesado todos los
        elementos o la acción genere una excepción. En este caso el Iterable es una lista de tipo List <ComprasProducto>,
        como cada elemento de esta lista es de tipo ComprasProducto entonces podemos llamar al método setCompra() que
        está integrado en esta clase (ComprasProducto) para establecer la compra a la que pertence un producto
        determinado.
        */
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
