package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.gccoffee.JdbcUtils.toLocalDateTime;
import static com.example.gccoffee.JdbcUtils.toUUID;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
        var productId = toUUID(resultSet.getBytes("product_id"));
        var productName = resultSet.getString("product_name");
        var category = Category.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var description = resultSet.getString("description");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Product(productId, productName, category, price, description, createdAt, updatedAt);
    };

    private static final String INSERT_SQL = "INSERT INTO products(product_id, product_name, category, price, description, created_at, updated_at) " +
            "VALUES (UUID_TO_BIN(:productId), :productName, :category, :price, :description, :createdAt, :updatedAt)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM products";
    private static final String UPDATE_SQL = "UPDATE products SET product_name = :productName, category = :category, price = :price, " +
            "description = :description, created_at = :createdAt, updated_at = :updatedAt " +
            "WHERE product_id = UUID_TO_BIN(:productId)";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM products WHERE product_id = UUID_TO_BIN(:productId)";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM products WHERE product_name = :productName";
    private static final String FIND_BY_CATEGORY_SQL = "SELECT * FROM products WHERE category = :category";
    private static final String DELETE_SQL = "DELETE FROM products";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(Product product) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("productId", product.getProductId().toString().getBytes());
        paramMap.put("productName", product.getProductName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());
        return paramMap;
    }


    @Override
    public Product insert(Product product) {
        var update = jdbcTemplate.update(INSERT_SQL, toParamMap(product));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        var update = jdbcTemplate.update(UPDATE_SQL, toParamMap(product));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return product;
    }


    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, productRowMapper);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_ID_SQL, Collections.singletonMap("productId", productId.toString().getBytes()), productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String productName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_NAME_SQL, Collections.singletonMap("productName", productName), productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query(FIND_BY_CATEGORY_SQL, Collections.singletonMap("category", category.toString()), productRowMapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_SQL, Collections.emptyMap());
    }

}
