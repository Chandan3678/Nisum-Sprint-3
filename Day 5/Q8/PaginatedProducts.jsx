import React, { useRef } from 'react';
import usePaginatedProducts from './usePaginatedProducts';
import ProductCard from './ProductCard';

const categories = ['electronics', 'jewelery', "men's clothing", "women's clothing"];

export default function PaginatedProducts() {
  const [category, setCategory] = React.useState(categories[0]);
  const { products, loadMore, loading } = usePaginatedProducts(category);

  const observerRef = useRef();

  return (
    <div>
      <h2>🛍️ Product Browser</h2>

      <div style={{ marginBottom: 20 }}>
        {categories.map((cat) => (
          <button
            key={cat}
            onClick={() => setCategory(cat)}
            style={{
              marginRight: 10,
              padding: '8px 12px',
              background: cat === category ? '#007bff' : '#eee',
              color: cat === category ? 'white' : '#333',
              border: 'none',
              borderRadius: '6px',
              cursor: 'pointer',
            }}
          >
            {cat}
          </button>
        ))}
      </div>

      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
        {products.map((product, index) => {
          const lastProduct = index === products.length - 1;
          return (
            <div
              key={product.id}
              ref={lastProduct ? loadMore : null}
            >
              <ProductCard product={product} />
            </div>
          );
        })}
      </div>

      {loading && <p>Loading more products...</p>}
    </div>
  );
}
