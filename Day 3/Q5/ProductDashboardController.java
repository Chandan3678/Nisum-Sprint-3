@RestController
@RequestMapping("/products")
public class ProductDashboardController {

    private List<Product> products = new ArrayList<>();

    @GetMapping
    public List<Product> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "price") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Stream<Product> stream = products.stream();

        if (category != null) stream = stream.filter(p -> p.getCategory().equalsIgnoreCase(category));
        if (minPrice != null) stream = stream.filter(p -> p.getPrice() >= minPrice);
        if (maxPrice != null) stream = stream.filter(p -> p.getPrice() <= maxPrice);

        Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
        if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();

        return stream.sorted(comparator)
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product updated) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(updated.getName());
                p.setPrice(updated.getPrice());
                p.setDescription(updated.getDescription());
                p.setStockQuantity(updated.getStockQuantity());
                p.setCategory(updated.getCategory());
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return ResponseEntity.noContent().build();
    }
}
