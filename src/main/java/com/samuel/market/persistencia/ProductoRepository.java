package com.samuel.market.persistencia;

import com.samuel.market.domain.Product;
import com.samuel.market.domain.repository.ProductRepository;
import com.samuel.market.persistencia.crud.ProductoCrudRepository;
import com.samuel.market.persistencia.entity.Producto;
import com.samuel.market.persistencia.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
Esta clase está interactuando directamente con la base de datos, por tanto es buena practica usar la anotación
@Repository, con esto le estamos indicando a Spring que desde aquí realizamos las operaciones que deseamos
aplicar en nuestras tablas.
Aparte de @Repository, podemos usar la anotación @Component, esta anotación es una generalización de @Repository,
esta anotación indica que la clase anotada es un componente de Spring. En este caso es recomendable usar
@Repository porque estamos indicando el componente especifico.
*/
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List <Product> getAll() {
        /*
        La lista de productos que serán recuperados en este método, los obtenedremos de la base de datos
        gracias a los repositorios de Spring Data.
        */
        List <Producto> productos = (List<Producto>) productoCrudRepository.findAll();
         return mapper.toProducts(productos);
    }

    /*
    Con el siguiente método vamos a obtener una lista de productos que pertencen a una categoría, además
    estarán en orden alfabetico.
    */
    @Override
    public Optional <List <Product>> getByCategory(int catergoryId) {
        // Primero vamos a recuperar los productos de la base de datos
        List <Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(catergoryId);
        // Despues vamos a realizar la conversión con mapper
        return Optional.of(mapper.toProducts(productos));
    }

    /*
    Con el siguiente método vamos a obtener una lista de productos que tienen una cantidad de stock menor
    a un valor indicado y cuyo estado sea activo.
    Otra manera se verlo sería obtener los productos que se están agotando y que actualmente se están
    vendiendo.
    */
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional <List <Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        /*
        No hay ningun mapeador que convierta una lista de opcionales (Optional <List>), entonces usamos el
        método map de la clase genérica Optional para que realice la operación de la expresión lambda que
        le hemos proveido, en este caso, la operación es mapear a través del objeto mapper.
        De acuerdo a lo anterior, el método map devuelve un objeto de tipo Optional como resultado de la
        ejecución de lo que estemos haciendo al interior de la expresión lambda.
        */
        return productos.map(prods -> mapper.toProducts(prods));
    }

    // Método para consultar un producto en particular
    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto)); // save() es un método de los repositorios de Spring Data
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId); // deleteById() es un método de los repositorios de Spring Data
    }
}
