@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Integer, Book> bookMap = new HashMap<>();

    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Stream<Book> stream = bookMap.values().stream();

        if (author != null) stream = stream.filter(book -> book.getAuthor().equalsIgnoreCase(author));
        if (publishedYear != null) stream = stream.filter(book -> book.getPublishedYear() == publishedYear);

        return stream.skip(page * size).limit(size).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Book book = bookMap.get(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookMap.put(book.getId(), book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        if (!bookMap.containsKey(id)) return ResponseEntity.notFound().build();
        bookMap.put(id, book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookMap.remove(id);
        return ResponseEntity.noContent().build();
    }
}
