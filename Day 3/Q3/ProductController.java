@PostMapping
public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result) {
    if (result.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    productList.add(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
}
