package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Product with id " + product.getId() + " already exists");
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public void remove(int id) {
        Product removingProduct = findById(id);
        if (removingProduct == null) {
            throw new NotFoundException(id);
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product p : products) {
            if (p.getId() != id) {
                tmp[copyToIndex] = p;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

