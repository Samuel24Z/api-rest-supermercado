package com.samuel.market.web.controller;

import com.samuel.market.domain.Product;
import com.samuel.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
La anotación @RestController le inidca a Spring que esta clase va a ser un controlador de una API REST.

La anotación @RequestMapping lleva como parametro el path que va aceptar las peticiones que le hagamos, en
este caso deseamos hacer las peticiones en /products
*/
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /*
    Publicamos o exponemos el método getAll() con la anotación @GetMapping, usamos este tipo de anotación porque este
    método se usa para obtener información. La anotación @GetMapping lleva como parametro el path sobre el que accedemos
    a este método.
    */
    @GetMapping("/all")
    /*
    Para modificar cómo se ve desde Swagger cada endpoint de los controladores podemos usar diversas anotaciones,
    algunas de estas son:
    @ApiOperation para describir una operación HTTP
    @ApiResponse para describir la posible respuesta de una operación
    @ApiResponse para envolver una lista de multiples objetos @ApiResponse
    @ApiParam para agregar metadatos adicionales a los parametros de una operación
    */
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List <Product>> getAll() {
        // HttpStatus.OK significa que la petición respondió de manera adecuada cuando fue llamada
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    /* En el paramatro de la anotación @GetMapping debemos colocar entre llaves el nombre de la variable que vamos a
    usar pasar el id que requiere el método getProduct(), el mismo nombre de variable que colocamos en @GetMapping
    debemos de colocarlo en el parametro de la anotación @PathVariable. Usamos @PathVariable para anotar al parametro
    productId del método getProduct()
    */
    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity <Product> getProduct(@ApiParam(value = "The id of the product")
            @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity <List <Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        Optional <List <Product>> products = productService.getByCategory(categoryId);

        if (products.isPresent() && !products.get().isEmpty()) {
            return new ResponseEntity<>(products.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    Usamos la anotación @RequestBody para indicar que el parametro product forma parte del cuerpo de la petición que
    realicemos a este método.
    */
    @PostMapping("/save")
    public ResponseEntity <Product> save(@RequestBody Product product) {
        // HttpStatus.CREATED nos indica que un producto ha sido creado exitosamente
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <Void> delete(@PathVariable("id") int productId) {
        if(productService.delete(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
