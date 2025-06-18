public class CustomValidator {
    public static void validateProduct(Product product) {
        if (product.getName().equalsIgnoreCase("invalid")) {
            throw new IllegalArgumentException("Invalid name is not allowed");
        }
    }
}
