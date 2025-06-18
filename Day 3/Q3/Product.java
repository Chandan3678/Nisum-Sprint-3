public class Product {
    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Min(value = 1, message = "Price must be at least 1")
    private double price;

    // Getters and Setters
}
